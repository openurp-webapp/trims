[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="student_depart_chart" title="${year}学年 按院系统计"
  xname='院系' yname='学生人数' names=names values=values/]
[@b.foot/]