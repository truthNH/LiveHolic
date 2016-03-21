<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.admin.*, java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
	//オブジェクトをインスタンス化
	ArrayList<AdminProductBean> liveInfo = new ArrayList<AdminProductBean>();
	DaoAdminProduct dao = new DaoAdminProduct();
	//ライブ情報取得
	liveInfo = dao.getLiveInfo();
%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>ライブ情報編集画面</title>
<link rel="stylesheet" href="./css/admin-product.css">
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
	<h1>ライブ情報編集画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<div id="index">
		<div class="live_id"
			style="background: #000; padding: 5px 0; text-align: center;">ライブID</div>
		<div class="live_name"
			style="background: #000; padding: 5px 0; text-align: center;">ライブ名</div>
		<div class="arthist_name"
			style="background: #000; padding: 5px 0; text-align: center;">アーティスト名</div>
		<div class="genre_name"
			style="background: #000; padding: 5px 0; text-align: center;">ジャンル名</div>
		<div class="total_length"
			style="background: #000; padding: 5px 0; text-align: center;">時間</div>
		<div class="venue_name"
			style="background: #000; padding: 5px 0; text-align: center;">会場名</div>
		<div class="lable_name"
			style="background: #000; padding: 5px 0; text-align: center;">レコード会社</div>
		<div class="live_date"
			style="background: #000; padding: 5px 0; text-align: center;">日付</div>
		<div class="total_track"
			style="background: #000; padding: 5px 0; text-align: center;">曲数</div>
		<div class="product_introduction"
			style="background: #000; padding: 5px 0; text-align: center;">商品紹介</div>
	</div>
	<%
		for (int i = 0 ; i < liveInfo.size() ; i++) {
	%>
	<div class="record-wrapper">
		<form class="record-form" name="form<%=i%>"
			action="AdminProductServlet" method="POST" onsubmit="return check()">
			<div class="live_id" style="text-align: center; font-weight: bold;"><%=liveInfo.get(i).getLive_id()%></div>
			<input type="hidden" name="live_id"
				value="<%=liveInfo.get(i).getLive_id()%>"> <input
				class="live_name" type="text" name="live_name"
				value="<%=liveInfo.get(i).getLive_name()%>"> <input
				class="arthist_name" type="text" name="arthist_name"
				value="<%=liveInfo.get(i).getArthist_name()%>">
			<%
			String J = "";
			String R = "";
			String A = "";
			String F = "";
			//セレクトボックスの初期値の処理
			if (liveInfo.get(i).getGenre_id().equals("J")) {
				J = "selected";
			} else if (liveInfo.get(i).getGenre_id().equals("R")) {
				R = "selected";
			} else if (liveInfo.get(i).getGenre_id().equals("A")) {
				A = "selected";
			} else if (liveInfo.get(i).getGenre_id().equals("F")) {
				F = "selected";
			}
		%>
			<select class="genre_name" name="genre_id">
				<option value="J" <%=J%>>J-pop</option>
				<option value="R" <%=R%>>Rock</option>
				<option value="A" <%=A%>>Anime</option>
				<option value="F" <%=F%>>Fes</option>
			</select> <input class="total_length" type="text" name="total_length"
				value="<%=liveInfo.get(i).getTotal_length()%>"> <input
				class="venue_name" type="text" name="venue_name"
				value="<%=liveInfo.get(i).getVenue_name()%>"> <input
				class="lable_name" type="text" name="label_name"
				value="<%=liveInfo.get(i).getLabel_name()%>"> <input
				class="live_date" type="text" name="live_date"
				value="<%=liveInfo.get(i).getLive_date()%>"> <input
				class="total_track" type="text" name="total_track"
				value="<%=liveInfo.get(i).getTotal_track()%>"> <input
				class="product_introduction" name="product_introduction" type="text"
				value="<%=liveInfo.get(i).getProduct_introduction()%>"> <input
				class="button" type="submit" value="編集" name="update">
		</form>
		<form class="to-product" action="AdminProductServlet" method="POST"
			name="form-product<%=i%>>">
			<input type="hidden" value="<%=liveInfo.get(i).getLive_id()%>"
				name="live_id"> <input type="submit" value="商品"
				name="admin-product">
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