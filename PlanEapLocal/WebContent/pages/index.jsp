<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.xxx.planeap.security.SecurityContextUtil"%>
<%@page import="com.xxx.planeap.domain.User"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./includeJsCss.jsp"/>
<link rel="stylesheet" href="${appPath}/resources/css/index.css">
<link rel="stylesheet" href="${appPath}/plug/zTree/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="${appPath}/resources/js/index.js"></script>
<script type="text/javascript" src="${appPath}/plug/zTree/js/jquery.ztree.all-3.1.min.js"></script>
<title>PlanEap</title>
<script type="text/javascript">
//function f_addTab(title,url,cache) {
//	$("#workspace").addTab(title,PlanEap.getActionURI(url),cache);
//}
$(document).ready(function(){
	$("#workspace").addWelcomeTab("Welcome",PlanEap.getActionURI("welcome"));
});
</script>
</head>
<body class="easyui-layout">
   <div class="north" region="north" border="false">
      <div style="height:52px; width:100%;">
          <div id="logo"></div>
          <div id="sysHelp">
             <span style="margin-right:2px;">
                 <s:a href="javascript:alert('想问什么就问吧，反正我不回答')">帮助</s:a></span>|
             <span style="margin-left:2px;">
                 <s:url id="logout" namespace="" action="logout"></s:url>
                 <s:a href="%{#logout}">注销</s:a>&nbsp;
             </span>
          </div>
      </div>
      <div id="navMenu">
         <ul>
            <s:iterator value="#session.UserModules" var="menu">
              <s:if test="%{#menu.pid==0}">
                 <li><a id="${menu.id}" name="${menu.name}" homeUrl="${menu.homeUrl}"><span>${menu.name}</span></a></li>
              </s:if>
            </s:iterator>
         </ul>
      </div>
   </div>
   <div class="west" region="west" split="true" title="功能导航">
      <ul class="ztree" id="navTree"></ul>
   </div>
   <div region="south" style="height:26px; line-height: 26px; text-align: center; background-color: #EFEFEF" border="false">Copyright © 2011-2012</div>  
   <div class="center" region="center">
      <div id="workspace">
      </div>
   </div>
</body>
</html>