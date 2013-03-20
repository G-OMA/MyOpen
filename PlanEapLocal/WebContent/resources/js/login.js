/**
 * Author: Goma 
 * Email: OMA1989@YEAH.NET 
 * DESC: This javascript file is maked for login.jsp
 */

$(document).ready(function() {
	/**
	 * 用户名、密码输入框的相关效果 开始 考虑到国际化问题，采用输入框value、title都取自资源文件
	 * 比较value、title是否相等便可知用户是否已填写信息
	 */
	$("#login").find(".name,.pwd").focus(function() {
		var type = $(this).attr("class");
		var value = $(this).val();
		var title = $(this).attr("title");
		if (type == "name" && value == title) {
			this.value = "";
		}
		if (type == "pwd" && value == title) {
			this.value = "";
		}
	});
	$("#login").find(".name,.pwd").blur(function() {
		var type = $(this).attr("class");
		var title = $(this).attr("title");
		if (type == "name" && this.value == "") {
			this.value = title;
		}
		if (type == "pwd" && this.value == "") {
			this.value = title;
		}
	});
	/** *************用户名、密码效果控制结束**************** */
    $("#sure").click(function(){
    	var params = $(".name,.pwd").serialize();
    	$.ajax({
    		url:PlanEap.getActionURI("securityLogin"),
    		data:params,
    		type:'post',
    		dataType:'json',
    		success:function(data){
    		    if(data.success){
    		    	location.href = PlanEap.getActionURI("findIndex");
    		    }else{
    		    	alert(data.status);
    		    }
    	    },beforeSend:function(){
    	    	$.messager.progress({
    				title:'Please waiting',
    				text:'Login...'
    			});
    	    },complete:function(){			
    	    	setTimeout(function(){
    	    		$.messager.progress('close');
    	    	},0);
    	    },error:function(data){
    	    	alert("ERROR:"+data);
    	    }
    	});
    });
    $("#cancel").click(function(){
    	var $name = $("[name='name']");
    	var $pwd = $("[name='pwd']");
    	$name.val($name.attr("title"));
    	$pwd.val($pwd.attr("title"));
    });
});