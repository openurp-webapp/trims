<#ftl>
<@b.head/>
<#-- 如果从其他地方点击的这个课程-->
<#if !type??>
	<#include "detailDefault.ftl" />
<#-- 如果是从培养计划中点击的这个课程-->
<#elseif type=="detailInMajor">
	<#include "detailInMajor.ftl" />
<#-- 如果是从教学大纲中点击的这个课程-->
<#elseif type=="detailInOutline">
	<#include "detailInOutline.ftl"/>
<#-- 如果是从多媒体课件中点击的这个课程-->
<#elseif type=="detailInCourseware">
	<#include "detailInCourseware.ftl"/>
<#else>
	<#include "detailDefault.ftl" />
</#if>
<@b.foot/>
