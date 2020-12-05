<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <el-form label-width="120px">

        <el-form-item label="课程标题">
            <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
        </el-form-item>
        </el-form>
  </div>
</template>
<script>


export default {
    data() {
        return {
            saveBtnDisabled:false,
            
        }   
    },
    created() {
        
    },
    methods:{
       next(){
           this.$router.push({ path: '/edu/course/chapter/1' })
       }
        
    }
}
</script>
