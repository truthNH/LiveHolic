<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	//セッションから値を取得
	String customerLogin = (String)session.getAttribute("customerLogin"); 
	
	//DAOオブジェクト作成
	DaoBuy daoB = new DaoBuy();
	ArrayList<Object> getSalesData= new ArrayList<Object>();
	getSalesData = daoB.getSalesData(customerLogin);
	
	DaoReview daoR = new DaoReview();
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>決済履歴</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/history.css">
<link rel="stylesheet" href="./css/mypage.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<jsp:include page="./jsp/left_navi.jsp" />

			<section class="section-main rt">
				<%
          if(!getSalesData.isEmpty()){
          %>
				<table>
					<tr>
						<td class="day">決済日</td>
						<td class="shohin" colspan="3">商品番号/タイトル/アーティスト</td>
						<td>商品区分/販売形式</td>
						<td class="price">価格（税込み）</td>
						<td class="creka">決済方法</td>
						<td class="dl">ダウンロード</td>
						<td class="review">レビュー</td>
					</tr>
					<%
				for (int i = 0; i < getSalesData.size(); i++) {
			%>
					<tr>
						<td><%=getSalesData.get(i++)%></td>
						<td><img class="gazou"
							src="./images/<%=getSalesData.get(i++)%>.jpg"></td>
						<td colspan="2"><%=getSalesData.get(i++)%>/<%=getSalesData.get(i++)%>/<%=getSalesData.get(i++)%></td>
						<td><%=getSalesData.get(i++)%><br /><%=getSalesData.get(i++)%></td>
						<td><%=getSalesData.get(i++)%>円</td>
						<td><%=getSalesData.get(i)%></td>
						<td>
							<form method="POST" action="DownloadServlet">
								<input type="hidden" name="filename"
									value="<%=getSalesData.get(i-6)%>"> <input
									type="hidden" name="fileformat"
									value="<%=getSalesData.get(i-2)%>"> <input
									type="submit" value="ダウンロード">
							</form>
						</td>
						<td>
							<form action="review.jsp">
								<input type="hidden" name="product_id"
									value="<%=getSalesData.get(i-6)%>">
								<% 
          		 	int ReviewFlag = daoR.FinReviewCheck(customerLogin,(String)getSalesData.get(i-6));
          		 	if(ReviewFlag != 1){
          		 %>
								<input type="submit" value="レビューする">
								<%
          		 	}else{
         		 %>
								レビュー済
								<%
          		 	}
         		 %>
							</form>
						</td>
					</tr>
					<%
				}
			%>
				</table>
				<%
          }else{
          %>
				<p>ご購入履歴がありません</p>
				<%
          }
          %>
			</section>
		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>