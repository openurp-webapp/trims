<#include "/template/trimsHead.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<div align="center"><b class="LargerText">全国各专业列表</b></div>

<form name="majorsOfNationSearchForm" method="post" action="${base}/majorSearchTrims.action?method=majorsOfNationSearch&type=${type?html}" target="majorNationContentFrame">
	<table class="SearchTable" align="center" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td nowrap="nowrap">
				学科门类
				<@htm.i18nSelect datas=subjectCategories! selected="" 
					name="major.subject.category.id" style="width:100px;" onchange="changeSubjects(this.value)">
   	     			<option value="">...</option>
   	  			</@>
   	  			一级学科
				<@htm.i18nSelect datas=subjects! selected="" name="major.subject.id" style="width:100px;">
   	     			<option value="">...</option>
   	  			</@>
				二级学科名称
				<input name="major.name" maxlength="50" size="10" value="" type="text">
				<input src="${base}/static/images/Buttons/Query.gif" title="查询" align="absmiddle" border="0" type="image">
			</td>
		</tr>
	</tbody>
	</table>
</form>

<script>
	//var categories = new Array();
	var categories = [];
<#list categorySubjects as categorySubject>
	categories[${categorySubject_index}] = {'cate_id':${categorySubject[0].id}, 'cate_name': '${categorySubject[0].name}', 'sub_id': ${categorySubject[1].id}, 'sub_name': '${categorySubject[1].name}' };
</#list>

	function changeSubjects(categoryId) {
		var subjectSelect = document.majorsOfNationSearchForm['major.subject.id'];
		subjectSelect.options.length = 0;
		//subjectSelect.add(new Option('...', ""), null);
		subjectSelect.options[subjectSelect.options.length]=new Option('...', "");
		for(var i=0; i < categories.length; i++) {
			if(categories[i]['cate_id'] == categoryId) {
				//subjectSelect.add(new Option(categories[i]['sub_name'], categories[i]['sub_id']), null);
				subjectSelect.options[subjectSelect.options.length]=new Option(categories[i]['sub_name'], categories[i]['sub_id']);
			}
		}
	}
</script>

<iframe src="${base}/majorSearchTrims.action?method=majorsOfNationSearch&type=${(type!)?html}" id="majorNationContentFrame" name="majorNationContentFrame" marginwidth="0" marginheight="0" scrolling="no" frameborder="0"  onload="Javascript:SetCwinHeight()" width="100%"></iframe>
</body>
<#include "/template/foot.ftl" />




