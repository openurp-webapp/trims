<#include "/template/trimsHead.ftl" />

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<br><br>
<#if grades?size == 0>
	<div align="center"><h1>抱歉，没有相关硕、博点设置的信息</h1></div>
<#else>
	<table border='1' cellPadding='1' cellSpacing='0' width='95%' align='center' class="TableNormal" bordercolor='#666666'>
	<#list grades as grade>
	  <tr>
		<td height="40">			
				<img src="${base}/static/images/Web/EnterSearching.gif" border="0" align="absmiddle" hspace="10">
				<a href="${base}/majorSearchTrims.action?method=shuoboOfSchoolInfo&shuoBoDian.grade=${grade}" target="_self">
					${grade}年上海工程技术大学硕、博士点设置一览表
				</a>			
		</td>
	  </tr>
	</#list>
	</table>
</#if>

</body>
<#include "/template/foot.ftl" />









<#--
<#include "/template/head.ftl"/>
<#include "/template/cssOverride.ftl" />

<BODY LEFTMARGIN="0" TOPMARGIN="0">
<div align="middle"><b><font size="3">硕、博点设置一览表</font></b></div><br>
<script language="JavaScript" type="text/JavaScript" src="${base}/static/scripts/baseTrims.js?ver=1"></script>
  <@getMessage/>
	<@table.table id="id" sortable="true" width="90%"  align="center" style="table-layout:fixed">
	 <@table.thead>
	  <@table.td id="shuoBoDian.name" text="名称" width="20%"/>
	  <@table.td id="shuoBoDian.department.id" text="所属学院" width="20%"/>
	  <@table.td id="shuoBoDian.type" text="类型" width="10%"/>	  
	 </@>
	 <#if (shuobodians?if_exists)??>
	 <@table.tbody datas=shuobodians!;shuobodian>
	  
	  <td style="word-wrap:break-word;word-break:break-all;">${(shuobodian.name)?if_exists}</td>
	  <td style="word-wrap:break-word;word-break:break-all;">${(shuobodian.department.name)?if_exists}</td>
	  
	 </@>
	 </#if>	
	</@>
  <@htm.actionForm name="actionForm" method="post" entity="shuoBoDian" action="shuoBoDianTrims.action"/>
  <script language="javascript">
   type="coursewareTrims";
   defaultOrderBy="${Parameters['orderBy']?default('null')}";
  </script>
</body>
<#include "/template/foot.ftl"/>




<#include "/template/trimsHead.ftl" />
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<script language="JavaScript">
</script>
<br>
<table border='1' cellPadding='1' cellSpacing='0' width='95%' align='center' class="TableNormal" bordercolor='#666666'>
  <tr>
	<td>列1</td>
	<td>列2</td>
	<td>列3</td>
	<td>列4</td>
  </tr>
    <tr>
	<td>行1</td>
	<td>行2</td>
	<td>行3</td>
	<td>4</td>
  </tr>
</table>
</body>
<#include "/template/foot.ftl" />

-->




