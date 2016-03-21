<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="jp.ac.hal.tokyo.admin.*,jp.ac.hal.tokyo.liveholic.* , java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
	@SuppressWarnings("unchecked")
	ArrayList<VideoBean> commentData = (ArrayList<VideoBean>)request.getAttribute("commentData");
	
%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>動画コメント削除画面</title>
<link rel="stylesheet" href="./css/admin-product.css">
</head>
<body>
	<h1>動画コメント削除画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<div id="index">
		<div class="id"
			style="background: #000; padding: 5px 0; text-align: center;">ID</div>
		<div class="sec"
			style="background: #000; padding: 5px 0; text-align: center;">コメント時間</div>
		<div class="comment"
			style="background: #000; padding: 5px 0; text-align: center;">コメント内容</div>
		<div class="comment-delete"
			style="background: #000; padding: 5px 0; text-align: center;">消去</div>
	</div>
	<%
	if (!commentData.isEmpty()) {
		for (int i = 0 ; i < commentData.size() ; i++) {
%>
	<div class="record"
		style="margin-bottom: 2px; border-bottom: solid 1px; width: 720px;">
		<div class="id"><%=commentData.get(i).getId()%></div>
		<div class="sec"><%=commentData.get(i).getSec()%></div>
		<div class="comment"><%=commentData.get(i).getComment()%></div>
		<form class="comment-delete" action="AdminCommentServlet"
			method="POST">
			<input type="hidden" value="<%=commentData.get(i).getId()%>"
				name="id"> <input type="submit" value="消去" name="commentDel">
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