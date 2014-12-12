[#ftl]
[@b.head title="本科学院专业设置一览表"/]
[#assign base=request.contextPath/]
<script language="JavaScript" type="text/JavaScript" src="${base}/static/scripts/frame.js"></script>
<table>
  <tr>
    <td width="100" nowrap>[@b.a href="!majorsOfSchool?type=major" target="majorContentFrame"]<button>专业设置</button>[/@]</td>
  </tr>
</table>
[@b.div id="majorContentFrame"][/@]
[@b.foot/]




