<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	////リクエストから受け取り
	String user_id = (String) request.getAttribute("user_id");
	String password = (String) request.getAttribute("password");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>登録完了</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signup.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="signup-main">
				<h4>登録完了</h4>
				<div class="kanryo">
					<p>登録が完了しました</p>
				</div>
				<p>
					<a href="index.jsp" class="top_link">TOPに戻る</a><span
						class="mypage_link"><a href="#"
						onClick="document.form1.submit();">マイページへ</a></span> <input type="hidden"
						name="flag" value="2" />
				</p>
				<form name="form1" method="POST" action="UserManagement">
					<input type="hidden" name="user_id" value="<%=user_id%>"> <input
						type="hidden" name="password" value="<%=password%>"> <input
						type="hidden" name="flag" value="signin" />
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