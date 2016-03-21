<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Object> movieData = (ArrayList<Object>) request
			.getAttribute("movieData");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/streaming.css">
<link rel="stylesheet" href="./css/mypage.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<!-- 左カラム呼び出し -->
			<jsp:include page="./jsp/left_navi.jsp" />
			<section id="section-main">
			<h3 class="stream_index">ストリーミング</h3>
				<%
					if (!movieData.isEmpty()) {
						int count = 0;
						for (int i = 0; i < movieData.size(); i++) {
							count++;
				%>
				
				<div class="my-video">
					<a href=""
						onclick="document.formToVCS<%=count%>.submit(); return false;">
						<div class="thumbnail">
							<img src="./images/<%=movieData.get(i++)%>.jpg">
						</div>
						<div class="title"><%=movieData.get(i++)%></div>
						<div class="title_2"><%=movieData.get(i++)%></div>
						<div class="title">コメント数 : <%=movieData.get(i++)%></div>
					</a>
					<form action="VideoControllerServlet" method="POST"
						name="formToVCS<%=count%>">
						<input type="hidden" value="<%=movieData.get(i)%>"
							name="product_id">
					</form>
				</div>
				<%
					}
					} else {
				%>
				<p>ストリーミング商品の購入履歴がありません。</p>
				<%
					}
				%>
			</section>
		</article>
	</div>
</body>
<footer>
	<!-- フッター呼び出し -->
	<jsp:include page="./jsp/footer.jsp" />
</footer>
</html>