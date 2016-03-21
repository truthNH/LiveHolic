<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
  
 	 //エラーフラグ設定
 	 boolean err = false;
 	 //リクエストからメッセージの取り出し
	 //メッセージ取得
	 @SuppressWarnings("unchecked")
	 ArrayList<String> msg = (ArrayList<String>)request.getAttribute("msg");
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
   <title>レビュー</title>
   <link rel="stylesheet" href="./css/style.css">
   <link rel="stylesheet" href="./css/account.css">
   <link rel="stylesheet" href="./css/mypage.css">
  </head>
<body>
<!-- ボディヘッダー呼び出し -->
      <jsp:include page="./jsp/header.jsp" />
  <div class="wrapper">
    
      
      <article>
        <section class="account-left">
          <jsp:include page="./jsp/left_navi.jsp" />
        </section>
  
        <section class="account-main">
          <div>
          <h3 class="req_index">リクエスト</h3>
           <form action="RequestServlet"  method="POST">
           		<table>
                		<tr>
							<td>ライブ名 ： 
							<input type="text" placeholder="ライブ名入力" name="live_name" size="50">
							</td>
						</tr>
						
				</table>
				<input type="button" value="戻る" onClick="history.back()" class="btn_back">
		           		
		           	 <input type="submit" value="送信する" class="btn">
            </form>
  
           <%if(!err){ %>
            <div class="err">
              <p>
              <%for(int i=0; i< msg.size(); i++)
				{
					if(msg.get(i)!=null)
					{
					%><%=msg.get(i)%>
					<%
					}
				}
              %>
              </p>
            </div>
        <%} %>
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