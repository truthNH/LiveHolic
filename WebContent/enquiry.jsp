<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>お問い合わせ</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/index.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<div class="enquiry_box">
				<h3 class="enquiry_index">お問い合わせ情報の入力</h3>
				<form action="" method="post">
					<table class="enquiry_tbl">
						<tr class="takasa">
							<td class="cen">お名前</td>
							<td class="cen"><input type="text" name="enquiry_name"></td>
						</tr>
						<tr class="takasa">
							<td class="cen">メールアドレス</td>
							<td class="cen"><input type="text" name="enquiry_add"></td>
						</tr>
						<tr>
							<td class="cen">お問い合わせ項目</td>
							<td class="pad">
								<p>
									<input type="checkbox" name="enquiry_check1">アカウント関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check2">課金関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check3">機器関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check4">プレイヤー関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check5">ダウンロード関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check6">楽曲関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check7">サービス関連
								</p>
								<p>
									<input type="checkbox" name="enquiry_check8">その他
								</p>
							</td>
						</tr>
						<tr class="takasa">
							<td class="cen">お問い合わせ内容</td>
							<td class="cen"><textarea></textarea></td>
						</tr>
					</table>

					<input type="button" value="戻る" onClick="history.back()"
						class="enquiry_back"> <input type="submit"
						class="enquiry_submit">
				</form>
			</div>
		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>