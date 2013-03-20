<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
  User user = new User();
  Object obj = request.getAttribute("user");
  if ( obj != null ) user = (User)obj;
  String uids = "",rids = "",unames = "",rnames = "";
  if(user.getOrgUnits()!=null){
	  for(int i=0;i<user.getOrgUnits().size();i++){
		  OrgUnit unit = user.getOrgUnits().get(i);
		  uids += unit.getId(); 
		  unames += unit.getName();
		  if( i < (user.getOrgUnits().size()-1) ){
			  uids += ",";
			  unames += ",";
		  }
	  }
  }
  if(user.getRoles()!=null){
	  for(int i=0;i<user.getRoles().size();i++){
		  Role role = user.getRoles().get(i);
		  rids += role.getId(); rnames += role.getName();
		  if( i < (user.getRoles().size()-1) ){
			  rids += ",";
			  rnames += ",";
		  }
	  }
  }
%>

<%@page import="com.xxx.planeap.domain.User"%>
<%@page import="com.xxx.planeap.domain.OrgUnit"%>
<%@page import="com.xxx.planeap.domain.Role"%>
<style>
<!--
.inp{
   width: 120px;
}
.title{
   display: block; 
   float: left; 
   margin-left: 20px; 
   text-align: center;
   line-height: 20px;
   vertical-align: bottom;
}
.row1{
   height:20px;
   line-height:20px;
   margin-top: 10px;
}
-->
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#role').combobox({
		valueField:'id',
		textField:'text',
		multiple:true,
		editable:false
	});
	$('#orgunit').combobox({
		valueField:'id',
		textField:'text',
		multiple:true,
		editable:false
	});
	$('#role').combobox("setValues","<%=rids%>");
	$('#role').combobox("setText","<%=rnames%>");
	$('#orgunit').combobox("setValues","<%=uids%>");
	$('#orgunit').combobox("setText","<%=unames%>");
});
</script>
<form name="userForm">
    <div>
	   <div class="row1">
	      <label class="title" style="width: 80px;">用户名称:</label>
	      <div style="float: left;">
	          <input class="inp" name="user.userName" value="${user.userName}">
	      </div>
	      <label class="title" style="width: 80px;">用户密码:</label>
	      <div style="float: left;">
	          <input class="inp" name="user.password" type="password" value="${user.password}">
	          <input type="hidden" name="user.id" value="${user.id}">
	      </div>
	   </div>
	   <div class="row1">
	      <label class="title" style="width: 80px;">用户帐号:</label>
	      <div style="float: left;">
	          <input class="inp" name="user.code" value="${user.code}">
	      </div>
	      <label class="title" style="width: 80px;">用户姓名:</label>
	      <div style="float: left;">
	          <input class="inp" name="user.name" value="${user.name}">
	      </div>
	   </div>
	   <div class="row1">
	      <label class="title" style="width: 80px;">组织单元:</label>
	      <div style="float: left;">
	         <s:select id="orgunit" list="orgUnits" cssClass="inp" listValue="name" listKey="id"></s:select>
	      </div>
	      <label class="title" style="width: 80px;">用户角色:</label>
	      <div style="float: left;">
	         <s:select id="role" list="roles" cssClass="inp" listValue="name" listKey="id"></s:select>
	      </div>
	   </div>
	</div>
</form>