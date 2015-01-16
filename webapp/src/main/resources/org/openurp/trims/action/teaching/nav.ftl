[#ftl]
[#include "../nav.ftl"/]
[@b.head/]
[@b.toolbar title="${person.name} 教学情况"][/@]
  [@nav2 "nav_teaching_div"]
    <li>[@b.a href="teaching!lessonLine?id=${id}" target="nav_teaching_div"]上课情况[/@]</li>
    <li>[@b.a href="teaching!quality?id=${id}" target="nav_teaching_div"]评教情况[/@]</li>
    <li>[@b.a href="teaching!grade?id=${id}" target="nav_teaching_div"]成绩及格率[/@]</li>
  [/@]
  [@b.div id="nav_teaching_div" href="teaching!lessonLine?id=${id}"/]
[@b.foot/]