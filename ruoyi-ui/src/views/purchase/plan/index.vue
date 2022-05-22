<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <el-form-item label="计划名称" prop="planName">
        <el-input
          v-model="queryParams.planName"
          placeholder="请输入计划名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['purchase:plan:add']"
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
          v-hasPermi="['purchase:plan:edit']"
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
          v-hasPermi="['purchase:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center">
        <template slot-scope="scope">
          <span>{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划名称" align="center" prop="planName" />
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
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDetail(scope.row)"
            v-hasPermi="['purchase:plan:detail']"
          >详情</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:plan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:plan:remove']"
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

    <!-- 添加或修改材料总计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="计划名称" prop="planName">
          <el-input v-model="form.planName" placeholder="请输入计划名称" />
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
        <el-form-item label="计划状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择材料总计划状态">
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
        <el-divider content-position="center">材料与材料总计划信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAddMmsPlanMaterial"
            >添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="handleDeleteMmsPlanMaterial"
            >删除</el-button>
          </el-col>
        </el-row>
        <el-table
          :data="mmsPlanMaterialList"
          :row-class-name="rowMmsPlanMaterialIndex"
          @selection-change="handleMmsPlanMaterialSelectionChange"
          ref="mmsPlanMaterial"
        >
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
          <el-table-column label="计划数量" prop="planNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.planNum" placeholder="请输入计划数量" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose"> -->
    <!-- <el-dialog :title="title" :visible.sync="visibleDetail" width="500px" append-to-body>
      <el-table :data="mmsPlanMaterialList" border style="width: 100%">
        <el-table-column prop="matId" label="日期" width="180"></el-table-column>
        <el-table-column prop="matName" label="姓名" width="180"></el-table-column>
  
      </el-table>

      <span slot="footer" class="dialog-footer">
        <el-button @click="visibleDetail = false">取 消</el-button>
        <el-button type="primary" @click="visibleDetail = false">确 定</el-button>
      </span>
    </el-dialog> -->
  </div>
</template>

<script>
import {
  listPlan,
  getPlan,
  delPlan,
  addPlan,
  updatePlan
} from "@/api/purchase/plan";
import { listProject } from "@/api/project/project";
import { listMaterial } from "@/api/material/material";
import { listUser } from "@/api/system/user";
export default {
  name: "Plan",
  dicts: ["mms_pur_status"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMmsPlanMaterial: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 材料总计划表格数据
      planList: [],
      // 材料与材料总计划表格数据
      projectList: [],
      mmsPlanMaterialList: [],
      materialList: [],
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      //
      visibleDetail: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planName: null,
        proId: null,
        status: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        planName: [
          { required: true, message: "计划名称不能为空", trigger: "blur" }
        ],
        proId: [{ required: true, message: "项目ID不能为空", trigger: "blur" }],
        userId: [
          { required: true, message: "审核人ID不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料总计划列表 */
    getList() {
      this.loading = true;
      listPlan(this.queryParams).then(response => {
        this.planList = response.rows;

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
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        planId: null,
        planName: null,
        proId: null,
        status: null,
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.mmsPlanMaterialList = [];
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
      this.ids = selection.map(item => item.planId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料总计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const planId = row.planId || this.ids;
      getPlan(planId).then(response => {
        this.form = response.data;
        this.mmsPlanMaterialList = response.data.mmsPlanMaterialList;
        this.open = true;
        this.title = "修改材料总计划";
      });
    },
    // handleDetail(row) {
    //   const planId = row.planId || this.ids;
    //   getPlan(planId).then(res => {
    //     console.log(res.data);
    //     this.form = res.data;
    //     this.mmsPlanMaterialList = res.data.mmsPlanMaterialList;
    //     this.visibleDetail = true;
    //     this.title = "计划详情";
    //   });
    // },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.mmsPlanMaterialList = this.mmsPlanMaterialList;
          if (this.form.planId != null) {
            updatePlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlan(this.form).then(response => {
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
      const planIds = row.planId || this.ids;
      this.$modal
        .confirm('是否确认删除材料总计划编号为"' + planIds + '"的数据项？')
        .then(function() {
          return delPlan(planIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 材料与材料总计划序号 */
    rowMmsPlanMaterialIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 材料与材料总计划添加按钮操作 */
    handleAddMmsPlanMaterial() {
      let obj = {};
      obj.matId = "";
      obj.planNum = "";
      obj.remark = "";
      this.mmsPlanMaterialList.push(obj);
    },
    /** 材料与材料总计划删除按钮操作 */
    handleDeleteMmsPlanMaterial() {
      if (this.checkedMmsPlanMaterial.length == 0) {
        this.$modal.msgError("请先选择要删除的材料与材料总计划数据");
      } else {
        const mmsPlanMaterialList = this.mmsPlanMaterialList;
        const checkedMmsPlanMaterial = this.checkedMmsPlanMaterial;
        this.mmsPlanMaterialList = mmsPlanMaterialList.filter(function(item) {
          return checkedMmsPlanMaterial.indexOf(item.index) == -1;
        });
      }
    },
    /** 复选框选中数据 */
    handleMmsPlanMaterialSelectionChange(selection) {
      this.checkedMmsPlanMaterial = selection.map(item => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "purchase/plan/export",
        {
          ...this.queryParams
        },
        `plan_${new Date().getTime()}.xlsx`
      );
    }
  }
};
</script>
