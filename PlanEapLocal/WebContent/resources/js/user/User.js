var User = {
	userWin : null,
	userGrid : null,
	init : function(grid, win) {
		this.userWin = $(win);
		this.userGrid = $(grid);
		this.createUserGrid(grid);
		this.createUserWin(win);
	},
	createUserGrid : function(grid) {
		createGrid(grid);
	},
	createUserWin : function(win) {
        createWin(win);
	},
	closeUserWin : function(){
        this.userWin.find("[name^='user.']").val("");
		this.userWin.dialog("close");
	},userGridRefresh:function(){
		this.userGrid.datagrid("unselectAll");
		this.userGrid.datagrid("reload");
	}
};
function createGrid(datagrid) {
	$(datagrid).datagrid({
		fit : true,
		border : 0,
		pageSize : 20,
		url : PlanEap.getActionURI("findUsers"),
		loadMsg : null,
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
		},{
			field : 'userName',
			title : 'userName',
			align : 'center',
			width : 200
		} ] ],
		pagination : true,
		rownumbers : true,
		toolbar : [ {
			id : 'btnadd',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
			   User.userWin.dialog({title:'添加用户'});
			   User.userWin.dialog('open');
			   User.userWin.dialog('refresh',PlanEap.getActionURI("findUserPage"));
			}
		},'-',{
			id : 'btncut',
			text : '删除',
			iconCls : 'icon-cut',
			handler : function() {
			   var params = "",tmp = "";
			   $(User.userGrid.datagrid("getSelections")).each(function(idx,user){
				   tmp = (idx == 0) ? "?" : "&";
				   params += tmp;params += "users[";params += idx;
				   params += "].id=";params += user.id;   
			   });
			   delUsers(params);
		    }
		},'-',{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
			   var row = User.userGrid.datagrid('getSelected');
		       User.userWin.dialog({title:'修改用户信息'});
			   User.userWin.dialog('open');
			   User.userWin.dialog('refresh',PlanEap.getActionURI("findUserPage")+"?user.id="+row.id);
		    }
		},'-',{
			id : 'locked',
			text : '锁定/解锁',
			iconCls : 'icon-locked',
			handler : function() {
			   var params = "",tmp = "";
			   $(User.userGrid.datagrid("getSelections")).each(function(idx,user){
				   tmp = (idx == 0) ? "?" : "&";
				   params += tmp;params += "users[";params += idx;
				   params += "].id=";params += user.id;   
			   });
			   lockUsers(params);
			}
		} ]
	});
	var p = $(datagrid).datagrid('getPager');
	$(p).pagination( {
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function createWin(win){
	$(win).dialog( {
		width : 500,
		height : 260,
		modal : true,
		closed : true,
		maximizable : false,
		minimizable : true,
		buttons : [ {
			text : 'Ok',
			iconCls : 'icon-ok',
			handler : function() {
			   var params = getFormData();
			   saveUser(params);
			}
		}, {
			text : 'Cancel',
			iconCls : 'icon-redo',
			handler : function() {
			   User.closeUserWin();
			}
		} ]
	});
}
function saveUser(params){
	$.ajax({
		url:PlanEap.getActionURI("saveUser")+"?"+params,
		type:'POST',
		dataType:'json',
		success:function(data){
		   User.closeUserWin();	  
		   User.userGridRefresh();
	    }
	});
}
function getFormData(){
	   var params = $("[name='userForm']").serialize();
	   var orgunits = ($("#orgunit").combobox("getValues")+"").split(",");
	   var roles = ($('#role').combobox('getValues')+"").split(",");
	   var j = 0 , x = 0;
	   for(var i=0;i<orgunits.length;i++){
		   if(orgunits[i] !=null && orgunits[i] != ""){
			   params += "&user.orgUnits[";
			   params += j;
			   params += "].id=";
			   params += orgunits[i];
			   j++;
		   }
	   }
	   for(var i=0;i<roles.length;i++){
		   if(roles[i] != null && roles[i] != ""){
			   params += "&user.roles[";
			   params += x;
			   params += "].id=";
			   params += roles[i];
			   x++;
		   }
	   }
	   return params;
}
function delUsers(params){
	$.ajax({
		url:PlanEap.getActionURI("delUsers")+params,
		type:'POST',
		dataType:'json',
		success:function(data){
		   alert(data);
		   User.userGridRefresh();
	    }
	});
}
function lockUsers(params){
	$.ajax({
		url:PlanEap.getActionURI("lockUsers")+params,
		type:'post',
		dataType:'json',
		success:function(data){
		   User.userGridRefresh();
	    }
	});
}