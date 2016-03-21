package jp.ac.hal.tokyo.liveholic;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//転送処理
		String disPage = "";
		//メッセージ転送
		disPage = "index.jsp";

		RequestDispatcher disp = request.getRequestDispatcher(disPage);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 受け取る文字コードの設定
		request.setCharacterEncoding("UTF-8");

		// パラメータを受け取り変数にセット
		String[] cart = request.getParameterValues("cart");
		String delCart = request.getParameter("delCart");
		//変数の宣言
		String[] cartList;
		// セッションの開始
		HttpSession session = request.getSession(true);

		if (cart != null) {
			// カートセッションがnullだった場合
			if (session.getAttribute("cartSession") == null) {
				cartList = cart;
				// カートセッション生成
				session.setAttribute("cartSession", cartList);

			} else { // カートセッションが既に存在する場合
				// カートセッションの要素を配列に格納
				String[] temp = (String[]) session.getAttribute("cartSession");
				// ArrayList<Object>の宣言
				ArrayList<Object> toCartList = new ArrayList<Object>();
				// カートに追加された要素がカートセッションに存在するかチェック
				// 重複しない場合カートリストにカートセッションの値を格納
				for (int i = 0; i < temp.length; i++) {
					if (!Arrays.asList(cart).contains(temp[i])) {
						toCartList.add(temp[i]);
					}
				}
				// 追加されたカートの中身をカートリストに格納
				for (int i = 0; i < cart.length; i++) {
					toCartList.add(cart[i]);
				}
				// ArrayListからString[]へキャスト
				cartList = toCartList.toArray(new String[0]);
				// カートセッション生成
				session.setAttribute("cartSession", cartList);
			}
		} else { // カートへの追加がない時の処理
			cartList = (String[]) session.getAttribute("cartSession");
			//カートリストの取り消し処理
			if (delCart != null) {
				// ArrayList<Object>の宣言
				ArrayList<Object> toCartList = new ArrayList<Object>();
				// 配列をArrayListへ変換
				for (int i = 0 ; i < cartList.length ; i++) {
					toCartList.add(cartList[i]);
				}
				//要素の削除
				toCartList.remove(toCartList.indexOf(delCart));
				// ArrayListからString[]へキャスト
				cartList = toCartList.toArray(new String[0]);
				// カートセッション生成
				session.setAttribute("cartSession", cartList);
			} //end if
		} 
		// ここからディスパッチ処理
		// レスポンスのコンテンツおよび文字コード設定
		response.setContentType("text/html; charset=UTF-8");
		// 転送するリクエストにパラメータ名と値を追加
		request.setAttribute("cartList", cartList);
		// cart.jspへのディスパッチオブジェクト作成
		String dispResult = "cart.jsp";
		RequestDispatcher retDisp = request.getRequestDispatcher(dispResult);
		// cart.jspへリクエストを転送
		retDisp.forward(request, response);
	}

}
