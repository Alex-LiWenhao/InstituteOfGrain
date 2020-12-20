<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>


      <el-button type="text" @click="openChapterDialog()">添加章节</el-button>

    <!-- 章节 -->
    <ul class="chanpterList">
        <li
            v-for="chapter in chapterVideoList"
            :key="chapter.id">
            <p>
                {{ chapter.title }}
                 <span class="acts">
                    <el-button style="" type="text" @click="openVideo(chapter.id)">添加小节</el-button>
                    <el-button style="" type="text" @click="openEditChatper(chapter.id)">编辑</el-button>
                    <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
                </span>
            </p>

             <!-- 视频 -->
            <ul class="chanpterList videoList">
                <li
                    v-for="video in chapter.children"
                    :key="video.id">
                    <p>{{ video.title }}
                <span class="acts">
                    <el-button style="" type="text">编辑</el-button>
                    <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                </span>
                    </p>
                </li>
            </ul>
        </li>
    </ul>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
      </el-form-item>
    </el-form>

   <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
        <el-form :model="chapter" label-width="120px">
            <el-form-item label="章节标题">
                <el-input v-model="chapter.title"/>
            </el-form-item>
            <el-form-item label="章节排序">
                <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
        </div>
    </el-dialog>
  </div>
</template>
<script>
import chapter from '@/api/edu/chapter'
export default {
    data() {
        return {
            saveBtnDisabled:false,
            chapterVideoList:[],
            courseId:'',
            dialogChapterFormVisible: false, //是否显示章节表单
            chapter: {// 章节对象
                title: '',
                sort: 0
            }
        }
    },
    created() {
        if(this.$route.params && this.$route.params.id){
          this.courseId = this.$route.params.id
          this.getChapterVideo()
        }
    },
    methods:{
      // =================章节操作=======================
      //删除章节
      removeChapter(chapterId){
        chapter.removeById(chapterId).then(response=>{
          //提示
          this.$message({
                    type: 'success',
                    message: '删除成功!'
                  })
          //刷新页面
          this.getChapterVideo()
        })
      },
      //修改章节弹框处理
      openEditChatper(chapterId){
        //弹框
            this.dialogChapterFormVisible = true
            //调用接口
            chapter.getChapterById(chapterId)   
                .then(response => {
                    this.chapter = response.data.eduChapter
              })
      },
      //添加章节
      addChapter(){
        this.chapter.courseId = this.courseId
        chapter.save(this.chapter).then(response=>{
          //关弹框
          this.dialogChapterFormVisible=false
          //提示
          this.$message({
                    type: 'success',
                    message: '添加成功!'
                  })
          //刷新页面
          this.getChapterVideo()
        })
      },
      updateChapter(){
          chapter.updateChapter(this.chapter).then(response=>{
             //关弹框
          this.dialogChapterFormVisible=false
          //提示
          this.$message({
                    type: 'success',
                    message: '修改成功!'
                  })
          //刷新页面
          this.getChapterVideo()
          })
      },
      //添加还是保存
      saveOrUpdate(){
        if(!this.chapter.id){
          this.addChapter()
        }else{
          this.updateChapter()
        }
          

      },
      //让模态框显示出来
      openChapterDialog(){
            //弹框
            this.dialogChapterFormVisible = true
            //表单数据清空
            this.chapter.title = ''
            this.chapter.sort = 0
      },
      getChapterVideo(){
        chapter.getNestedTreeList(this.courseId).then(response=>{
          this.chapterVideoList = response.data.list
        })
      },
        previous() {
            this.$router.push({path:'/edu/course/info/'+this.courseId})
        },
        next() {
            //跳转到第二步
            this.$router.push({path:'/edu/course/publish/1'})
        }
    }
}
</script>
<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}
