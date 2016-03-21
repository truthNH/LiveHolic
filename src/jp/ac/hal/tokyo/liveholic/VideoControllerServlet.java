package jp.ac.hal.tokyo.liveholic;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class VideoControllerServlet
 */
@WebServlet("/VideoControllerServlet")
public class VideoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoControllerServlet() {
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

		// 文字コード,コンテントタイプの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		// DAOオブジェクト生成
		DaoMovieComment daoM = new DaoMovieComment();
		DaoShowProducts daoP = new DaoShowProducts();

		// カスタマーIDを受け取り購入したムービ商品を取得する
		if (request.getParameter("customer_id") != null) {

			// 変数に格納
			String customer_id = request.getParameter("customer_id");
			// ArrayListオブジェクト生成
			ArrayList<Object> movieData = new ArrayList<Object>();
			// ムービー商品取得
			movieData = daoP.getMovieData(customer_id);

			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("movieData", movieData);
			// streming.jspへのディスパッチオブジェクト作成
			String dispResult = "streaming.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			// streaming.jspへリクエストを転送
			retDisp.forward(request, response);
		}

		// 動画データ取得処理
		if (request.getParameter("product_id") != null) {
			//パラメータ受け取り
			String product_id = request.getParameter("product_id");
			//コメントデータ取得
			ArrayList<Object> commentData = daoM.getComment(product_id);
			//ビデオデータ取得
			ArrayList<Object> movieData = daoP.getMovieDetails(product_id);
			//セットリスト取得
			ArrayList<Object> setList = daoP.getSetList(product_id);
			//コメント数取得
			int commentNum = daoM.getCommentNumber(product_id);
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("product_id", product_id);
			request.setAttribute("commentData", commentData);
			request.setAttribute("movieData", movieData);
			request.setAttribute("setList", setList);
			request.setAttribute("commentNum", commentNum);
			// live-streming.jspへのディスパッチオブジェクト作成
			String dispResult = "live-streaming.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			// live-streaming.jspへリクエストを転送
			retDisp.forward(request, response);
		}

		// コメントデータを取得した場合
		if (request.getParameter("json") != null) {
			// jsonデータ受け取り
			String jsonFromClient = request.getParameter("json");
			// 戻り値
			int ret = 0;
			// JSONをPOJOに変換
			VideoBean bean = JSON.decode(jsonFromClient, VideoBean.class);
			//エラーが無い場合DB登録処理
			if (! bean.isErr()) {
				// コメントデータをDBに登録
				ret = daoM.setComment(bean);
				// DB処理判定 ret:1 成功 / ret:0 失敗
				if (ret == 1) {
					System.out.println("データ登録成功");
				} else {
					System.out.println("データ登録失敗");
				}
			}
		}
	}
}
