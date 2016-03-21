<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,jp.ac.hal.tokyo.liveholic.*"%>
<%
  //リクエストから商品IDを取得
  String product_id = request.getParameter("product_id");
  if(product_id == null || product_id.isEmpty())
  {
	  product_id = (String)request.getAttribute("product_id");
  }

  //DAOオブジェクト作成
  DaoShowProducts dao = new DaoShowProducts();
  ArrayList<Object> getliveDetails = new ArrayList<Object>();
  getliveDetails = dao.getliveDetails(product_id);
  
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
   <title>レビュー</title>
   <link rel="stylesheet" href="./css/style.css">
   <link rel="stylesheet" href="./css/account.css">
   <link rel="stylesheet" href="./css/mypage.css">
   <link rel="stylesheet" href="./css/star.css">
   <script  type="text/javascript" src="./js/jQuery-JavaScript-Library-v1.7.js"></script>
   <script  type="text/javascript" src="./js/checkstar.js"></script>
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
           
                <h3 class="acount_index">この商品についてレビューする</h3>
                <div class="revi clearfix">
                  <div class="revi_img left"><img src="./images/<%=getliveDetails.get(0)%>.jpg" alt="画像" width="120px" height="120px">
                  </div>
                  <div class="revi_expo left">
                    <p>ライブ名：<%=getliveDetails.get(1)%></p>
                    <p class="ppp">タイトル：<%=getliveDetails.get(2)%></p>
                    <p class="ppp">アーティスト名：<%=getliveDetails.get(3)%></p>
                  </div>
                </div>
                </div>
               
            <form action="ReviewServlet" method="POST">
				<div id="stars"></div>
				<input type="hidden" name="star" value="1" id="star" />
				
				<div>
                  <p class="revi_cus">投稿者名(任意)：<input type="text" name="contributor" class="revi_name"></p>
                  <p class="revi_textarea">レビュー：<textarea name="review_content" class="revi_textarea" rows="5" cols="50"></textarea></p>
                  <input type="hidden" name="product_id" value="<%=product_id%>"/>
                </div>
                  <input type="submit" value="投稿する" class="revi_btn">
            
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