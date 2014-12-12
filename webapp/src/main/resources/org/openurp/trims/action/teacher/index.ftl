[#ftl]
[#include "../nav.ftl"/]
[@b.head/]
[@b.div id="teacher_div"]
  [@b.toolbar title=title!"教师查询"/]
  <table  class="indexpanel">
    <tr>
      <td class="index_view" style = "width:180px">
      [@b.form name="teacherSearchForm" action="!search" target="teacherlist" theme="search"]
        [@b.textfields names="teacher.person.name;教师姓名"/]
        [@b.textfields names="teacher.department.name;所属部门"/]
      [/@]
      </td>
      <td class="index_content">[@b.div id="teacherlist" href="!search" /]</td>
    </tr>
  </table>
[/@]
[@b.foot/]