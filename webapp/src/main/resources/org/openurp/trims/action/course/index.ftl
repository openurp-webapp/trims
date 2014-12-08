[#ftl]
[#include "../nav.ftl"/]
[@b.head/]
[@b.div id="course_div"]
  [@b.toolbar title=title!"课程查询"/]
  <table  class="indexpanel">
    <tr>
      <td class="index_view">
      [@b.form name="courseSearchForm" action="!search" target="courselist" title="ui.searchForm" theme="search"]
        [@b.textfields names="course.code;代码"/]
        [@b.textfields names="course.name;名称"/]
      [/@]
      </td>
      <td class="index_content">[@b.div id="courselist" href="!search" /]</td>
    </tr>
  </table>
[/@]
[@b.foot/]