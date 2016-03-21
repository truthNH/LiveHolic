<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.admin.*, java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String msg = (String)request.getAttribute("msg");
	@SuppressWarnings("unchecked")
	ArrayList<String> err_msg = (ArrayList<String>)request.getAttribute("err_msg");
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>エラーメッセージ</title>
</head>
<body>
	<%
		if (msg != null) {
	%>
	<p><%=msg%></p>
	<%
		}
		if (err_msg != null) {
			for (int i = 0 ; i < err_msg.size() ; i++) {
	%>
	<p style="color: red;"><%=err_msg.get(i) %></p>
	<%
			 }
		 }
	%>
	<p>
		<a href="admin-product-view.jsp">ライブ編集画面</a>
	</p>
	<p>
		<a href="admin-index.jsp">管理者トップ画面</a>
	</p>
</body>
</html>