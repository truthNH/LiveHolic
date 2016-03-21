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

import jp.ac.hal.tokyo.liveholic.DaoBuy;
import jp.ac.hal.tokyo.liveholic.DaoShowProducts;

/**
 * Servlet implementation class UserBuy
 */
@WebServlet("/UserBuy")
public class UserBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBuy() {
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
		//セッションから値を取得
		String customer_id = (String)session.getAttribute("customerLogin");
		String[] cartSession = (String[]) session.getAttribute("cartSession");
		//リクエストから値を取得
		String payment_id = request.getParameter("payment_id");
		
		//DAOオブジェクト作成
		DaoBuy dao = new DaoBuy();
		
		DaoShowProducts daoSP = new DaoShowProducts();

		//商品合計金額取得
		int total_sales = daoSP.getTotalAmount(cartSession);
		
		
		//戻り値
		int ret1 = 0;
		int ret2 = 0;
		//ArrayListオブジェクト作成
		ArrayList<Object> salesData = new ArrayList<Object>();
		//登録日の日付
		Date date = new Date();
		SimpleDateFormat rd = new SimpleDateFormat("yyyyMMddHHmmss");
		String sales_date = rd.format(date);
		
		//値をArrayListオブジェクトsalesDataに設定
		salesData.add(sales_date);
		salesData.add(customer_id);
		salesData.add(payment_id);
		salesData.add(total_sales);
		
		//DAOオブジェクトのaddSalesDataメソッド実行
		ret1 = dao.addSalesData(salesData);
	
		if(ret1 != 1)
		{
			err = true;
			msg = "データベース処理で失敗しました。1";
			System.out.print(msg);
		}
		else{
			int sales_id = dao.getSalesId(salesData);
			
			//売上詳細用ArrayListオブジェクト作成
			ArrayList<Object> data = new ArrayList<Object>();
			//値をArrayListオブジェクトdataに設定
			for(int i= 0;i < cartSession.length;i++){
				//売上金額を格納
				int proceeds = daoSP.getProceeds(cartSession[i]);
				
				data.add(sales_id);
				data.add(cartSession[i]);
				data.add(proceeds);
			}
			ret2 = dao.addSalesDetailsData(data);
			
			if(ret2 == -1){
				err = true;
				msg = "データベース処理で失敗しました。2";
				System.out.print(msg);
			}
			else if(cartSession.length == ret2){
				
				//購入商品セッション生成
				session.setAttribute("buySession",cartSession);
				//カートセッションを削除
				session.removeAttribute("cartSession");
				
				//buy-comp.jspへのディスパッチオブジェクト作成
				String dispResult = "buy-comp.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
					
				//文字コード
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにエラーフラグを設定
				request.setAttribute("err",err);
				
				resultDisp.forward(request, response);
			}
		}
	}

}
