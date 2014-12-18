[@b.head/]

[#include "../echarts.ftl"/]

<script>
  function selectTitle(param){
    var departmentIds = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]}[/#list]]
    bg.Go('${b.url('!department')}?did='+departmentIds[param.dataIndex],'LessonDepartmentChartDiv')
  }
</script>
[#assign title][#if year??]${year}学年[/#if][#if term??]  第${term}学期[/#if] 按开课数量统计[/#assign]
[@echarts id="lessonTrimsChart" title=title title2="点击图表显示某院系按学期统计"
  xname='职称' yname='教师人数' maxAndMin=false 
  names=names values=values onclick="selectTitle"/]
  
[@b.div id="LessonDepartmentChartDiv"/]

[@b.foot/]