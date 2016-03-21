<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	//セッションから値を取得
	String customerLogin = (String) session.getAttribute("customerLogin");
	if(customerLogin == null ||customerLogin.isEmpty()){
	//転送処理
		String disPage = "";
		//メッセージ転送
		disPage = "index.jsp";

		RequestDispatcher disp = request.getRequestDispatcher(disPage);
		disp.forward(request, response);
	}	
	
	String[] buySession = (String[]) session.getAttribute("buySession");
	/* session.invalidate(); */
	ArrayList<Object> productDetails = new ArrayList<Object>();
	//DAOオブジェクト生成
	DaoShowProducts dao = new DaoShowProducts();
	//商品詳細取得
	productDetails = dao.getPurchaseProducts(buySession);
	
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/cart.css">
<link rel="stylesheet" href="./css/download.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">


		<article>
			<section class="cart-section">

				<table class="cart_tbl">
					<tr>
						<th>No</th>
						<th colspan="2">商品番号/商品タイトル/アーティスト</th>
						<th class="tblbox-size2">商品区分/販売形式</th>
						<th>ダウンロード</th>
					</tr>
					<%
						int count = 0;
						for (int i = 0; i < productDetails.size(); i++) {
							count++;
					%>
					<form action="DownloadServlet" method="POST">
						<tr>
							<td rowspan="3"><%=count%></td>
							<td rowspan="3" class="tblbox-size1"><img
								src="./images/<%=productDetails.get(i++)%>.jpg" width="100px"></td>
							<input type="hidden" name="filename"
								value="<%=productDetails.get(i)%>">
							<td><%=productDetails.get(i++)%></td>
							<td><%=productDetails.get(i++)%></td>
							<%i++; %>
							<td rowspan="3"><input type="hidden" name="DLflag"
								value="download"> <input type="submit" value="ダウンロード">

							</td>
						</tr>
						<%i++; %>
						<tr>
							<td><%=productDetails.get(i++)%></td>
							<input type="hidden" name="fileformat"
								value="<%=productDetails.get(i)%>">
							<td><%=productDetails.get(i++)%></td>
						</tr>
						<tr>
							<td><%=productDetails.get(i)%></td>

							<td>ライブ楽曲</td>
						</tr>
						<%
						}
					%>
					</form>
				</table>


				<br />
				<form action="AllDownloadServlet" method="POST">
					<input type="submit" value="まとめてダウンロード" class="dl">
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