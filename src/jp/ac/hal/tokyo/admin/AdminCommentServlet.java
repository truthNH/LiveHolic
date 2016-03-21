package jp.ac.hal.tokyo.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import jp.ac.hal.tokyo.liveholic.*;

/**
 * Servlet implementation class AdminCommentServlet
 */
@WebServlet("/AdminCommentServlet")
public class AdminCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//文字コード設定
		request.setCharacterEncoding("UTF-8");
		
		//Daoオブジェクト生成
		DaoAdminProduct dao = new DaoAdminProduct();
		
		//定数宣言
		final String ADMIN_COMMENT_VIEW= "admin-comment-view.jsp";
		final String ADMIN_DB_RESULT= "admin-db-result.jsp";
		final String COMMENT_DELETE_SUCCESS= "コメントを消去しました。";
		final String DB_ERR_MSG = "DB処理でエラーが発生しました。";
		
		//変数初期化
		String msg = "";
		
		//動画選択処理
		if (request.getParameter("comment") != null) {
			//パラメータ受け取り
			String product_id = request.getParameter("product_id");
			
			//ArrayList<VideoBean>オブジェクト生成
			ArrayList<VideoBean> commentData = dao.getAdminComment(product_id);
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");

			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("commentData", commentData);

			// admin-comment-view.jspへのディスパッチオブジェクト作成
			RequestDispatcher retDisp = request
					.getRequestDispatcher(ADMIN_COMMENT_VIEW);

			// admin-product.jspへリクエストを転送
			retDisp.forward(request, response);
			
		//コメント消去処理
		} else if (request.getParameter("commentDel") != null) {
			//パラメータ受け取り
			int id = Integer.parseInt(request.getParameter("id"));
			
			//戻り値
			int ret = 0;
			
			//コメント消去処理実行
			ret = dao.deleteComment(id);
			
			//DB処理成功
			if (ret == 1) {
				msg = COMMENT_DELETE_SUCCESS;
			//DB処理失敗	
			} else {
				msg = DB_ERR_MSG;
			}
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");

			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("msg", msg);

			// admin-db-result.jspへのディスパッチオブジェクト作成
			RequestDispatcher retDisp = request
					.getRequestDispatcher(ADMIN_DB_RESULT);

			// admin-db-result.jspへリクエストを転送
			retDisp.forward(request, response);
			
		}
	}
}
