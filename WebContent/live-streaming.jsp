<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,jp.ac.hal.tokyo.liveholic.*,net.arnx.jsonic.JSON"%>
<%
	String product_id = (String) request.getAttribute("product_id");
	@SuppressWarnings("unchecked")
	ArrayList<Object> commentData = (ArrayList<Object>) request
	.getAttribute("commentData");
	@SuppressWarnings("unchecked")
	ArrayList<Object> movieData = (ArrayList<Object>) request
	.getAttribute("movieData");
	@SuppressWarnings("unchecked")
	ArrayList<Object> setList = (ArrayList<Object>) request
	.getAttribute("setList");
	int commentNum = Integer.parseInt(request.getAttribute("commentNum").toString());
%>
<!DOCTYPE html>
<html lang="ja">
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>ストリーミング</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/live-streaming.css">
<script type="text/javascript" src="./js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./js/jquery.json-2.4.js"></script>
<script type="text/javascript" src="./js/move-comment.js"></script>
<head>
<script>
	var textD = <%=JSON.encode(commentData)%>;
	var product_id = "<%=product_id%>";
</script>
</head>
<body>
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<!-- ボディヘッダー呼び出し -->
		<div id="video-all-wrapper">
			<h1>
				<span style="color: red; font-size: 25px;"><%=movieData.get(0)%></span>
				/ <span style="font-size: 20px;"><i><%=movieData.get(1)%></i></span>
			</h1>
			<div id="comment-num">
				コメント：<%=commentNum%></div>

			<div id="video-wrapper">
				<div id="viewArea">
					<video id="video" style="width: 600px;">
						<source src="./music/<%=product_id%>.mp4">
					</video>
					<div id="telop"></div>
					<div id="currentTelop"></div>
				</div>
			</div>
			<form id="controller" name="form" onsubmit="getText(); return false;">
				<input id="text" type="text" name="textArea" size="70"
					maxlength="50"><input type="button" value="COMMENT"
					onclick="getText()"> <input type="button" value="PLAY"
					id="play" onclick="playVideo()"> <input type="button"
					value="RELOAD" onclick="location.reload();">
			</form>
		</div>
		<div id="set-list">
			<h2>Setlist</h2>
			<div id="set-list-scroll">
				<%
			int count = 0;
			for (int j = 0; j < setList.size(); j++) {
				count++;
		%>
				<a class="title" href=""
					onclick="jumpTitle(<%=setList.get(j++)%>); return false;"><%=count%>.&nbsp;&nbsp;<%=setList.get(j)%></a>
				<%
			}
		%>
			</div>
		</div>
		<p style="clear: both;"></p>
		<p style="font-size: 13px; margin: 10px auto 30px auto; width: 30px;">
			<a href="javascript:history.back();">BACK</a>
		</p>
	</div>
</body>
<footer>
	<!-- フッター呼び出し -->
	<jsp:include page="./jsp/footer.jsp" />
</footer>
</html>