var Menu = {
	zTree : null,
	menuWin : null,
	oldParentNode : null,
	newParentNode : null,
	init:function(zTreeId,setting,zNodes,winId){
	    this.menuWin = $(winId);
	    this.createWindow(this.menuWin);
	    this.createTree(zTreeId, setting, zNodes);
    },
    createTree : function(zTreeId, setting, zNodes) {
    	this.zTree = $.fn.zTree.init($(zTreeId), setting, zNodes);
	},
	createWindow : function(winId){
		createMenuWin(winId);
	},
	closeWin : function(){
		this.menuWin.find("[name^='menu.']").val("");
		this.menuWin.dialog('close');
	},
	createMenu : function(id, pid, idx, isParent, size) {
		var obj = new Object;
		obj.id = id;
		obj.pid = pid;
		obj.idx = idx;
		obj.isParent = isParent;
		obj.size = size;
		return obj;
	},
	refresh : function() {
		if(!(this.oldParentNode && this.newParentNode)) {
			this.zTree.reAsyncChildNodes(null, "refresh");
		}else{
			this.zTree.reAsyncChildNodes(this.oldParentNode, "refresh");
			this.zTree.reAsyncChildNodes(this.newParentNode, "refresh");
		}
	},
	initWinData:function(node,type,pnode){
		initMenuWinData(node,type,pnode);
	}
	,
	addNode : function() {
		var nodes = this.zTree.getSelectedNodes();
		if(nodes!=null && nodes!=""){
			this.oldParentNode = this.newParentNode = nodes[0];
		}else{
			this.oldParentNode = this.newParentNode = null;
		}
		this.menuWin.dialog({title:'添加节点'});
		var params = "?moveType=add";
		if(nodes != null && nodes != ""){
			params += "&nid="+nodes[0].id;
		}
		this.menuWin.dialog('open');
		this.menuWin.dialog("refresh",PlanEap.getActionURI("findMenuPage")+params);
	},
	delNode : function() {
		var nodes = this.zTree.getSelectedNodes();
		var childrens = 0;
		if(nodes){
	    	if(nodes[0].getParentNode()){
		    	childrens = nodes[0].getParentNode().children.length-1;
	    	    if(childrens>0){
		    	    this.oldParentNode = this.newParentNode = nodes[0].getParentNode();
	    	    }else{
		    	    this.oldParentNode = this.newParentNode = nodes[0].getParentNode().getParentNode();
	    	    }
	    	}else{
		    	this.oldParentNode = this.newParentNode = null;
	    	}
	    	delMenu(nodes[0].id,childrens,nodes[0].pid);
		}else{
	    	this.oldParentNode = this.newParentNode = null;
		}
	},
	updNode : function() {
		var nodes = this.zTree.getSelectedNodes();
		if(nodes!=null && nodes!=""){
	    	this.oldParentNode = this.newParentNode = nodes[0].getParentNode();
			var params = "?moveType=edit";
			params += "&nid="+nodes[0].id;
			this.menuWin.dialog({title:'修改节点'});
			this.menuWin.dialog('open');
			this.menuWin.dialog("refresh",PlanEap.getActionURI("findMenuPage")+params);
		}
	}
};
var setting = {
	async: {
        enable: true,
        url:PlanEap.getActionURI("findMenuByPid"),
        autoParam:["id=nid"]
    },
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onDrop : zTreeOnDrop,
		beforeDrop : zTreeBeforeDrop,
		onRightClick : OnRightClick
	},view : {
		addDiyDom: addDiyDom
	}
};
function addDiyDom(treeId, treeNode) {
	var aObj = $("#" + treeNode.tId + "_a");
	var editStr = "<span id='diyBtn_" +treeNode.id+ "'>";
	$(treeNode.funs).each(function(st,fun){
		if(st==0)
			editStr +="<span style='margin-left:20px;color:gray;'>菜单功能:</span>";
		editStr += "<span style='margin-left:5px; color:gray;' title = '此功能Action名称格式如:["+fun.expression+"]'>"+fun.name+"</span>";
	});
	editStr += "</span>";
	aObj.append(editStr);
}


function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
	Menu.oldParentNode = treeNodes[0].getParentNode();
	if (moveType == "inner") {
		Menu.newParentNode = targetNode;
	} else {
		Menu.newParentNode = targetNode.getParentNode();
	}
	return true;
}
function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
	var nodes = new Array();
	var oldParent = "";
	// Change treeNodes To Menu
	$(treeNodes).each(function() {
		nodes.push(Menu.createMenu(this.id, this.pid, this.idx,this.isParent, 0));
	});
	treeNodes = PlanEap.toJsonStr(nodes);
	// Change OldParentNode To Menu
	if (Menu.oldParentNode) {
		oldParent = PlanEap.toJsonStr(Menu.createMenu(Menu.oldParentNode.id,
				Menu.oldParentNode.pid, Menu.oldParentNode.idx,
				Menu.oldParentNode.isParent, 0));
	} else {
		oldParent = PlanEap.toJsonStr(new Object);
	}

	if (targetNode) {
		// Change TargetNode To Menu
		targetNode = PlanEap.toJsonStr(Menu.createMenu(targetNode.id,
				targetNode.pid, targetNode.idx, targetNode.isParent, targetNode.children.length));
		$.ajax( {
			url : PlanEap.getActionURI("moveMenu"),
			type : "post",
			async : false,
			data : {
				"targetNode" : targetNode,
				"treeNodes" : treeNodes,
				"oldParentNode" : oldParent,
				"moveType" : moveType
			}
		});
		Menu.refresh();
	}
}

function OnRightClick(event, treeId, treeNode) {
	if (!treeNode) {
		$("#m-upd,#m-del,.menu-sep").hide();
		Menu.zTree.cancelSelectedNode();
	} else {
		Menu.zTree.expandNode(treeNode, true, false, true);
		$("#m-upd,#m-del,.menu-sep").show();
		Menu.zTree.selectNode(treeNode);
	}
	$('#menu').menu('show', {
		left : event.pageX,
		top : event.pageY
	});
}
function saveMenu(){
	var params = Menu.menuWin.find("form").serialize();
	$.ajax({
		url:PlanEap.getActionURI("saveOrUpdate"),
		data:params,
		type:'post',
		dataType:'json',
		success:function(data){
		    if(Menu.oldParentNode!=null && Menu.newParentNode!=null)
		    	Menu.oldParentNode.isParent = Menu.newParentNode.isParent = true;
		    Menu.refresh();
			Menu.menuWin.dialog('close');
	    }
	});
}
function delMenu(mid,isParent,pid){
	$.ajax({
		url:PlanEap.getActionURI("delMenu"),
		type:'post',
		data:{'menu.id':mid,'menu.isParent':isParent>0?1:0,'menu.pid':pid},
		dataType:'json',
		success:function(data){
			Menu.refresh();
		}
	});
}


function createMenuWin(win){
	win.dialog( {
		width : 540,
		height : 320,
		modal : true,
		closed : true,
		minimizable : false,
		buttons : [ {
			text : 'Ok',
			iconCls : 'icon-ok',
			handler : function() {
				saveMenu();
			}
		}, {
			text : 'Cancel',
			iconCls : 'icon-redo',
			handler : function() {
				Menu.closeWin();
			}
		} ]
	});
}