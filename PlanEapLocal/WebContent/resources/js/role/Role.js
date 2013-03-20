var Role = {
	zTree : null,
	win : null,
	datagrid : null,
	grantWin : null,
	init : function(datagrid,data,winId,grantWin) {
	    this.win = $(winId);
	    this.datagrid = $(datagrid);
	    this.grantWin = $(grantWin);
		this.createWindow(winId);
		this.createDatagrid(datagrid,data);
		this.createGrantWin(grantWin);
	},createDatagrid : function(datagrid,data) {
		createDatagrid(datagrid,data);
	},createWindow : function(winId) {
        createWin(winId);
	},createRole : function(id,code){
		var role = new Object();
		role.id = id;
		role.code = code;
		return role;
	},createGrantWin : function(win){
		grantWin(win);
	},closeRoleWin:function(){
		this.win.find("input").val("");
		this.win.find("textarea").val("");
		this.win.dialog("close");
	},datagridRefresh:function(){
		this.datagrid.datagrid("unselectAll");
		this.datagrid.datagrid("reload");
	}
};
function createDatagrid(datagrid,data) {
	$(datagrid).datagrid( {
		fit : true,
		border : false,
		data : data,
		pageSize : 20,
		url : PlanEap.getActionURI("findRoles"),
		loadMsg:null,
		sortName : 'code',
		sortOrder : 'asc',
		remoteSort : false,
		idField : 'id',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : 'code',
			field : 'code',
			align : 'center',
			width : 80
		} ] ],
		columns : [ [ {
			field : 'name',
			title : 'Name',
			align : 'center',
			width : 50
		}, {
			field : 'description',
			title : 'description',
			align : 'center',
			width : 200
		}, {
			field : 'status',
			title : 'status',
			align : 'center',
			width : 100
		}, {
			field : 'createdBy',
			title : 'createdBy',
			align : 'center',
			width : 100
		}, {
			field : 'createdDate',
			title : 'createdDate',
			align : 'center',
			width : 120
		}, {
			field : 'updatedBy',
			title : 'updatedBy',
			align : 'center',
			width : 100
		}, {
			field : 'updatedDate',
			title : 'updatedDate',
			align : 'center',
			width : 120
		}, {
			field : 'opt',
			title : 'Operation',
			width : 100,
			align : 'center',
			formatter : function(value, rec) {
				return '<span style="color:red">Edit Delete</span>';
			}
		} ] ],
		pagination : true,
		rownumbers : true,
		toolbar : [ {
			id : 'btnadd',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
			    Role.win.dialog({title:'添加角色'});
				Role.win.dialog('open');
			}
		},{
			id : 'btncut',
			text : '删除',
			iconCls : 'icon-cut',
			handler : function() {
				var roles = new Array();
				$(Role.datagrid.datagrid("getSelections")).each(function(){
					roles.push(Role.createRole(this.id, this.code));
				});
				delRoles(PlanEap.toJsonStr(roles));
			}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
			   var row = Role.datagrid.datagrid('getSelected');
			   initRoleWin(row);
			   Role.win.dialog({title:'修改角色'});
			   Role.win.dialog('open');
			}
		},{
			id : 'btngrant',
			text : '授权',
			iconCls : 'icon-grant',
			handler : function() {
			   var row = Role.datagrid.datagrid('getSelected');
			   Role.grantWin.dialog({title:'角色: '+row.name+" 权限分配",rid:row.id});
			   Role.grantWin.dialog('open');
			   Role.grantWin.dialog('refresh',PlanEap.getActionURI("grantFind")+"?role.id="+row.id);
			}
		} ]
	});
	var p = $(datagrid).datagrid('getPager');
	$(p).pagination( {
		beforePageText:'第',
		afterPageText: '页    共 {pages} 页',           
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function grantWin(win){
	$(win).dialog( {
		width : 560,
		height : 280,
		modal : true,
		resizable : true,
		closed : true,
		maximizable : true,
		buttons : [ {
			text : 'Ok',
			iconCls : 'icon-ok',
			handler : function() {
			   var permissions = PlanEap.toJsonStr(PlanEap.permissions);
			   grantSave(permissions);
			   Role.grantWin.dialog("close");
			}
		}, {
			text : 'Cancel',
			iconCls : 'icon-redo',
			handler : function() {
			   Role.grantWin.dialog("close");
			}
		} ]
	});
}
function createWin(win){
	$(win).dialog( {
		width : 520,
		height : 240,
		modal : true,
		closed : true,
		maximizable : false,
		minimizable : true,
		buttons : [ {
			text : 'Ok',
			iconCls : 'icon-ok',
			handler : function() {
			    var params = $(win).find("form").serialize();
			    saveOrUpdateRole("saveRole",params);
			}
		}, {
			text : 'Cancel',
			iconCls : 'icon-redo',
			handler : function() {
				Role.closeRoleWin();
			}
		} ]
	});
}
function saveOrUpdateRole(actionName,params){
	$.ajax({
		url:PlanEap.getActionURI(actionName),
		type:'POST',
		data:params,
		dataType:'json',
		success:function(data){
		    Role.closeRoleWin();
		    Role.datagridRefresh();
	    }
	});
}
function delRoles(roles){
	$.ajax({
		url:PlanEap.getActionURI("delRoles"),
		type:'POST',
		data:{"delRoles":roles},
		dataType:'json',
		success:function(){
		    Role.datagridRefresh();
	    }
	});
}
function initRoleWin(role){
	$("[name='role.id']").val(role.id);
	$("[name='role.code']").val(role.code);
	$("[name='role.name']").val(role.name);
	$("[name='role.description']").val(role.description);
}
function grantSave(permissions){
	$.ajax({
		url:PlanEap.getActionURI("grantSave"),
		type : 'post',
		data : {'permissions':permissions},
		dataType : 'json',
		success : function(data){
			alert(data);
		}
	});
}