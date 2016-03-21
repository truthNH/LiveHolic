<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//エラーフラグ設定
	boolean err = false;
	//リクエストからメッセージの取り出し
	String msg = (String) request.getAttribute("msg");
	//メッセージがnullもしくは空の場合
	if (msg == null || msg.isEmpty()) {
		err = true;
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/signup.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="signup-main">
				<h5 class="signup_index">お客様入力情報</h5>
				<form action="UserManagement" method="POST">
					<table>
						<tr>
							<td class="SignupServlet">ユーザID</td>
							<td><input type="text" placeholder="ユーザ名入力" name="user_id" size="50">
							</td>
						</tr>
						<tr>
							<td>パスワード</td>
							<td><input type="password"
								placeholder="英数字4文字以上20文字以内で入力してください" name="password" size="30"> <input
								type="password" placeholder="パスワードﾞの再入力" name="re_password"  size="30">
							</td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><input type="text" placeholder="メールアドレス入力" name="email" size="50">
							</td>
						</tr>
						<tr>
							<td>氏名</td>
							<td><input type="text" placeholder="姓" name="last_name" size="30">
								<input type="text" placeholder="名" name="first_name" size="30"></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td><select name="birth_date_y">
									<option value="生年月日" selected="selected">生年月日</option>
									<option value="2013">2013</option>
									<option value="2012">2012</option>
									<option value="2011">2011</option>
									<option value="2010">2010</option>
									<option value="2009">2009</option>
									<option value="2008">2008</option>
									<option value="2007">2007</option>
									<option value="2006">2006</option>
									<option value="2005">2005</option>
									<option value="2004">2004</option>
									<option value="2003">2003</option>
									<option value="2002">2002</option>
									<option value="2001">2001</option>
									<option value="2000">2000</option>
									<option value="1999">1999</option>
									<option value="1998">1998</option>
									<option value="1997">1997</option>
									<option value="1996">1996</option>
									<option value="1995">1995</option>
									<option value="1994">1994</option>
									<option value="1993">1993</option>
									<option value="1992">1992</option>
									<option value="1991">1991</option>
									<option value="1989">1989</option>
									<option value="1988">1988</option>
									<option value="1987">1987</option>
									<option value="1986">1986</option>
									<option value="1985">1985</option>
									<option value="1984">1984</option>
									<option value="1983">1983</option>
									<option value="1982">1982</option>
									<option value="1981">1981</option>
									<option value="1980">1980</option>
									<option value="1979">1979</option>
									<option value="1978">1978</option>
									<option value="1977">1977</option>
									<option value="1976">1976</option>
									<option value="1975">1975</option>
									<option value="1974">1974</option>
									<option value="1973">1973</option>
									<option value="1972">1972</option>
									<option value="1971">1971</option>
									<option value="1970">1970</option>
									<option value="1969">1969</option>
									<option value="1968">1968</option>
									<option value="1967">1967</option>
									<option value="1966">1966</option>
									<option value="1966">1966</option>
									<option value="1965">1965</option>
									<option value="1964">1964</option>
									<option value="1963">1963</option>
									<option value="1962">1962</option>
									<option value="1961">1961</option>
									<option value="1960">1960</option>
							</select> <select name="birth_date_m">
									<option value="月" selected="selected">月</option>
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
							</select> <select name="birth_date_d">
									<option value="日" selected="selected">日</option>
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
							</select></td>
						</tr>
						<tr>
							<td>性別</td>
							<td><input type="radio" value="0" name="sex">男 <input
								type="radio" value="1" name="sex">女</td>
						</tr>
						<tr>
							<td>郵便番号</td>
							<td><input type="text" onKeyup="this.value=this.value.replace(/[^0-9]+/i,'')" maxlength="7" placeholder="ハイフンなし7ｹﾀ"
								name="postal_code"></td>
						</tr>
						<tr>
							<td>住所</td>
							<td><select name="address_prefectures">
									<option selected="selected">都道府県を選択</option>
									<option>北海道</option>
									<option>青森県</option>
									<option>岩手県</option>
									<option>宮城県</option>
									<option>秋田県</option>
									<option>山形県</option>
									<option>福島県</option>
									<option>茨城県</option>
									<option>栃木県</option>
									<option>群馬県</option>
									<option>埼玉県</option>
									<option>千葉県</option>
									<option>東京都</option>
									<option>神奈川県</option>
									<option>新潟県</option>
									<option>富山県</option>
									<option>石川県</option>
									<option>福井県</option>
									<option>山梨県</option>
									<option>長野県</option>
									<option>岐阜県</option>
									<option>静岡県</option>
									<option>愛知県</option>
									<option>三重県</option>
									<option>滋賀県</option>
									<option>京都府</option>
									<option>大阪府</option>
									<option>兵庫県</option>
									<option>奈良県</option>
									<option>和歌山県</option>
									<option>鳥取県</option>
									<option>岡山県</option>
									<option>広島県</option>
									<option>山口県</option>
									<option>徳島県</option>
									<option>香川県</option>
									<option>愛媛県</option>
									<option>高知県</option>
									<option>福岡県</option>
									<option>佐賀県</option>
									<option>長崎県</option>
									<option>熊本県</option>
									<option>大分県</option>
									<option>宮崎県</option>
									<option>鹿児島県</option>
									<option>沖縄県</option>
							</select> <br><br> <input type="text" placeholder="住所を入力" class="jusyo"
								name="street_address"></td>
						</tr>
					</table>
					<input type="button" value="戻る" onClick="history.back()"
						class="btn_back"> <input type="hidden" name="flag"
						value="signup" /> <input type="submit" value="確認画面へ" class="btn">
				</form>

				<%
					if (!err) {
				%>
				<div class="err">
					<p><%=msg%></p>
				</div>
				<%
					}
				%>
			</section>
		</article>
	</div>
</body>
<footer>
	<!-- フッター呼び出し -->
	<jsp:include page="./jsp/footer.jsp" />
</footer>
</html>