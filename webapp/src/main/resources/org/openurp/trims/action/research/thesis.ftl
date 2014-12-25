[#ftl]
[@b.head/]
[#include "../nav.ftl"/]
[@b.toolbar title="${teacher.person.name} 科研情况"][/@]
<h4>论文成果</h4>
[#if datas?size gt 0]
  <table class="gridtable">
    <tr>
      <th width="30%">名称</th>
      <th width="10%">字数</th>
      <th width="20%">发表情况</th>
      <th width="10%">成果类型</th>
      <th width="20%">发表范围</th>
      <th width="10%">发表日期</th>
    </tr>
    [#list datas as d]
      <tr>
        <td>${d[0]!}</td>
        <td>${d[1]!}</td>
        <td>${d[2]!}</td>
        <td>${d[3]!}</td>
        <td>${d[4]!}</td>
        <td>${d[5]!}</td>
      </tr>
     [/#list]
  </table>
[#else]
<div style="padding:50px; font-size:20px; text-align:center">暂无论文成果数据</div>
[/#if]
[@b.div href="!literature?id=${id!}"/]
[@b.foot/]