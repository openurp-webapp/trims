[@b.head/]
[#include "../echarts.ftl"/]
<script>
  function selectTitle(param){
    var ids = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]!}[/#list]]
    bg.Go('${b.url('!department')}?tid='+ids[param.dataIndex],'teacherTitleDepartDiv')
  }
</script>
[@echarts id="teacher_title_chart" title="按职称统计" title2="点击图表查看某职称按院系统计"
  xname='职称' yname='教师人数' maxAndMin=false 
  names=names values=values onclick="selectTitle"/]
[@b.div id="teacherTitleDepartDiv"/]
[@b.div href="!titleLevel"/]
[@b.foot/]