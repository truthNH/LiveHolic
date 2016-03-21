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
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailsServlet() {
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
		String liveId = request.getParameter("liveId");
		System.out.println(liveId);
		// DAOオブジェクトの宣言
		DaoShowProducts dao = new DaoShowProducts();
		// ArrayListオブジェクトの宣言
		ArrayList<Object> liveName = new ArrayList<Object>();
		ArrayList<Object> arthistName = new ArrayList<Object>();
		ArrayList<Object> venueName = new ArrayList<Object>();
		ArrayList<Object> liveDetails = new ArrayList<Object>();
		ArrayList<Object> musicDetails = new ArrayList<Object>();
		ArrayList<Object> movieDetails = new ArrayList<Object>();
		ArrayList<Object> liveGoods = new ArrayList<Object>();

		// 値を受け取ったかチェック
		if (liveId != null) {
			try {
				// ライブ名を取得
				liveName = dao.getLiveName(liveId);
				// アーティスト名を取得
				arthistName = dao.getLiveArthist(liveId);
				// ライブ会場名を取得
				venueName = dao.getVenueName(liveId);
				// ライブ詳細を取得
				liveDetails = dao.getLiveDetails(liveId);
				// ミュージック商品詳細を取得
				musicDetails = dao.getMusicDetails(liveId);
				// ムビー商品詳細を取得
				movieDetails = dao.getVideoDetails(liveId);
				//　ライブグッズ取得
				liveGoods = dao.getLiveGoods(liveId);

				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");
				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("liveName", liveName);
				request.setAttribute("arthistName", arthistName);
				request.setAttribute("venueName", venueName);
				request.setAttribute("liveDetails", liveDetails);
				request.setAttribute("musicDetails", musicDetails);
				request.setAttribute("movieDetails", movieDetails);
				request.setAttribute("liveGoods", liveGoods);
				
				request.setAttribute("liveId", liveId);
				// package.jspへのディスパッチオブジェクト作成
				String dispResult = "package.jsp";
				RequestDispatcher retDisp = request
						.getRequestDispatcher(dispResult);
				// package.jspへリクエストを転送
				retDisp.forward(request, response);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
}
