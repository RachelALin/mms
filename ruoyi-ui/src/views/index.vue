 <template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>消息通知</h2>
      </el-col>
    </el-row>
    <el-divider />

    <div>
      <el-row :gutter="20" v-for="( item, index) in noticeList" :key="index">
        <el-col :sm="24" :lg="24">
          <blockquote style="font-size: 14px">
            <el-card prop="noticeContent">
              <h3
                style="font-family:Courier, monospace; font-weight:bold;font-style:italic; "
              >标题： {{item.noticeTitle}}</h3>
              <h6 style="font-family:Courier, monospace;">时间：{{item.createTime}}</h6>
              <div style="font-family:Courier, monospace;" v-html="item.noticeContent"></div>
              <br />
            </el-card>

            <br />
          </blockquote>
        </el-col>
      </el-row>
    </div>

    <el-divider />
  </div>
</template>

<script>
import {
  listNotice,
  getNotice,
  delNotice,
  addNotice,
  updateNotice
} from "@/api/system/notice";
export default {
  name: "Index",

  data() {
    return {
      noticeList: []
    };
  },
  created() {
    this.getList();
  },

  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    getList() {
      this.loading = true;
      // var patt = /<[^>]+>/g;
      listNotice(this.queryParams).then(response => {
        this.noticeList = response.rows;
        // this.noticeList.noticeContent = response.rows.noticeContent.replace(patt,"");
        this.total = response.total;
        this.loading = false;
        console.log(response.rows);
      });
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

