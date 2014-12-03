[#ftl]
[#include "../nav.ftl"/]
[@b.head/]
[@nav "a" 10/]
[@b.toolbar title="${teacher.person.name} 基本信息"]
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">照片</td>
    <td class="content"></td>
    <td class="title" width="20%">性别</td>
    <td class="content">${(teacher.person.gender.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">年龄</td>
    <td class="content">${age!}</td>
    <td class="title" width="20%">手机</td>
    <td class="content">${(teacher.person.mobile)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">籍贯</td>
    <td class="content">${(teacher.person.country)!}</td>
    <td class="title" width="20%">办公室电话</td>
    <td class="content"></td>
  </tr>
  <tr>
    <td class="title" width="20%">职称</td>
    <td class="content">${(teacher.title.name)!}</td>
    <td class="title" width="20%">邮箱</td>
    <td class="content">${teacher.person.email}</td>
  </tr>
  <tr>
    <td class="title" width="20%">部门</td>
    <td class="content">${teacher.person.department.name}</td>
    <td class="title" width="20%">职务</td>
    <td class="content"> </td>
  </tr>
</table>
[@b.foot/]