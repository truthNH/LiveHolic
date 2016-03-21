<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="jp.ac.hal.tokyo.liveholic.*,java.util.ArrayList,java.util.Arrays;"%>
<%
	//DaoShowProductsオブジェクトの宣言
	DaoShowProducts dao = new DaoShowProducts();
	//ArrayList<Object>の宣言
	ArrayList<Object> liveId = new ArrayList<Object>();
	ArrayList<Object> liveName = new ArrayList<Object>();
	ArrayList<Object> liveArthist = new ArrayList<Object>();
	ArrayList<Object> venueName = new ArrayList<Object>();
	//変数の初期化
	String msg = "";
	String param = "";
	boolean err = false;
	try {
		//getListに商品一覧を格納
		liveId = dao.getLiveId();
	} catch (NullPointerException e) {
		err = true;
		msg = "データが登録されていません。";
	} catch (Exception e) {
		err = true;
		msg = "不明なエラーが発生しました。";
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>LIVE_HOLIC_TOP</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/index.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/jquery.bxslider.min.js"></script>
<link href="./css/jquery.bxslider.css" rel="stylesheet" />
<script type="text/javascript">
        $(document).ready(function(){
            $('.bxslider').bxSlider({
                auto: true,
                pause: 7000,
                
            });
        });
</script>
</head>
<body>
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<!-- ボディヘッダー呼び出し -->
		<article>
			<!-- コンテンツ上部呼び出し -->
			<jsp:include page="./jsp/mainTop.jsp" />
			<div class="main-big clearfix">
				<div class="main-in right">
					<section class="main-big-main2">
						<h4 class="index">特集</h4>
						<img src="./images/perfume.jpg" width="714px">
						<div class="shohin_i">
							<a href="" onclick="document.feature1.submit(); return false;">
								<img src="./images/P0004.jpg" width="100px" height="100px">
								<div class="index-livename">GAME</div>
								<div class="index-arthist">Perfume</div>
								<div class="index-venuename">Zepp Tokyo</div>
							</a>
							<form name="feature1" action="ProductDetailsServlet"
								method="POST">
								<input type="hidden" name="liveId" value="P0004">
							</form>
						</div>
						<div class="shohin_i">
							<a href="" onclick="document.feature2.submit(); return false;">
								<img src="./images/P0003.jpg" width="100px" height="100px">
								<div class="index-livename">直角二等辺三角形TOUR</div>
								<div class="index-arthist">Perfume</div>
								<div class="index-venuename">横浜アリーナ</div>
							</a>
							<form name="feature2" action="ProductDetailsServlet"
								method="POST">
								<input type="hidden" name="liveId" value="P0003">
							</form>
						</div>
						<div class="shohin_i">
							<a href="" onclick="document.feature3.submit(); return false;">
								<img src="./images/P0002.jpg" width="100px" height="100px">
								<div class="index-livename">JPN</div>
								<div class="index-arthist">Perfume</div>
								<div class="index-venuename">広島グリーンアリーナ</div>
							</a>
							<form name="feature3" action="ProductDetailsServlet"
								method="POST">
								<input type="hidden" name="liveId" value="P0002">
							</form>
						</div>
						<div class="shohin_i">
							<a href="" onclick="document.feature4.submit(); return false;">
								<img src="./images/P0001.jpg" width="100px" height="100px">
								<div class="index-livename">LEVEL3</div>
								<div class="index-arthist">Perfume</div>
								<div class="index-venuename">東京ドーム</div>
							</a>
							<form name="feature4" action="ProductDetailsServlet"
								method="POST">
								<input type="hidden" name="liveId" value="P0001">
							</form>
						</div>
						<div class="shohin_i">
							<a href="" onclick="document.feature5.submit(); return false;">
								<img src="./images/P0005.jpg" width="100px" height="100px">
								<div class="index-livename">BUDOUKaaaaaaaaaN!!!</div>
								<div class="index-arthist">Perfume</div>
								<div class="index-venuename">日本武道館</div>
							</a>
							<form name="feature5" action="ProductDetailsServlet"
								method="POST">
								<input type="hidden" name="liveId" value="P0005">
							</form>
						</div>
						<div class="shohin_i">
							<a href="" onclick="document.feature6.submit(); return false;">
								<img src="./images/P0006.jpg" width="100px" height="100px">
								<div class="index-livename">東京ドーム『 1 2 3 4 5 6 7 8 9 10
									11』</div>
								<div class="index-arthist">Perfume</div>
								<div class="index-venuename">東京ドーム</div>
							</a>
							<form name="feature6" action="ProductDetailsServlet"
								method="POST">
								<input type="hidden" name="liveId" value="P0006">
							</form>
						</div>
					</section>
					<p style="clear: both;"></p>

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
									<img src="./images/<%=liveId.get(i)%>.jpg" alt="" width="100px"
									height="100px" /> <!-- ライブ名の出力 -->
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

