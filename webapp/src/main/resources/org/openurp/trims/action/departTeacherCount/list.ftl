[@b.head/]
  [#include "../echarts.ftl"/]
  <script>
    function selectDepart(param){
      var departIds = [[#list departIds as v][#if v_index gt 0],[/#if]'${v}'[/#list]]
      bg.Go('${b.url('!teachers')}?teaching=${(teaching?string(1,0))!}&departId='+departIds[param.dataIndex],'teachersDiv')
    }
  </script>
  [#assign series]
  [
  [#list values as v][#if v_index gt 0],[/#if]
    {
        name:'${v[0]}',
        type:'bar',
        stack: '职称',
        barMinHeight: 10,
        data:[[#list 1..(v?size-1) as i][#if i > 1],[/#if]${v[i]}[/#list]]
    }
  [/#list]
  ]
  [/#assign]
  [#assign legend][[#list titles as t][#if t_index gt 0],[/#if]'${t}'[/#list]][/#assign]
  [@echarts id="student_year_chart" title="在职专任教师"
    xname='院系' yname='教师人数' maxAndMin=false 
    names=names series=series legend=legend height=500 trigger='axis' onclick="selectDepart"/]
[@b.div id="teacherTitleDepartDiv"/]
[@b.div id="teachersDiv"/]
[@b.foot/]