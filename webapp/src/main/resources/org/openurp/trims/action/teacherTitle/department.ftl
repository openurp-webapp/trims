[@b.head/]
  [#include "../echarts.ftl"/]
  [@echarts id="teacher_title_department_chart" title="${title.name} 按院系统计"
    xname='院系' yname='教师人数'
    names=names values=values/]
[@b.foot/]