<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="材料调拨名称" prop="chaName">
        <el-input
          v-model="queryParams.chaName"
          placeholder="请输入材料调拨名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="proId">
        <el-select v-model="queryParams.proId" placeholder="请选择项目" clearable size="small">
          <el-option
            v-for="dict in projectList"
            :key="dict.id"
            :label="dict.proName"
            :value="dict.proId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="仓库名称" prop="storeId">
        <el-select v-model="queryParams.storeId" placeholder="请选择仓库" clearable size="small">
          <el-option
            v-for="dict in storeList"
            :key="dict.id"
            :label="dict.storeName"
            :value="dict.storeId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="材料调拨状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择材料调拨状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_pur_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人" prop="userId">
        <el-select v-model="queryParams.userId" placeholder="请选择审核人" clearable size="small">
          <el-option
            v-for="dict in userList"
            :key="dict.id"
            :label="dict.nickName"
            :value="dict.userId"
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
          v-hasPermi="['inventory:change:add']"
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
          v-hasPermi="['inventory:change:edit']"
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
          v-hasPermi="['inventory:change:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inventory:change:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="changeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="材料调拨名称" align="center" prop="chaName" />
      <el-table-column label="项目名称" align="center" prop="project.proName" />
      <el-table-column label="仓库名称" align="center" prop="store.storeName" />
      <el-table-column label="材料调拨状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_pur_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inventory:change:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inventory:change:remove']"
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

    <!-- 添加或修改材料调拨对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="材料调拨名称" prop="chaName">
          <el-input v-model="form.chaName" placeholder="请输入材料调拨名称" />
        </el-form-item>

        <el-form-item label="仓库名称" prop="storeId">
          <el-select
            v-model="form.storeId"
            placeholder="请选择合适仓库"
            @change="handleShowPro(form.storeId)"
          >
            <el-option
              v-for="dict in storeList"
              :key="dict.id"
              :label="dict.storeName"
              :value="dict.storeId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目名称" prop="proId">
          <el-select v-model="form.proId" placeholder="请选择合适项目">
            <el-option
              v-for="dict in projectList"
              :key="dict.id"
              :label="dict.proName"
              :value="dict.proId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="材料调拨状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.mms_pur_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listChange,
  getChange,
  delChange,
  addChange,
  updateChange
} from "@/api/inventory/change";
import { listProject, getProject } from "@/api/project/project";
import { listMaterial } from "@/api/material/material";
import { listUser } from "@/api/system/user";
import {
  listStore,
  getStoreByProId,
  getStore,
  updateStore,
  updateStoreReceived
} from "@/api/storage/store";
export default {
  name: "Change",
  dicts: ["mms_pur_status"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsChangeMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料调拨表格数据
      changeList: [],

      projectList: [],

      materialList: [],
      userList: [],
      storeList: [],
      mmsStoreMaterialList: [],
      // 材料与材料调拨关联表格数据
      mmsChangeMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chaName: null,
        proId: null,
        storeId: null,
        status: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        chaName: [
          { required: true, message: "材料调拨名称不能为空", trigger: "blur" }
        ],
        proId: [{ required: true, message: "项目ID不能为空", trigger: "blur" }],
        storeId: [
          { required: true, message: "仓库ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "审核人ID不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料调拨列表 */
    getList() {
      this.loading = true;
      listChange(this.queryParams).then(response => {
        this.changeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listProject().then(res => {
        this.projectList = res.rows;
      });
      listMaterial().then(res => {
        this.materialList = res.rows;
      });
      listUser().then(res => {
        this.userList = res.rows;
      });
      listStore().then(res => {
        this.storeList = res.rows;
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
        chaId: null,
        chaName: null,
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
      this.mmsChangeMaterialList = [];
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
      this.ids = selection.map(item => item.chaId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    handleShowPro(scope) {
      getStore(scope)
        .then(res => {
         
          this.mmsChangeMaterialList = res.data.mmsStoreMaterialList;
          this.form.proId = res.data.proId;
          this.form.storeId = res.data.storeId;
          console.log(res.data.proId);
        })
        .catch(err => {
          this.$message.error(err.message);
          console.log(err);
        });

      // console.log(pid)
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料调拨";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const chaId = row.chaId || this.ids;
      getChange(chaId).then(response => {
        this.form = response.data;
        this.mmsChangeMaterialList = response.data.mmsChangeMaterialList;
        this.open = true;
        this.title = "修改材料调拨";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsChangeMaterialList = this.mmsChangeMaterialList;
          if (this.form.chaId != null) {
            updateChange(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChange(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
        if (this.form.status == "2") {
            this.storeForm = {
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
            this.storeForm.storeId = this.form.storeId;
            this.storeForm.proId = this.form.proId;
              getStore(this.form.storeId).then(res => {
              this.mmsStoreMaterialList = res.data.mmsStoreMaterialList;
              console.log(res.data)
              this.storeForm = res.data; 
              this.storeForm.storeAddress = this.form.remark;
              this.storeForm.storeName = this.form.chaName;
              console.log(this.storeForm)
              // this.storeForm = res.data;
              // var listre = res.data.mmsStoreMaterialList;
              // var listde = this.form.mmsCheckMaterialList;

              // var i = 0;
              // var j = 0;
              // var k = 0;
              // for (var o in listre) {
              //   i = listre[o].matNum;
              //   j = listde[o].cheNum;

              //   i = j;
              //   k = i;

              //   listre[o].matNum = k;
              //   listre[o].matTprice = listre[o].matUprice * k;
              // }

              // this.storeForm.mmsStoreMaterialList = listre;
              // this.storeForm.status = this.form.status;

              updateStoreReceived(this.storeForm).then(res => {
                this.$modal.msgSuccess("已更新仓库内容");
              });
            });
          }
        
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const chaIds = row.chaId || this.ids;
      this.$modal
        .confirm('是否确认删除材料调拨编号为"' + chaIds + '"的数据项？')
        .then(function() {
          return delChange(chaIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 材料与材料调拨关联序号 */
    rowMmsChangeMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与材料调拨关联添加按钮操作 */
    handleAddMmsChangeMaterial() {
      let obj = {};
      obj.matId = "";
      obj.matUprice = "";
      obj.matNum = "";
      obj.matTprice = "";
      obj.remark = "";
      this.mmsChangeMaterialList.push(obj);
    },
    /** 材料与材料调拨关联删除按钮操作 */
    handleDeleteMmsChangeMaterial() {
      if (this.checkedMmsChangeMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与材料调拨关联数据");
      } else {
        const mmsChangeMaterialList = this.mmsChangeMaterialList;
        const checkedMmsChangeMaterial = this.checkedMmsChangeMaterial;
        this.mmsChangeMaterialList = mmsChangeMaterialList.filter(function(
          item
        ) {
          return checkedMmsChangeMaterial.indexOf(item.index) == -1;
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsChangeMaterialSelectionChange(selection) {
      this.checkedMmsChangeMaterial = selection.map(item => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "inventory/change/export",
        {
          ...this.queryParams
        },
        `change_${new Date().getTime()}.xlsx`
      );
    }
  }
};
</script>
