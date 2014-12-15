[@b.head/]
[#include "../echarts.ftl"/]
<script>
  function selectGrade(param){
    var semesterIds = [[#list datas as d][#if d_index gt 0],[/#if]'${d[0]}'[/#list]]
    bg.Go('${b.url('!department')}?semesterId='+semesterIds[param.dataIndex],'semesterDepartDiv')
  }
</script>
[@echarts id="course_take_re_study_div" title="按学期统计重修门次" title2="点击图表显示某学期按学院统计"
  xname='学期' yname='重修人次' names=names values=values onclick="selectGrade"/]
[@b.div id="semesterDepartDiv"/]
[@b.foot/]