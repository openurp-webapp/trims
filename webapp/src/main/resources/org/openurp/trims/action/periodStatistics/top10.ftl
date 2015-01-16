[@b.head/]
<h4>[#if beginYear??]${beginYear}[/#if][#if beginYear?? && endYear??]-[/#if][#if endYear??]${endYear}[/#if]  平均课时 Top10</h4>
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
[@b.foot/]