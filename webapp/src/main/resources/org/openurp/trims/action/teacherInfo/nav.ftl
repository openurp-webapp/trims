[#ftl]
[#include "../nav.ftl"/]
[@b.head/]
  [@nav "nav_top_div"]
    <li>[@b.a href="teacher-info!info?id=${id}" target="nav_top_div"]基本信息[/@]</li>
    <li>[@b.a href="teaching!nav?id=${id}" target="nav_top_div"]教学情况[/@]</li>
    <li>[@b.a href="teacher-info!info?id=${id}" target="nav_top_div"]科研情况[/@]</li>
    <li>[@b.a href="teacher-info!info?id=${id}" target="nav_top_div"]师资情况[/@]</li>
    <li>[@b.a href="teacher-info!info?id=${id}" target="nav_top_div"]获奖情况[/@]</li>
    <li>[@b.a href="teacher-info!info?id=${id}" target="nav_top_div"]其他情况[/@]</li>
  [/@]
  [@b.div id="nav_top_div" href="teacher-info!info?id=${id}"/]
[@b.foot/]