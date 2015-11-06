[@b.head/]
<style>
.gridtable td, .gridtable th{padding:0px 5px}
</style>
<h4>${department.name} ${title.name} 学期平均课时明细</h4>
<table class="gridtable" style = "width:400px;margin-left:70px">
  <tr>
    <th>姓名</th>
    <th>学期平均课时</th>
  </tr>
    [#list datas as data]
  <tr>
      <td>${data[0]}</td>
      <td>${data[1]}</td>
  </tr>
    [/#list]
</table>
[@b.foot/]