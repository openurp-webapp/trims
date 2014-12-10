[#ftl]
[@b.head/]
[#assign base=request.contextPath/]
<script language="JavaScript" type="text/JavaScript" src="${base}/static/scripts/frame.js"></script>

[#--<img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_QG_Selected.gif" alt="全国专业设置" border="0">--]
<table>
  <tr>
    <td width="100" nowrap>[@b.a href="${base}/majorSearchTrims.action?method=majorsOfSchool&type=major" target="majorContentFrame"]<img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ.gif" alt="专业设置" border="0">[/@]</td>
    [#--
    <td width="100" nowrap><a href="${base}/majorSearchTrims.action?method=shuoboOfSchool" target="majorContentFrame"><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_SB.gif" alt="硕、博点设置" border="0"></a></td>
    <td width="100" nowrap><a href="${base}/majorSearchTrims.action?method=majorsOfNation&type=major" target="majorContentFrame"><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_QG.gif" alt="全国专业设置" border="0"></a></td>
    <td width="100" nowrap><a href="${nation_master_url}" target="_blank"><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_SB_QG.gif" alt="全国硕、博点设置" border="0"></a></td>
    --]
  </tr>
</table>
<iframe src="${base}/majorSearchTrims.action?method=majorsOfSchool&type=major" id="majorContentFrame" 
	name="majorContentFrame" marginwidth="0" marginheight="0" scrolling="no" 
	frameborder="0" width="100%" onload="Javascript:SetCwinHeight()">
</iframe>
</body>
[@b.foot/]




