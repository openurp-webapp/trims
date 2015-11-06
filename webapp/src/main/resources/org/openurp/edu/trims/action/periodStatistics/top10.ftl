[@b.head/]
[#include "../trims.ftl"/]
<h4>[@beginYearAndEndYear/] 平均课时 Top10</h4>
[#if datas?size gt 0]
<table class="gridtable">
  <tr>
    <th>学年学期</th>
    <th>教师姓名</th>
    <th>所属部门</th>
    <th>平均课时</th>
  </tr>
    [#list datas as data]
  <tr>
      <td>${data[1]}</td>
      <td>${data[0]}</td>
      <td>${data[3]}</td>
      <td>${data[2]}</td>
  </tr>
    [/#list]
</table>
[#else]
<div style="padding:100px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[@b.foot/]