<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Object> liveName = (ArrayList<Object>) request
			.getAttribute("liveName");
	@SuppressWarnings("unchecked")
	ArrayList<Object> arthistName = (ArrayList<Object>) request
			.getAttribute("arthistName");
	@SuppressWarnings("unchecked")
	ArrayList<Object> venueName = (ArrayList<Object>) request
			.getAttribute("venueName");
	@SuppressWarnings("unchecked")
	ArrayList<Object> liveDetails = (ArrayList<Object>) request
			.getAttribute("liveDetails");
	@SuppressWarnings("unchecked")
	ArrayList<Object> liveGoods = (ArrayList<Object>) request
				.getAttribute("liveGoods");
	@SuppressWarnings("unchecked")
	ArrayList<Object> musicDetails = (ArrayList<Object>) request
			.getAttribute("musicDetails");
	@SuppressWarnings("unchecked")
	ArrayList<Object> movieDetails = (ArrayList<Object>) request
			.getAttribute("movieDetails");
	String liveId = (String)request.getAttribute("liveId");
	DaoReview dao = new DaoReview();
	double AvgStar = dao.getAvgStar(liveId);
	ArrayList<Object> ReviewData = new ArrayList<Object>();
	ReviewData = dao.getReviewData(liveId);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>パッケージ</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/package.css">
<link rel="stylesheet" href="./css/star.css">

<script src="./js/checkbox.js"></script>

