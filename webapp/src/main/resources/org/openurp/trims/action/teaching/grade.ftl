[#ftl]
[@b.head/]
[#include "../nav.ftl"/]
[@nav3 "year${curYear!}"]
  [#list years as v]
    <li class="year${v[0]}" [#if curYear == v[0]]class="active"[/#if]>[@b.a href="teaching!grade?id=${teacher.id}&year=${v[0]}"]${v[0]}(<span style="color: red;">${v[1]}</span>)[/@]</li>
  [/#list]
[/@]
<script>
  $(".year${curYear}").addClass("active").siblings().removeClass("active");
</script>

<table class="gridtable">
  <tr>
    <th>学年学期</th>
    <th>课程代码</th>
    <th>课程名称</th>
    <th>教学任务</th>
    <th>课程类别</th>
    <th>期末成绩及格率</th>
    <th>期末总评及格率</th>
  </tr>
  ${gaTotalMap[""+201101320]}
  ${(examGradesMap["2011013201"]/examTotalMap[""+201101320])?string.percent}
  [#list lessons as lesson]
    <tr>
      <td>${lesson.semester.schoolYear}-${lesson.semester.name}</td>
      <td>${lesson.course.code}</td>
      <td>${lesson.course.name}</td>
      <td>${(lesson.teachClass.fullName)!}</td>
      <td>${lesson.courseType.name}</td>
      <td>${((examGradesMap["" + lesson.id + 1]/examTotalMap[""+lesson.id])?string.percent)!}</td>
      <td>${((gaGradesMap["" + lesson.id + 1]/gaTotalMap[""+lesson.id])?string.percent)!}</td>
    </tr>
   [/#list]
</table>
[@b.foot/]