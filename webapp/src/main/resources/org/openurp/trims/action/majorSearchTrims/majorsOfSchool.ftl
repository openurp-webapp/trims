<#include "/template/trimsHead.ftl" />
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tbody><tr>
	<td align="middle">
		<b class="LargerText">
		<#if type=="major">本科学院专业设置一览表
		<#elseif type=="plan">培养方案
		<#elseif type=="textbook">使用教材
		<#elseif type=="thesis">毕业论文
		</#if></b>
	</td>
  </tr>
  <tr>
	<td align="right" nowrap="nowrap"><b>(共<font class="CountText">${sum}</font>个)</b></td>

  </tr>
</tbody></table>

<table class="TableHighLight"
	align="center" border="1" bordercolor="#666666" 
	cellpadding="1" cellspacing="0" width="100%"
>
<tbody>
  	<tr class="TrHead">
		<td align="middle" nowrap="nowrap" width="13%">学院</td>
		<td align="middle" nowrap="nowrap" width="10%">专业代码</td>
		<td align="middle" nowrap="nowrap" width="40%">专业名称</td>
		<td align="middle" nowrap="nowrap" width="10%">培养类型</td>
		<td align="middle" nowrap="nowrap" width="5%">学制</td>
		<td align="middle" nowrap="nowrap" width="10%">学科门类</td>
		<td align="middle" nowrap="nowrap" width="12%">设立时间</td>
	</tr>
	<#list departMajorFieldMap?keys as departCode>
	<tr class="TrNormal" style="">
		<td rowspan="${departMajorFieldMap[departCode]?size}" class="TrNormal" style="cursor: default;" align="center">
			${departMajorFieldMap[departCode]?first[0].name}
		</td>
	<#list departMajorFieldMap[departCode] as departMajorAndField>
		<#if departMajorAndField_index != 0><tr></#if>
		
		<#assign major=departMajorAndField[1] />
		<#assign majorField=departMajorAndField[2]! />
		
		<td align="center" nowrap="nowrap">${major.code}</td>
		<td title="" align="left" class="TrNormal">
		<#if type=="textbook">
			<#assign condition="&major.id=" +major.id?string />
			<#assign condition=condition+"&majorField.id=" + (majorField.id?string)!''/>
			<a href="${base}/textBookSearchTrims.action?method=textbooksOfMajor&${condition}" target="_parent">
				${major.name}${("&nbsp;[" + majorField.name + "]")!}
			</a>
		<#elseif type=="thesis">
			<#assign condition="department.id=" + departMajorFieldMap[departCode]?first[0].id?string />
			<#assign condition=condition+"&major.id=" +major.id?string />
			<#assign condition=condition+"&majorField.id=" + (majorField.id?string)!''/>
			<a href="${base}/thesisSearchTrims.action?method=thesisOfMajor&${condition}" target="_self">
				${major.name}${("&nbsp;[" + majorField.name + "]")!}
			</a>
		<#else>
			<#assign condition="teachPlan.department.id=" + departMajorFieldMap[departCode]?first[0].id?string />
			<#assign condition=condition+"&teachPlan.major.id=" +major.id?string />
			<#assign condition=condition+"&teachPlan.isConfirm=true"/>
			<#assign condition=condition+"&teachPlan.majorField.id=" + (majorField.id?string)!''/>
			
			<a href="${base}/teachPlanSearchTrims.action?method=plansOfMajor&${condition}" target="_parent">
				${major.name}${("&nbsp;[" + majorField.name + "]")!}
			</a>
		</#if>
		
		</td>
		<td align="center" nowrap="nowrap">
			<#list major.educationTypes?if_exists as educationType><@i18nName educationType/>&nbsp;</#list>
		</td>
		<td align="center" nowrap="nowrap">
			<#if major.educationTypes??>
				<#if (major.educationTypes[0].id)! == 1>4年<#else>3年</#if>
			</#if>
		</td>
		<td align="center" nowrap="nowrap">${(major.subject.category.name)!}</td>
		<td align="center" nowrap="nowrap">${(major.createdAt?string("yyyy年MM月"))!}</td>
	</#list>
	</tr>
	</#list>
</tbody>
</table>
<#--
<table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
	<tbody>
	<tr>
		<td align="right" nowrap="nowrap">(备注：专业名称后加<font class="HighlightText">*</font>者代表该专业被多个校区共有<b>)</b></td>
	</tr>
	</tbody>
</table>
-->
</body>
<#include "/template/foot.ftl" />