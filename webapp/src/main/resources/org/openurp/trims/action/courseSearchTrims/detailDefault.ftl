<#ftl>
<div align="center" class="LargerText" style="{line-height: 200%}"><b>课程详细信息</b></div>

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
	<td nowrap class="TdContent">&nbsp;${(course.courseType.name)!}</td>
	<td align="center" width="90" nowrap class="TdLabel">教学环节</td>
	<td nowrap class="TdContent">&nbsp;${(course.extInfo.courseType.theoretical?string('理论教学环节','实践教学环节'))!}</td>
  </tr>
  <tr class='TrNormal'>
	<td align="center" width="90" nowrap class="TdLabel">学分</td>
	<td nowrap class="TdContent">&nbsp;${course.credits?default('')}<#--<#if !course.required>&nbsp;（不计)</#if>--></td>
	<td align="center" width="90" nowrap class="TdLabel">学时</td>
	<td nowrap class="TdContent">&nbsp;总学时：${(course.period)?if_exists}<#--<#if !course.period.required>&nbsp;（不计)</#if>-->
	<#list courseHourTypes?sort_by('code') as courseHourType>
		<#assign courseHour = course.period.getCourseHour(courseHourType)!"0" />
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
			<#assign cellsInRow = 3>
			<#--
			<#list teachObjects as teachObject>
				<td class="TdContent" width="${100/cellsInRow}%">
				&nbsp;<a href="${base}/teachPlanSearchTrims.action?method=plansOfMajor&teachPlan.major.id=${teachObject[0].id!}&teachPlan.isConfirm=true&teachPlan.majorField.id=${(teachObject[1].id)!}" title="查看专业信息">
						${teachObject[0].name!} ${( "[" + teachObject[1].name + "]")!}
					</a>
					
				</td>
				<#if (teachObject_index + 1) % cellsInRow == 0>
			</tr>
			<#if (teachObject_index + 1) != teachObjects?size>
			<tr class="TrNormal">
			</#if>
				</#if>
			</#list>
			<#if teachObjects?size % cellsInRow != 0>
			<#list 1.. (cellsInRow - teachObjects?size % cellsInRow) as i>
				<td class="TdContent" nowrap="nowrap" width="${100/cellsInRow}%"></td>
			</#list>
			</#if>
			-->
			</tr>
		</tbody>
		</table>
	</td>
  </tr>
  <tr class='TrNormal'>
	<td align="center" width="90" nowrap class="TdLabel">开课学院</td>
	<td nowrap class="TdContent" colspan="3">&nbsp;${(course.extInfo.department.name)!}</td>
  </tr>

  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">教学大纲附件</td>
	<td class="TdContent" width="50%" colspan="3">
		<#if outlines?? &&  outlines?size gt 0>
			<img src="${base}/static/images/Attachment.gif" align="absmiddle" border="0">
			<a href="${base}/teachOutlineSearchTrims.action?method=download&teachOutlineIds=${outlines[0].id}">
				大纲下载
			</a>
		<#else>
			&nbsp;未上传该课程大纲
		</#if>
	</td>
<#--
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">课程性质</td>
	<td class="TdContent" nowrap="nowrap">&nbsp;${(course.extInfo.courseType.compulsory?string('必修','选修'))!}</td>
-->
  </tr>
  <tr class="TrNormal">
  	<td align="center" width="90" nowrap class="TdLabel">课程简介</td>
  	<td class="TdContent" width="50%" colspan="3">${(course.extInfo.description)!}</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">任课教师</td>
	<td class="TdContent" width="50%" colspan="3">
		&nbsp;<#list teachers as t><#if t_index gt 0>、</#if>${t.name}</#list>
	</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">教材教参</td>
	<td colspan="3" class="TdContent" width="100%">
	<#--
	<#list textbooks as textbook>
		<a href='${base}/textBookSearchTrims.action?method=info&textbookId=${textbook.id}'>
			&nbsp;${textbook.name}
		</a><br>
	</#list>
	-->
	</td>
  </tr>
  <tr class="TrNormal">
	<td class="TdLabel" align="center" nowrap="nowrap" width="90">推介书籍</td>
	<td colspan="3" class="TdContent" width="100%">
		&nbsp;
		<#--
	<#list readings as reading>
		<a href="${base}/readingSearchTrims.action?method=info&readingBookId=${reading.id}"> 《${reading.name}》</a>&nbsp;
	</#list>
		-->
	</td>
  </tr>
  <#if courseSiteUrl??>
  <tr class="TrNormal">
  <td class="TdLabel" align="center" nowrap="nowrap" width="90">课程网站</td>
  <td colspan="3" class="TdContent" width="100%">
    &nbsp;<a href="${courseSiteUrl}" target="_blank">${courseSiteUrl}</a>
  </td>
  </tr>
  </#if>
</table>
<br>
<div align="center">
	<a href="javascript:history.back();"><button>返回</button></a>
	<br><br>
</div>
