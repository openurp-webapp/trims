[@b.head/]
[#include "../echarts.ftl"/]
  <div>
    <div style="float:left; width:69%">
      <script>
        function selectDepart(param){
          var departmentIds = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]}[/#list]]
          bg.Go('${b.url('!season')}?departmentId='+departmentIds[param.dataIndex],'departSeasonDiv')
        }
      </script>
      <br>
      [@echarts id="student_depart_chart" title="${season}按院系统计" title2="点击图表查看院系统计"
        xname='院系' yname='就业率' names=names values=values onclick='selectDepart'/]
    </div>
    <div style="float:left; width:29%; padding-top:30px;">
      [@b.div href="!status?seasonId=${seasonId}"/]
    </div>
    <div style="clear:both;"></div>
  </div>
  [@b.div id="departSeasonDiv"/]
[@b.foot/]
