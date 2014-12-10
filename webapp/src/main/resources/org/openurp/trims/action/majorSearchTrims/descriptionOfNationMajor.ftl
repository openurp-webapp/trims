<#include "/template/trimsHead.ftl" />
<#include "/template/cssOverride.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<div width="95%" align="center" class="hasMargin"><font class="LargerText"><b>${major.name}</b>(${major.code})</font></div>
<table class='TableNormal' border='1' cellPadding='1' cellSpacing='0' bordercolor='#666666' width='95%' align='center'>
	<tr class='TrNormal'>

		<td align="center" nowrap class="TdLabel" width="90">门类代码</td>
		<td nowrap class="TdContent" width="50%">&nbsp;${(major.subject.category.code)!}</td>
		<td align="center" nowrap class="TdLabel" width="90">门类名称</td>
		<td nowrap class="TdContent" width="50%">&nbsp;${(major.subject.category.name)!}</td>
	</tr>
	<tr class='TrNormal'>
		<td align="center" nowrap class="TdLabel">二级门类代码</td>
		<td nowrap class="TdContent">&nbsp;${(major.subject.code)!}</td>
		<td align="center" nowrap class="TdLabel">二级门类名称</td>
		<td nowrap class="TdContent">&nbsp;${(major.subject.name)!}</td>
	</tr>
	<tr class='TrNormal'>
		<td align="center" nowrap class="TdLabel">专业介绍</td>
		<td class="TdContent" colspan="3">${(major.introduction)!}</td>
	</tr>
	<tr>
		<td align="right" colspan="4">
			<a href="${base}/majorSearchTrims.action?method=establishInfosOfNationMajor&major.id=${major.id}">开设此专业的学校列表</a>
		</td>
	</tr>
</table>

<div align="center">
	<a href="javascript:history.back();"><img src="${base}/static/images/Buttons/Return.gif" border="0"></a>
	<br><br>
</div>

<#include "/template/foot.ftl" />





