<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="合同名称" prop="conName">
        <el-input
          v-model="queryParams.conName"
          placeholder="请输入合同名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付方式" prop="paymentWay">
        <el-select v-model="queryParams.paymentWay" placeholder="请选择支付方式" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_payment"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="交货方式" prop="deliveryWay">
        <el-select v-model="queryParams.deliveryWay" placeholder="请选择交货方式" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_delivery"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="结算方式" prop="clearingForm">
        <el-select v-model="queryParams.clearingForm" placeholder="请选择结算方式" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_clear"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开工日期" prop="startDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开工日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="竣工日期" prop="endDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择竣工日期">
        </el-date-picker>
      </el-form-item>
          <el-form-item label="原需用计划名称" prop="needId">
        <el-select
          v-model="queryParams.needId"
          placeholder="请选择原需用计划"
          @keyup.enter.native="handleQuery"
          size="small"
        >
          <el-option
            v-for="dict in needList"
            :key="dict.id"
            :label="dict.needName"
            :value="dict.needId"
          ></el-option>
        </el-select>
      </el-form-item>
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
          v-hasPermi="['purchase:contract:add']"
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
          v-hasPermi="['purchase:contract:edit']"
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
          v-hasPermi="['purchase:contract:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:contract:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contractList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="80" align="center" />
      <el-table-column fixed label="序号" type="index" width="50" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
          </el-table-column>
      <el-table-column fixed label="合同名称" align="center" prop="conName" />
      <el-table-column fixed label="项目名称" align="center" prop="project.proName" />
  
      <el-table-column fixed label="供应商名称" align="center" prop="supplier.supplierName" />
      <el-table-column label="需用计划名称" align="center" prop="need.needName" />
      <el-table-column label="录入人" align="center" prop="user.nickName" />
      <el-table-column label="支付方式" align="center" prop="paymentWay">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_payment" :value="scope.row.paymentWay"/>
        </template>
      </el-table-column>
      <el-table-column label="交货方式" align="center" prop="deliveryWay">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_delivery" :value="scope.row.deliveryWay"/>
        </template>
      </el-table-column>
      <el-table-column label="结算方式" align="center" prop="clearingForm">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_clear" :value="scope.row.clearingForm"/>
        </template>
      </el-table-column>
      <el-table-column label="开工日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="竣工日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
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
            v-hasPermi="['purchase:contract:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:contract:remove']"
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

    <!-- 添加或修改材料合同对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="合同名称" prop="conName">
          <el-input v-model="form.conName" placeholder="请输入合同名称" />
        </el-form-item>
         <el-form-item label="原需用计划名称" prop="needId">
          <el-select
            v-model="form.needId"
            placeholder="请选择需用计划名称"
            @change="handleShowPro(form.needId)"
          >
            <el-option
              v-for="dict in needList"
              :key="dict.id"
              :label="dict.needName"
              :value="dict.needId"
         
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplierId">
          <el-select
            v-model="form.supplierId"
            placeholder="请选择合适供应商"
          >
            <el-option
              v-for="dict in supplierList"
              :key="dict.id"
              :label="dict.supplierName"
              :value="dict.supplierId"
         
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付方式" prop="paymentWay">
          <el-select v-model="form.paymentWay" placeholder="请选择支付方式">
            <el-option
              v-for="dict in dict.type.mms_payment"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交货方式" prop="deliveryWay">
          <el-select v-model="form.deliveryWay" placeholder="请选择交货方式">
            <el-option
              v-for="dict in dict.type.mms_delivery"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="结算方式" prop="clearingForm">
          <el-select v-model="form.clearingForm" placeholder="请选择结算方式">
            <el-option
              v-for="dict in dict.type.mms_clear"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开工日期" prop="startDate">
          <el-date-picker clearable size="small"
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开工日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="竣工日期" prop="endDate">
          <el-date-picker clearable size="small"
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择竣工日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">材料与合同关联信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMmsContractMaterial">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMmsContractMaterial">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="mmsContractMaterialList" :row-class-name="rowMmsContractMaterialIndex" @selection-change="handleMmsContractMaterialSelectionChange" ref="mmsContractMaterial">
          <el-table-column type="selection" width="50" align="center" />
         
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
          <el-table-column label="材料单价/元" prop="conUprice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.conUprice" placeholder="请输入材料单价" />
            </template>
          </el-table-column>
          <el-table-column label="材料数量" prop="conNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.conNum" placeholder="请输入材料数量" />
            </template>
          </el-table-column>
          <el-table-column label="税率" prop="conTax" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.conTax" placeholder="请输入税率" />
            </template>
          </el-table-column>
          <el-table-column label="含税金额" prop="conTprice" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.conTprice" placeholder="请输入含税金额" />
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
import { listContract, getContract, delContract, addContract, updateContract } from "@/api/purchase/contract";
import { listNeed, getNeed } from "@/api/purchase/need";
import { listProject } from "@/api/project/project";
import { listMaterial } from "@/api/material/material";
import { listUser } from "@/api/system/user";
import { listSupplier } from "@/api/supplier/supplier";
export default {
  name: "Contract",
  dicts: ['mms_payment', 'mms_delivery', 'mms_clear'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsContractMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料合同表格数据
      contractList: [],

      projectList: [],
      mmsNeedMaterialList: [],
      materialList: [],
      userList: [],
      needList:[],
      supplierList: [],
      // 材料与合同关联表格数据
      mmsContractMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        conName: null,
        proId: null,
        needId: null,
        supplierId: null,
        userId: null,
        paymentWay: null,
        deliveryWay: null,
        clearingForm: null,
        startDate: null,
        endDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        conName: [
          { required: true, message: "合同名称不能为空", trigger: "blur" }
        ],
        proId: [
          { required: true, message: "项目ID不能为空", trigger: "change" }
        ],
        needId: [
          { required: true, message: "需用计划ID不能为空", trigger: "change" }
        ],
        supplierId: [
          { required: true, message: "供应商ID不能为空", trigger: "change" }
        ],
        userId: [
          { required: true, message: "录入人ID不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料合同列表 */
    getList() {
      this.loading = true;
      listContract(this.queryParams).then(response => {
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
        // console.log(response.rows)
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
      listNeed().then(res => {
        this.needList = res.rows;
        // console.log(res.rows)
   
      });
      listSupplier().then(res =>{
        this.supplierList = res.rows;
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
        conId: null,
        conName: null,
        proId: null,
        needId: null,
        supplierId: null,
        userId: null,
        paymentWay: null,
        deliveryWay: null,
        clearingForm: null,
        startDate: null,
        endDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.mmsContractMaterialList = [];
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
      this.ids = selection.map(item => item.conId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料合同";
    },
      //根据需用计划ID获取项目ID
    handleShowPro(scope){
      getNeed(scope).then(res => {
            console.log(scope);
            console.log(res.data);
            this.form.proId = res.data.proId;
            var list = res.data.mmsNeedMaterialList;
            var obj = JSON.parse(JSON.stringify(list).replace(/matUprice/g,"conUprice"));
            this.mmsContractMaterialList = obj;
        
      });
  
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const conId = row.conId || this.ids
      getContract(conId).then(response => {
        this.form = response.data;
        this.mmsContractMaterialList = response.data.mmsContractMaterialList;
        this.open = true;
        this.title = "修改材料合同";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsContractMaterialList = this.mmsContractMaterialList;
          if (this.form.conId != null) {
            updateContract(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContract(this.form).then(response => {
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
      const conIds = row.conId || this.ids;
      this.$modal.confirm('是否确认删除材料合同编号为"' + conIds + '"的数据项？').then(function() {
        return delContract(conIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 材料与合同关联序号 */
    rowMmsContractMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与合同关联添加按钮操作 */
    handleAddMmsContractMaterial() {
      let obj = {};
      obj.matId = "";
      obj.conUprice = "";
      obj.conNum = "";
      obj.conTax ="";
      obj.conTprice = "";
      obj.remark = "";
      this.mmsContractMaterialList.push(obj);
    },
    /** 材料与合同关联删除按钮操作 */
    handleDeleteMmsContractMaterial() {
      if (this.checkedMmsContractMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与合同关联数据");
      } else {
        const mmsContractMaterialList = this.mmsContractMaterialList;
        const checkedMmsContractMaterial = this.checkedMmsContractMaterial;
        this.mmsContractMaterialList = mmsContractMaterialList.filter(function(item) {
          return checkedMmsContractMaterial.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsContractMaterialSelectionChange(selection) {
      this.checkedMmsContractMaterial = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/contract/export', {
        ...this.queryParams
      }, `contract_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
