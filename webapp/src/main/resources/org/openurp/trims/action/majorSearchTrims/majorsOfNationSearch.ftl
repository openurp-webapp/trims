<#include "/template/trimsHead.ftl" />
<#include "/template/cssOverride.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<@table.table id="id" sortable="true" width="100%"  align="center">
 <@table.thead>
  <@table.td id="teacher.code" text="学科门类代码" width="12%"/>
  <@table.td id="teacher.name" text="学科门类" width="15%"/>
  <@table.td id="teacher.gender.code" text="一级学科代码" width="12%"/>
  <@table.td id="teacher.title.id" text="一级学科" width="15%"/>
  <@table.td id="teacher.department.name" text="二级学科代码" width="12%"/>
  <@table.td id="teacher.workState.name" text="二级学科" width="34%"/>
 </@>
 <@table.tbody datas=majors;major>
  <td>${(major.subject.category.code)!}</td>
  <td>${(major.subject.category.name)!}</td>
  <td>${(major.subject.code)!}</td>
  <td>${(major.subject.name)!}</td>
  <td>
    <#if type='major'>
  		<a href="${base}/majorSearchTrims.action?method=establishInfosOfNationMajor&major.id=${major.id}" target="_parent">${(major.code)!}</a>
  	<#else>
  		<a href="${base}/majorSearchTrims.action?method=descriptionOfNationMajor&major.id=${major.id}" target="_parent">${(major.code)!}</a>
  	</#if>
  </td>
  <td>
  	<#if type='major'>
  		<a href="${base}/majorSearchTrims.action?method=establishInfosOfNationMajor&major.id=${major.id}" target="_parent">${(major.name)!}</a>
  	<#else>
  		<a href="${base}/majorSearchTrims.action?method=descriptionOfNationMajor&major.id=${major.id}" target="_parent">${(major.name)!}</a>
  	</#if>
  </td>
 </@>
</@>
<@htm.actionForm name="actionForm" method="post" entity="major" action="major.action"/>
<#include "/template/foot.ftl" />




