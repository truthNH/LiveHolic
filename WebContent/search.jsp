<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Object> liveId = (ArrayList<Object>) request
			.getAttribute("liveId");
	@SuppressWarnings("unchecked")
	ArrayList<Object> searchProduct = (ArrayList<Object>) request
			.getAttribute("searchProduct");
	String keyword = (String) request.getAttribute("keyword");
	//DaoShowProductsオブジェクトの宣言
	DaoShowProducts dao = new DaoShowProducts();
	//ArrayList<Object>の宣言
	ArrayList<Object> liveName = new ArrayList<Object>();
	ArrayList<Object> liveArthist = new ArrayList<Object>();
	ArrayList<Object> venueName = new ArrayList<Object>();
	String param = "";
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>検索結果</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/search.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<div id="search-contents-box">
			<h2 class="search_index"><%=keyword%> の検索結果
			</h2>
			<%
				if (liveId.isEmpty() && searchProduct.isEmpty()) {
			%>
			<p class="message">入力されたキーワードを含む検索結果が見つかりませんでした</p>
			<%
				} else {
			%>
			<!-- ライブID全件分処理を繰り返す -->
			<article id="search-live-contents">
				<h3>
					ライブ検索結果&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=liveId.size()%>件がヒットしました
				</h3>
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
			</article>
			<article id="search-product-contents">
				<h3>
					商品検索結果&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=(searchProduct.size() / 5)%>件がヒットしました
				</h3>
				<%
					if (!searchProduct.isEmpty()) {
				%>
				<div id="product-line-index">
					<div class="product-title">商品名</div>
					<div class="live-title">ライブ名</div>
					<div class="category">商品カテゴリー</div>
					<div class="arthist-title">アーティスト名</div>
				</div>			
				<%
					int count = 0;
					for (int i = 0; i < searchProduct.size(); i++) {
						count++;
				%>
				<div class="product-line">
					<div class="product-title"><a href="" onclick="document.num<%=count%>.submit(); return false;"><%=searchProduct.get(i++)%></a></div>
					<form class="to-servlet" action="ProductDetailsServlet" method="POST" name="num<%=count%>">
						<input type="hidden" value="<%=searchProduct.get(i++)%>" name="liveId">
					</form>
					<div class="live-title"><%=searchProduct.get(i++)%></div>
					<div class="category"><%=searchProduct.get(i++)%></div>
					<div class="arthist-title"><%=searchProduct.get(i)%></div>
				</div>
				<%
						}
					}
				}
				%>
			</article>
		</div>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>