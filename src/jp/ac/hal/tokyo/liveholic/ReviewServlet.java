package jp.ac.hal.tokyo.liveholic;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
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
		
		//エラーフラグ
		boolean err = false;
		//エラーメッセージ
		String msg ="";
		//セッションを生成
		HttpSession session = request.getSession(true);
		
		//DAOオブジェクト作成
		DaoReview dao = new DaoReview();
		
		//戻り値
		int ret = 0;
		
		//ArrayListオブジェクト作成
		ArrayList<Object> reviewData = new ArrayList<Object>();
		
		//リクエストから値を取得
		String product_id = request.getParameter("product_id");
	
		String contributor = request.getParameter("contributor");
		
		String star = request.getParameter("star");
	
		String review_content = request.getParameter("review_content");
		
		
		try{
			//contributorが10文字以上なら
			if(contributor.length() > 10){
				err = true;
				msg = msg + "ユーザ名は10文字以内で入力してください。" + "<br />";
			}
			//contributorがnullか空なら
			else if(contributor == null || contributor.isEmpty()){
				//contributorを名無し設定
				contributor = "名無し";
			}
			
			//starがnullか空なら
			if(star == null || star.isEmpty()){
				err = true;
				msg = msg + "☆の数を選択してください。" + "<br />";
			}
			
			//review_contentがnullか空か255文字より多い場合
			if(review_content == null || review_content.isEmpty() || review_content.length()>255){
				err = true;
				msg = msg + "レビューは255文字以内で入力してください。" + "<br />";
			}
			
		}catch(Exception e){
			err = true;
			msg = "不明なエラーが発生しました。";
		}
		
		//エラーがない場合
		if(!err){
		
			//セッションからcustomer_idを取得
			String customer_id = (String)session.getAttribute("customerLogin");
			//登録日の日付
			Date date = new Date();
			SimpleDateFormat rd = new SimpleDateFormat("yyyyMMddHHmmss");
			String review_date = rd.format(date);
			
			//値をArrayListオブジェクトreviewDataに設定
			reviewData.add(customer_id);
			reviewData.add(product_id);
			reviewData.add(contributor);
			reviewData.add(star);
			reviewData.add(review_content);
			reviewData.add(review_date);
			
			//DAOオブジェクトのaddUserDataメソッド実行
			ret = dao.addReviewData(reviewData);
		
			if(ret != 1)
			{
				msg = "データベース処理で失敗しました。";
			}else{
				msg = "レビューを投稿しました。";
			}
		}	
			//review.jspへのディスパッチオブジェクト作成
			String dispResult = "review.jsp";
			RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			
			response.setContentType("text/html; charset=UTF-8");
			//リクエストにliveIdを設定
			request.setAttribute("product_id",product_id);
			//リクエストにメッセージを設定
			request.setAttribute("msg",msg);
			
			resultDisp.forward(request, response);
			
	}

}
