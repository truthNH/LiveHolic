<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>ヘルプ</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="css/pulldown.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<div class="help_box">

				<!-- プルダウン01 -->

				<div class="question01">
					<input type="checkbox" id="pullDown"> <label for="pullDown">
						<div id="btn">新規登録ができない</div>
					</label>
					<ul id="sample">
						<li>入力内容に誤りがないかご確認ください。</li>
					</ul>
				</div>

				<!-- プルダウン02 -->
				<div class="question02">
					<input type="checkbox" id="pullDown2"> <label
						for="pullDown2">
						<div id="btn2">サインインできない</div>
					</label>
					<ul id="sample2">
						<li>メールアドレス・パスワードや決済情報の入力を連続で間違えた場合、セキュリティの観点から、一時的にロックがかかる仕組みになっています。しばらく時間をおいて、再度サインインをお試しください。</li>
					</ul>
				</div>

				<!-- プルダウン03 -->
				<div class="question03">
					<input type="checkbox" id="pullDown3"> <label
						for="pullDown3">
						<div id="btn3">メールアドレス・パスワードを忘れた</div>
					</label>
					<ul id="sample3">
						<li>メールアドレスが分からない場合は、申し訳ございませんが改めてアカウント登録をお願いします。<br>パスワードを忘れてしまった場合は、パスワード再発行手続きをお願いします。
						</li>
					</ul>
				</div>


			</div>
		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>