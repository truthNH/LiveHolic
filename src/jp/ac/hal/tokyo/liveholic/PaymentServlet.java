package jp.ac.hal.tokyo.liveholic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
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
				
		//リクエストから値を取得
		String paymentId = request.getParameter("paymentId");

		String msg = "";
		
		//paymentIdがnullでなければ
		if(paymentId != null){
			String dispResult = "buy-cfm.jsp";
			RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			//文字コード
			response.setContentType("text/html; charset=UTF-8");
		
			//リクエストに値を設定
			request.setAttribute("paymentId",paymentId);
			//リクエストを転送
			resultDisp.forward(request, response);
		}
		else{
			msg = "支払い方法を選択してください。";
			String dispResult = "buy.jsp";
			RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			//文字コード
			response.setContentType("text/html; charset=UTF-8");
			//リクエストにメッセージを設定
			request.setAttribute("msg",msg);
			
			//リクエストを転送
			resultDisp.forward(request, response);
		}
			
	}

}
