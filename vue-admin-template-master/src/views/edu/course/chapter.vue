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
                    v-for="video in chapter.video"
                    :key="video.id">
                    <p>{{ video.title }}
                <span class="acts">
                    <el-button style="" type="text" @click="openEditVideo(video.id)">编辑</el-button>
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

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="0">收费</el-radio>
            <el-radio :label="1">免费</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
              <el-upload
                :on-success="handleVodUploadSuccess"
                :on-remove="handleVodRemove"
                :before-remove="beforeVodRemove"
                :on-exceed="handleUploadExceed"
                :file-list="fileList"
                :action="BASE_API+'/eduvod/video/uploadVideo'"
                :limit="1"
                class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
                <div slot="content">最大支持1G，<br>
                    支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                    GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                    MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                    SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
                <i class="el-icon-question"/>
            </el-tooltip>
            </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
import chapter from '@/api/edu/chapter'
import video from '@/api/edu/video'
export default {
    data() {
        return {
            saveBtnDisabled:false,
            chapterVideoList:[],
            BASE_API: process.env.BASE_API, // 接口API地址
            courseId:'',
            dialogChapterFormVisible: false, //是否显示章节表单
            dialogVideoFormVisible: false,//是否显示小节表单
            chapter: {// 章节对象
                title: '',
                sort: 0
            },
            video: {
                title: '',
                sort: 0,
                isFree: 0,
                videoSourceId: '',
                videoOriginalName: ''
            },
            fileList: []//上传文件列表
        }
    },
    created() {
        if(this.$route.params && this.$route.params.id){
          this.courseId = this.$route.params.id
          this.getChapterVideo()
        }
    },
    methods:{
      //===================视频操作=====================
      handleVodRemove(){//删除视频时调用
        video.removeVideoByid(this.video.videoSourceId).then(response=>{
           //提示信息
            this.$message({
                type: 'success',
                message: '删除视屏成功!'
            });
            //将fileList设置为空
            fileList=[]
            this.video.videoSourceId = ''
            this.video.videoOriginalName=''
        })
      },
      beforeVodRemove(file, fileList){//删除视频之前调用
        return this.$confirm(`确定移除 ${ file.name }？`);
      },

      handleVodUploadSuccess(response, file, fileList){
          this.video.videoSourceId = response.data.videoId
          this.video.videoOriginalName = file.name
      },handleUploadExceed(){
          this.$message.warning('想要重新上传视频，请先删除已上传的视频')
      },
      //==================小结操作=======================
      //删除小结
      removeVideo(videoId){
        this.$confirm('此操作将删除小节, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                video.deleteVideo(videoId)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除小节成功!'
                    });
                    //刷新页面
                    this.getChapterVideo()
                })
            }) //点击取消，执行catch方法
      },
      //打开修改小结的模态框
      openEditVideo(videoId){
        //打开模态框
        this.dialogVideoFormVisible=true
        //查询video然后放入信息
        video.getVideoById(videoId).then(response=>{
            this.video = response.data.video
        })
      },
      addVideo(){
        //设置课程id
            this.video.courseId = this.courseId
           video.addVideo(this.video).then(response=>{
           //提示
          this.$message({
                    type: 'success',
                    message: '添加成功!'
                  })
          //关闭模态框
          this.dialogVideoFormVisible=false
          //刷新页面
          this.getChapterVideo()
        })
      },
      //修改小结
      saveOrUpdateVideo(){
       this.addVideo()
      },
      openVideo(chapterId){
        //将video信息设为空
            this.video = {}
        //将上传列表设置为空

            //弹框
            this.dialogVideoFormVisible = true
            //设置章节id
            this.video.chapterId = chapterId
      },
      // =================章节操作=======================
      //删除章节
      removeChapter(chapterId){
        this.$confirm('此操作将删除章节, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                chapter.removeById(chapterId)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除章节成功!'
                    });
                    //刷新页面
                    this.getChapterVideo()
                })
            }) //点击取消，执行catch方法
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
            this.$router.push({path:'/edu/course/publish/'+this.courseId})
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
