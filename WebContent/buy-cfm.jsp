<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	String[] cartSession = (String[]) session.getAttribute("cartSession");
	/* session.invalidate(); */
	ArrayList<Object> productDetails = new ArrayList<Object>();
	//DAOオブジェクト生成
	DaoShowProducts dao = new DaoShowProducts();
	//商品詳細取得
	productDetails = dao.getPurchaseProducts(cartSession);
	//商品合計金額取得
	int sum = dao.getTotalAmount(cartSession);
	
	String paymentId = (String)request.getAttribute("paymentId");
	String getPaymentMethod = "";
	//DAOオブジェクト生成
	DaoBuy dao2 = new DaoBuy();
	//決済情報取得
	getPaymentMethod = dao2.getPaymentMethod(paymentId);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>購入確認</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/cart.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">

		<article>
			<section class="cart-section">
				<h3 class="index">ご購入内容</h3>
				<form action="UserBuy" method="POST">
					<table class="cart_tbl">
						<tr>
							<th>No</th>
							<th colspan="2">商品番号/商品タイトル/アーティスト</th>
							<th class="tblbox-size2">商品区分/販売形式</th>
							<th>価格(税込)</th>
						</tr>

						<%
						int count = 0;
						for (int i = 0; i < productDetails.size(); i++) {
							count++;
					%>
						<tr>
							<td rowspan="3"><%=count%></td>
							<td rowspan="3" class="tblbox-size1"><img
								src="./images/<%=productDetails.get(i++)%>.jpg" width="100px"></td>
							<td><%=productDetails.get(i++)%></td>
							<td><%=productDetails.get(i++)%></td>
							<td rowspan="3" class="price"><%=productDetails.get(i++)%>円</td>
							<%i++; %>
						</tr>
						<tr>
							<td><%=productDetails.get(i++)%></td>
							<td><%=productDetails.get(i++)%></td>
						</tr>
						<tr>
							<td><%=productDetails.get(i)%></td>
							<td>ライブ楽曲</td>
						</tr>
						<%
						}
					%>
						<tr>
							<td colspan="5" id="sum"><span>商品合計&nbsp;:&nbsp;<%=count%>点<br>
								<br>合計金額&nbsp;:&nbsp;<%=sum%>円
							</span></td>
						</tr>
					</table>

					<h3 class="index osiharai">お支払方法</h3>
					<!--  <div class="cart-desc">   -->
					<div class="cart-msg">
						<p><%=getPaymentMethod%></p>
						<p class="howToBuy">
							※お支払い方法に<%=getPaymentMethod%>が選択されています。
						</p>
					</div>
					<input type="hidden" name="payment_id" value="<%=paymentId%>">
					<input type="button" value="買い物を続ける" onClick="history.back()"
						class="btn_back"> <input type="submit" value="購入を確定する"
						class="btn">
				</form>
			</section>

		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>