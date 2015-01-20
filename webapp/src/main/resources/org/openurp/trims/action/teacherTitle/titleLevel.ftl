[@b.head/]
[#include "../echarts.ftl"/]
[@echarts id="teacher_title_level_chart" title="按职称级别统计"
  xname='职称' yname='教师人数' maxAndMin=false 
  names=names values=values/]
[@b.div id="teacherTitleLevelDiv"/]
[@b.foot/]