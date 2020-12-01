<template>
  <div class="app-container">
   <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;"/>
        <el-tree
        ref="subjectTree"
        :data="subjectList"
        :props="defaultProps"
        :filter-node-method="filterNode"
        class="filter-tree"
        default-expand-all/>
  </div>
</template>
<script>
import  subject from '@/api/edu/subject'
export default {
    data(){
        return {
          filterText: '',
          subjectList: [],
          defaultProps: {
          children: 'children',
          label: 'title'
          }
        }
    },
    watch: {
        filterText(val) {
          this.$refs.subjectTree.filter(val)
        }
      },
    created() {
    this.fetchNodeList()
    },
    methods:{
      fetchNodeList() {
        subject.getNestedTreeList().then(response => {
        if (response.success === true) {
            this.subjectList = response.data.items
        }
        })
      },
      filterNode(value, data) {
        if (!value) return true
        return data.title.indexOf(value) !== -1
      }
    }
}
</script>
