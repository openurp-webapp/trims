[#ftl]
[@b.head/]
[#include "../nav.ftl"/]
[#if curYear??]
[@nav3 "year${curYear!}"]
  [#list years as v]
    <li class="year${v[0]}" [#if curYear == v[0]]class="active"[/#if]>[@b.a href="teaching!grade?id=${staff.id}&year=${v[0]}"]${v[0]}(<span style="color: red;">${v[1]}</span>)[/@]</li>
  [/#list]
[/@]
<script>
  $(".year${curYear}").addClass("active").siblings().removeClass("active");
</script>

<table class="gridtable">
  <tr>
    <th width="10%">学年学期</th>
    <th width="10%">课程代码</th>
    <th width="15%">课程名称</th>
    <th width="30%">面向学生</th>
    <th width="15%">课程类别</th>
    <th width="10%">期末成绩及格率</th>
    <th width="10%">期末总评及格率</th>
  </tr>
  [#list lessons as lesson]
    <tr>
      <td>${lesson.semester.schoolYear}-${lesson.semester.name}</td>
      <td>${lesson.course.code}</td>
      <td>${lesson.course.name}</td>
      <td>${(lesson.teachClass.fullname)!}</td>
      <td>${lesson.courseType.name}</td>
      <td>${((examGradesMap["" + lesson.id + 1]/examTotalMap[""+lesson.id])?string.percent)!}</td>
      <td>${((gaGradesMap["" + lesson.id + 1]/gaTotalMap[""+lesson.id])?string.percent)!}</td>
    </tr>
   [/#list]
</table>
[#else]
<div style="padding:100px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[@b.foot/]