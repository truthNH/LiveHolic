<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	////リクエストから受け取り
	String password = (String)request.getAttribute("password");
	String email = (String)request.getAttribute("email");
	String postal_code = (String)request.getAttribute("postal_code");
	String address_prefectures = (String)request.getAttribute("address_prefectures");
	String street_address = (String)request.getAttribute("street_address");
	
	//セッションから値を取得
		String customerLogin = (String)session.getAttribute("customerLogin");
		
	//DAOオブジェクト作成
	DaoUser dao = new DaoUser();
	ArrayList<Object> getUserData = new ArrayList<Object>();
	getUserData = dao.getUserData(customerLogin); 
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>アカウント情報の確認</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signup.css">
</head>
<body>
	<div class="wrapper">
		<!-- ボディヘッダー呼び出し -->
		<jsp:include page="./jsp/header.jsp" />

		<article>
			<section class="signup-main">
				<h3 class="a_cnf_index">登録内容の確認</h3>
				<form action="UserManagement" method="POST">
					<table>
						<tr>
							<td class="signup-w">ユーザID</td>
							<td><%=getUserData.get(0) %></td>
						</tr>
						<tr>
							<td>ﾊﾟｽﾜｰﾄﾞ</td>
							<td>
								<%for(int i = 0;i <password.length();i++){ %> ● <%} %> <input
								type="hidden" name="password" value="<%=password%>" />
							</td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><%=email %> <input type="hidden" name="email"
								value="<%=email%>" /></td>
						</tr>
						<tr>
							<td>氏名</td>
							<td><%=getUserData.get(2)%><%=getUserData.get(3)%></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td><%=getUserData.get(4)  %></td>
						</tr>
						<tr>
							<td>性別</td>

							<td><%=getUserData.get(5)%></td>
						</tr>
						<tr>
							<td>郵便番号</td>
							<td><%=postal_code %> <input type="hidden"
								name="postal_code" value="<%=postal_code%>" /></td>
						</tr>
						<tr>
							<td>住所</td>
							<td><%=address_prefectures %> <input type="hidden"
								name="address_prefectures" value="<%=address_prefectures%>" /> <br>
								<%=street_address%> <input type="hidden" name="street_address"
								value="<%=street_address%>" /></td>
						</tr>
					</table>
					<div class="kakunin">
						<p>入力内容に誤りが無いかご確認ください</p>
					</div>
					<input type="button" value="修正する" onClick="history.back()"
						class="btn_back"> <input type="hidden" name="flag"
						value="altercfm" /> <input type="submit" value="登録する" class="btn">
				</form>
			</section>

		</article>

		<footer>
			<!-- フッター呼び出し -->
			<jsp:include page="./jsp/footer.jsp" />
		</footer>
	</div>

</body>
</html>