<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.admin.*, java.util.*"%>
<%
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
	DaoAdminUser dao = new DaoAdminUser();
	ArrayList<AdminUserBean> userAll = dao.getAllUser();
%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/admin-product.css">
<title>ユーザ管理画面</title>
</head>
<script>
function check(){
	if(window.confirm('送信してよろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は送信を実行
	} else { // 「キャンセル」時の処理
		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止
	}
}
</script>
<body>
	<h1>ユーザ管理画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<div id="index">
		<div class="customer-id"
			style="background: #000; padding: 5px 0; text-align: center;">ID</div>
		<div class="user-id"
			style="background: #000; padding: 5px 0; text-align: center;">ユーザID</div>
		<div class="email"
			style="background: #000; padding: 5px 0; text-align: center;">メールアドレス</div>
		<div class="passwd"
			style="background: #000; padding: 5px 0; text-align: center;">パスワード</div>
		<div class="first-name"
			style="background: #000; padding: 5px 0; text-align: center;">姓</div>
		<div class="last-name"
			style="background: #000; padding: 5px 0; text-align: center;">名</div>
		<div class="record-date"
			style="background: #000; padding: 5px 0; text-align: center;">登録日</div>
		<div class="user-delete"
			style="background: #000; padding: 5px 0; text-align: center;">削除</div>
	</div>
	<%
		for (int i = 0 ; i < userAll.size() ; i++) {
	%>
	<div id="record" style="font-size: 10px;">
		<div class="customer-id"><%=userAll.get(i).getCustomer_id()%></div>
		<div class="user-id"><%=userAll.get(i).getUser_id()%></div>
		<div class="email"><%=userAll.get(i).getEmail()%></div>
		<div class="email"><%=userAll.get(i).getPassword()%></div>
		<div class="first-name"><%=userAll.get(i).getFirst_name()%></div>
		<div class="last-name"><%=userAll.get(i).getLast_name()%></div>
		<div class="record-date"><%=userAll.get(i).getRecode_date()%></div>
		<form class="user-delete" action="AdminControllerServlet"
			method="POST" style="display: inline-block;"
			onsubmit="return check()">
			<input type="hidden" value="<%=userAll.get(i).getCustomer_id()%>"
				name="customer_id"> <input type="submit" value="消去"
				name="userDel">
		</form>
	</div>
	<%
		}
	%>
	<p>
		<a href="admin-index.jsp">管理者トップ画面</a>
	</p>
</body>
</html>