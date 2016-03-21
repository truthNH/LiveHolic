<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jp.ac.hal.tokyo.liveholic.*"%>
<%
	//エラーフラグ設定
	boolean err = false;
	//リクエストからメッセージの取り出し
	String msg = (String) request.getAttribute("msg");
	//メッセージがnullもしくは空の場合
	if (msg == null || msg.isEmpty()) {
		err = true;
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signin.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="signin-main">
				<h4 class="sinin_index">ログインページ</h4>
				<form action="UserManagement" method="POST">
					<div class="signin-box">
						<p>
							<span class="inpt_user">ユーザID</span><input type="text" name="user_id">
						</p>
						<p>
							パスワード<input type="password" name="password">
						</p>
					</div>

					<%
						if (!err) {
					%>
					<div class="err">
						<p><%=msg%></p>
					</div>
					<%
						}
					%>
					<input type="hidden" name="flag" value="signin" /> <input
						type="submit" value="ログイン" class="signin">
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