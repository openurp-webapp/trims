<#include "/template/trimsHead.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<div align="center"><b class="LargerText">全校实验室（实习基地）一览表</b></div>

<table class="TableNormal" border='1' cellPadding='1' cellSpacing='0' bordercolor='#666666' width="100%" align='center'>
  <tr class='TrHead'>
	<td align='center' nowrap width="60%">实验室（实习基地）名称</td>
	<td align='center' nowrap width="40%">所属院系（系）</td>
  </tr>
  <#list labs! as lab>
  <tr class='TrNormal'>
	<td align='center' nowrap width="60%"><a href="${(lab.url)!}" target="_blank">${(lab.name)!}</a></td>
	<td align='center' nowrap width="40%">${(lab.department.name)!}</td>
  </tr>
  </#list>
</table>

</body>
<#include "/template/foot.ftl" />