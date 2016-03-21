package jp.ac.hal.tokyo.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminProductServlet
 */
@WebServlet("/AdminProductServlet")
public class AdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductServlet() {
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
		
		//インスタンス生成
		AdminProductBean bean = new AdminProductBean();
		DaoAdminProduct dao = new DaoAdminProduct();
		
		//戻り値
		int ret = 0;
		
		//メッセージ宣言
		String msg = "";
		
		//定数宣言
		final String DB_ERR_MSG = "DB処理でエラーが発生しました。";
		final String DB_UPDATE_SUCCESS_MSG = "更新処理が正常に処理されました。";
		
		//エラーメッセージ宣言
		ArrayList<String> err_msg = new ArrayList<String>();
		
		/****************
		 * ライブ登録処理
		 ****************/
		if (request.getParameter("insert") != null) {
			
			//会場名取得
			bean.setVenue_name(request.getParameter("venue_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//アーティスト名名取得
			bean.setArthist_name(request.getParameter("arthist_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//レコード会社名取得
			bean.setLabel_name(request.getParameter("label_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ライブID取得
			bean.setLive_id(request.getParameter("live_id"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ライブ名取得
			bean.setLive_name(request.getParameter("live_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ジャンルID取得
			bean.setGenre_id(request.getParameter("genre_id"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//時間取得
			bean.setTotal_length(request.getParameter("total_length"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ライブ日時取得
			bean.setLive_date(request.getParameter("live_date"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//商品紹介取得
			bean.setProduct_introduction(request.getParameter("product_introduction"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//曲数取得
			if (request.getParameter("total_track") == null || request.getParameter("total_track").isEmpty()) {
				err_msg.add("曲数の入力形式が間違っています。");
			} else {
				try {
					bean.setTotal_track(Integer.parseInt(request.getParameter("total_track")));
				} catch (NumberFormatException e) {
					err_msg.add("曲数の入力形式が間違っています。");
				}
			}
			
			//エラーがない場合DB処理実行
			if (err_msg.isEmpty()) {
				//ライブ会場名をinsert
				dao.insertVenueName(bean);
				//アーティスト名をinsert
				dao.insertArthistName(bean);
				//レコード会社名をinsert
				dao.insertLabelName(bean);
				//ライブテーブルにinsert
				ret = dao.insertLive(bean);
				
				//正常終了した場合
				if (ret == 1) {
					ret = dao.insertLiveArthist(bean);
					// DBエラー
					if (ret != 1) {
						msg = DB_ERR_MSG;
					}
				} else {
					// DBエラー
					msg = DB_ERR_MSG;
				}
				
				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");
				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("msg", msg);
				
			} else {
				
				//エラーがあった場合
				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");
				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("err_msg", err_msg);
			}
			
			// admin-product.jspへのディスパッチオブジェクト作成
			String dispResult = "admin-product.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			
			// admin-product.jspへリクエストを転送
			retDisp.forward(request, response);
		}
		
		/****************
		 * ライブ更新処理
		 ****************/
		if (request.getParameter("update") != null) {
			
			//ライブID取得
			bean.setLive_id(request.getParameter("live_id"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//会場名取得
			bean.setVenue_name(request.getParameter("venue_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//アーティスト名名取得
			bean.setArthist_name(request.getParameter("arthist_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//レコード会社名取得
			bean.setLabel_name(request.getParameter("label_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ライブ名取得
			bean.setLive_name(request.getParameter("live_name"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ジャンルネーム取得
			bean.setGenre_id(request.getParameter("genre_id"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//時間取得
			bean.setTotal_length(request.getParameter("total_length"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//ライブ日時取得
			bean.setLive_date(request.getParameter("live_date"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//商品紹介取得
			bean.setProduct_introduction(request.getParameter("product_introduction"));
			if (bean.isErr()) {
				err_msg.add(bean.getErrMsg());
			}
			
			//曲数取得
			if (request.getParameter("total_track") == null || request.getParameter("total_track").isEmpty()) {
				err_msg.add("曲数の入力形式が間違っています。");
			} else {
				try {
					bean.setTotal_track(Integer.parseInt(request.getParameter("total_track")));
				} catch (NumberFormatException e) {
					err_msg.add("曲数の入力形式が間違っています。");
				}
			}
			 
			//エラーがない場合DB処理実行
			if (err_msg.isEmpty()) {
				
				//ライブ会場名をinsert
				dao.insertVenueName(bean);
				//アーティスト名をinsert
				dao.insertArthistName(bean);
				//レコード会社名をinsert
				dao.insertLabelName(bean);
				
				//ライブテーブルアップデート処理実行
				ret = dao.updateLive(bean);
				
				// DB処理成功
				if (ret == 1) {
					//LIVE_ARTHISTテーブルの更新
					ret = dao.updateLiveArthist(bean);
					// DB処理成功
					if (ret == 1) {
						msg = DB_UPDATE_SUCCESS_MSG;
					} else //DBエラー
						msg = DB_ERR_MSG;
				} else {
					// DBエラー
					msg = DB_ERR_MSG;
				}
				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");
				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("msg", msg);
			} else { // エラーがあった場合
				// 転送するパラメータの文字コード設定
				response.setContentType("text/html; charset=UTF-8");
				// 転送するリクエストにパラメータ名と値を追加
				request.setAttribute("err_msg", err_msg);
			}
			// admin-db-result.jspへのディスパッチオブジェクト作成
			String dispResult = "admin-db-result.jsp";
			
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			
			// admin-product.jspへリクエストを転送
			retDisp.forward(request, response);
		}
		
		/************
		 商品管理処理
		 ************/
		
		//商品一覧取得
		if (request.getParameter("admin-product") != null) {
			
			//パラメータ受け取り
			String live_id = request.getParameter("live_id");
			//商品取得処理
			ArrayList<AdminProductBean> allProduct = dao.getAdminProductDetails(live_id);
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("allProduct", allProduct);
			request.setAttribute("live_id", live_id);
			
			String dispResult = "admin-product-details.jsp";
			
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			
			// admin-product.jspへリクエストを転送
			retDisp.forward(request, response);
		}
		
		//商品情報更新処理
		if (request.getParameter("update-product") != null) {
			
			AdminProductBean beans = new AdminProductBean();
			//パラメータ取得しAdminProductbeanオブジェクトにセット
			beans.setProduct_id(request.getParameter("product_id"));
			beans.setProduct_name(request.getParameter("product_name"));
			beans.setArthist_name(request.getParameter("arthist_name"));
			beans.setProduct_category(request.getParameter("product_category"));
			beans.setTime(request.getParameter("time"));
			beans.setFormat(request.getParameter("format"));
			beans.setPrice(request.getParameter("price"));
			
			//アーティスト名更新処理
			dao.insertArthistName(beans);
			
			//商品更新処理
			ret = dao.updateProducts(beans);
			
			//正常処理
			if (ret == 1) {
				msg = DB_UPDATE_SUCCESS_MSG;
			//異常	
			} else {
				msg = DB_ERR_MSG;
			}
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("msg", msg);
			String dispResult = "admin-db-result.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			
			// admin-db-result.jspへリクエストを転送
			retDisp.forward(request, response);
		}
		
		//商品登録処理
		if (request.getParameter("product-entry") != null) {

			//登録日の日付
			Date date = new Date();
			SimpleDateFormat rd = new SimpleDateFormat("yyyyMMdd");
			String release_data = rd.format(date);
			
			bean.setProduct_id(request.getParameter("product_id"));
			bean.setProduct_name(request.getParameter("product_name"));
			bean.setArthist_name(request.getParameter("arthist_name"));
			bean.setLive_id(request.getParameter("live_id"));
			bean.setProduct_category(request.getParameter("product_category"));
			bean.setTime(request.getParameter("time"));
			bean.setFormat(request.getParameter("format"));
			bean.setPrice(request.getParameter("price"));
			bean.setTrack_number(request.getParameter("track_number"));
			bean.setRelease_data(release_data);
			
			//アーティスト名更新処理
			dao.insertArthistName(bean);
			
			//商品登録処理実行
			ret = dao.insertProducts(bean);
			
			//正常処理
			if (ret == 1) {
				msg = DB_UPDATE_SUCCESS_MSG;
			//異常	
			} else {
				msg = DB_ERR_MSG;
			}
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("msg", msg);
			String dispResult = "admin-db-result.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			
			// admin-db-result.jspへリクエストを転送
			retDisp.forward(request, response);
		}
		
		//商品削除処理
		if (request.getParameter("delete-product") != null) {
			
			//パラメータ受け取り
			String product_id = request.getParameter("product_id");
			
			System.out.println(product_id);
			ret = dao.deleteProduct(product_id);
			
			//正常処理
			if (ret == 1) {
				msg = DB_UPDATE_SUCCESS_MSG;
			//異常	
			} else {
				msg = DB_ERR_MSG;
			}
			
			// 転送するパラメータの文字コード設定
			response.setContentType("text/html; charset=UTF-8");
			// 転送するリクエストにパラメータ名と値を追加
			request.setAttribute("msg", msg);
			String dispResult = "admin-db-result.jsp";
			RequestDispatcher retDisp = request
					.getRequestDispatcher(dispResult);
			
			// admin-db-result.jspへリクエストを転送
			retDisp.forward(request, response);
		}
	}
}
