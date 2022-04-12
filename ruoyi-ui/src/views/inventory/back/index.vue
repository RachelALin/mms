<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="材料退库名称" prop="backName">
        <el-input
          v-model="queryParams.backName"
          placeholder="请输入材料退库名称"
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
      <el-form-item label="材料退库状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择材料退库状态" clearable size="small">
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
          v-hasPermi="['inventory:back:add']"
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
          v-hasPermi="['inventory:back:edit']"
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
          v-hasPermi="['inventory:back:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inventory:back:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="backList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="材料退库ID" align="center" prop="backId" />
      <el-table-column label="材料退库名称" align="center" prop="backName" />
      <el-table-column label="项目ID" align="center" prop="proId" />
      <el-table-column label="仓库ID" align="center" prop="storeId" />
      <el-table-column label="材料退库状态" align="center" prop="status">
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
            v-hasPermi="['inventory:back:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inventory:back:remove']"
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

    <!-- 添加或修改材料退库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="材料退库名称" prop="backName">
          <el-input v-model="form.backName" placeholder="请输入材料退库名称" />
        </el-form-item>
        <el-form-item label="项目ID" prop="proId">
          <el-input v-model="form.proId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="仓库ID" prop="storeId">
          <el-input v-model="form.storeId" placeholder="请输入仓库ID" />
        </el-form-item>
        <el-form-item label="材料退库状态">
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
        <el-divider content-position="center">材料与材料退库关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMmsBackMaterial">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMmsBackMaterial">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="mmsBackMaterialList" :row-class-name="rowMmsBackMaterialIndex" @selection-change="handleMmsBackMaterialSelectionChange" ref="mmsBackMaterial">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="材料ID" prop="matId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.matId" placeholder="请输入材料ID" />
            </template>
          </el-table-column>
          <el-table-column label="出库数量" prop="backNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.backNum" placeholder="请输入出库数量" />
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
import { listBack, getBack, delBack, addBack, updateBack } from "@/api/inventory/back";

export default {
  name: "Back",
  dicts: ['mms_pur_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsBackMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料退库表格数据
      backList: [],
      // 材料与材料退库关联表格数据
      mmsBackMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        backName: null,
        proId: null,
        storeId: null,
        status: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        backName: [
          { required: true, message: "材料退库名称不能为空", trigger: "blur" }
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
    /** 查询材料退库列表 */
    getList() {
      this.loading = true;
      listBack(this.queryParams).then(response => {
        this.backList = response.rows;
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
        backId: null,
        backName: null,
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
      this.mmsBackMaterialList = [];
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
      this.ids = selection.map(item => item.backId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料退库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const backId = row.backId || this.ids
      getBack(backId).then(response => {
        this.form = response.data;
        this.mmsBackMaterialList = response.data.mmsBackMaterialList;
        this.open = true;
        this.title = "修改材料退库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsBackMaterialList = this.mmsBackMaterialList;
          if (this.form.backId != null) {
            updateBack(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBack(this.form).then(response => {
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
      const backIds = row.backId || this.ids;
      this.$modal.confirm('是否确认删除材料退库编号为"' + backIds + '"的数据项？').then(function() {
        return delBack(backIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 材料与材料退库关联序号 */
    rowMmsBackMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与材料退库关联添加按钮操作 */
    handleAddMmsBackMaterial() {
      let obj = {};
      obj.matId = "";
      obj.backNum = "";
      obj.remark = "";
      this.mmsBackMaterialList.push(obj);
    },
    /** 材料与材料退库关联删除按钮操作 */
    handleDeleteMmsBackMaterial() {
      if (this.checkedMmsBackMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与材料退库关联数据");
      } else {
        const mmsBackMaterialList = this.mmsBackMaterialList;
        const checkedMmsBackMaterial = this.checkedMmsBackMaterial;
        this.mmsBackMaterialList = mmsBackMaterialList.filter(function(item) {
          return checkedMmsBackMaterial.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsBackMaterialSelectionChange(selection) {
      this.checkedMmsBackMaterial = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inventory/back/export', {
        ...this.queryParams
      }, `back_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
