<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="材料盘点名称" prop="cheName">
        <el-input
          v-model="queryParams.cheName"
          placeholder="请输入材料盘点名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目ID" prop="proId">
        <el-input
          v-model="queryParams.proId"
          placeholder="请输入项目ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库ID" prop="storeId">
        <el-input
          v-model="queryParams.storeId"
          placeholder="请输入仓库ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料盘点状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择材料盘点状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_pur_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入审核人ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['inventory:check:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['inventory:check:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['inventory:check:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inventory:check:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="checkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="材料盘点ID" align="center" prop="cheId" />
      <el-table-column label="材料盘点名称" align="center" prop="cheName" />
      <el-table-column label="项目ID" align="center" prop="proId" />
      <el-table-column label="仓库ID" align="center" prop="storeId" />
      <el-table-column label="材料盘点状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_pur_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="审核人ID" align="center" prop="userId" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inventory:check:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inventory:check:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改材料盘点对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="材料盘点名称" prop="cheName">
          <el-input v-model="form.cheName" placeholder="请输入材料盘点名称" />
        </el-form-item>
        <el-form-item label="项目ID" prop="proId">
          <el-input v-model="form.proId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="仓库ID" prop="storeId">
          <el-input v-model="form.storeId" placeholder="请输入仓库ID" />
        </el-form-item>
        <el-form-item label="材料盘点状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.mms_pur_status"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入审核人ID" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">材料与材料盘点关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMmsCheckMaterial">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMmsCheckMaterial">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="mmsCheckMaterialList" :row-class-name="rowMmsCheckMaterialIndex" @selection-change="handleMmsCheckMaterialSelectionChange" ref="mmsCheckMaterial">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="材料ID" prop="matId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.matId" placeholder="请输入材料ID" />
            </template>
          </el-table-column>
          <el-table-column label="账存数量" prop="num" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.num" placeholder="请输入账存数量" />
            </template>
          </el-table-column>
          <el-table-column label="盘点数量" prop="cheNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cheNum" placeholder="请输入盘点数量" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCheck, getCheck, delCheck, addCheck, updateCheck } from "@/api/inventory/check";

export default {
  name: "Check",
  dicts: ['mms_pur_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsCheckMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料盘点表格数据
      checkList: [],
      // 材料与材料盘点关联表格数据
      mmsCheckMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cheName: null,
        proId: null,
        storeId: null,
        status: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cheName: [
          { required: true, message: "材料盘点名称不能为空", trigger: "blur" }
        ],
        proId: [
          { required: true, message: "项目ID不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "仓库ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "审核人ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料盘点列表 */
    getList() {
      this.loading = true;
      listCheck(this.queryParams).then(response => {
        this.checkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        cheId: null,
        cheName: null,
        proId: null,
        storeId: null,
        status: "0",
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.mmsCheckMaterialList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.cheId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料盘点";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cheId = row.cheId || this.ids
      getCheck(cheId).then(response => {
        this.form = response.data;
        this.mmsCheckMaterialList = response.data.mmsCheckMaterialList;
        this.open = true;
        this.title = "修改材料盘点";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsCheckMaterialList = this.mmsCheckMaterialList;
          if (this.form.cheId != null) {
            updateCheck(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCheck(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const cheIds = row.cheId || this.ids;
      this.$modal.confirm('是否确认删除材料盘点编号为"' + cheIds + '"的数据项？').then(function() {
        return delCheck(cheIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 材料与材料盘点关联序号 */
    rowMmsCheckMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与材料盘点关联添加按钮操作 */
    handleAddMmsCheckMaterial() {
      let obj = {};
      obj.matId = "";
      obj.num = "";
      obj.cheNum = "";
      obj.remark = "";
      this.mmsCheckMaterialList.push(obj);
    },
    /** 材料与材料盘点关联删除按钮操作 */
    handleDeleteMmsCheckMaterial() {
      if (this.checkedMmsCheckMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与材料盘点关联数据");
      } else {
        const mmsCheckMaterialList = this.mmsCheckMaterialList;
        const checkedMmsCheckMaterial = this.checkedMmsCheckMaterial;
        this.mmsCheckMaterialList = mmsCheckMaterialList.filter(function(item) {
          return checkedMmsCheckMaterial.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsCheckMaterialSelectionChange(selection) {
      this.checkedMmsCheckMaterial = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inventory/check/export', {
        ...this.queryParams
      }, `check_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