</head>
<body>
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<!-- ボディヘッダー呼び出し -->
		<article>
			<section class="package-section1">
				<div class="package-con">
					<p><%=liveDetails.get(5)%>
					</p>
				</div>

				<div class="package-list">
					<p id="live-title"><%=liveName.get(0)%></p>
					<p id="venue">
						@<%=venueName.get(0)%></p>
					<div id="arthist-name-box">
						<%
							for (int i = 0; i < arthistName.size(); i++) {
						%>
						<span class="arthist-name"><%=arthistName.get(i)%></span>
						<%
							}
						%>
					</div>
					<div>
						<p class="label">日付</p>
						<p class="detail"><%=liveDetails.get(1)%></p>
					</div>
					<div>
						<p class="label">収録時間</p>
						<p class="detail"><%=liveDetails.get(2)%></p>
					</div>
					<div>
						<p class="label">収録曲数</p>
						<p class="detail"><%=liveDetails.get(3)%></p>
					</div>
					<div>
						<p class="label">レーベル</p>
						<p class="detail"><%=liveDetails.get(4)%></p>
					</div>
				</div>

				<div class="package-img">
					<img src="./images/<%=liveDetails.get(0)%>.jpg" width="210px"
						height="210px">
				</div>
			</section>
			<%
				if (!musicDetails.isEmpty()) {
			%>
			<section class="package-section2">
				<form action="PurchaseServlet" name="form1" method="POST">
					<%
						if (!movieDetails.isEmpty()) {
					%>
					<div class="buy-all">
						<div class="clearfix bor">
							<p class="lt stl01">NO</p>
							<p class="lt stl03">商品名</p>
							<p class="lt stl04">スペック</p>
							<p class="lt stl05">アーティスト</p>
							<p class="lt stl06">時間</p>
							<p class="lt stl07">価格</p>
							<p class="lt stl02">試聴</p>
							<p class="lt stl07">カート</p>
						</div>
						<div class="clearfix bor">
							<p class="lt stl01">ー</p>
							<p class="lt stl03"><%=movieDetails.get(0) %></p>
							<p class="lt stl04"><%=movieDetails.get(1) %></p>
							<p class="lt stl05"><%=movieDetails.get(2) %></p>
							<p class="lt stl06"><%=movieDetails.get(3) %></p>
							<p class="lt stl07"><%=movieDetails.get(4) %>円</p>
							<p class="lt stl02">ー</p>
							<p class="lt stl07">
								<input type="checkbox" name="cart"
									value="<%=movieDetails.get(5)%>">
							</p>
						</div>
						<%
							if (!liveGoods.isEmpty()) {
						%>
						<div class="clearfix bor">
							<p class="lt stl01">ー</p>
							<p class="lt stl03"><%=liveGoods.get(0) %></p>
							<p class="lt stl04"><%=liveGoods.get(1) %></p>
							<p class="lt stl05"><%=liveGoods.get(2) %></p>
							<p class="lt stl06">ー</p>
							<p class="lt stl07"><%=liveGoods.get(3) %>円</p>
							<p class="lt stl02">ー</p>
							<p class="lt stl07">
								<input type="checkbox" name="cart"
									value="<%=liveGoods.get(4)%>">
							</p>
							<%-- <p class="lt stl10"><%=liveGoods.get(0) %></p>
							<p class="lt stl04"><%=liveGoods.get(1) %></p>
							<p class="lt stl05"><%=liveGoods.get(2) %></p>
							<p class="lt stl06">ー</p>
							<p class="lt stl07"><%=liveGoods.get(3) %>円</p>
							<p class="lt stl07">
								<input type="checkbox" name="cart"
									value="<%=liveGoods.get(4)%>">
							</p> --%>
						</div>	
						<%
							}
						%>
					</div>
					<%
						}
					%>
					
					<div class="buy-single clearfix">
						<div class="clearfix bor">
							<p class="lt listen">セットリスト</p>
						</div>
						<div class="clearfix bor">
							<p class="lt stl01">NO</p>
							<p class="lt stl03">トラックタイトル</p>
							<p class="lt stl04">スペック</p>
							<p class="lt stl05">アーティスト</p>
							<p class="lt stl06">時間</p>
							<p class="lt stl07">価格</p>
							<p class="lt stl02">試聴</p>
							<p class="lt stl07">
								<input type="checkbox" name="cartall" onclick="checkBox(true)">
								全て
							</p>
						</div>
						<%
							for (int i = 0; i < musicDetails.size(); i++) {
						%>
						<div class="clearfix bor">
							<p class="lt stl01"><%=musicDetails.get(i++)%></p>
							<p class="lt stl03"><%=musicDetails.get(i++)%></p>
							<p class="lt stl04_ch"><%=musicDetails.get(i++)%></p>
							<p class="lt stl05"><%=musicDetails.get(i++)%></p>
							<p class="lt stl06"><%=musicDetails.get(i++)%></p>
							<p class="lt stl07"><%=musicDetails.get(i++)%>円
							</p>
							<audio id="<%=musicDetails.get(i)%>">
								<source src="./music/<%=musicDetails.get(i)%>.mp3">
							</audio>
							<p class="lt stl02">
								<input type="button" name="listen" value="PLAY"
									onclick="play('<%=musicDetails.get(i)%>');">
							</p>
							<p class="lt stl07">
								<input type="checkbox" name="cart"
									value="<%=musicDetails.get(i)%>">
							</p>
						</div>
						<%
							}
						%>
					</div>


					<input type="submit" value="買い物カゴに入れる" class="kaimono">
				</form>
			</section>
			<%
				} else {
			%>
			<p class="message-1">商品登録がありません</p>
			<%
				}
			%>
			
			
			<%if(!ReviewData.isEmpty()){%>
			<section class="package-section3" style="margin-top: 50px;">
				<h2>レビュー</h2>
				<h3>総合
				<%
				int n =0;
				for(int i = 0; i< Math.floor(AvgStar); i++) {
				%>
				<img src="./images/star.png" alt="" width="24" height="24"/>
				<%
				}if(AvgStar%Math.floor(AvgStar) != 0){
					n = 1;
				%>
				<img src="./images/star-herf02.png" alt="" width="24" height="24"/>
				<%
				}for(int i = 0; i< 5-(n+Math.floor(AvgStar)); i++) {
				%>
				<img src="./images/star-no.png" alt="" width="24" height="24"/>
				<%
				}
				%>
				 <%=AvgStar%> 
				 </h3>
				<br/>
				<%
					for(int i = 0; i < ReviewData.size(); i++) {
				%>
				<div class="lev-content lt">
					<h3>商品名:<%=ReviewData.get(i++)%></h3>
					<h3 class="stars">
						<%
							for(int j = 0; j < (Integer)ReviewData.get(i); j++) {
						%>
						<img src="./images/star.png" alt="" width="24" height="24"/>
						<%
							}for(int j = 0; j < 5-(Integer)ReviewData.get(i); j++) {
						%>
						<img src="./images/star-no.png" alt="" width="24" height="24"/>
						<%
							}
						%>
						</h3>
					<%i++; %>
					<p>
						投稿者(任意)&nbsp;:&nbsp;<%=ReviewData.get(i++) %><span><br>投稿日時&nbsp;:&nbsp;<%=ReviewData.get(i++)%></span>
					</p>
					<p class="lev-msg"><%=ReviewData.get(i)%></p>
				</div>
				<% } %>
			</section>
				<%
				}else{ 
				%>
				<p class="message-1">まだレビューがありません</p>
				<%
				}
				%>
		</article>
	</div>
	<script src="./js/listening.js"></script>
</body>
<footer>
	<!-- フッター呼び出し -->
	<jsp:include page="./jsp/footer.jsp" />
</footer>
</html>