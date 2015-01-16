[#ftl]
[@b.head/]
[@b.toolbar title="${person.name} 基本信息"]
[/@]
<table class="infoTable">
  <tr>
    <td class="title" width="20%">照片</td>
    <td class="content"><image src="${service_base}/sns/photo/${photo_url}.jpg" style="width:100px"></td>
    <td class="title" width="20%">性别</td>
    <td class="content">${(person.gender.name)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">年龄</td>
    <td class="content">${age!}</td>
    <td class="title" width="20%">手机</td>
    <td class="content">${(person.mobile)!}</td>
  </tr>
  <tr>
    <td class="title" width="20%">国籍</td>
    <td class="content">${(person.country.name)!}</td>
    <td class="title" width="20%">办公室电话</td>
    <td class="content"></td>
  </tr>
  <tr>
    <td class="title" width="20%">职称</td>
    <td class="content">${(staff.post.head.title.name)!}</td>
    <td class="title" width="20%">邮箱</td>
    <td class="content">${person.email}</td>
  </tr>
  <tr>
    <td class="title" width="20%">部门</td>
    <td class="content">${staff.post.head.department.name}</td>
    <td class="title" width="20%">职务</td>
    <td class="content"> </td>
  </tr>
</table>
[@b.foot/]