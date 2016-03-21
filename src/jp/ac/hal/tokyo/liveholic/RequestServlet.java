package jp.ac.hal.tokyo.liveholic;

import java.io.IOException;
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
 * Servlet implementation class RequestServlet
 */
@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
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
		
		//RequestBeanオブジェクト生成
		RequestBean requestdata = new RequestBean();
		
		//DaoRequestオブジェクト生成
		DaoRequest dao = new DaoRequest();
		
		Date date = new Date();

		//メッセージ格納用ArrayList
		ArrayList<String> msg = new ArrayList<String>();

		//セッションを生成
		HttpSession session = request.getSession(true);
		String customer_idStr = (String)session.getAttribute("customerLogin");
		int customer_id = Integer.parseInt(customer_idStr);
		//customer_idの設定
		requestdata.setCustomer_id(customer_id);
		msg.add(requestdata.getMsg());
		
		//live_nameの設定
		requestdata.setLive_name(request.getParameter("live_name"));
		msg.add(requestdata.getMsg());
		
		//登録日時の設定
		requestdata.setAddDateTime(date);
		
		//データベース登録
		if(!requestdata.getErr())
		{
			//データベース登録処理
			dao.addRequestData(requestdata);
			msg.add(dao.getMsg());
		}
		//転送処理
		String disPage = "";
		//メッセージ転送
		disPage = "request.jsp";

		RequestDispatcher disp = request.getRequestDispatcher(disPage);
		//メッセージ転送
		request.setAttribute("msg", msg);
		disp.forward(request, response);

	}

}
