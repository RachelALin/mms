<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="材料名称" prop="matName">
        <el-input
          v-model="queryParams.matName"
          placeholder="请输入材料名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料规格" prop="matSize">
        <el-input
          v-model="queryParams.matSize"
          placeholder="请输入材料规格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料单位" prop="matUnit">
        <el-input
          v-model="queryParams.matUnit"
          placeholder="请输入材料单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="材料类型" prop="matType">
        <el-select v-model="queryParams.matType" placeholder="请选择材料类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.mms_material_type"
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
          v-hasPermi="['material:material:add']"
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
          v-hasPermi="['material:material:edit']"
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
          v-hasPermi="['material:material:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['material:material:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="材料ID" align="center" prop="matId" />
      <el-table-column label="材料名称" align="center" prop="matName" />
      <el-table-column label="材料规格" align="center" prop="matSize" />
      <el-table-column label="材料单位" align="center" prop="matUnit" />
      <el-table-column label="材料类型" align="center" prop="matType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mms_material_type" :value="scope.row.matType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['material:material:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['material:material:remove']"
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

    <!-- 添加或修改材料管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="材料名称" prop="matName">
          <el-input v-model="form.matName" placeholder="请输入材料名称" />
        </el-form-item>
        <el-form-item label="材料规格" prop="matSize">
          <el-input v-model="form.matSize" placeholder="请输入材料规格" />
        </el-form-item>
        <el-form-item label="材料单位" prop="matUnit">
          <el-input v-model="form.matUnit" placeholder="请输入材料单位" />
        </el-form-item>
        <el-form-item label="材料类型" prop="matType">
          <el-select v-model="form.matType" placeholder="请选择材料类型">
            <el-option
              v-for="dict in dict.type.mms_material_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
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
import { listMaterial, getMaterial, delMaterial, addMaterial, updateMaterial } from "@/api/material/material";

export default {
  name: "Material",
  dicts: ['mms_material_type'],
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
      // 材料管理表格数据
      materialList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        matName: null,
        matSize: null,
        matUnit: null,
        matType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        matName: [
          { required: true, message: "材料名称不能为空", trigger: "blur" }
        ],
        matSize: [
          { required: true, message: "材料规格不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料管理列表 */
    getList() {
      this.loading = true;
      listMaterial(this.queryParams).then(response => {
        this.materialList = response.rows;
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
        matId: null,
        matName: null,
        matSize: null,
        matUnit: null,
        matType: null,
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
      this.ids = selection.map(item => item.matId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const matId = row.matId || this.ids
      getMaterial(matId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改材料管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.matId != null) {
            updateMaterial(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaterial(this.form).then(response => {
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
      const matIds = row.matId || this.ids;
      this.$modal.confirm('是否确认删除材料管理编号为"' + matIds + '"的数据项？').then(function() {
        return delMaterial(matIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('material/material/export', {
        ...this.queryParams
      }, `material_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
