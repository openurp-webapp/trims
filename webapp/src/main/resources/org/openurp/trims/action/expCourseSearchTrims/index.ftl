<#include "/template/trimsHead.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<table>
  <tr>
    <td width="100" nowrap>
    	<a href="${base}/expCourseSearchTrims.action?method=expCourses" target="expContentFrame">
    		<img src="${base}/static/images/Web/JiaoXueDaGang/SYJX.gif" alt="实验教学" border="0">
    	</a>
    </td>
    <td width="100" nowrap>
    	<a href="${base}/expCourseSearchTrims.action?method=labs" target="expContentFrame">
    		<img src="${base}/static/images/Web/JiaoXueDaGang/SYS.gif" alt="实验室" border="0">
    	</a>
    </td>
    <td width="100" nowrap>
    	<a href="http://www.google.com" target="_blank">
    		<img src="${base}/static/images/Web/JiaoXueDaGang/SYLX.gif" alt="实验教学录像" border="0">
    	</a>
    </td>
  </tr>
</table>
<iframe src="${base}/expCourseSearchTrims.action?method=expCourses" id="expContentFrame" name="expContentFrame" marginwidth="0" marginheight="0" scrolling="no" frameborder="0"  onload="Javascript:SetCwinHeight()" width="100%"></iframe>
</body>
<#include "/template/foot.ftl" />






