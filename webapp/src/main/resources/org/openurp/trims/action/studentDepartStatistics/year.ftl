[@b.head/]
[#include "../echarts.ftl"/]
<div style="max-width:1000px; margin:10px auto; border:1px solid #ccc; border-radius:5px; padding:10px;">
  <script>
    function selectYear(param){
      var years = [[#list datas as d][#if d_index gt 0],[/#if]'${d[0]}'[/#list]]
      bg.Go('${b.url('!yearDepart')}?year='+years[param.dataIndex],'yearDepartDiv')
    }
  </script>
  [@echarts id="student_year_chart" title="按年级统计" title2="点击图表显示某年级按院系统计"
    xname='年级' yname='学生人数'
    names=names values=values onclick="selectYear" type='line'/]
  [@b.div id="yearDepartDiv"/]
</div>
[@b.foot/]