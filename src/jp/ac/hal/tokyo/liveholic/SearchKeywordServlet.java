package jp.ac.hal.tokyo.liveholic;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchKeywordServlet
 */
@WebServlet("/SearchKeywordServlet")
public class SearchKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchKeywordServlet() {
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
		
		//文字コード設定
		request.setCharacterEncoding("UTF-8");
		//パラメータ取得
		String keyword =  request.getParameter("keyword");
		//ArrayList<Object>宣言
		ArrayList<Object> liveId = new ArrayList<Object>();
		ArrayList<Object> searchProduct = new ArrayList<Object>();
		
		//パラメータ取得
		if (keyword != null && ! keyword.isEmpty()) {
			DaoShowProducts dao = new DaoShowProducts();
			//ライブ検索
			liveId = dao.searchLive(keyword);
			//商品検索
			searchProduct = dao.searchProduct(keyword);
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("keyword", keyword);
			request.setAttribute("liveId", liveId);
			request.setAttribute("searchProduct", searchProduct);
			
			// search.jspへのディスパッチオブジェクト作成
			String dispResult = "search.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			// search.jspへリクエストを転送
			retDisp.forward(request, response);
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
