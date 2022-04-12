<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="proName">
        <el-input
          v-model="queryParams.proName"
          placeholder="请输入项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程面积" prop="proArea">
        <el-input
          v-model="queryParams.proArea"
          placeholder="请输入工程面积"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建设单位" prop="proUnit">
        <el-input
          v-model="queryParams.proUnit"
          placeholder="请输入建设单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监督单位" prop="supervisionUnit">
        <el-input
          v-model="queryParams.supervisionUnit"
          placeholder="请输入监督单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程地点" prop="proAddress">
        <el-input
          v-model="queryParams.proAddress"
          placeholder="请输入工程地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程电话" prop="proPhone">
        <el-input
          v-model="queryParams.proPhone"
          placeholder="请输入工程电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
       <el-form-item label="工程类型" prop="ptypeId">
        <el-select v-model="queryParams.ptypeId" placeholder="请选择工程类型" clearable size="small">
          <el-option
            v-for="dict in projectTypes"
            :key="dict.id"
            :label="dict.typeName"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="工程状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择工程状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_pro_status"
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
      <el-form-item label="项目备注" prop="proNote">
        <el-input
          v-model="queryParams.proNote"
          placeholder="请输入项目备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计税方式" prop="taxWay">
        <el-select v-model="queryParams.taxWay" placeholder="请选择计税方式" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_tax_way"
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
          v-hasPermi="['project:project:add']"
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
          v-hasPermi="['project:project:edit']"
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
          v-hasPermi="['project:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange" stripe border height="250">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column fixed label="项目ID" align="center" prop="proId" />
      <el-table-column fixed label="项目名称" align="center" prop="proName" />
    
      <el-table-column fixed label="工程类型" align="center" prop="projectType.typeName" />
      <el-table-column label="工程面积" align="center" prop="proArea" />
      <el-table-column label="建设单位" align="center" prop="proUnit" />
      <el-table-column label="监督单位" align="center" prop="supervisionUnit" />
      <el-table-column label="工程地点" align="center" prop="proAddress" />
      <el-table-column label="工程电话" align="center" prop="proPhone" />
      <el-table-column label="工程状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_pro_status" :value="scope.row.status"/>
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
      <el-table-column label="项目备注" align="center" prop="proNote" />
      <el-table-column label="计税方式" align="center" prop="taxWay">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_tax_way" :value="scope.row.taxWay"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="user.nickName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:project:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:project:remove']"
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

    <!-- 添加或修改项目立项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="proName">
          <el-input v-model="form.proName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="工程面积" prop="proArea">
          <el-input v-model="form.proArea" placeholder="请输入工程面积" />
        </el-form-item>
        <el-form-item label="建设单位" prop="proUnit">
          <el-input v-model="form.proUnit" placeholder="请输入建设单位" />
        </el-form-item>
        <el-form-item label="监督单位" prop="supervisionUnit">
          <el-input v-model="form.supervisionUnit" placeholder="请输入监督单位" />
        </el-form-item>
        <el-form-item label="工程地点" prop="proAddress">
          <el-input v-model="form.proAddress" placeholder="请输入工程地点" />
        </el-form-item>
        <el-form-item label="工程电话" prop="proPhone">
          <el-input v-model="form.proPhone" placeholder="请输入工程电话" />
        </el-form-item>
  
        <el-form-item label="工程类型" prop="ptypeId">
          <el-select v-model="form.ptypeId" placeholder="请选择工程类型">
            <el-option
              v-for="dict in projectTypes"
              :key="dict.id"
              :label="dict.typeName"
              :value="dict.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工程状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择工程状态">
            <el-option
              v-for="dict in dict.type.mms_pro_status"
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
        <el-form-item label="项目备注" prop="proNote">
          <el-input v-model="form.proNote" placeholder="请输入项目备注" />
        </el-form-item>
        <el-form-item label="计税方式">
          <el-radio-group v-model="form.taxWay">
            <el-radio
              v-for="dict in dict.type.mms_tax_way"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/project/project";
import { listType } from "@/api/project/type";

export default {
  name: "Project",
  dicts: ['mms_pro_status', 'mms_tax_way'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目立项表格数据
      projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        proName: null,
        ptypeId: null,
        proArea: null,
        proUnit: null,
        supervisionUnit: null,
        proAddress: null,
        proPhone: null,
        userName: null,
        status: null,
        startDate: null,
        endDate: null,
        proNote: null,
        taxWay: null,
      },
      projectTypes:[],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        proName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        ptypeId: [
          { required: true, message: "工程类型不能为空", trigger: "change" }
        ],
        proArea: [
          { required: true, message: "工程面积不能为空", trigger: "blur" }
        ],
        proAddress: [
          { required: true, message: "工程地点不能为空", trigger: "blur" }
        ],
        // userId: [
        //   { required: true, message: "用户ID不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    //获取项目信息
    this.getList();
    //获取类型信息
    this.getlistType();
  },
  methods: {
    /** 查询项目立项列表 */
    getList() {
      this.loading = true;
      listProject(this.queryParams).then(response => {
    
        this.projectList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(response);
      });
    },
    getlistType(){
      listType().then(res =>{
     
        this.projectTypes = res.rows;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        proId: null,
        proName: null,
        ptypeId: null,
        proArea: null,
        proUnit: null,
        supervisionUnit: null,
        proAddress: null,
        proPhone: null,
        userId: null,
        status: null,
        startDate: null,
        endDate: null,
        proNote: null,
        taxWay: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
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
      this.ids = selection.map(item => item.proId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目立项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const proId = row.proId || this.ids
      getProject(proId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目立项";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.proId != null) {
            updateProject(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProject(this.form).then(response => {
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
      const proIds = row.proId || this.ids;
      this.$modal.confirm('是否确认删除项目立项编号为"' + proIds + '"的数据项？').then(function() {
        return delProject(proIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/project/export', {
        ...this.queryParams
      }, `project_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
