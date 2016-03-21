<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	//次へ進むのリンク
	//ログイン処理が完了していなかった場合
	String link = "signin.jsp";
	//
	String customerLogin = (String)session.getAttribute("customerLogin");
	//ログイン処理が完了してた場合
	if(customerLogin != null && !customerLogin.isEmpty())
	{
		link = "CartDoubleCheck";
	}
	String[] cartList = (String[]) request.getAttribute("cartList");
	/* session.invalidate(); */
	ArrayList<Object> productDetails = new ArrayList<Object>();
	//DAOオブジェクト生成
	DaoShowProducts dao = new DaoShowProducts();
	//商品詳細取得
	productDetails = dao.getPurchaseProducts(cartList);
	//商品合計金額取得
	int sum = dao.getTotalAmount(cartList);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>買い物かご</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/cart.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="cart-section">
				<h3 class="index">買い物かご</h3>
				<%
					if (! productDetails.isEmpty()) {
				%>

				<form action=<%=link%> method="POST">
					<input type="button" value="買い物を続ける" onClick="history.back()"
						class="btn_back2"> <input type="submit" value="ご購入手続きへ進む"
						class="btn2">
				</form>
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
				<form action=<%=link%> method="POST">
					<input type="button" value="買い物を続ける" onClick="history.back()"
						class="btn_back"> <input type="submit" value="ご購入手続きへ進む"
						class="btn">
				</form>
			</section>
			<%
				} else {
			%>
			<p class="message-1">カートに商品がありません</p>
			<%
				}
			%>

			<section class="cart-section2">
				<h3 class="index">買い物かごについて</h3>
				<div class="cart-desc">
					<p style="text-align: center; margin-top: 10px;">ショッピングカートの商品は入れたり出したり自由にできます。</p>
					<p style="text-align: center; margin-top: 10px;">気に入った商品を見つけたら、カートに入れて保存しておくこともできます。</p>
				</div>
			</section>

		</article>
	</div>
</body>
<footer>
	<!-- フッター呼び出し -->
	<jsp:include page="./jsp/footer.jsp" />
</footer>

</html>