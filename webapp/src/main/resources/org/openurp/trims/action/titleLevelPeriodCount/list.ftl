[@b.head/]
  [#include "../echarts.ftl"/]
  <script>
    function selectTitle(param){
      var ids = [[#list datas as d][#if d_index gt 0],[/#if]${d[0]!}[/#list]]
      bg.Go('${b.url('!department')}?beginYear=${beginYear!}&endYear=${endYear!}&teaching=${(teaching?string(1,0))!}&tid='+ids[param.dataIndex],'titlePeriodDepartDiv')
    }
  </script>
  [@echarts id="title_period_chart" title="按职称统计" title2="点击图表查看某职称按部门统计"
    xname='职称' yname='学期平均课时数' maxAndMin=false
    names=names values=values onclick="selectTitle"/]
[@b.div id="titlePeriodDepartDiv"/]
[@b.foot/]