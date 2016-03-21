<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.admin.*, java.util.*"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<String> errMsg = (ArrayList<String>)request.getAttribute("errMsg");
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/admin-product.css">
</head>
<body>
	<h1>管理者ログイン画面</h1>
	<%
	if (errMsg != null) {
		for (int i = 0 ; i < errMsg.size() ; i++) {
%>
	<p style="color: red;"><%=errMsg.get(i)%></p>
	<%
		}
	}
%>
	<form action="AdminControllerServlet" method="POST">
		<div class="formCol">
			<label for="user">管理者名：</label><input type="text" name="user"
				id="user">
		</div>
		<div class="formCol">
			<label for="user">パスワード：</label><input type="password" name="passwd"
				id="passwd"><br>
		</div>
		<div class="formCol">
			<input type="submit" value="ログイン" name="submit">
		</div>
	</form>
</body>
</html>