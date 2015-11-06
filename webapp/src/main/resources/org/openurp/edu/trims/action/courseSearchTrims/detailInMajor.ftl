<#include "/template/trimsHead.ftl" />
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">

<#assign major=teachPlan.major />
<#assign majorField=teachPlan.majorField! />
<#assign course=planCourse.course />
<div align="center" class="LargerText" style="{line-height: 200%}"><b>${major.name!}${("(" + majorField.name + ")")!}  专业课程信息</b></div>

<table class="TableNormal" align="center" border="1" bordercolor="#666666" cellpadding="1" cellspacing="0" width="95%">
  <tr class='TrNormal' height="40">
    <td align="center" width="90" nowrap class="TdLabel">课程名称</td>
	<td nowrap width="50%" class="TdContent">&nbsp;<b><font class="LargerText">${course.name}</font></b></td>
	<td align="center" width="90" nowrap class="TdLabel">课程编号</td>
	<td nowrap width="50%" class="TdContent">&nbsp;<b><font class="LargerText">${course.code}</font></b></td>
  </tr>
  <tr class='TrNormal'>
	<td align="center" width="90" nowrap class="TdLabel">英文名</td>
	<td nowrap colspan="3" class="TdContent">&nbsp;${course.engName!}</td>
  </tr>
  <tr class='TrNormal'>
	<td align="center" width="90" nowrap class="TdLabel">课程类别</td>
	<td nowrap class="TdContent">&nbsp;<@i18nName course.extInfo.courseType!/></td>
	<td align="center" width="90" nowrap class="TdLabel">教学环节</td>
	<td nowrap class="TdContent">&nbsp;${(course.extInfo.courseType.theoretical?string('理论教学环节','实践教学环节'))!}</td>
  </tr>
  <tr class='TrNormal'>
	<td align="center" width="90" nowrap class="TdLabel">学分</td>
	<td nowrap class="TdContent">&nbsp;${course.credits?default('')}<#if !course.required>&nbsp;（不计)</#if></td>
	<td align="center" width="90" nowrap class="TdLabel">学时</td>
	<td nowrap class="TdContent">&nbsp;总学时：${(course.period.period)?if_exists}<#if !course.period.required>&nbsp;（不计)</#if>
	<#list courseHourTypes?sort_by('code') as courseHourType>
		&nbsp;${courseHourType.name}:${course.period.getCourseHour(courseHourType)!"0"}
    </#list>
	</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">授课对象</td>
	<td colspan="3" class="TdContent" width="100%">
		<table class="TableNormal" align="center" border="1" bordercolor="#666666" cellpadding="0" cellspacing="0" frame="void" width="100%">
		<tbody>
			<tr class="TrNormal">
				<td class="TdContent" width="25%">
					&nbsp;${major.name!}${("(" + majorField.name + ")")!}</a>
				</td>
			</tr>
		</tbody>
		</table>
	</td>
  </tr>
  <tr class='TrNormal'>
	<td align="center" width="90" nowrap class="TdLabel">开课院系</td>
	<td nowrap class="TdContent" colspan="3">&nbsp;${(course.extInfo.department.name)!}</td>
  </tr>

  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">开课学期</td>
	<td class="TdContent" nowrap="nowrap">&nbsp;<#assign terms= planCourse.terms />
			<#if planCourse.terms?starts_with(',')><#assign terms=terms?substring(1)/></#if>
			<#if planCourse.terms?ends_with(',')><#assign terms=terms?substring(0, terms?length-1)/></#if>
			<#assign terms=terms?split(',') />
			<#list terms?sort as term>${term}<#if term_index != terms?size - 1>,</#if></#list>
	</td>
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">课程性质</td>
	<td class="TdContent" nowrap="nowrap">&nbsp;${planCourse.courseGroup.courseType.compulsory?string('必修','选修')}</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">任课教师</td>
	<td class="TdContent" width="50%">
		&nbsp;<@teachersHref teachers />
	</td>
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">教学大纲附件</td>
	<td class="TdContent" width="50%">
		<#if outlines?size &gt; 0>
			<img src="${base}/static/images/Attachment.gif" align="absmiddle" border="0">
			<a href="${base}/teachOutlineSearchTrims.action?method=download&teachOutlineIds=${outlines[0].id}">
				大纲下载
			</a>
		<#else>
			&nbsp;未上传该课程大纲
		</#if>
	</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">教材教参</td>
	<td colspan="3" class="TdContent" width="100%">
	<#list textbooks as textbook>
		<a href='${base}/textBookSearchTrims.action?method=info&textbookId=${textbook.id}'>
			&nbsp;${textbook.name}
		</a><br>
	</#list>
	</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">推介书籍</td>
	<td colspan="3" class="TdContent" width="100%">
		&nbsp;
	<#list readings as reading>
		<a href="${base}/readingSearchTrims.action?method=info&readingBookId=${reading.id}"> 《${reading.name}》</a>&nbsp;	</#list>
	</td>
  </tr>
</table>
<br>
<div align="center">
	<a href="${base}/courseSearchTrims.action?method=detail&course.id=${course.id}"><img src="${base}/static/images/Buttons/KeChengXiangXi.gif" border="0"></a>&nbsp;
	<a href="javascript:history.back();"><img src="${base}/static/images/Buttons/Return.gif" border="0"></a>
	<br><br>
</div>
</body>
<#include "/template/foot.ftl" />





