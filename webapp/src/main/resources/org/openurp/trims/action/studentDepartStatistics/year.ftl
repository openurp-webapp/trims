[@b.head/]
[#include "../echarts.ftl"/]
<script>
  function selectYear(param){
    bg.Go('${b.url('!yearDepart')}?year='+param.name,'yearDepartDiv')
  }
</script>
[@echartsLine id="student_year_chart" title="按年级统计" names=names values=values onclick="selectYear"/]
[@b.div id="yearDepartDiv"/]
[@b.foot/]