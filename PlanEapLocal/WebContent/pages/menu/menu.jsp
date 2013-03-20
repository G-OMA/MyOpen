<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="${appPath}/resources/js/menu/Menu.js"></script>
<script type="text/javascript">
	var zNodes = ${executeResult.result};
	$(document).ready(function() {
		Menu.init("#treeDemo", setting, zNodes,"#AddNodeWindow");
	});
</script>
<div class="zTreeDemoBackground left" style="width: 98%; height: 98%;">
   <ul id="treeDemo" class="ztree" style="width: 98%; height: 98%;"></ul>
</div>

<div id="menu" class="easyui-menu" style="width: 150px; display: none;">
   <div id="m-add" onclick="Menu.addNode()">Add Node</div>
   <div class="menu-sep"></div>
   <div id="m-del" onclick="Menu.delNode()">Del Node</div>
   <div class="menu-sep"></div>
   <div id="m-upd" onclick="Menu.updNode()">Upd Node</div>
</div>
<div id="AddNodeWindow"></div>
