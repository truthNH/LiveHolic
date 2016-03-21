<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	////リクエストから受け取り
	String user_id = (String) request.getAttribute("user_id");
	String password = (String) request.getAttribute("password");
	String email = (String) request.getAttribute("email");
	String last_name = (String) request.getAttribute("last_name");
	String first_name = (String) request.getAttribute("first_name");
	String birth_date_y = (String) request.getAttribute("birth_date_y");
	String birth_date_m = (String) request.getAttribute("birth_date_m");
	String birth_date_d = (String) request.getAttribute("birth_date_d");
	String sex = (String) request.getAttribute("sex");
	String postal_code = (String) request.getAttribute("postal_code");
	String address_prefectures = (String) request
			.getAttribute("address_prefectures");
	String street_address = (String) request
			.getAttribute("street_address");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>登録内容確認</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signup.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="signup-main">
				<h4>新規登録</h4>
				<h5>登録内容の確認</h5>
				<form action="UserManagement" method="POST">
					<table>
						<tr>
							<td class="signup-w">ユーザID</td>
							<td><%=user_id%> <input type="hidden" name="user_id"
								value="<%=user_id%>" /></td>
						</tr>
						<tr>
							<td>ﾊﾟｽﾜｰﾄﾞ</td>
							<td><%=password%> <input type="hidden" name="password"
								value="<%=password%>" /></td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><%=email%> <input type="hidden" name="email"
								value="<%=email%>" /></td>
						</tr>
						<tr>
							<td>氏名</td>
							<td><%=last_name%> <%=first_name%> <input type="hidden"
								name="last_name" value="<%=last_name%>" /> <input type="hidden"
								name="first_name" value="<%=first_name%>" /></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td><%=birth_date_y%>年<%=birth_date_m%>月<%=birth_date_d%>日
								<input type="hidden" name="birth_date_y"
								value="<%=birth_date_y%>" /> <input type="hidden"
								name="birth_date_m" value="<%=birth_date_m%>" /> <input
								type="hidden" name="birth_date_d" value="<%=birth_date_d%>" /></td>
						</tr>
						<tr>
							<td>性別</td>

							<td>
								<%
									if ("0".equals(sex)) {
								%>男<%
									} else if ("1".equals(sex)) {
								%>女<%
									}
								%> <input
								type="hidden" name="sex" value="<%=sex%>" />
							</td>
						</tr>
						<tr>
							<td>郵便番号</td>
							<td><%=postal_code%> <input type="hidden"
								name="postal_code" value="<%=postal_code%>" /></td>
						</tr>
						<tr>
							<td>住所</td>
							<td><%=address_prefectures%> <input type="hidden"
								name="address_prefectures" value="<%=address_prefectures%>" /> <br>
								<%=street_address%> <input type="hidden" name="street_address"
								value="<%=street_address%>" /></td>
						</tr>
					</table>
					<div class="kakunin">
						<p>入力内容に誤りが無いかご確認ください</p>
					</div>
					<input type="button" value="戻る" onClick="history.back()"
						class="btn_back"> <input type="hidden" name="flag"
						value="signupcfm" /> <input type="submit" value="登録する" class="btn">
				</form>
			</section>
		</article>
	</div>
</body>
<footer>
	<!-- フッター呼び出し -->
	<jsp:include page="./jsp/footer.jsp" />
</footer>
</html>