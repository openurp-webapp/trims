[@b.head/]
[#include "../echarts.ftl"/]
[#if thesises?size gt 0]
<h4>[@beginAndEnd/]  论文成果 Top10</h4>
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
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无论文成果数据</div>
[/#if]
[#if literatures?size gt 0]
<h4>[@beginAndEnd/]  著作成果 Top10</h4>
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
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无著作成果数据</div>
[/#if]
[@b.foot/]