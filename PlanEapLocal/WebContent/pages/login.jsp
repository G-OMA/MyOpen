<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./includeJsCss.jsp"/>
<link rel="stylesheet" type="text/css" href="${appPath}/resources/css/login.css">
<script type="text/javascript" src="${appPath}/resources/js/login.js"></script>
<title>Login</title>
</head>
<body>
<div id="container">
   <div id="login">
       <div class="logo">
          <span>弗兰斯软件科技有限公司</span></div>
       <div class="form">
          <input class="name" name="user.userName" title="请输入用户名" value="admin"/>
          <input class="pwd" type="password" name="user.password" title="请输入密码" value="administrator">
          <input type="button" id="sure">
          <input type="button" id="cancel">
       </div>
       <div id="copyright" align="center">
          <span>弗兰斯软件科技有限公司 版权所有Version 1.0 Copyright 2010 Goma</span>
       </div>
   </div>
</div>
</body>
</html>