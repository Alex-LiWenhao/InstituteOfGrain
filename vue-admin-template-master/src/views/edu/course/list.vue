<template>
  <div class="app-container">
   <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="courseQuery.status" clearable placeholder="课程状态">
          <el-option value="Normal" label="已发布"/>
          <el-option value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>

      <el-button type="default" @click="resetData()">清空</el-button>
      <el-button type="primary" icon="el-icon-search" @click="getAllcourseList()">查询</el-button>
      
    </el-form>
<!--表格-->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="200"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="课程名称" width="200" align="center"/>

      <el-table-column label="课程状态" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.status==='Normal'?'已发布':'未发布' }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="518" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/course/info/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程基本信息</el-button>
          </router-link>
          <router-link :to="'/edu/course/chapter/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程大纲信息</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除课程信息</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getAllcourseList"
    />
  </div>
</template>
<script>
import course from '@/api/edu/course'
  export default {
    data(){
        return{
          list:null,
          current:1,
          limit:5,
          total:0,
          courseQuery:{}
        }
    },
    created(){
      this.getAllcourseList()
    },
    methods:{
      //根据课程ID去删除课程相关内容
      removeDataById(courseId){
             this.$confirm('此操作将删除小节, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                course.removeCourseByCourseId(courseId)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    //刷新页面
                    this.getAllcourseList()
                })
            }) //点击取消，执行catch方法
      },
      //清空条件
      resetData(){
        this.courseQuery={}
        this.getAllcourseList()
      },
    //条件查询所有数据
      getAllcourseList(page = 1){
        this.page = page
        course.findAll(this.page,this.limit,this.courseQuery).then(response=>{
          this.total = response.data.total
          this.list = response.data.rows
          console.log(this.list)
        }).catch(error=>{
                 console.log(error)
        })
      }
    }
  }
</script>