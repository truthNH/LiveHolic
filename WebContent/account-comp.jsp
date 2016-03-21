<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>変更完了</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signup.css">
</head>
<body>
	<div class="wrapper">
		<!-- ボディヘッダー呼び出し -->
		<jsp:include page="./jsp/header.jsp" />

		<article>
			<section class="signup-main">
				<h4>変更完了</h4>
				<div class="kanryo">
					<p>変更が完了しました</p>
				</div>
				<p>
					<a href="index.jsp" class="top_link">TOPに戻る</a><span
						class="mypage_link"><a href="mypage.jsp">マイページへ</a></span> <input
						type="hidden" name="flag" value="2" />
				</p>
			</section>

		</article>

		<footer>
			<!-- フッター呼び出し -->
			<jsp:include page="./jsp/footer.jsp" />
		</footer>
	</div>

</body>
</html>