[@b.head/]
[#include "../echarts.ftl"/]
<script>
  function selectDepart(param){
    var departmentIds = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]}[/#list]]
    bg.Go('${b.url('!departYear')}?departmentId='+departmentIds[param.dataIndex],'departYearDiv')
  }
</script>
[@echarts id="student_depart_chart" title="按学院统计" names=names values=values onclick='selectDepart'/]
[@b.div id="departYearDiv"/]
[@b.foot/]