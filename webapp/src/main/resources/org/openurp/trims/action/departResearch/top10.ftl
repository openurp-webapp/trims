[@b.head/]
<h4>论文成果 Top10</h4>
<table class="gridtable">
  <tr>
    <th>教师姓名</th>
    <th>所属部门</th>
    <th>数量</th>
  </tr>
    [#list thesises as thesis]
  <tr>
      <td>${thesis[0]}</td>
      <td>${thesis[1]}</td>
      <td>${thesis[2]}</td>
  </tr>
    [/#list]
</table>
<h4>专著成果 Top10</h4>
<table class="gridtable">
  <tr>
    <th>教师姓名</th>
    <th>所属部门</th>
    <th>数量</th>
  </tr>
    [#list literatures as literature]
  <tr>
      <td>${literature[0]}</td>
      <td>${literature[1]}</td>
      <td>${literature[2]}</td>
  </tr>
    [/#list]
</table>
[@b.foot/]