<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="jp.ac.hal.tokyo.admin.*, jp.ac.hal.tokyo.liveholic.*,java.util.*"%>
<jsp:useBean id="adminUser" scope="session"
	class="jp.ac.hal.tokyo.admin.AdminUserBean" />
<%
	if (session.getAttribute("adminUser") == null) {
		response.sendRedirect("admin-message.jsp");
	}
	DaoAdminUser dao = new DaoAdminUser();
	ArrayList<RequestBean> req = dao.getRequest();
	ArrayList<RequestBean> reqRank = dao.getRequestRank();
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>リクエスト管理画面</title>
<link rel="stylesheet" href="./css/admin-product.css">
</head>
<body>
	<h1>リクエスト管理画面</h1>
	<p>
		ログインユーザ：<jsp:getProperty name="adminUser" property="user" /></p>
	<div>
		<h2
			style="background: #000; color: #fff; font-size: 15px; width: 400px;">リクエストランキング</h2>
		<%
		 if (reqRank != null || reqRank.isEmpty()) {
			 int count = 0;
			 for (int j = 0 ; j < reqRank.size() ; j++) {
				count++;
		%>
		<div class="record">
			<div class="customer_id"><%=count%>位
			</div>
			<div class="live_name"><%=reqRank.get(j).getLive_name()%></div>
			<div class="req-num"><%=reqRank.get(j).getReqCount()%>件
			</div>
			<form class="req-delete" action="AdminControllerServlet"
				method="POST">
				<input type="hidden" value="<%=reqRank.get(j).getLive_name()%>"
					name="req-live"> <input type="submit" value="消去"
					name="reqDel">
			</form>
		</div>
		<%
		 	}
		 }
		%>
		<div id="">
			<div id="index">
				<div class="customer_id"
					style="background: #000; color: white; font-weight: bold;">ID</div>
				<div class="live_name"
					style="background: #000; color: white; font-weight: bold;">リクエストライブ名</div>
				<div class="req-date"
					style="background: #000; color: white; font-weight: bold;">リクエスト日時</div>
			</div>
			<%
			if (req != null || req.isEmpty()) {
				for (int i = 0; i < req.size(); i++) {
		%>
			<div class="record">
				<div class="customer_id"><%=req.get(i).getCustomer_id()%></div>
				<div class="live_name"><%=req.get(i).getLive_name()%></div>
				<div class="req-date"><%=req.get(i).getAddDateTime()%></div>
			</div>
			<%
				}
			}
		%>
		</div>
	</div>
	<p>
		<a href="admin-index.jsp">管理者トップ画面</a>
	</p>
</body>
</html>