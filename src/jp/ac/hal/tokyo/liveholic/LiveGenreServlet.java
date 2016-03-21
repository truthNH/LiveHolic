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
 * Servlet implementation class LiveGenreServlet
 */
@WebServlet("/LiveGenreServlet")
public class LiveGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiveGenreServlet() {
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
		//ArrayList<Object>の宣言
		ArrayList<Object> liveId = new ArrayList<Object>();
		// パラメータを受け取り変数に格納
		String liveGenre = request.getParameter("liveGenre");
		// DAOオブジェクトの宣言
		DaoShowProducts dao = new DaoShowProducts();
		
		if (liveGenre != null) {
			try {
				liveId = dao.getLiveId(liveGenre);
				
				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");
				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("liveId", liveId);
				// shohinlist.jspへのディスパッチオブジェクト作成
				String dispResult = "shohinlist.jsp";
				RequestDispatcher retDisp = request
						.getRequestDispatcher(dispResult);
				// shohinlist.jspへリクエストを転送
				retDisp.forward(request, response);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
