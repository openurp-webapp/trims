[@b.head/]
[#if datas?size gt 0]
<h4>平均课时 Top10</h4>
<table class="gridtable">
  <tr>
    <th>学年</th>
    <th>教师姓名</th>
    <th>平均课时</th>
  </tr>
    [#list datas as data]
      <tr>
          <td>${data[0]}</td>
          <td>${data[2]}</td>
          <td>${data[3]}</td>
      </tr>
    [/#list]
</table>
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[@b.foot/]