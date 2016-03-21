<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	String[] cartSession = (String[]) session.getAttribute("cartSession");
	
	/* session.invalidate(); */
	ArrayList<Object> productDetails = new ArrayList<Object>();
	//DAOオブジェクト生成
	DaoShowProducts daoSP = new DaoShowProducts();
	//商品詳細取得
	productDetails = daoSP.getPurchaseProducts(cartSession);
	//商品合計金額取得
	int sum = daoSP.getTotalAmount(cartSession);
	
	ArrayList<Object> getPaymentData = new ArrayList<Object>();
	//DAOオブジェクト生成
	DaoBuy daoB = new DaoBuy();
	//決済情報取得
	getPaymentData = daoB.getPaymentData();

	//エラーフラグ設定
		boolean err = false;
		//リクエストからメッセージの取り出し
		String msg = (String)request.getAttribute("msg");
		//メッセージがnullもしくは空の場合
		if(msg == null || msg.isEmpty())
		{
			err = true;
		}
	
	
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>購入画面</title>
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
				<%
					if (! productDetails.isEmpty()) {
				%>
				<table class="cart_tbl">
					<tr>
						<th>No</th>
						<th colspan="2">商品番号/商品タイトル/アーティスト</th>
						<th class="tblbox-size2">商品区分/販売形式</th>
						<th>価格(税込)</th>
						<th>取消</th>
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
						<td rowspan="3" class="del">
							<form method="POST" action="PurchaseServlet">
								<input type="hidden" name="delCart"
									value="<%=productDetails.get(i++)%>"> <input
									type="submit" value="取消">
							</form>
						</td>
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
						<td colspan="6" id="sum"><span>商品合計&nbsp;:&nbsp;<%=count%>点<br>
							<br>合計金額&nbsp;:&nbsp;<%=sum%>円
						</span></td>
					</tr>
				</table>
			</section>

			<form action="#">
				<input type="button" value="買い物かごに戻る" onClick="history.back()"
					class="btn_back3"> <input type="button" value="購入確認へ"
					onClick="document.formk.submit();" class="btn">
			</form>

			<section class="cart-section2">
				<h3 class="index">お支払方法の変更</h3>

				<form name="formk" action="PaymentServlet" method="POST">
					<table>
						<%
						for (int i = 0; i < getPaymentData.size(); i++) {
					%>
						<tr>
							<td><input type="radio" name="paymentId"
								value="<%=getPaymentData.get(i++)%>"></td>
							<td class="kessai-tbl"><%=getPaymentData.get(i)%></td>
						</tr>
						<%} %>
					</table>
				</form>
				<%
				} else {
			%>
				<p class="message-1">カートに商品がありません</p>
				<%
				}
			%>
				<%if(!err){ %>
				<br />
				<div class="err">
					<p style="text-align: center;"><%=msg %></p>
				</div>
				<%} %>


			</section>

		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>