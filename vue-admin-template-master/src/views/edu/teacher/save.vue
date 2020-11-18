<template>
  <div class="app-container">添加讲师
     <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像：TODO -->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>
<script>
import teacherApi from '@/api/edu/teacher'
  export default {
    data(){
        return{
           teacher:{
              name: '',
              sort: 0,
              level: 1,
              career: '',
              intro: '',
              avatar: ''
            },
           saveBtnDisabled:false  // 保存按钮是否禁用,
        }
    },
    created(){
      this.init()
    },
    watch:{
        $route(to, from) { //路由变化方式，路由发生变化，方法就会执行
          this.init()
        }
    },
    methods:{
      init(){
          if(this.$route.params && this.$route.params.id){
          const id = this.$route.params.id
          this.getTeacherById(id)
        }else{
          this.teacher = {}
        }
      },
      saveOrUpdate(){
       if (!this.teacher.id) {
         this.saveTeacher()
       }else{
          this.updateTeacher()
       }
      },
      getTeacherById(id){
            teacherApi.getTeacherInfo(id).then(response=>{
              this.teacher = response.data.teacher
            })
      },
      saveTeacher(){
        teacherApi.addTeacher(this.teacher).then(response=>{
                  this.$message({
                    type: 'success',
                    message: '添加成功!'
                  });
                  this.$router.push({path:'/teacher/table'})
                })
      },
      updateTeacher(){
          teacherApi.updateTeacher(this.teacher).then(response=>{
            this.$message({
                    type: 'success',
                    message: '修改成功!'
                  });
                  this.$router.push({path:'/teacher/table'})
          })
      }
    }
    
  }
</script>