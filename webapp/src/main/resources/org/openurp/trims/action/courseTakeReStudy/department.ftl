[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="student_depart_chart" title="${semester.id}学期 按学院统计"
  xname='院系' yname='学生人数' names=names values=values/]
[@b.foot/]