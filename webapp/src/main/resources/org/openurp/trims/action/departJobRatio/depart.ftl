[@b.head/]
[#include "../echarts.ftl"/]
  <script>
    function selectDepart(param){
      var departmentIds = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]}[/#list]]
      bg.Go('${b.url('!season')}?departmentId='+departmentIds[param.dataIndex],'departSeasonDiv')
    }
  </script>
  <br>
  [@echarts id="student_depart_chart" title="${season}按院系统计" title2="点击图表查看院系统计"
    xname='院系' yname='就业率' names=names values=values onclick='selectDepart'/]
  [@b.div id="departSeasonDiv"/]
[@b.foot/]
