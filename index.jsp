<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSP基本语法学习</title>
</head>
<body>
	<h3>JSP基本语法学习</h3>
	<hr>
	<!-- 局部代码块 -->
	<%
		//声明JAVA代码域
		int a = 2;
		if(a>3){
	%>
		<b>哈哈，JSP学习好简单</b>
	<% } else {%>
		<i>JSP中使用逻辑校验很难受</i>
	<% test();} %>
	
	<!-- 全局代码块 -->
	<%!
		int  a = 5;
		public void test(){
		System.out.println("我是全局代码块声明");
	}
	%>
</body>
</html>
