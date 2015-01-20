[@b.head/]
[#include "../echarts.ftl"/]
<script>
  function selectTitleLevel(param){
    var ids = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]!}[/#list]]
    bg.Go('${b.url('!levelDepart')}?tid='+ids[param.dataIndex],'teacherTitleLevelDepartDiv')
  }
</script>
[@echarts id="teacher_title_level_chart" title="按职称类别统计" title2="点击图表查看某职称按院系统计"
  xname='职称类别' yname='教师1人数' maxAndMin=false 
  names=names values=values onclick="selectTitleLevel"/]
[@b.div id="teacherTitleLevelDepartDiv"/]
[@b.foot/]