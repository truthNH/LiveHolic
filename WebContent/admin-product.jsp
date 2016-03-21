<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, jp.ac.hal.tokyo.admin.*"%>
<%
	//セッションからAdminUserBeanオブジェクトを受け取る
	AdminUserBean admin = (AdminUserBean)session.getAttribute("adminUser");
	//正規ログイン処理か確認
	if (admin.getUser() == null) {
		response.sendRedirect("admin-message.jsp");
	}
	request.setCharacterEncoding("UTF-8");
	String msg = (String)request.getAttribute("msg");
	@SuppressWarnings("unchecked")
	ArrayList<String> err_msg = (ArrayList<String>)request.getAttribute("err_msg");
%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>ライブ登録画面</title>
</head>
<link rel="stylesheet" href="./css/admin-product.css">
<body>
	<h1>ライブ登録画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<%
	if (msg != null) {
%>
	<p><%=msg%></p>
	<%
	}
%>
	<%
 if (err_msg != null) {
	for (int i = 0 ; i < err_msg.size() ; i++) {
%>
	<p style="color: red;"><%=err_msg.get(i) %></p>
	<%
	 }
 }
%>

	<form action="AdminProductServlet" id="formLive" method="POST">
		<div class="formCol">
			<label for="live_id">ライブID：</label><input type="text" name="live_id"
				id="live_id"><span class="example">[例] J0001</span><br>
		</div>
		<div class="formCol">
			<label for="live_name">ライブ名：</label><input type="text"
				name="live_name" id="live_name"><br>
		</div>
		<div class="formCol">
			<label for="arthist_name">アーティスト名：</label><input type="text"
				name="arthist_name" id="arthist_name"><br>
		</div>
		<div class="formCol">
			<label for="genre_id">ジャンル：</label>
		</div>
		<div class="formCol">
			<input type="radio" name="genre_id" value="J" checked>J-pop <input
				type="radio" name="genre_id" value="R">Rock <input
				type="radio" name="genre_id" value="A">Anime <input
				type="radio" name="genre_id" value="F">Fes<br>
		</div>
		<div class="formCol">
			<label for="total_length">ライブ時間：</label><input type="text"
				name="total_length" id="total_length"><span class="example">[例]
				02:34:56</span>
		</div>
		<div class="formCol">
			<label for="venue_name">ライブ会場名：</label><input type="text"
				name="venue_name" id="venue_name"><br>
		</div>
		<div class="formCol">
			<label for="label_name">レコード会社：</label><input type="text"
				name="label_name" id="label_name"><br>
		</div>
		<div class="formCol">
			<label for="live_date">ライブ日付：</label><input type="text"
				name="live_date" id="live_date"><span class="example">[例]
				2015-03-10</span><br>
		</div>
		<div class="formColBig">
			<label for="product_introduction">商品紹介：</label>
			<textarea name="product_introduction" id="product_introduction"
				cols="30" rows="7"></textarea>
			<br>
		</div>
		<div class="formCol">
			<label for="total_track">曲数</label><input type="text"
				name="total_track" id="total_track"><span class="example">[例]
				19</span>
		</div>
		<div class="formCol">
			<input type="submit" name="insert" value="登録">
		</div>
		<div>
			<a href="admin-product.jsp">クリア</a>
		</div>
	</form>
	<p>
		<a href="admin-index.jsp">管理者トップ画面</a>
	</p>
</body>
</html>