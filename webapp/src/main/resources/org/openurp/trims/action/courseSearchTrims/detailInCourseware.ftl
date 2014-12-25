<#include "/template/head.ftl"/>
<#include "/template/cssOverride.ftl" />
<#if coursewares?exists && coursewares?size != 0>
<BODY LEFTMARGIN="0" TOPMARGIN="0">
<script language="JavaScript" type="text/JavaScript" src="${base}/static/scripts/baseTrims.js?ver=1"></script>
  <@getMessage/>
  <div align="center"><b class="LargerText">课件信息</b><font color="red">(点击名称进行下载)</font></div><br>
	<@table.table id="id" sortable="true" width="90%"  align="center" style="table-layout:fixed">
	 <@table.thead>
	  <@table.td id="courseware.seqNo" text="编号" width="20%"/>
	  <@table.td id="courseware.name" text="名称" width="20%"/>
	  <@table.td id="courseware.course.name" text="适用课程" width="20%"/>
	  <@table.td id="courseware.type" text="文件类型" width="10%"/>
	  <@table.td id="courseware.developer" text="开发者" width="15%"/>
	  <@table.td id="courseware.department.name" text="院系" width="15%"/>
	</@>
	<@table.tbody datas=coursewares!;courseware>
	  <td style="word-wrap:break-word;word-break:break-all;"><a href="${base}/coursewareSearchTrims.action?method=download&courseware.id=${courseware.id!}">${(courseware.seqNo)!}</a></td>
	  <td style="word-wrap:break-word;word-break:break-all;"><a href="${base}/coursewareSearchTrims.action?method=download&courseware.id=${courseware.id!}">${(courseware.name)!}</a></td>
	  <td style="word-wrap:break-word;word-break:break-all;">${(courseware.course.name)!}</td>
	  <td style="word-wrap:break-word;word-break:break-all;">${(courseware.type)!}</td>
	  <td style="word-wrap:break-word;word-break:break-all;">${(courseware.developer)!}</td>
	  <td style="word-wrap:break-word;word-break:break-all;">${(courseware.department.name)!}</td>
	 </@>
	</@>
	<br><br>
<div align="center">	
	<a href="javascript:history.back();"><img src="${base}/static/images/Buttons/Return.gif" border="0"></a>
	<br><br>
</div>
</#if>

<#if !(coursewares?exists) || coursewares?size == 0>
	<br><br>
	<div align="center">
		<font color="red" size="4">对不起，没有找到属于该课程的课件～</font>
	</div><br>
	<div align="center" id="message"></div>
	<br>
	<div align="center">	
		<a href="javascript:history.back();"><img src="${base}/static/images/Buttons/Return.gif" border="0"></a>
		<br><br>
	</div>		
	<script>		
		var time = 0;
		var keyWord = escape("${name}");											
		forward_url="http://ecourse.sues.edu.cn/course_center/course/course_search/course_search.jsp?keyWord="+keyWord+"&"+ Math.random();	
		function forward_to(){			
    		time ++;    		
     		if(time == 3){     			   			   
      	    	window.open(forward_url);
     		}
     		if(time<=3){     			
     			document.getElementById("message").innerHTML = "您可以选择返回      或者      3 秒后自动跳转到 <a href="  +forward_url+ ">上海工程技术大学本科教学课程平台</a>";
			}else{
				document.getElementById("message").innerHTML = "<a href="  +forward_url+ " target='_blank'>点击此处进入</a>    上海工程技术大学本科教学课程平台";
			}
		}	
		window.setInterval("forward_to()",1000);
			
	</script>
</#if>
</body>
<#include "/template/foot.ftl" />


