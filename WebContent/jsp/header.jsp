<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//ログインフラグ設定
	boolean loginflag = false;
	//セッションから値を取得
	String customerLogin = (String) session
			.getAttribute("customerLogin");
	//メッセージがnullもしくは空でない場合
	if (customerLogin != null && !customerLogin.isEmpty()) {
		//ログインフラグをtrueに
		loginflag = true;
	}
%>
<script src="./js/checkForm.js"></script>
<header class="clearfix">
	<div class="hd-in">
		<div class="logo left">
			<a href="index.jsp"><img src="./images/logo.png" width="100%"></a>
		</div>

		<div class="hd1 left">
			<p class="tag-line left">ライブの無い生活なんて考えられない! NOLIVE NOLIFE ～ノーライブ
				ノーライフ～</p>
			<%
				if (loginflag) {
			%>
			<a href="#" onClick="document.formso.submit();"><p
					class="login left">ログアウト</p></a>
			<form name="formso" method="POST" action="UserManagement">
				<input type="hidden" name="flag" value="signout" />
			</form>
			<%
				} else {
			%>
			<a href="signin.jsp"><p class="login left">ログイン</p></a>
			<%
				}
			%>
		</div>

		<nav>
			<ul>
				<a href="index.jsp"><li>TOP</li></a>
				<a href="" onclick="document.formj.submit(); return false;"><li>JPOP</li></a>
				<a href="" onclick="document.formr.submit(); return false;"><li>ROCK</li></a>

				<a href="" onclick="document.forma.submit(); return false;"><li>ANIME</li></a>

				<a href="" onclick="document.formf.submit(); return false;"><li>FES</li></a>
			</ul>
		</nav>

		<!-- <nav>
      <ul>
        <li><a href="index.jsp"><img src="./images/navi_top.png"
            alt="top">
        </a></li>
        <li><a href="" onclick="document.formj.submit(); return false;"><img
            src="./images/navi_jpop.png" alt="jpop"></a></li>
        <li><a href="" onclick="document.formr.submit(); return false;"><img
            src="./images/navi_rock.png" alt="rock"></a></li>

        <li><a href="" onclick="document.forma.submit(); return false;"><img
            src="./images/navi_anime.png" alt="anime"></a></li>

        <li><a href="" onclick="document.formf.submit(); return false;"><img
            src="./images/navi_fes.png" alt="content"></a></li>
      </ul>
    </nav>   -->


		<!-- ナビのhiddenパラメータここから -->
		<form name="formj" method="POST" action="LiveGenreServlet">
			<input type="hidden" name="liveGenre" value="J">
		</form>
		<form name="formr" method="POST" action="LiveGenreServlet">
			<input type="hidden" name="liveGenre" value="R">
		</form>
		<form name="forma" method="POST" action="LiveGenreServlet">
			<input type="hidden" name="liveGenre" value="A">
		</form>
		<form name="formf" method="POST" action="LiveGenreServlet">
			<input type="hidden" name="liveGenre" value="F">
		</form>
		<!-- end -->

		<div class="hd2 left">
			<span class="se left">
				<form action="SearchKeywordServlet" method="POST"
					name="keywordSearch" onsubmit="return checkForm()">
					<input type="search" placeholder="keyword" class="search"
						name="keyword" maxlength="30"> <input type="image"
						class="go" src="./images/search.gif">
				</form>
			</span>
			<p class="buy left">
				<a href="" onclick="document.formCart.submit(); return false;">買い物かご</a>
			</p>
			<form name="formCart" method="POST" action="PurchaseServlet">
				<input type="hidden">
			</form>
			<p class="inpt left">
				<%if(loginflag){ %>
				<a href="mypage.jsp">マイページ </a>
				<%}else{ %>
				<a href="signup.jsp">新規登録</a>
				<%} %>
			</p>
		</div>
	</div>
</header>