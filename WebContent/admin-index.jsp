<%-- <%@page import="javax.websocket.SendResult"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.admin.*"%>
<%
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面トップページ</title>
</head>
<body>
	<h1>管理者画面トップページ</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<ul>
		<li><a href="admin-product.jsp">ライブ登録画面</a></li>
		<li><a href="admin-product-view.jsp">ライブ管理画面</a></li>
		<li><a href="admin-request-view.jsp">リクエスト管理画面</a></li>
		<li><a href="admin-comment.jsp">動画コメント管理画面</a></li>
		<li><a href="admin-user.jsp">ユーザ管理画面</a></li>
	</ul>
	<form action="AdminControllerServlet" method="POST">
		<input type="submit" value="ログアウト" name="logout">
	</form>
</body>
</html>