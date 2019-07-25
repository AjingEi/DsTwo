<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<s:head theme="xhtml"/>
<sx:head parseContent="true" extraLocales="UTF-8"/> 
<script src="./js/jquery/2.0.0/jquery.min.js"></script>
<link href="./css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="./js/bootstrap/3.3.6/bootstrap.min.js"></script>

<link rel="stylesheet" href="./css/mynav.css" type="text/css">

<script src="./js/mynav.js"></script>

<style>
.forms{
  width:100%;
}
.sp{
font-size:15px;
}
.sp2{
 
 margin-left:333px;
  padding-left:0px;
}
.tt{
 width:100%;
}
.ss{
 width:100%;
}
.cw{
font-weight:800;color:red;
}
</style>

</head>
<body>

<%@include file="mynav.jsp" %>
  <h5>	<a href="index.jsp">首页</a>>>注册</h5>
       <hr class="simple" color="#6f5499" />
<div class="container">
  <div class="row">
    <div class="col-sm-3">
    </div>
    <div class="col-sm-6">
 <div class="cw">  <s:property value="#session.registerfail"/></div> 
<s:form action="Hupdate"  method="post" enctype="multipart/form-data" role="form" class="forms"> 
 
  <table class="ss">
    <tr class="tt"><td class="sp">编号：</td><td class="sp2">    <s:textfield name="hr.id" readonly="true" class="form-control" id="hr.id"/>
       </td></tr>
       <tr class="tt"><td class="sp">用户名：</td><td class="sp2">    <s:textfield name="hr.username" class="form-control" id="hr.username" placeholder="请输入用户名"/>
       </td></tr>
      
         <tr><td class="sp">密码：</td><td class="sp2">
           <s:textfield name="hr.password" class="form-control" id="hr.password"  placeholder="请输入密码"/></td></tr>
           
  <tr class="tt"><td class="sp">姓名：</td><td class="sp2">   
            <s:textfield name="hr.name" class="form-control" id="hr.name" placeholder="请输入姓名"/></td></tr>
            
       <tr class="tt"><td class="sp">公司地址：</td><td class="sp2">    
            <s:textfield name="hr.position" class="form-control" id="hr.position" placeholder="请输入公司地址"/></td></tr>
            
            <tr class="tt"><td class="sp">公司：</td><td class="sp2">   
           <s:textfield name="hr.company" class="form-control" id="hr.company" placeholder="请输入公司"/></td></tr>
           
           <tr class="tt"><td class="sp">工作地点：</td><td class="sp2">   
           <s:textfield name="hr.workplace" class="form-control" id="hr.workplace" placeholder="请输入工作地点"/></td></tr>
           
           <tr class="tt"><td class="sp">电话号码：</td><td class="sp2">   
           <s:textfield name="hr.phone" class="form-control" id="hr.phone" placeholder="请输入电话号码"/></td></tr>
           
           <tr class="tt"><td class="sp">邮箱：</td><td class="sp2">   
          <s:textfield name="hr.email" class="form-control" id="hr.email" placeholder="请输入邮箱"/></td></tr>
  
                             <tr><td>请导入头像：  </td><td><input type="file" name="pic" required="required" style=" -webkit-box-shadow: 0 0 0px 1000px white  inset !important; margin-top:20px;"></td></tr>
  
  <s:submit value="注册"  class="btn btn-default" style="width:100%;margin-top:20px;"/>
   </table> 
</s:form>
</div>
<div class="col-sm-3">
</div>
</div>
</div>
</body>
</html>