package jp.ac.hal.tokyo.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AdminControllerServlet
 */
@WebServlet("/AdminControllerServlet")
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminControllerServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		//パラメータ取得
		String login = request.getParameter("submit");
		String logout = request.getParameter("logout");

		// Beanオブジェクト生成
		AdminUserBean adminUser = new AdminUserBean();

		// DAOオブジェクト生成
		DaoAdminUser checkAdminUser = new DaoAdminUser();
		DaoAdminUser delUser = new DaoAdminUser();
		DaoAdminUser delReq = new DaoAdminUser();

		// エラーメッセージ格納のArrayList宣言
		ArrayList<String> errMsg = new ArrayList<String>();

		// セッションオブジェクト生成
		HttpSession session = request.getSession();
		// セッション最大時間設定
		session.setMaxInactiveInterval(120000);
		
		// メッセージ設定
		final String errLoginMsg = "ログインに失敗しました。ユーザ名とパスワードが一致しません。";
		final String DEL_USER_SUCCESS_MSG = "ユーザを消去しました。";
		final String DEL_USER_FAIL_MSG = "ユーザを消去に失敗しました。";
		final String DEL_REQ_SUCCESS_MSG = "件のリクエストを消去しました。";
		final String DEL_REQ_FAIL_MSG = "リクエスト消去に失敗しました。";
		String msg = "";

		// リンク設定
		final String urlLogin = "admin-login.jsp";
		final String urlIndex = "admin-index.jsp";
		final String dbresult = "admin-db-result.jsp";

		// 戻り値
		int ret = 0;
		
		//ログアウト処理
		if (logout != null) {
			//セッション削除
			session.removeAttribute("adminUser");;
			//ログインページへリダイレクト
			response.sendRedirect(urlLogin);
		} else if (login != null) {
			// 正常なログイン処理かチェック
			// ユーザ名をオブジェクトに格納
			adminUser.setUser(request.getParameter("user"));
			if (adminUser.getErr()) {
				errMsg.add(adminUser.getErr_msg());
			}

			// パスワードをオブジェクトに格納
			adminUser.setPasswd(request.getParameter("passwd"));
			if (adminUser.getErr()) {
				errMsg.add(adminUser.getErr_msg());
			}

			// エラーが無い場合
			if (errMsg.isEmpty()) {
				// DB処理実行
				ret = checkAdminUser.chkLogin(adminUser);
				
				if (ret == 1) {
					// ログイン処理成功
					session.setAttribute("adminUser", adminUser);
					// 管理者画面トップページへ遷移処理
					response.sendRedirect(urlIndex);
				} else {
					// ログイン処理失敗
					errMsg.add(errLoginMsg);

					// 転送するリクエストにパラメータ名と値を追加
					request.setAttribute("errMsg", errMsg);

					// admin-login.jspへのディスパッチオブジェクト作成
					RequestDispatcher retDisp = request
							.getRequestDispatcher(urlLogin);

					// admin-product.jspへリクエストを転送
					retDisp.forward(request, response);
				}
				
			} else {
				// エラーがあった場合
				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");

				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("errMsg", errMsg);

				// admin-login.jspへのディスパッチオブジェクト作成
				RequestDispatcher retDisp = request
						.getRequestDispatcher(urlLogin);

				// admin-product.jspへリクエストを転送
				retDisp.forward(request, response);
			}
		}
		
		//ユーザ消去処理
		if (request.getParameter("userDel") != null) {
			//カスタマーIDの受け取り
			int customer_id = Integer.parseInt(request.getParameter("customer_id"));
			
			//ユーザ消去処理実行
			ret =  delUser.deleteUser(customer_id);
			
			//DB処理成功
			if (ret == 1) {
				msg = DEL_USER_SUCCESS_MSG;
			//DB処理失敗	
			} else {
				msg = DEL_USER_FAIL_MSG;
			}
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");

			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("msg", msg);

			// admin-db-result.jspへのディスパッチオブジェクト作成
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dbresult);

			// admin-db-result.jspへリクエストを転送
			retDisp.forward(request, response);
			
		}
		
		if (request.getParameter("reqDel") != null) {
			//ライブ名受け取り
			String live_name = request.getParameter("req-live");
			
			//リクエスト消去処理実行
			ret = delReq.delRequest(live_name);
			
			//DB処理成功
			if (ret != -1) {
				msg = ret + DEL_REQ_SUCCESS_MSG;
			//DB処理失敗	
			} else {
				msg = DEL_REQ_FAIL_MSG;
			}
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");

			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("msg", msg);

			// admin-db-result.jspへのディスパッチオブジェクト作成
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dbresult);

			// admin-db-result.jspへリクエストを転送
			retDisp.forward(request, response);
		}
	}
}
