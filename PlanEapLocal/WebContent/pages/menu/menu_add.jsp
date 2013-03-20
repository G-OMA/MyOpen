<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<style>
<!--
   #AddNodeWindow,input,textarea{
      font-size: 12px;
   }
   .inp{
      width: 120px;
   }
   .row{
      margin-top: 10px;
      height: 24px;
      line-height: 24px;
      vertical-align: middle;
   }
   .columnTitle{
      margin-left: 30px;
      float: left;
      height: 24px;
      line-height: 24px;
      width: 80px;
      vertical-align: middle;
   }
   .columnContent{
      float: left;
      height: 24px;
      line-height: 24px;
      width: 130px;
      vertical-align: middle;
   }
-->
</style>
<script type="text/javascript">
<!--
    $(document).ready(function(){
        if("${menu}"==""){
            $("[name='menu.pid']").val(0);
            $("[name='menu.idx']").val(1);
        }else if("${moveType}"=="add"){
            $("[name='menu.id'],[name='menu.name'],"+
                    "[name='menu.enName'],[name='menu.action'],"+
                    "[name='menu.description']").val("");
        }
        $("[title='"+"add"+"']").attr("checked","checked");
    });
//-->
</script>
<div id="AddNodeWindow">
   <form name="menuForm">
      <div class="row">
         <div class="columnTitle">父节点:</div>
         <div class="columnContent">
              <input name="pname" class="inp readOnly" readonly="readonly" value="${menu.pName}"/>
              <input type="hidden" name="menu.id" value="${menu.id }"/>
              <input type="hidden" name="menu.pid" value="${menu.pid}"/>
              <input type="hidden" name="menu.idx" value="${menu.idx}"/>
         </div>
         <div class="columnTitle">HomeUrl:</div>
         <div class="columnContent">
            <input name="menu.homeUrl" class="inp" value="${menu.homeUrl}"/></div>
      </div>
      <div class="row">
         <div class="columnTitle">节点名称:</div>
         <div class="columnContent"><input name="menu.name" class="inp" value="${menu.name}"/></div>
         <div class="columnTitle">名称简写:</div>
         <div class="columnContent"><input name="menu.enName" class="inp" value="${menu.enName}"/></div>
      </div>
      <div class="row">
         <div class="columnTitle">节点描述:</div>
         <div class="columnContent rightDown"></div>
         <div class="columnTitle">ActionName:</div>
         <div class="columnContent"><input name="menu.action" class="inp" value="${menu.action}"/></div>
      </div>
      <div style="margin-top: 2px;">
         <div style="margin-left: 30px;">
             <textarea style="width: 440px; height: 60px;" name="menu.description">${menu.description}</textarea>
         </div>
      </div>
      <div class="row"><div class="columnTitle"><b>包含功能选择</b></div></div>
      <div style="margin-top: 5px;">
         <s:iterator value="funs" var="fun" status="st">
            <s:if test="%{#st.index%7==6}"><br></s:if>
            <label style="margin-left:31px;" title="此功能Action名称格式如:[${fun.expression}]">${fun.name}</label>
            <s:set var="checked" value="false"></s:set>
            <s:iterator value="%{menu.funs}" var="f">
               <s:if test="%{#f.id==#fun.id}">
                   <s:set var="checked" value="true"></s:set>
               </s:if>
            </s:iterator>
            <s:checkbox fieldValue="%{#fun.id}" value="%{#checked}" name="menu.funs.id"></s:checkbox>
         </s:iterator>
      </div>
   </form>
</div>