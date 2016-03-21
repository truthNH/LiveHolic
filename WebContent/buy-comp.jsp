<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>購入完了</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signup.css">
<link rel="stylesheet" href="./css/cart.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="signup-main">
				<div class="kanryo">
					<p style="font-size: 20px;">購入が完了しました</p>
					<p style="font-size: 14px; margin-top: 100px;">
						<a href="download.jsp">ダウンロード画面へ</a>
					</p>
					<p style="font-size: 14px; margin-top: 20px;">
						<a href="mypage.jsp">マイページへ</a>
					</p>
				</div>
			</section>
		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>