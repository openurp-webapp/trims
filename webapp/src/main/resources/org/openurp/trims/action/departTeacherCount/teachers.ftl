[@b.head/]
[#if teachers?size gt 0]
<h4>${depart.name}</h4>
<table class="gridtable">
  <tr>
    <th>工号</th>
    <th>姓名</th>
    <th>类型</th>
  </tr>
    [#list teachers as v]
  <tr>
      <td>${v[0]}</td>
      <td>${v[1]}</td>
      <td>${v[2]}</td>
  </tr>
    [/#list]
</table>
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无数据</div>
[/#if]
[@b.foot/]