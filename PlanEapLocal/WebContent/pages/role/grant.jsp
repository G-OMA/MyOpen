<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    var IDMark_A = "_a";
	var zTreeNodes = ${executeResult.result};
	var perms = ${executeResult.otherParam.role.permissions};
	var rid = ${executeResult.otherParam.role.id};
	var config = {
	        async : {
				enable : true,
				url : PlanEap.getActionURI("grantFindChild"),
				autoParam : [ "id=mid" ],
				otherParam : { "role.id":rid }
			},data : {
				simpleData : {
					enable : true
				}
			},view : {
				addDiyDom: addDiyDom
			}
		};
	function addDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId + IDMark_A);
		var editStr = "<span id='diyBtn_" +treeNode.id+ "'>";
		$(treeNode.funs).each(function(st,fun){
			editStr += "<span style='margin-left:5px;'>"+fun.name+"</span>";
			editStr += "<input type='checkbox' value='"+fun.id+"' onClick='javascript:grant("+treeNode.id+");' ";
			$(perms).each(function(idx,perm){
				if(perm.mid==treeNode.id){
					if(perm.fid == fun.id)
						editStr += "checked=true ";
				}
			});
			editStr +="style='margin-top:-2px;'/>";
		});
		editStr += "</span>";
		aObj.append(editStr);
	}
	function grant(mid){//将角色权限存放到全局变量PlanEap.permissions中
		var treeNode = PlanEap.tempGrant.getNodeByParam("id", mid, null);
		$(PlanEap.permissions).each(function(idx){
			if(mid==this.mid)
				PlanEap.permissions.splice(idx,1);
		});
		var permission = new Object();
		permission.fun = new Array();
		permission.mid = mid;
		permission.rid = rid;
		$($("#diyBtn_"+mid).find("input:checked")).each(function(){
			permission.fun.push(this.value);
		});
		PlanEap.permissions.push(permission);
    }

	$(document).ready(function() {
		PlanEap.permissions = new Array();
		PlanEap.tempGrant = $.fn.zTree.init($("#permTree"), config, zTreeNodes);
	});
</script>
<div style="margin-left:20px; width: 90%; margin-top:10px;">
<ul id="permTree" class="ztree" style="width: 90%; height: 90%;"></ul>
</div>
