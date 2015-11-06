[@b.head/]
<div style="text-align:center;">
  <div align="center"><b class="LargerText">全校课程信息一览表</b>(点击查看详细信息)</div>
  [@b.form name="courseSearchForm" action="!search" target="courselist" title="ui.searchForm"]
    课程编号：<input name="course.code" maxlength="15" size="8" value="" type="text"/>
    课程名称：<input name="course.name" maxlength="50" size="8" value="" type="text"/>
    [#--
    知识点：<input name="course.extInfo.description" maxlength="50" size="8" value="" type="text"/>
    开课院系
    [@b.select items=departments selected="" name="course.department.id" style="width:100px;"]
      <option value="">...</option>
    [/@]
    --]
    [@b.submit value="查询"/]
  [/@]
</div>
[@b.div id="courselist" href="!search" /]
[@b.foot/]
[#--
<div align="center"><b class="LargerText">全校课程信息一览表</b>(点击查看详细信息)</div>

<form name="courseSearchForm" method="post" action="${b.url('!search')}" target="courseContentFrame">
	<table class="SearchTable" align="center" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td nowrap="nowrap">
				课程编号：<input name="course.code" maxlength="15" size="8" value="" type="text"/>
				课程名称：<input name="course.name" maxlength="50" size="8" value="" type="text"/>
				知识点：<input name="course.extInfo.description" maxlength="50" size="8" value="" type="text"/>
				开课院系
				[@b.select items=departments selected="" name="course.extInfo.department.id" style="width:100px;"]
   	    	<option value="">...</option>
   	  	[/@]
				<input src="${base}/static/images/Buttons/Query.gif" title="查询" align="absmiddle" border="0" type="image">
			</td>
		</tr>
	</tbody>
	</table>
</form>

<iframe src="#" id="courseContentFrame" name="courseContentFrame" marginwidth="0" marginheight="0" scrolling="no" frameborder="0"  onload="Javascript:SetCwinHeight()" width="100%"></iframe>
<script>document.courseSearchForm.submit()</script>
[@b.foot/]
--]