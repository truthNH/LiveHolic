package jp.ac.hal.tokyo.liveholic;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartDoubleCheck
 */
@WebServlet("/CartDoubleCheck")
public class CartDoubleCheck extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDoubleCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				//転送処理
				String disPage = "";
				//メッセージ転送
				disPage = "index.jsp";

				RequestDispatcher disp = request.getRequestDispatcher(disPage);
				disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 受け取る文字コードの設定
		request.setCharacterEncoding("UTF-8");
		
		String msg = "";
		int ret = 0;
		// セッションの開始
		HttpSession session = request.getSession(true);
		
		String customer_idStr = (String)session.getAttribute("customerLogin");
		int customer_id = Integer.parseInt(customer_idStr);
		
		String[] cartList = (String[]) session.getAttribute("cartSession");

		//DAOオブジェクト作成
		DaoBuy dao = new DaoBuy();
		
		//ArrayListオブジェクト作成
		ArrayList<Object> cartDoubleData = new ArrayList<Object>();
		
		//商品購入重複チェック
		for(int i = 0;i < cartList.length;i++){
			ret = dao.checkCart(customer_id,cartList[i]);
			if(ret >= 1){
				//重複があった場合、重複商品リストに追加
				cartDoubleData.add(cartList[i]);
			}
		}
		
		for(int i=0;i<cartDoubleData.size();i++){
		System.out.print(cartDoubleData.get(i) +"\n");
		}
		
		//重複商品リストがnull出ない場合
		if(cartDoubleData != null)
		{
			// ArrayList<Object>の宣言
			ArrayList<Object> toCartList = new ArrayList<Object>();
			ArrayList<Object> ProductName = new ArrayList<Object>();
		
			// 配列をArrayListへ変換
			for (int i = 0 ; i < cartList.length ; i++) {
				toCartList.add(cartList[i]);
			}
		
			for(int i = 0; i < cartDoubleData.size(); i++){
				//要素の削除
				toCartList.remove(toCartList.indexOf(cartDoubleData.get(i)));
			}
			// ArrayListからString[]へキャスト
			cartList = toCartList.toArray(new String[0]);
			// カートセッション生成
			session.setAttribute("cartSession", cartList);
		
			ProductName = dao.getProductName(cartDoubleData);
			for(int i = 0; i <cartDoubleData.size();i++){
				msg = msg + ProductName.get(i) +"("+cartDoubleData.get(i) +")"+ "は既に購入しています。" + "<br/><br/>";
			}
		}
		// ここからディスパッチ処理
		// レスポンスのコンテンツおよび文字コード設定
		response.setContentType("text/html; charset=UTF-8");
		// buy.jspへのディスパッチオブジェクト作成
		String dispResult = "buy.jsp";
		RequestDispatcher retDisp = request.getRequestDispatcher(dispResult);
		
		//メッセージを設定
		request.setAttribute("msg", msg);
		
		// cart.jspへリクエストを転送
		retDisp.forward(request, response);


	}

}
