<%@ Language=VBScript %>
<% Option Explicit %>
<% Response.Buffer = True %>

<!--#include file="../Lib/Global.asp"-->
<!--#include file="../Lib/DBConfig.asp"-->

<html><!-- #BeginTemplate "/Templates/Web.dwt" --><!-- DW6 -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title><%=SITE_NAME%> -- 专业设置</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="${base}/static/css/Web.css" type="text/css">
<script language="JavaScript" src="${base}/static/scripts/WebMenu.js"></script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" class="ShowWatermark">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" height="100%">
  <tr>
	<td>
	  <table width=100% border=0 cellpadding=0 cellspacing=0 height="100%">
		<tr>
		  <td> <img src="${base}/static/images/Web/top_01.gif" width=729 height=116></td>
		  <td width="100%" background="${base}/static/images/Web/top_02.gif"></td>
		  <td> <img src="${base}/static/images/Web/top_03.gif" width=44 height=116></td>
		</tr>
	  </table>
	</td>
  </tr>
  <tr>
	<td height="100%" valign="top">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr> 
          <td background="${base}/static/images/Web/1_02.gif" width="143" valign="top"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="MenuTable">
              <tr> 
                <td></td>
                <td><img src="${base}/static/images/Web/1_01.gif" width="131" height="10"></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>			  
              <tr> 
                <td width="12">&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="Default.asp">首　　页</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="ZhuanYeSheZhi.asp">专业设置</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="PeiYangFangAn.asp">培养方案</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="KeChengSheZhi.asp">课程总览</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="JiaoXueDaGang.asp">教学大纲</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="ShiYanJiaoXue.asp">实验教学</a></td>
              </tr>
			  <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="ShiZiDuiWu.asp">师资队伍</a></td>
              </tr>
			  <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="JingPinKeCheng.asp">精品课程</a></td>
              </tr>
			  <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="DuoMeiTiKeJian.asp">多媒体课件</a></td>
              </tr>
			  <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="BiYeLunWen.asp">毕业论文</a></td>
              </tr>
			   <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="JiaoXueZhouLi.asp">教学方案</a></td>
              </tr>
			  <!--
			  <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="../Web/KeChengBiao.asp">课程表&nbsp;&nbsp;</a></td>
              </tr>
			  //-->
              
              
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="JiaoCaiJiaoCan.asp">教材教参</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="XinShuTuiJie.asp" target='_blank'>新书推介</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666" align="center"></td>
              </tr>
              <tr> 
                <td>&nbsp;</td>
                <td align="center" id="MenuItem"><img src="${base}/static/images/Web/ItemMark.gif" width="11" height="11" align="absmiddle">　<a class="MenuItem" href="KeWaiYueDu.asp" target='_blank'>课外阅读</a></td>
              </tr>
              <tr> 
                <td height="1"></td>
                <td bgcolor="#666666"></td>
              </tr>
              <tr> 
                <td colspan="2" height="10"></td>
              </tr>			  
            </table>
          </td>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td>&nbsp;</td>
                <td><!-- #BeginEditable "neirong" -->

<script language="JavaScript" src="${base}/static/scripts/Paginate.js"></script>
<script language="JavaScript">
<!--
SetCurrentMenuItem(MI_ZhuanYeSheZhi);
//-->
</script>
<table>
  <tr>
    <td width="100" nowrap><a href="ZhuanYeSheZhi.asp"><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ.gif" alt="专业设置" border="0"></a></td>
    <td width="100" nowrap><a href="ZhuanYe_ShuoBo.asp"><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_SB.gif" alt="硕、博点设置" border="0"></a></td>
    <td width="100" nowrap><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_QG_Selected.gif" alt="全国专业设置" border="0"></td>
    <td width="100" nowrap><a href="ZhuanYe_QuanGuoShuoBo.asp"><img src="${base}/static/images/Web/ZhuanYeSheZhi/ZYSZ_SB_QG.gif" alt="全国硕、博点设置" border="0"></a></td>
  </tr>
</table>
<%
	Dim nId
	Dim conMIS
	Dim sqlGaoXiao, rsGaoXiao
	Dim sZhuanYeName
	
	nId = CLng(g_GetVerifiedValue(Request("Id"), -1))
	
	Set conMIS = Server.CreateObject("ADODB.Connection")
	conMIS.Open g_strDbConnection
	
	sZhuanYeName = g_GetValueFromSQL(conMIS, "Select ZhuanYeMing From ZhuanYe_QuanGuoJieShao Where Id = " & nId)

	sqlGaoXiao = "SELECT GaoXiaoMing, XueZhi, XueWei, YuanXi, KaiSheShiJian, ShengShiMing, ZhuGuanMing, DiQuMing"
	sqlGaoXiao = sqlGaoXiao & " From ZhuanYe_QuanGuoSheZhi Where ZhuanYeId = " & nId & " Order By DiQuMing ASC"
	
	Set rsGaoXiao = Server.CreateObject("ADODB.RecordSet")
	rsGaoXiao.Open sqlGaoXiao, conMIS, 0, 1
	
	If Not rsGaoXiao.EOF Then
%>
<div width="95%" align="center" class="hasMargin"><font class="LargerText">开设<b><%=sZhuanYeName%></b>专业的主要学校列表</font></div>
<table class='TableNormal' border='1' cellPadding='1' cellSpacing='0' bordercolor='#666666' width='95%' align='center'>
  <tr class='TrHead'>
	<td align='middle' nowrap>高校名称</td>
	<td align='middle' nowrap>学制</td>
	<td align='middle' nowrap>学位</td>
	<td align='middle' nowrap>院系</td>
	<td align='middle' nowrap>开设时间</td>
	<td align='middle' nowrap>省市名称</td>
	<td align='middle' nowrap>主管单位</td>
	<td align='middle' nowrap>地区名</td>
  </tr>
<%
		Do While Not rsGaoXiao.EOF
%>
  <tr>
    <td><%=rsGaoXiao("GaoXiaoMing")%></td>
    <td><%=rsGaoXiao("XueZhi")%></td>
    <td><%=rsGaoXiao("XueWei")%></td>
    <td><%=rsGaoXiao("YuanXi")%></td>
    <td><%=rsGaoXiao("KaiSheShiJian")%></td>
    <td><%=rsGaoXiao("ShengShiMing")%></td>
    <td><%=rsGaoXiao("ZhuGuanMing")%></td>
    <td><%=rsGaoXiao("DiQuMing")%></td>
  </tr>
<%
			rsGaoXiao.MoveNext
		Loop
%>
</table>
<%
	End If

	rsGaoXiao.Close
	Set rsGaoXiao = Nothing
	
	conMIS.Close
	Set conMIS = Nothing
%>
<br>
<div align="center">
  <a href="javascript:history.back();"><img src="${base}/static/images/Buttons/Return.gif" border="0"></a>
	<br><br>
</div>
<br>
				
				<!-- #EndEditable --></td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	</td>
  </tr>
  <tr>
	<td height="1" bgcolor="#666666"></td>
  </tr>
  <tr>
	<td bgcolor="#eeeeee" class="TailText" height="24" align="center"> 上海工程技术大学教务处版权所有　　E-Mail：<a href="mailto:gcd@sues.edu.cn">gcd@sues.edu.cn</a><br>
		<a href="http://www.ekingstar.com" class="CompanyUrl" target="_blank">金仕达多媒体<sup>&reg;</sup></a>提供技术支持
	</td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>






