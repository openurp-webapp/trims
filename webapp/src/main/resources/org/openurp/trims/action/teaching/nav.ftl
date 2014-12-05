[#ftl]
[#include "../nav.ftl"/]
[@b.head/]
[@b.toolbar title="${teacher.person.name} 教学情况"][/@]
  [@nav2 "nav_teaching_div"]
    <li>[@b.a href="teaching!lesson?id=${id}" target="nav_teaching_div"]上课情况[/@]</li>
    <li>[@b.a href="teaching!lesson?id=${id}" target="nav_teaching_div"]评教情况[/@]</li>
    <li>[@b.a href="teaching!lesson?id=${id}" target="nav_teaching_div"]考勤情况[/@]</li>
    <li>[@b.a href="teaching!lesson?id=${id}" target="nav_teaching_div"]教学工作量[/@]</li>
    <li>[@b.a href="teaching!grade?id=${id}" target="nav_teaching_div"]成绩及格率[/@]</li>
  [/@]
  [@b.div id="nav_teaching_div" href="teaching!lesson?id=${id}"/]
[@b.foot/]