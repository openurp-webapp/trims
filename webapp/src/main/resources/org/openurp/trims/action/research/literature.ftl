[#ftl]
[@b.head/]
<h4>专著成果</h4>
[#if datas?size gt 0]
  <table class="gridtable">
    <tr>
      <th width="30%">名称</th>
      <th width="10%">字数(万字)</th>
      <th width="20%">出版社</th>
      <th width="20%">成果类型</th>
      <th width="10%">发表日期</th>
    </tr>
    [#list datas as d]
      <tr>
        <td>${d[0]!}</td>
        <td>${d[1]!}</td>
        <td>${d[2]!}</td>
        <td>${d[3]!}</td>
        <td>${d[4]!}</td>
      </tr>
     [/#list]
  </table>
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无专著成果数据</div>
[/#if]
[@b.foot/]