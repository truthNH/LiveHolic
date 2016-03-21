<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//セッションから値を取得
	String customerLogin = (String) session
			.getAttribute("customerLogin");
%>
<section class="left-section">
	<ul>
		<li><a href="mypage.jsp">マイページ</a></li>
		<li><a href="account.jsp">アカウント情報変更</a></li>
		<li><a href="history.jsp">ご購入履歴</a></li>
		<li><a href="request.jsp">リクエストページ</a></li>
		<li><a href=""
			onclick="document.customerIdForm.submit(); return false">ストリーミング</a></li>
		<form name="customerIdForm" action="VideoControllerServlet"
			method="POST">
			<input type="hidden" name="customer_id" value="<%=customerLogin%>">
		</form>
		</li>
	</ul>
</section>