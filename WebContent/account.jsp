<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
	//セッションから値を取得
	String customerLogin = (String)session.getAttribute("customerLogin");
	
	//DAOオブジェクト作成
	DaoUser dao = new DaoUser();
	ArrayList<Object> getUserData = new ArrayList<Object>();
	getUserData = dao.getUserData(customerLogin); 
	//エラーフラグ設定
	boolean err = false;	
	//リクエストからメッセージの取り出し
	String msg = (String)request.getAttribute("msg");
	//メッセージがnullもしくは空の場合
	if(msg == null || msg.isEmpty())
	{
		err = true;
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<!-- ヘッダー呼び出し -->
<jsp:include page="./jsp/head.jsp" />
<title>アカウント情報</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/mypage.css">
<link rel="stylesheet" href="./css/account.css">
</head>
<body>
	<!-- ボディヘッダー呼び出し -->
	<jsp:include page="./jsp/header.jsp" />
	<div class="wrapper">
		<article>
			<section class="left-section">
				<ul>
					<li><a href="mypage.jsp">マイページ</a></li>
					<li><a href="taikai.jsp">退会する</a></li>
				</ul>
			</section>

			<section class="account-main">
				<div>
					<h3 class="acount_index">アカウント情報変更</h3>
					<form action="UserManagement" method="POST">
						<table class="ac_tbl">
							<tr>
								<td class="SignupServlet">ユーザID</td>
								<td><%=getUserData.get(0)%></td>
							</tr>
							<tr>
								<td>ﾊﾟｽﾜｰﾄﾞ</td>
								<td><input type="password"
									placeholder="英数字4文字以上20文字以内で入力してください" name="password"><br>
									<input type="password" placeholder="ﾊﾟｽﾜｰﾄﾞの再入力"
									name="re_password"></td>
							</tr>
							<tr>
								<td>現在のパスワード</td>
								<td><input type="text" placeholder="現在のパスワード入力"
									name="current_password"></td>
							</tr>
							<tr>
								<td>メールアドレス</td>
								<td><input type="text" placeholder="メールアドレス入力" name="email"
									value="<%=getUserData.get(1)%>"></td>
							</tr>
							<tr>
								<td>氏名</td>
								<td><%=getUserData.get(2) %><%=getUserData.get(3)%></td>
							</tr>
							<tr>
								<td>生年月日</td>
								<td><%=getUserData.get(4)%>
							</tr>
							<tr>
								<td>性別</td>
								<td><%=getUserData.get(5)%></td>
							</tr>
							<tr>
								<td>郵便番号</td>
								<td><input type="text"
									onKeyup="this.value=this.value.replace(/[^0-9]+/i,'')"
									maxlength="7" placeholder="ハイフンなし7ｹﾀ" name="postal_code"
									value="<%=getUserData.get(6)%>"></td>
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
								</select> <br> <input type="text" placeholder="住所を入力" class="jusyo"
									name="street_address"></td>
							</tr>
						</table>
						<input type="button" value="戻る" onClick="history.back()"
							class="btn_back"> <input type="hidden" name="flag"
							value="alter" /> <input type="submit" value="確認画面へ" class="btn">
					</form>

					<%if(!err){ %>
					<div class="err">
						<p><%=msg %></p>
					</div>
					<%} %>
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