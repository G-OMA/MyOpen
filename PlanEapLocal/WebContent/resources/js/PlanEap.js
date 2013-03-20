/**
 * 
 * @author Goma OMA1989@YEAH.NET
 * @category PlanEap common js file
 * @version v1.0
 * @since 2012-05-31
 */
var PlanEap = {
    menuTemp : null,
	tempGrant : null,
	permisssions : null,
	APP_PATH : '',
	STRUTS_EXTENSION : '',
	toJsonStr : function(obj) {
		switch (obj.constructor) {
		case Object:
			var str = "{";
			for ( var o in obj) {
				str += o + ":" + this.toJsonStr(obj[o]) + ",";
			}
			if (str.substr(str.length - 1) == ",")
				str = str.substr(0, str.length - 1);
			return str + "}";
			break;
		case Array:
			var str = "[";
			for ( var o in obj) {
				str += this.toJsonStr(obj[o]) + ",";
			}
			if (str.substr(str.length - 1) == ",")
				str = str.substr(0, str.length - 1);
			return str + "]";
			break;
		case Boolean:
			return "\"" + obj.toString() + "\"";
			break;
		case Date:
			return "\"" + obj.toString() + "\"";
			break;
		case Function:
			break;
		case Number:
			return "\"" + obj.toString() + "\"";
			break;
		case String:
			return "\"" + obj.toString() + "\"";
			break;
		}
	},
	getActionURI : function(actionName) {
		return this.APP_PATH + "/" + actionName + "." + this.STRUTS_EXTENSION;
	}
};

//全局的AJAX访问，处理AJAX清求时SESSION超时
$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete:function(XMLHttpRequest,textStatus){
	   //通过XMLHttpRequest取得响应头，sessionstatus
	   var planEapStatus=XMLHttpRequest.getResponseHeader("PlanEapStatus"); 
	   if(planEapStatus=="timeout"){
		   $.messager.alert('系统提示','会话失效，请重新登录！','adf',function(){
			   window.location.replace(PlanEap.getActionURI("login"));
		   });
	   }else if(planEapStatus=="error"){
		   $.messager.alert('系统错误','系统运行错误，请反馈管理员！');
	   }
	}
});

