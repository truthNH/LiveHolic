<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="main-top">
	<section class="main-top-main right">
		<ul class="bxslider">
			<li><img src="./images/main_visual03.jpg" alt="" width="716px"></li>
			<li><img src="./images/main_visual04.jpg" alt="" width="716px"></li>
			<li><img src="./images/main_visual05.jpg" alt="" width="716px"></li>
		</ul>
	</section>

	<section class="main-top-left clearfix">
		<h4 class="hd_index">更新情報</h4>
		<ul class="koushin_expo">
			<li>[3/9]音楽サイト「LIVEHOLIC」オープン!!</li>
			<li class="koushin_margin"><a href=""
				onclick="document.new1.submit(); return false;">[3/9]perfumeのライブLEVEL3を追加</a></li>
			<li class="koushin_margin"><a href=""
				onclick="document.new2.submit(); return false;">[3/9]宮野真守のライブWAKENINGを追加</a></li>
			<li class="koushin_margin"><a href=""
				onclick="document.new3.submit(); return false;">[3/9]ROCK IN
					JAPAN2014 GRASS-STAGEを追加</a></li>
		</ul>
		<form name="new1" action="ProductDetailsServlet" method="POST">
			<input type="hidden" name="liveId" value="P0001">
		</form>
		<form name="new2" action="ProductDetailsServlet" method="POST">
			<input type="hidden" name="liveId" value="A0001">
		</form>
		<form name="new3" action="ProductDetailsServlet" method="POST">
			<input type="hidden" name="liveId" value="F0001">
		</form>

		<div class="koushin_bana">
			<a href="#"><img src="./images/miyano01.png" width="224px"></a>
			<a href="#"><img src="./images/perfume_bana01.png" width="224px"></a>
			<!-- 
	      <a href="#"><img src="./images/LiSA_bana01.png" width="224px"></a>
	      <a href="#"><img src="./images/bokaro01.png" width="224px"></a>
	     -->
		</div>
	</section>
</div>