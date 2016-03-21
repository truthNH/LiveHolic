<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="jp.ac.hal.tokyo.liveholic.*,java.util.ArrayList,java.util.Arrays;"%>
<%
	//DaoShowProductsオブジェクトの宣言
	DaoShowProducts dao = new DaoShowProducts();
	//パラメータ取得
	@SuppressWarnings("unchecked")
	ArrayList<Object> liveId = (ArrayList<Object>) request
			.getAttribute("liveId");
	//ArrayList<Object>の宣言
	ArrayList<Object> liveName = new ArrayList<Object>();
	ArrayList<Object> liveArthist = new ArrayList<Object>();
	ArrayList<Object> venueName = new ArrayList<Object>();
	//変数の初期化
	String param = "";
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>商品一覧</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/index.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<!-- コンテンツ上部呼び出し -->
			<jsp:include page="./jsp/mainTop.jsp" />
			<div class="main-big">
				<div class="main-in right">
					<section class="main-big-main1">
						<h4 class="index">商品一覧</h4>
						<div class="itiran">
							<!-- ライブID全件分処理を繰り返す -->
							<%
								for (int i = 0; i < liveId.size(); i++) {
							%>
							<div class="shohin_i">

								<%
									//ライブIDをparamに格納
										param = liveId.get(i).toString();
										//ライブ名の取得
										liveName = dao.getLiveName(param);
										//ライブアーティストの取得
										liveArthist = dao.getLiveArthist(param);
										//ライブ会場名の取得
										venueName = dao.getVenueName(param);
								%>
								<!-- オンクリックでformを起動 -->
								<a href="" onclick="document.form<%=i%>.submit(); return false;">
									<img src="./images/<%=liveId.get(i)%>.jpg" alt="" width="100%"
									height="100%" /> <!-- ライブ名の出力 -->
									<div class="index-livename"><%=liveName.get(0).toString()%></div>
									<!-- アーティスト名の出力 -->
									<div class="index-arthist">
										<%
											for (int j = 0; j < liveArthist.size(); j++) {
										%>
										<span><%=liveArthist.get(j)%></span>
										<%
											}
										%>
									</div> <!-- ライブ会場名の出力 -->
									<div class="index-venuename"><%=venueName.get(0)%></div>

								</a>
								<!-- hiddenでvalueにライブIDをセット -->
								<form name="form<%=i%>" method="POST"
									action="ProductDetailsServlet">
									<input type="hidden" name="liveId" value="<%=param%>">
								</form>
							</div>
							<%
								}
							%><!-- endfor -->
						</div>
					</section>

				</div>

				<!-- 左カラム(ランキング)呼び出し -->
				<jsp:include page="./jsp/lanking.jsp" />
			</div>

		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>