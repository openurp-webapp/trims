<#include "/template/trimsHead.ftl" />
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<#-- 如果从其他地方点击的这个课程-->
<#if !type??>
	<#include "detailDefault.ftl" />
<#-- 如果是从培养计划中点击的这个课程-->
<#elseif type=="detailInMajor">
	<#include "detailInMajor.ftl" />
<#-- 如果是从教学大纲中点击的这个课程-->
<#elseif type=="detailInOutline">
	<#include "detailInOutline.ftl"/>
<#else>
	<#include "detailDefault.ftl" />
</#if>
</body>
<#include "/template/foot.ftl" />
