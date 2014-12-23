[@b.head/]
  [#include "../echarts.ftl"/]
  <script>
    function selectDepartment(param){
      var ids = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]!}[/#list]]
      bg.Go('${b.url('!title')}?tid=${title.id}&did='+ids[param.dataIndex],'teacherTitleDepartDiv')
    }
  </script>
  [@echarts id="title_period_department_chart" title="${title.name}按部门统计" title2="点击图表查看个人平均课时明细"
    xname='部门' yname='学期平均课时数' maxAndMin=false
    names=names values=values onclick="selectDepartment"/]
[@b.div id="teacherTitleDepartDiv"/]
[@b.foot/]