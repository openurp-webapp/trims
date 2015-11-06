[#ftl]
[@b.head/]

[#include "../echarts.ftl"/]
[#assign series]
[
[#list values as v][#if v_index gt 0],[/#if]
  {
      name:'${v[0]}',
      type:'line',
      data:[[#list 1..(v?size-1) as i][#if i > 1],[/#if]'${v[i]}'[/#list]]
  }
[/#list]
]
[/#assign]
[#assign legend][[#list titles as t][#if t_index gt 0],[/#if]'${t}'[/#list]][/#assign]
[@echarts id="student_year_chart" title="按院系职称统计"
  xname='学年' yname='评教分数' maxAndMin=false 
  names=names series=series legend=legend/]
[@b.div id="qualityYearDiv" href="!qualityYear"/]
[@b.foot/]