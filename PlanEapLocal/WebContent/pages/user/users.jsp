<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${appPath}/resources/js/user/User.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		User.init("#userGird", "#userWin");
	});
</script>
<table id="userGird"></table>
<div id="userWin"></div>