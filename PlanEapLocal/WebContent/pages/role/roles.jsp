<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.xxx.common.Pager"%>
<%@page import="java.util.List"%>
<%@page import="com.xxx.planeap.domain.Role"%>
<%@page import="net.sf.json.JSONObject"%>
<%
Pager<List<Role>> pager = (Pager<List<Role>>)request.getAttribute("pager");
JSONObject data = JSONObject.fromObject(pager);
%>
<script type="text/javascript" src="${appPath}/resources/js/role/Role.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		Role.init("#roleGird",<%=data%>,"#roleWindow","#grantWin");
	});
</script>
<style>
<!--
   .inp{
      width: 120px;
   }
   .row{
      margin-top: 10px;
      height: 24px;
      line-height: 24px;
      vertical-align: middle;
   }
   .columnTitle{
      margin-left: 30px;
      float: left;
      height: 24px;
      line-height: 24px;
      width: 80px;
      vertical-align: middle;
   }
   .columnContent{
      float: left;
      height: 24px;
      line-height: 24px;
      width: 130px;
      vertical-align: middle;
   }
-->
</style>
<table id="roleGird"></table>
<div id="grantWin"></div>

<div id="roleWindow">
  <form name="role">
   <div class="row">
      <div class="columnTitle">角色编码:</div>
      <div class="columnContent"><input class="inp" name="role.code"/></div>
      <input type="hidden" name="role.id">
      <div class="columnTitle">角色名称:</div>
      <div class="columnContent"><input class="inp" name="role.name"/></div>
   </div>
   <div class="row">
      <div class="columnTitle">角色描述:</div>
      <div class="columnContent rightDown">&nbsp;</div>
   </div>
   <div class="row">
      <div class="columnTitle"><textarea style="width: 440px; height: 60px;" name="role.description"></textarea></div>
   </div>
  </form>
</div>
