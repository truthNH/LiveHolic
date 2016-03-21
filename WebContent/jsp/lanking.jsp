<%@page import="jp.ac.hal.tokyo.liveholic.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DaoShowProducts live = new DaoShowProducts();
	ArrayList<String> liveRank = live.getLiveRaking();
	ArrayList<Object> liveNameTmp = new ArrayList<Object>();
	ArrayList<Object> liveArthistTmp = new ArrayList<Object>();
	ArrayList<Object> venueNameTmp = new ArrayList<Object>();
%>
<section class="main-left">

	<div id="ranking">
		<h4 class="hd_index">ランキング</h4>
	</div>
	<%
		int count = 0;
		for (int i = 0; i < liveRank.size(); i++) {
			//ライブIDをparamに格納
			String liveTmp = liveRank.get(i).toString();
			//ライブ名の取得
			liveNameTmp = live.getLiveName(liveTmp);
			//ライブアーティストの取得
			liveArthistTmp = live.getLiveArthist(liveTmp);
			//ライブ会場名の取得
			venueNameTmp = live.getVenueName(liveTmp);
			count++;
	%>
	<div class="ranking-box">
		<a href="" onclick="document.rank<%=count%>.submit(); return false;">
			<div class="No"><%=count%></div>
			<div class="imag">
				<img src="./images/<%=liveRank.get(i).toString()%>.jpg" width="80px"
					height="80px">
			</div>
			<div class="ranking_index"><%=liveNameTmp.get(0).toString()%></div>
			<div class="ranking_index">
				<%
					for (int j = 0; j < liveArthistTmp.size(); j++) {
				%>
				<span><%=liveArthistTmp.get(j).toString()%></span>
				<%
					}
				%>
			</div>
			<div class="ranking_index"><%=venueNameTmp.get(0).toString()%></div>
		</a>
		<form name="rank<%=count%>" method="POST"
			action="ProductDetailsServlet">
			<input type="hidden" name="liveId" value="<%=liveTmp%>">
		</form>
	</div>
	<%
		}
	%>
</section>
