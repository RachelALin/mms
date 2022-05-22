<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="proId">
        <el-select
          v-model="queryParams.proId"
          placeholder="请选择合适项目"
          @keyup.enter.native="handleQuery"
          size="small"
        >
          <el-option
            v-for="dict in projectList"
            :key="dict.id"
            :label="dict.proName"
            :value="dict.proId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="仓管名称" prop="userId">
        <el-select
          v-model="queryParams.userId"
          placeholder="请选择仓管者"
          @keyup.enter.native="handleQuery"
          size="small"
        >
          <el-option
            v-for="dict in userList"
            :key="dict.id"
            :label="dict.nickName"
            :value="dict.userId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="仓库名称" prop="storeName">
        <el-input
          v-model="queryParams.storeName"
          placeholder="请输入仓库名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库电话" prop="storePhone">
        <el-input
          v-model="queryParams.storePhone"
          placeholder="请输入仓库电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库地址" prop="storeAddress">
        <el-input
          v-model="queryParams.storeAddress"
          placeholder="请输入仓库地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择仓库状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_store_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['storage:store:add']"
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
          v-hasPermi="['storage:store:edit']"
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
          v-hasPermi="['storage:store:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['storage:store:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="storeList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="80" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="仓库名称" align="center" prop="storeName" />
      <el-table-column label="项目名称" align="center" prop="project.proName" />
    
      <el-table-column label="仓管者" align="center" prop="user.nickName" />
      <el-table-column label="仓库电话" align="center" prop="storePhone" />
      <el-table-column label="仓库地址" align="center" prop="storeAddress" />
      <el-table-column label="仓库状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_store_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['storage:store:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['storage:store:remove']"
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

    <!-- 添加或修改仓库管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="仓库名称" prop="storeName">
          <el-input v-model="form.storeName" placeholder="请输入仓库名称" />
        </el-form-item>
        <el-form-item label="项目名称" prop="proId">
          <el-select
            v-model="form.proId"
            placeholder="请选择项目名称"
            @keyup.enter.native="handleQuery"
            size="small"
          >
            <el-option
              v-for="dict in projectList"
              :key="dict.id"
              :label="dict.proName"
              :value="dict.proId"
            ></el-option>
          </el-select>
        </el-form-item>
  
   
        <el-form-item label="仓库电话" prop="storePhone">
          <el-input v-model="form.storePhone" placeholder="请输入仓库电话" />
        </el-form-item>
        <el-form-item label="仓库地址" prop="storeAddress">
          <el-input v-model="form.storeAddress" placeholder="请输入仓库地址" />
        </el-form-item>
        <el-form-item label="仓库状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.mms_store_status"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">材料与仓库关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMmsStoreMaterial">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMmsStoreMaterial">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="mmsStoreMaterialList" :row-class-name="rowMmsStoreMaterialIndex" @selection-change="handleMmsStoreMaterialSelectionChange" ref="mmsStoreMaterial">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="材料名称" prop="matId" width="150">
             <template slot-scope="scope">
              <el-select v-model="scope.row.matId" placeholder="请选择合适的材料">
                <el-option
                  v-for="dict in materialList"
                  :key="dict.id"
                  :label="dict.matName"
                  :value="dict.matId"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="材料单价" prop="matUprice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.matUprice" placeholder="请输入材料单价" />
            </template>
          </el-table-column>
          <el-table-column label="材料数量" prop="matNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.matNum" placeholder="请输入材料数量" />
            </template>
          </el-table-column>
          <el-table-column label="材料总价" prop="matTprice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.matTprice" placeholder="请输入材料总价" />
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
import { listStore, getStore, delStore, addStore, updateStore } from "@/api/storage/store";
import { listProject } from "@/api/project/project";
import { listUser } from "@/api/system/user";
import { listMaterial } from "@/api/material/material";

export default {
  name: "Store",
  dicts: ['mms_store_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsStoreMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 仓库管理表格数据
      storeList: [],

      projectList: [],
      materialList:[],
      userList: [],
      // 材料与仓库关联表格数据
      mmsStoreMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        proId: null,
        storeName: null,
        userId: null,
        storePhone: null,
        storeAddress: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        proId: [
          { required: true, message: "项目ID不能为空", trigger: "blur" }
        ],
        storeName: [
          { required: true, message: "仓库名称不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询仓库管理列表 */
    getList() {
      this.loading = true;
      listStore(this.queryParams).then(response => {
        this.storeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listProject().then(res => {
        this.projectList = res.rows;
      });
      listUser().then(res => {
        this.userList = res.rows;
      });
      listMaterial().then(res => {
        this.materialList = res.rows;
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
        storeId: null,
        proId: null,
        storeName: null,
        userId: null,
        storePhone: null,
        storeAddress: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.mmsStoreMaterialList = [];
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
      this.ids = selection.map(item => item.storeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加仓库管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const storeId = row.storeId || this.ids
      getStore(storeId).then(response => {
        this.form = response.data;
        this.mmsStoreMaterialList = response.data.mmsStoreMaterialList;
        this.open = true;
        this.title = "修改仓库管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsStoreMaterialList = this.mmsStoreMaterialList;
          if (this.form.storeId != null) {
            updateStore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStore(this.form).then(response => {
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
      const storeIds = row.storeId || this.ids;
      this.$modal.confirm('是否确认删除仓库管理编号为"' + storeIds + '"的数据项？').then(function() {
        return delStore(storeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 材料与仓库关联序号 */
    rowMmsStoreMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与仓库关联添加按钮操作 */
    handleAddMmsStoreMaterial() {
      let obj = {};
      obj.matId = "";
      obj.matUprice = "";
      obj.matNum = "";
      obj.matTprice = "";
      obj.remark = "";
      this.mmsStoreMaterialList.push(obj);
    },
    /** 材料与仓库关联删除按钮操作 */
    handleDeleteMmsStoreMaterial() {
      if (this.checkedMmsStoreMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与仓库关联数据");
      } else {
        const mmsStoreMaterialList = this.mmsStoreMaterialList;
        const checkedMmsStoreMaterial = this.checkedMmsStoreMaterial;
        this.mmsStoreMaterialList = mmsStoreMaterialList.filter(function(item) {
          return checkedMmsStoreMaterial.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsStoreMaterialSelectionChange(selection) {
      this.checkedMmsStoreMaterial = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('storage/store/export', {
        ...this.queryParams
      }, `store_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
