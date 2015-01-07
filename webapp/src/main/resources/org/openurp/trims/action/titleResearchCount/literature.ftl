[@b.head/]
  [#include "../echarts.ftl"/]
[#if datas?size gt 0]
  [#assign title][@beginAndEnd/]  著作按教师职称统计[/#assign]
  [@echarts id="literature_chart" title=title maxAndMin=false 
    xname='职称' yname='数量'
    names=names values=values/]
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无著作成果数据</div>
[/#if]
[@b.foot/]