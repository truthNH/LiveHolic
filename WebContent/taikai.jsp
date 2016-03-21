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
   <title>退会ページ</title>
   <link rel="stylesheet" href="./css/style.css">
   <link rel="stylesheet" href="./css/taikai.css">
  </head>
<body>
      <!-- ボディヘッダー呼び出し -->
      <jsp:include page="./jsp/header.jsp" />
  <div class="wrapper">     
      <article>
        <section class="setumei">
          <h3 class="taikai_index">退会ページ</h3>
          <p>退会を行うと以下の情報を削除します</p>
        </section>


        <section class="section-main">
          <table>
            <tr>
              <td>ユーザID</td>
              <td><%= getUserData.get(0)%></td>
            </tr>
            <tr>
              <td>メールアドレス</td>
              <td><%= getUserData.get(1)%></td>
            </tr>
            <tr>
                <td>氏名</td>
                <td><%= getUserData.get(2)%><%= getUserData.get(3)%></td>
            </tr>
            <tr>
              <td>生年月日</td>
              <td><%= getUserData.get(4)%></td>
            </tr>
            <tr>
              <td>性別</td> 
              <td><%= getUserData.get(5)%></td>
            </tr>
            <tr>
              <td>郵便番号</td> 
              <td><%= getUserData.get(6)%></td>
            </tr>
            <tr>
            <td>住所</td> 
            <td><%= getUserData.get(7)%></td>
            </tr>
          </table>
          
          
        </section>
        
        <section class="taikai-msg">
        <form action="UserManagement"  method="POST">
        	<p>パスワード:<input type="password" placeholder="英数字4文字以上20文字以内で入力してください" name="password"></p>
        	       		
       		<!-- 
	       		<table>
	              <tr>
	              <td>パスワード</td>
	              <td><input type="password" placeholder="英数字4文字以上20文字以内で入力してください" name="password"></td>
	              </tr>
	          </table>
           -->
          <p class="taikai_cfm">退会しますか？</p> 
          <input type="button" value="キャンセル" onClick="history.back()" class="btn_back">
		  <input type="hidden" name="flag" value="taikai"/>
		  <input type="submit" value="はい" class="btn">
         </form>
         <%if(!err){ %>
		        <div class="err">
		          <p><%=msg %></p>
		        </div>
		   <%} %>
         
        </section>

        
      
      </article>
  </div>
  <footer>
      <!-- フッター呼び出し -->
      <jsp:include page="./jsp/footer.jsp" />
      </footer>

</body>
</html>