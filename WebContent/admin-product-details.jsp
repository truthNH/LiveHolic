<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.admin.*, java.util.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
	//オブジェクトをインスタンス化
	@SuppressWarnings("unchecked")
	ArrayList<AdminProductBean> allProduct = (ArrayList<AdminProductBean>)request.getAttribute("allProduct");
	String live_id = (String)request.getAttribute("live_id");
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
	<h1>商品編集画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<h2>商品登録</h2>
	<form action="AdminProductServlet" method="POST">
		<div class="formCol">
			<label>プロダクトID:</label><input type="text" name="product_id"
				value="<%=live_id%>">
		</div>
		<div class="formCol">
			<label>商品名:</label><input type="text" name="product_name">
		</div>
		<div class="formCol">
			<label>アーティスト名:</label><input type="text" name="arthist_name">
		</div>
		<div class="formCol">
			<label>カテゴリ:</label> <select name="product_category">
				<option value="1">ミュージック</option>
				<option value="2">ムービー</option>
				<option value="3">グッズ</option>
			</select>
		</div>
		<div class="formCol">
			<label>時間:</label><input type="text" name="time">
		</div>
		<div class="formCol">
			<label>フォーマット:</label> <select name="format">
				<option value="1">MP3 320kbps</option>
				<option value="2">MP4 H.264</option>
				<option value="3">PDF</option>
			</select>
		</div>
		<div class="formCol">
			<label>価格:</label><input type="text" name="price">
		</div>
		<div class="formCol">
			<label>曲順:</label><input type="text" name="track_number">
		</div>
		<div class="formCol">
			<input type="hidden" name="live_id" value="<%=live_id%>"> <input
				type="submit" name="product-entry" value="登録">
		</div>
	</form>
	<%
		//商品登録がある場合
		if (allProduct != null || !allProduct.isEmpty()) {
	%>
	<h2>商品一覧</h2>
	<div id="index">
		<div class="product_id2"
			style="background: #000; padding: 5px 0; text-align: center;">商品ID</div>
		<div class="product_name"
			style="background: #000; padding: 5px 0; text-align: center;">商品名</div>
		<div class="arthist_name"
			style="background: #000; padding: 5px 0; text-align: center;">アーティスト名</div>
		<div class="product_category"
			style="background: #000; padding: 5px 0; text-align: center;">カテゴリ</div>
		<div class="time"
			style="background: #000; padding: 5px 0; text-align: center;">時間</div>
		<div class="format"
			style="background: #000; padding: 5px 0; text-align: center;">フォーマット</div>
		<div class="price"
			style="background: #000; padding: 5px 0; text-align: center;">価格</div>
	</div>
	<%
			for(int i = 0 ; i < allProduct.size() ; i++) {
	%>
	<div class="record-wrapper">
		<form class="record2" style="font-size: 10px;" method="POST"
			action="AdminProductServlet" onsubmit="return check()">
			<div class="product_id2"
				style="text-align: center; font-weight: bold;"><%=allProduct.get(i).getProduct_id()%></div>
			<input class="product_name2" type="text" name="product_name"
				value="<%=allProduct.get(i).getProduct_name()%>"> <input
				class="arthist_name" type="text" name="arthist_name"
				value="<%=allProduct.get(i).getArthist_name()%>">
			<%
			String c1 = "";
			String c2 = "";
			String c3 = "";
			//セレクトボックスの初期値の処理
			if (allProduct.get(i).getProduct_category().equals("1")) {
				c1 = "selected";
			} else if (allProduct.get(i).getProduct_category().equals("2")) {
				c2 = "selected";
			} else if (allProduct.get(i).getProduct_category().equals("3")) {
				c3 = "selected";
			} 
		%>
			<select class="product_category" name="product_category">
				<option value="1" <%=c1%>>ミュージック</option>
				<option value="2" <%=c2%>>ムービー</option>
				<option value="3" <%=c3%>>グッズ</option>
			</select> <input class="time" type="text" name="time"
				value="<%=allProduct.get(i).getTime()%>">
			<%
			String f1 = "";
			String f2 = "";
			String f3 = "";
			//セレクトボックスの初期値の処理
			if (allProduct.get(i).getFormat().equals("1")) {
				f1 = "selected";
			} else if (allProduct.get(i).getFormat().equals("2")) {
				f2 = "selected";
			} else if (allProduct.get(i).getFormat().equals("3")) {
				f3 = "selected";
			} 
		%>
			<select class="format" name="format">
				<option value="1" <%=f1 %>>MP3 320kbps</option>
				<option value="2" <%=f2 %>>MP4 H.264</option>
				<option value="3" <%=f3 %>>PDF</option>
			</select> <input class="price" type="text" name="price"
				value="<%=allProduct.get(i).getPrice()%>"> <input
				type="hidden" name="product_id"
				value="<%=allProduct.get(i).getProduct_id()%>"> <input
				type="submit" value="編集" name="update-product">
		</form>
		<form action="AdminProductServlet" method="POST"
			style="display: inline-block; width: 50px;" onsubmit="return check()">
			<input type="hidden" value="<%=allProduct.get(i).getProduct_id()%>"
				name="product_id"> <input type="submit" value="削除"
				name="delete-product">
		</form>
	</div>
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