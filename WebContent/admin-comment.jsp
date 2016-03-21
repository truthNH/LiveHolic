<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="jp.ac.hal.tokyo.admin.*, jp.ac.hal.tokyo.liveholic.* ,java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
	// DAOオブジェクト生成
	DaoAdminProduct dao = new DaoAdminProduct();
	ArrayList<AdminProductBean> liveMovie = dao.getMovie();
%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>動画コメント管理画面</title>
<link rel="stylesheet" href="./css/admin-product.css">
</head>
<body>
	<h1>動画コメント管理画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<div id="index">
		<div class="product_id"
			style="background: #000; color: #fff; font-weight: bold; text-align: center;">プロダクトID</div>
		<div class="product_name"
			style="background: #000; color: #fff; font-weight: bold; text-align: center;">ライブ動画名</div>
		<div class="comment-num"
			style="background: #000; color: #fff; font-weight: bold; text-align: center;">コメント数</div>
		<div class="form"
			style="background: #000; color: #fff; font-weight: bold; text-align: center;">編集</div>
	</div>
	<% 
	if (!liveMovie.isEmpty()) {
		for (int i = 0 ; i < liveMovie.size() ; i++) {
%>
	<div class="record" style="margin-bottom: 2px;">
		<div class="product_id"><%=liveMovie.get(i).getProduct_id()%></div>
		<div class="product_name"><%=liveMovie.get(i).getProduct_name()%></div>
		<div class="comment-num"><%=liveMovie.get(i).getComment_num()%></div>
		<form class="form" action="AdminCommentServlet" method="POST"
			style="display: inline-block">
			<input type="hidden" value="<%=liveMovie.get(i).getProduct_id()%>"
				name="product_id"> <input type="submit" value="編集"
				name="comment">
		</form>
	</div>
	<%
		}
	} 
%>
	<p>
		<a href="admin-index.jsp">管理者トップ画面</a>
	</p>
</body>
</html>