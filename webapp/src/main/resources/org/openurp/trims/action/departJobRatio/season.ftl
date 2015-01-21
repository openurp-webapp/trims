[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="student_depart_season_chart" title="${department.name} 按年级统计"
  xname='毕业季' yname='就业率'
  names=names values=values type='line'/]
[@b.div id="yearDepartDiv"/]
[@b.foot/]