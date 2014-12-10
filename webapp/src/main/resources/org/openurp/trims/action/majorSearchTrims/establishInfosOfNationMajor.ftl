<#include "/template/trimsHead.ftl" />
<#include "/template/cssOverride.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<div align="center"><font class="LargerText">开设<b>${(major.name)!}</b>专业的主要学校列表</font></div>

<@table.table id="id" sortable="true" width="90%"  align="center">
 <@table.thead>
  <@table.td width="15%" id="establishInfo.schoolName" text="高校名称"/>
  <@table.td width="5%" id="establishInfo.lengthOfSchooling" text="学制"/>
  <@table.td width="10%" id="establishInfo.degreeName" text="学位"/>
  <@table.td width="15%" id="establishInfo.departmentName" text="院系"/>
  <@table.td width="10%" id="establishInfo.establishedAt" text="开设时间"/>
  <@table.td width="15%" id="establishInfo.province" text="省市名称"/>
  <@table.td width="15%" id="establishInfo.managedBy" text="主管单位"/>
  <@table.td width="15%" id="establishInfo.district" text="地区名"/>
 </@>
 <@table.tbody datas=establishInfos;establishInfo>
  <td width="15%">${(establishInfo.schoolName)!}</td>
  <td width="5%">${(establishInfo.lengthOfSchooling)!}</td>
  <td width="10%">${(establishInfo.degreeName)!}</td>
  <td width="15%">${(establishInfo.departmentName)!}</td>
  <td width="10%">${(establishInfo.establishedAt)!}</td>
  <td width="15%">${(establishInfo.province)!}</td>
  <td width="15%">${(establishInfo.managedBy)!}</td>
  <td width="15%">${(establishInfo.district)!}</td>
 </@>
</@>

<@htm.actionForm name="actionForm" method="post" entity="establishInfo" action="major.action"/>
<div align="center">
	<a href="javascript:history.back();"><img src="${base}/static/images/Buttons/Return.gif" border="0"></a>
	<br><br>
</div>

<#include "/template/foot.ftl" />





