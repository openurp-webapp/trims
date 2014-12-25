[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="student_depart_chart" title="${semester.id}学期 按院系统计"
  xname='院系' yname='重修人次' names=names values=values/]
[@b.foot/]