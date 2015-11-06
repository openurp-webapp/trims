<#include "/template/trimsHead.ftl" />
<#include "/template/cssOverride.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
  <@table.table id="listTable" width="100%" sortable="true"  align="center">
    <@table.thead>
        <@table.sortTd name="attr.code" id="course.code"/>
        <@table.sortTd name="common.name" id="course.name"/>
        <@table.sortTd name="educationType" id="course.educationType.name"/>
        <@table.sortTd name="studentType" id="stdType.name"/>
        <@table.sortTd name="course.credits" id="course.credits"/>        
        <@table.sortTd name="course.period" id="course.period.period"/>
        <@table.sortTd name="course.weekHour" id="course.period.weekHour"/>
        <@table.sortTd text="周数" id="course.period.weeks"/>
        <#list courseHourTypes?sort_by('code') as courseHourType>
        	<td>${courseHourType.name}</td>
    	</#list>
        <@table.sortTd name="examMode" id="course.extInfo.examMode.name"/>
     </@>
     <@table.tbody datas=courses;course>
        <td>${course.code}</td>
        <td><a href="courseSearchTrims.action?method=detail&course.id=${course.id}&type=${type!}" target="_parent"><@i18nName course/></a></td>
        <td><@i18nName (course.educationType)?if_exists/></td>
        <td><#if course.stdType?exists><@i18nName course.stdType/></#if></td>
        <td>${course.credits?if_exists}</td>
        <td>${(course.period.period)?if_exists}</td>
        <td>${(course.period.weekHour)?if_exists}</td>
        <td>${(course.period.weeks)?if_exists}</td>
        <#list courseHourTypes?sort_by('code') as courseHourType>
        <td>${course.period.getCourseHour(courseHourType)?if_exists}</td>
    	</#list>
        <td>${(course.extInfo.examMode.name)?if_exists}</td>
     </@>
   </@>
</body>
<#include "/template/foot.ftl" />