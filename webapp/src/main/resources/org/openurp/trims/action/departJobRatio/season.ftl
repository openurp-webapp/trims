[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="student_depart_season_chart" title="${department.name} 按毕业时间统计"
  xname='毕业时间' yname='就业率'
  names=names values=values type='line'/]
[@b.div id="yearDepartDiv"/]
[@b.foot/]