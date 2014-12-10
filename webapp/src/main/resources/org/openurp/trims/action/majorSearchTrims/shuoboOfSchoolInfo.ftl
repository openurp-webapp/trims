<#include "/template/trimsHead.ftl" />
<BODY LEFTMARGIN="0" TOPMARGIN="0">
<script language="JavaScript" type="text/JavaScript" src="${base}/static/scripts/baseTrims.js?ver=1"></script>
  <div align="center"><font class="LargerText"><b>硕、博士点设置一览表 </b></font></div>
  <br> 
  <table class="TableHighLight" align="center" border="1" bordercolor="#666666" cellpadding="1" cellspacing="0" width="100%" style="table-layout:fixed">
  <tbody> 
  	<tr class="TrHead">
  	  <td align="middle" width="10%">学院代码</td>
  	  <td align="middle" width="15%">学院</td>
  	  <td align="middle" width="10%">专业代码</td>
  	  <td align="middle" width="30%">专业名称</td>
  	  <td align="middle" width="13%">研究方向</td>
  	  <td align="middle" width="11%">硕士点</td>
  	  <td align="middle" width="11%">博士点</td>   	  	 
	</tr> 
	 <#if (shuobodiansmap?if_exists)??>
	 <#list shuobodiansmap?keys as shuobodiancode>
	  <tr>
	  <td align="center" rowspan="${shuobodiansmap[shuobodiancode]?size}">${shuobodiancode}</td>
	  <td align="center" rowspan="${shuobodiansmap[shuobodiancode]?size}">${shuobodiansmap[shuobodiancode]?first.department.name}</td>	  
	  
	  <#list shuobodiansmap[shuobodiancode] as shuobodian>	
	  	<#if shuobodian_index != 0><tr></#if> 
		  <td align="center" style="word-wrap:break-word;word-break:break-all;">${(shuobodian.specialitycode)?if_exists}</td>
		  <td align="center" style="word-wrap:break-word;word-break:break-all;">
		  <#if (shuobodiansmap[shuobodiancode]?first.url)??>
		  	<a href="${(shuobodiansmap[shuobodiancode]?first.url)!}" target="_blank">${(shuobodian.specialityname)?if_exists}</a>
		  <#else>
		  	${(shuobodian.specialityname)?if_exists}
		  </#if>
		  </td>
		  <td align="center" style="word-wrap:break-word;word-break:break-all;">${(shuobodian.research)?if_exists}</td>
		  <td align="center" style="word-wrap:break-word;word-break:break-all;">${(shuobodian.master)?if_exists} </td>
		  <td align="center" style="word-wrap:break-word;word-break:break-all;">${(shuobodian.doctoral)?if_exists}</td>
	  	<#if shuobodian_index != 0></tr></#if>
	  </#list>
	  
	   </tr>
	 </#list>
	 </#if>
	</tbody>
	</table>
  <@htm.actionForm name="actionForm" method="post" entity="shuoBoDian" action="shuoBoDianTrims.action"/>
  <script language="javascript">
   type="labTrims";
   defaultOrderBy="${Parameters['orderBy']?default('null')}";
  </script>
</body>
<#include "/template/foot.ftl"/>
