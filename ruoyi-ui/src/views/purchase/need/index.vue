<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <el-form-item label="需用计划名称" prop="needName">
        <el-input
          v-model="queryParams.needName"
          placeholder="请输入需用计划名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="原计划名称" prop="planId">
        <el-select
          v-model="queryParams.planId"
          placeholder="请选择原计划"
          @keyup.enter.native="handleQuery"
          size="small"
        >
          <el-option
            v-for="dict in planList"
            :key="dict.id"
            :label="dict.planName"
            :value="dict.planId"
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
      <el-form-item label="材料总计划状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择材料总计划状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_pur_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人名称" prop="userId">
        <el-select
          v-model="queryParams.userId"
          placeholder="请选择审核人"
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
          v-hasPermi="['purchase:need:add']"
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
          v-hasPermi="['purchase:need:edit']"
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
          v-hasPermi="['purchase:need:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:need:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="needList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="需用计划序号" align="center" prop="needId" /> -->
      <el-table-column label="需用计划名称" align="center" prop="needName" />
      <el-table-column label="原计划名称" align="center" prop="plan.planName" />
      <el-table-column label="项目名称" align="center" prop="project.proName" />
      <el-table-column label="材料总计划状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_pur_status" :value="scope.row.status" />
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
            v-hasPermi="['purchase:need:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:need:remove']"
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

    <!-- 添加或修改材料需用计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="需用计划名" prop="needName">
          <el-input v-model="form.needName" placeholder="请输入需用计划名称" />
        </el-form-item>

        <el-form-item label="原计划名称" prop="planId">
          <el-select
            v-model="form.planId"
            placeholder="请选择原计划名称"
            @change="handleShowPro(form.planId)"
          >
            <el-option
              v-for="dict in planList"
              :key="dict.id"
              :label="dict.planName"
              :value="dict.planId"
         
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="项目名称" prop="proId">
          <el-input v-model="form.proId" placeholder="请输入需用计划名称"  />
        </el-form-item> -->
        <!-- <el-form-item label="项目名称" prop="proId">
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
        </el-form-item> -->
        <el-form-item label="需用计划状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择需用计划状态">
            <el-option
              v-for="dict in dict.type.mms_pur_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">材料与材料需用计划信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAddMmsNeedMaterial"
            >添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="handleDeleteMmsNeedMaterial"
            >删除</el-button>
          </el-col>
          <!-- <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-download"
              size="mini"
              @click="handleExportMmsPlanMaterial"
            >导入</el-button>
          </el-col> -->
        </el-row>
        <el-table
          :data="mmsNeedMaterialList"
          :row-class-name="rowMmsNeedMaterialIndex"
          @selection-change="handleMmsNeedMaterialSelectionChange"
          ref="mmsNeedMaterial"
          stripe border
        >
          <el-table-column type="selection" width="80" align="center" />
           <el-table-column label="序号" align="center" prop="index" width="50" />
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
import {
  listNeed,
  getNeed,
  delNeed,
  addNeed,
  updateNeed
} from "@/api/purchase/need";
import { listProject } from "@/api/project/project";
import { listMaterial } from "@/api/material/material";
import { listUser } from "@/api/system/user";
import { listPlan, getPlan } from "@/api/purchase/plan";
export default {
  name: "Need",
  dicts: ["mms_pur_status"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsNeedMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料需用计划表格数据
      needList: [],
      planList: [],
      // 材料与材料总计划表格数据
      projectList: [],
      mmsPlanMaterialList: [],
      materialList: [],
      userList: [],
      // 材料与材料需用计划表格数据
      mmsNeedMaterialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        needName: null,
        planId: null,
        proId: null,
        status: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        needName: [
          { required: true, message: "需用计划名称不能为空", trigger: "blur" }
        ],
        planId: [
          { required: true, message: "原计划ID不能为空", trigger: "blur" }
        ],
        proId: [{ required: true, message: "项目ID不能为空", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料需用计划列表 */
    getList() {
      this.loading = true;
      listNeed(this.queryParams).then(response => {
        this.needList = response.rows;
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
      listPlan().then(res => {
        this.planList = res.rows;
        console.log(res.rows)
        // this.needList.proId = this.planList.proId;
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
        needId: null,
        needName: null,
        planId: null,
        proId: null,
        status: null,
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.mmsNeedMaterialList = [];
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
      this.ids = selection.map(item => item.needId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料需用计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const needId = row.needId || this.ids;
      getNeed(needId).then(response => {
        this.form = response.data;
        this.mmsNeedMaterialList = response.data.mmsNeedMaterialList;
        this.open = true;
        this.title = "修改材料需用计划";
      });
    },
    //根据原计划ID获取项目ID
    handleShowPro(scope){
      getPlan(scope).then(res => {
            console.log(scope);
            console.log(res.data);
            this.form.proId = res.data.proId;
            this.mmsNeedMaterialList = res.data.mmsPlanMaterialList;
            console.log(this.form)
      });
  
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsNeedMaterialList = this.mmsNeedMaterialList;
          if (this.form.needId != null) {
            updateNeed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNeed(this.form).then(response => {
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
      const needIds = row.needId || this.ids;
      this.$modal
        .confirm('是否确认删除材料需用计划编号为"' + needIds + '"的数据项？')
        .then(function() {
          return delNeed(needIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 材料与材料需用计划序号 */
    rowMmsNeedMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与材料需用计划添加按钮操作 */
    handleAddMmsNeedMaterial() {
      let obj = {};
      obj.matId = "";
      obj.matUprice = "";
      obj.remark = "";
      this.mmsNeedMaterialList.push(obj);
    },
    // handleExportMmsPlanMaterial() {
      
    // },
    /** 材料与材料需用计划删除按钮操作 */
    handleDeleteMmsNeedMaterial() {
      if (this.checkedMmsNeedMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与材料需用计划数据");
      } else {
        const mmsNeedMaterialList = this.mmsNeedMaterialList;
        const checkedMmsNeedMaterial = this.checkedMmsNeedMaterial;
        this.mmsNeedMaterialList = mmsNeedMaterialList.filter(function(item) {
          return checkedMmsNeedMaterial.indexOf(item.index) == -1;
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsNeedMaterialSelectionChange(selection) {
      this.checkedMmsNeedMaterial = selection.map(item => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "purchase/need/export",
        {
          ...this.queryParams
        },
        `need_${new Date().getTime()}.xlsx`
      );
    }
  }
};
</script>
