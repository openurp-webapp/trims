[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="student_depart_year_chart" title="${department.name} 按年级统计"
  xname='年级' yname='学生人数'
  names=names values=values type='line'/]
[@b.div id="yearDepartDiv"/]
[@b.foot/]