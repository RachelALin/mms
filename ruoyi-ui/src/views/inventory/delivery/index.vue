<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="材料出库名称" prop="outName">
        <el-input
          v-model="queryParams.outName"
          placeholder="请输入材料出库名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料入库名称" prop="inId">
      <el-select v-model="queryParams.inId" placeholder="请选择材料入库名称" clearable size="small">
          <el-option
            v-for="dict in receivedList"
            :key="dict.id"
            :label="dict.inName"
            :value="dict.inId"
          />
        </el-select>
      </el-form-item>
      
      <!-- <el-form-item label="材料合同" prop="conId">
        <el-select v-model="queryParams.conId" placeholder="请选择材料合同" clearable size="small">
          <el-option
            v-for="dict in contractList"
            :key="dict.id"
            :label="dict.conName"
            :value="dict.conId"
          />
        </el-select>
      </el-form-item> -->
    <el-form-item label="项目名称" prop="conId">
        <el-select v-model="queryParams.conId" placeholder="请选择项目" clearable size="small">
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
      <el-form-item label="材料出库状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择材料出库状态" clearable size="small">
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
          v-hasPermi="['inventory:delivery:add']"
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
          v-hasPermi="['inventory:delivery:edit']"
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
          v-hasPermi="['inventory:delivery:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inventory:delivery:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deliveryList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="材料出库ID" align="center" prop="outId" /> -->
      <el-table-column label="材料出库名称" align="center" prop="outName" />
      <el-table-column label="材料入库名称" align="center" prop="received.inName" />
      <el-table-column label="项目名称" align="center" prop="project.proName" />
      <el-table-column label="仓库名称" align="center" prop="store.storeName" />
      <el-table-column label="材料出库状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_pur_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="user.nickName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inventory:delivery:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inventory:delivery:remove']"
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

    <!-- 添加或修改材料出库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="材料出库名称" prop="outName">
          <el-input v-model="form.outName" placeholder="请输入材料出库名称" />
        </el-form-item>
    
        <el-form-item label="材料入库名称" prop="inId">
          <el-select
            v-model="form.inId"
            placeholder="请选择材料入库名称"
            @change="handleShowPro(form.inId)"
          >
            <el-option
              v-for="dict in receivedList"
              :key="dict.id"
              :label="dict.inName"
              :value="dict.inId"
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
        <el-form-item label="仓库名称" prop="storeId">
          <el-select v-model="form.storeId" placeholder="请选择合适仓库">
            <el-option
              v-for="dict in storeList"
              :key="dict.id"
              :label="dict.storeName"
              :value="dict.storeId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="材料出库状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.mms_pur_status"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label="审核人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入审核人ID" />
        </el-form-item> -->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">材料与材料出库关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMmsDeliveryMaterial">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMmsDeliveryMaterial">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="mmsDeliveryMaterialList" :row-class-name="rowMmsDeliveryMaterialIndex" @selection-change="handleMmsDeliveryMaterialSelectionChange" ref="mmsDeliveryMaterial">
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
          <el-table-column label="出库数量" prop="outNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.outNum" placeholder="请输入出库数量" />
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
import { listDelivery, getDelivery, delDelivery, addDelivery, updateDelivery } from "@/api/inventory/delivery";
import { listReceived, getReceived } from "@/api/inventory/received";
import { listProject, getProject } from "@/api/project/project";
import { listMaterial } from "@/api/material/material";
import { listUser } from "@/api/system/user";
import { listSupplier } from "@/api/supplier/supplier";
import { listStore, getStoreByProId, updateStore, updateStoreReceived } from "@/api/storage/store";
export default {
  name: "Delivery",
  dicts: ['mms_pur_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsDeliveryMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料出库表格数据
      deliveryList: [],
      receivedList: [],
      projectList: [],
      mmsReceivedMaterialList: [],
      materialList: [],
      userList: [],
      storeList: [],
      supplierList: [],
      // 材料与材料出库关联表格数据
      mmsDeliveryMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        outName: null,
        inId: null,
        proId: null,
        storeId: null,
        status: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        outName: [
          { required: true, message: "材料出库名称不能为空", trigger: "blur" }
        ],
        inId: [
          { required: true, message: "材料入库ID不能为空", trigger: "blur" }
        ],
        proId: [
          { required: true, message: "项目ID不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "仓库ID不能为空", trigger: "blur" }
        ],
        // userId: [
        //   { required: true, message: "审核人ID不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料出库列表 */
    getList() {
      this.loading = true;
      listDelivery(this.queryParams).then(response => {
        this.deliveryList = response.rows;
        console.log(response.rows);
        
        this.total = response.total;
        this.loading = false;
      });
      listReceived().then(res => {
        this.receivedList = res.rows;
      });
      listProject().then(res => {
        this.projectList = res.rows;
      });
      listMaterial().then(res => {
        this.materialList = res.rows;
        console.log(res.rows);
        
      });
      listUser().then(res => {
        this.userList = res.rows;
      });
      listSupplier().then(res => {
        this.supplierList = res.rows;
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
        outId: null,
        outName: null,
        inId: null,
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
      this.mmsDeliveryMaterialList = [];
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

  handleShowPro(scope) {
      var pid = "";

      getReceived(scope)
        .then(res => {
          console.log("hhh");
          console.log(res.data);
          this.form.proId = res.data.proId;
          this.form.storeId = res.data.storeId;
          this.mmsDeliveryMaterialList = res.data.mmsReceivedMaterialList;
          this.pid = res.data.proId;
          console.log(res.data.proId);
          getStoreByProId(res.data.proId).then(res => {
            this.storeList = res.data;
            console.log(res.data);
          });
          console.log(res.data.mmsReceivedMaterialList);
        })
        .catch(err => {
          this.$message.error(err.message);
          console.log(err);
        });

      // console.log(pid)
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.outId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料出库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const outId = row.outId || this.ids
      getDelivery(outId).then(response => {
        this.form = response.data;
        this.mmsDeliveryMaterialList = response.data.mmsDeliveryMaterialList;
        this.open = true;
        this.title = "修改材料出库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsDeliveryMaterialList = this.mmsDeliveryMaterialList;
          if (this.form.outId != null) {
            updateDelivery(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDelivery(this.form).then(response => {
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
      const outIds = row.outId || this.ids;
      this.$modal.confirm('是否确认删除材料出库编号为"' + outIds + '"的数据项？').then(function() {
        return delDelivery(outIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 材料与材料出库关联序号 */
    rowMmsDeliveryMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与材料出库关联添加按钮操作 */
    handleAddMmsDeliveryMaterial() {
      let obj = {};
      obj.matId = "";
      obj.outNum = "";
      obj.remark = "";
      this.mmsDeliveryMaterialList.push(obj);
    },
    /** 材料与材料出库关联删除按钮操作 */
    handleDeleteMmsDeliveryMaterial() {
      if (this.checkedMmsDeliveryMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与材料出库关联数据");
      } else {
        const mmsDeliveryMaterialList = this.mmsDeliveryMaterialList;
        const checkedMmsDeliveryMaterial = this.checkedMmsDeliveryMaterial;
        this.mmsDeliveryMaterialList = mmsDeliveryMaterialList.filter(function(item) {
          return checkedMmsDeliveryMaterial.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsDeliveryMaterialSelectionChange(selection) {
      this.checkedMmsDeliveryMaterial = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inventory/delivery/export', {
        ...this.queryParams
      }, `delivery_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
