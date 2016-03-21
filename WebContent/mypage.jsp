<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	//セッションから値を取得
	String customer_id = (String) session.getAttribute("customerLogin");
	if(customer_id == null ||customer_id.isEmpty()){
		//転送処理
		String disPage = "";
		//メッセージ転送
		disPage = "index.jsp";

		RequestDispatcher disp = request.getRequestDispatcher(disPage);
		disp.forward(request, response);
	}
	
	//DAOオブジェクト作成
	DaoUser dao = new DaoUser();
	ArrayList<Object> getUserData = new ArrayList<Object>();
	getUserData = dao.getUserData(customer_id);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/mypage.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<!-- 左カラム呼び出し -->
        	<jsp:include page="./jsp/left_navi.jsp" />
		
			<section class="main-section">
				<div class="my-akaunto">
					<h3 class="acount_index">アカウント情報</h3>
					<table>
						<tr>
							<td>ユーザID</td>
							<td><%=getUserData.get(0)%></td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><%=getUserData.get(1)%></td>
						</tr>
						<tr>
							<td>氏名</td>
							<td><%=getUserData.get(2)%><%=getUserData.get(3)%></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td><%=getUserData.get(4)%></td>
						</tr>
						<tr>
							<td>性別</td>
							<td><%=getUserData.get(5)%></td>
						</tr>
						<tr>
							<td>郵便番号</td>
							<td><%=getUserData.get(6)%></td>
						</tr>
						<tr>
							<td>住所</td>
							<td><%=getUserData.get(7)%></td>
						</tr>
					</table>
				</div>
			</section>

		</article>
	</div>
	<footer>
		<!-- フッター呼び出し -->
		<jsp:include page="./jsp/footer.jsp" />
	</footer>

</body>
</html>