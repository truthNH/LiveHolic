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

import jp.ac.hal.tokyo.liveholic.DaoUser;

/**
 * Servlet implementation class UserManagement
 */
@WebServlet("/UserManagement")
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagement() {
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
		
		String flag = request.getParameter("flag");
		//エラーフラグ
		boolean err = false;
		//エラーメッセージ
		String msg ="";
		
		//セッションを生成
		HttpSession session = request.getSession(true);
		//セッションの有効時間を1日に設定
		session.setMaxInactiveInterval(86400);
		
		
		//DAOオブジェクト作成
		DaoUser dao = new DaoUser();
		
		//***新規登録のとき***
		if(flag.equals("signup"))
		{
			//戻り値
			int retUser = 0;
			int retemail = 0;
			//ArrayListオブジェクト作成
			ArrayList<Object> useridData = new ArrayList<Object>();
			ArrayList<Object> emailData = new ArrayList<Object>();
			
			//リクエストから各種パラメータを取得
			String user_id = request.getParameter("user_id");
			String password = request.getParameter("password");
			String re_password = request.getParameter("re_password");
			String email = request.getParameter("email");
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			String birth_date_y = request.getParameter("birth_date_y");
			String birth_date_m = request.getParameter("birth_date_m");
			String birth_date_d = request.getParameter("birth_date_d");
			String sex = request.getParameter("sex");
			String postal_code = request.getParameter("postal_code");
			String address_prefectures = request.getParameter("address_prefectures");
			String street_address = request.getParameter("street_address");
		
			
			try{
				//user_idエラーチェック
				if(user_id == null || user_id.isEmpty() || user_id.length()>10){
					err = true;
					msg = msg + "ユーザ名は10文字以内で入力してください。" + "<br />";
				}
				else {
					//値をArrayListオブジェクトuseridDataに設定
					useridData.add(user_id);
					//DAOオブジェクトのconfUserDataメソッド実行
					retUser = dao.confUserData(useridData);
					
					//user_idが重複する場合
					if(retUser==1){
						err = true;
						msg = msg + "そのユーザ名は既に使われています。" + "<br />";
					}
					else if(retUser==-1){
						err = true;
						msg = msg + "データベース処理で失敗しました。"+ "<br />";
					}
				}
				//passwordエラーチェック
				if(password == null || password.isEmpty() || password.length()<4 || password.length()>20 ){
					if(password != re_password){
						err = true;
						msg = msg + "パスワードが一致しません。もう一度入力してください。"+ "<br />";
					}else{
						err = true;
						msg = msg + "パスワードは4文字以上20文字以内の半角英数文字で入力して下さい。" + "<br />";
					}
				}
				//emailエラーチェック
				if(email == null || email.isEmpty()){
					err = true;
					msg = msg + "メールアドレスを入力してください。" + "<br />";
				}else {
					//値をArrayListオブジェクトemailDataに設定
					emailData.add(email);
					//DAOオブジェクトのconfEmailDataメソッド実行
					retemail = dao.confEmailData(emailData);
					
					//emailが重複する場合
					if(retemail==1){
						err = true;
						msg = msg + "そのメールアドレスは既に使われています。" + "<br />";
					}
					else if(retemail==-1){
						err = true;
						msg = msg + "データベース処理で失敗しました。"+ "<br />";
					}
				}
				//nameエラーチェック
				if(last_name == null || last_name.isEmpty() || first_name == null || first_name.isEmpty()){
					err = true;
					msg = msg + "氏名を入力してください。" + "<br />";
				}
				//birth_dateエラーチェック
				if("生年月日".equals(birth_date_y) || "月".equals(birth_date_m) || "日".equals(birth_date_d)){
					err = true;
					msg = msg + "生年月日を選択してください。" + "<br />";
				}
				//sexエラーチェック
				if(sex == null || sex.isEmpty()){
					err = true;
					msg = msg + "性別を選択してください。" + "<br />";
				}
				//postal_codeエラーチェック
				if(postal_code == null || postal_code.isEmpty() || postal_code.length()!=7){
					err = true;
					msg = msg + "郵便番号は半角数字7文字で入力してください。" + "<br />";
				}
				//addressエラーチェック
				if("都道府県を選択".equals(address_prefectures)){
					err = true;
					msg = msg + "都道府県を選択してください。" + "<br />";
				}
				if(street_address == null || street_address.isEmpty() || street_address.length()>250 ){
					err = true;
					msg = msg + "住所は250文字以内で入力してください。" + "<br />";
				}
	
			}
			catch(Exception e){
				err = true;
				msg = "不明なエラーが発生しました。";
			}
	
			
			//エラーがない場合
			if(!err){
				
				//signup-cfm.jspへのディスパッチオブジェクト作成
				String dispResult = "signup-cfm.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
				
				response.setContentType("text/html; charset=UTF-8");
								
					//リクエストに設定
					request.setAttribute("user_id",user_id);
					request.setAttribute("password",password);
					request.setAttribute("email",email);
					request.setAttribute("last_name",last_name);
					request.setAttribute("first_name",first_name);
					request.setAttribute("birth_date_y",birth_date_y);
					request.setAttribute("birth_date_m",birth_date_m);
					request.setAttribute("birth_date_d",birth_date_d);
					request.setAttribute("sex",sex);
					request.setAttribute("postal_code",postal_code);
					request.setAttribute("address_prefectures",address_prefectures);
					request.setAttribute("street_address",street_address);
					//リクエストにエラーフラグを設定
					request.setAttribute("err",err);
			
				resultDisp.forward(request, response);
			}
			//エラーの場合
			else{
				//signup.jspにメッセージを転送
				//ディスパッチャーオブジェクトの生成
				//signup.jsp"へのディスパッチオブジェクト作成
				String dispResult ="signup.jsp";
				
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
				//文字コード
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにメッセージを設定
				request.setAttribute("msg",msg);
					
				resultDisp.forward(request, response);
				
			}
				
		}
		
		//***新規登録確認のとき***
		else if(flag.equals("signupcfm")){
			
			//戻り値
			int ret = 0;
			//ArrayListオブジェクト作成
			ArrayList<Object> userData = new ArrayList<Object>();
			
			String user_id = request.getParameter("user_id");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			String birth_date_y = request.getParameter("birth_date_y");
			String birth_date_m = request.getParameter("birth_date_m");
			String birth_date_d = request.getParameter("birth_date_d");
			String sex = request.getParameter("sex");
			String postal_code = request.getParameter("postal_code");
			String address_prefectures = request.getParameter("address_prefectures");
			String street_address = request.getParameter("street_address");
		
			//性別をint型に変換
			int sex_i = Integer.parseInt(sex);
			//誕生日を統合
			String birth_date = birth_date_y + birth_date_m  + birth_date_d;
			//都道府県と住所を統合
			String address = address_prefectures + street_address;
		
			
			//登録日の日付
			Date date = new Date();
			SimpleDateFormat rd = new SimpleDateFormat("yyyyMMdd");
			String record_date = rd.format(date);
			
			//値をArrayListオブジェクトuserDataに設定
			userData.add(user_id);
			userData.add(password);
			userData.add(email);
			userData.add(last_name);
			userData.add(first_name);
			userData.add(birth_date);
			userData.add(sex_i);
			userData.add(postal_code);
			userData.add(address);
			userData.add(record_date);
			
			//DAOオブジェクトのaddUserDataメソッド実行
			ret = dao.addUserData(userData);
		
			if(ret != 1)
			{
				err = true;
				msg = "データベース処理で失敗しました。";
				System.out.print(msg);
			}
			else
			{			
				//signup-comp.jspへのディスパッチオブジェクト作成
				String dispResult = "signup-comp.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			
				response.setContentType("text/html; charset=UTF-8");
				//リクエストに値を設定
				request.setAttribute("user_id",user_id);
				request.setAttribute("password",password);
				//リクエストにエラーフラグを設定
				request.setAttribute("err",err);
			
				resultDisp.forward(request, response);
			}
		}
		
		//**サインインのとき***
		else if(flag.equals("signin"))
		{
			
			String user_id = request.getParameter("user_id");
			String password = request.getParameter("password");
			//取得データ用ArrayList
			ArrayList<Object> checkLogin = new ArrayList<Object>();
			ArrayList<Object> userData = new ArrayList<Object>(); 
			
			if(user_id != null && !user_id.isEmpty() && user_id.length()<=10 && password != null && !password.isEmpty() && password.length()<=20)
			{
				userData.add(user_id);
				userData.add(password);
						
				checkLogin = dao.checkLogin(userData);
				if(checkLogin == null )
				{
					err = true;
					msg = "ユーザ名またはパスワードが正しくありません。 入力内容をご確認ください。";
				
				}
			}else{
				err = true;
				msg = "ユーザ名またはパスワードが正しくありません。 入力内容をご確認ください。";
			
			}
			
			//エラーがない場合
			if(!err){
				//mypage.jspにgetUserDataを転送
				
				//セッション生成
				session.setAttribute("customerLogin",checkLogin.get(0));
				
				//mypage.jspへのディスパッチオブジェクト作成
				String dispResult = "mypage.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
					
				//文字コード
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにエラーフラグを設定
				request.setAttribute("err",err);
				
				resultDisp.forward(request, response);
			}
			//エラーの場合
			else{
				//signin.jspにメッセージを転送
				//signin.jspへのディスパッチオブジェクト作成
				String dispResult = "signin.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
				//文字コード
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにメッセージを設定
				request.setAttribute("msg",msg);
				//リクエストにエラーフラグを設定
				request.setAttribute("err",err);
					
				resultDisp.forward(request, response);
				
			}
		}
		
		//***サインアウトのとき***
		else if(flag.equals("signout"))
		{
			session.removeAttribute("customerLogin");
			session.removeAttribute("cartSession");
			
			//signin.jspへのディスパッチオブジェクト作成
			String dispResult = "index.jsp";
			RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			
			resultDisp.forward(request, response);
		}
		
	
		//***アカウント情報変更のとき***
		else if(flag.equals("alter"))
		{
			//戻り値
			int retpass = 0;
			int retemail = 0;
			String customer_id = (String)session.getAttribute("customerLogin");
			//ArrayListオブジェクト作成
			ArrayList<Object> data = new ArrayList<Object>();
			ArrayList<Object> emailData = new ArrayList<Object>();
			ArrayList<Object> getUserData = new ArrayList<Object>();
			
			getUserData = dao.getUserData(customer_id);
			
			//リクエストから各種パラメータを取得
			String password = request.getParameter("password");
			String re_password = request.getParameter("re_password");
			String current_password = request.getParameter("current_password");
			String email = request.getParameter("email");
			String postal_code = request.getParameter("postal_code");
			String address_prefectures = request.getParameter("address_prefectures");
			String street_address = request.getParameter("street_address");
			
			try{
				//passwordエラーチェック
				if(password == null || password.isEmpty() || password.length()<4 || password.length()>20 ){
					if(password != re_password){
						err = true;
						msg = msg + "パスワードが一致しません。もう一度入力してください。"+ "<br />";
					}else{
						err = true;
						msg = msg + "パスワードは4文字以上20文字以内の半角英数文字で入力して下さい。" + "<br />";
					}
				}
				//current_passwordエラーチェック
				if(current_password == null || current_password.isEmpty() || current_password.length()<4 || current_password.length()>20 ){
					err = true;
					msg = msg + "現在のパスワードは4文字以上20文字以内の半角英数文字で入力して下さい。" + "<br />";
				}else{
					//値をArrayListオブジェクトdataに設定
					data.add(customer_id);
					data.add(current_password);
					//DAOオブジェクトのconfpasswordDataメソッド実行
					retpass = dao.confpasswordData(data);
					if(retpass != 1){
						err = true;
						msg = msg + "現在のパスワードが正しくありません。" + "<br />";
					}else if(retpass == -1){
						err = true;
						msg = msg + "データベース処理で失敗しました。"+ "<br />";
					}
				}
				//emailエラーチェック
				if(email == null || email.isEmpty()){
					err = true;
					msg = msg + "メールアドレスを入力してください。" + "<br />";
				}else {
					//値をArrayListオブジェクトemailDataに設定
					emailData.add(email);
					//DAOオブジェクトのconfEmailDataメソッド実行
					retemail = dao.confEmailData(emailData);
					
					
					//emailが重複する場合
					if(retemail==1 && !email.equals(getUserData.get(1))){
						err = true;
						msg = msg + "そのメールアドレスは既に使われています。" + "<br />";
					}
					else if(retemail==-1){
						err = true;
						msg = msg + "データベース処理で失敗しました。"+ "<br />";
					}
					//postal_codeエラーチェック
					if(postal_code == null || postal_code.isEmpty() || postal_code.length()!=7){
						err = true;
						msg = msg + "郵便番号は半角数字7文字で入力してください。" + "<br />";
					}
					//addressエラーチェック
					if("都道府県を選択".equals(address_prefectures)){
						err = true;
						msg = msg + "都道府県を選択してください。" + "<br />";
					}
					if(street_address == null || street_address.isEmpty() || street_address.length()>250 ){
						err = true;
						msg = msg + "住所は250文字以内で入力してください。" + "<br />";
					}
				}
			}catch(Exception e){
				err = true;
				msg = "不明なエラーが発生しました。";
			}
			
			//エラーがない場合
			if(!err){
				
				//signup-cfm.jspへのディスパッチオブジェクト作成
				String dispResult = "account-cfm.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
				
				response.setContentType("text/html; charset=UTF-8");
								
					//リクエストに設定
					request.setAttribute("password",password);
					request.setAttribute("email",email);
					request.setAttribute("postal_code",postal_code);
					request.setAttribute("address_prefectures",address_prefectures);
					request.setAttribute("street_address",street_address);
					//リクエストにエラーフラグを設定
					request.setAttribute("err",err);
			
				resultDisp.forward(request, response);
			}
			
			//エラーの場合
			else{
				//signup.jspにメッセージを転送
				//ディスパッチャーオブジェクトの生成
				//signup.jsp"へのディスパッチオブジェクト作成
				String dispResult = "account.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
				//文字コード
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにメッセージを設定
				request.setAttribute("msg",msg);
					
				resultDisp.forward(request, response);
				
			}
	
		}
		
		//***アカウント登録変更確認のとき***
		else if(flag.equals("altercfm"))
		{
			//戻り値
			int ret = 0;
			//ArrayListオブジェクト作成
			ArrayList<Object> userData = new ArrayList<Object>();
			
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String postal_code = request.getParameter("postal_code");
			String address_prefectures = request.getParameter("address_prefectures");
			String street_address = request.getParameter("street_address");
			
			String customer_id = (String) session.getAttribute("customerLogin");
			
			//都道府県と住所を統合
			String address = address_prefectures + street_address;
			

			//値をArrayListオブジェクトuserDataに設定
			userData.add(password);
			userData.add(email);
			userData.add(postal_code);
			userData.add(address);
			userData.add(customer_id);
			
			//DAOオブジェクトのaddUserDataメソッド実行
			ret = dao.altUserData(userData);
		
			if(ret != 1)
			{
				err = true;
				msg = "データベース処理で失敗しました。";
				System.out.print(msg);
			}
			else
			{
				//signup-comp.jspへのディスパッチオブジェクト作成
				String dispResult = "account-comp.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにエラーフラグを設定
				request.setAttribute("err",err);
			
				resultDisp.forward(request, response);
			}
		}
		
		//***退会のとき***
		else if(flag.equals("taikai"))
		{
			//戻り値
			int ret = 0;
			//ArrayListオブジェクト作成
			ArrayList<Object> userData = new ArrayList<Object>();
			
			//セッションから値を取得
			String customer_id = (String) session.getAttribute("customerLogin");
			//リクエストから値を取得
			String password = request.getParameter("password");
			
			//パスワードチェック
			if(password == null || password.isEmpty() || password.length()<4 || password.length()>20 ){
				err = true;
				msg = msg + "パスワードは4文字以上20文字以内の半角英数文字で入力して下さい。" + "<br />";
			}else{
				//値をArrayListオブジェクトdataに設定
				ArrayList<Object> data = new ArrayList<Object>();
				data.add(customer_id);
				data.add(password);
				//DAOオブジェクトのconfpasswordDataメソッド実行
				int retpass = 0;
				retpass = dao.confpasswordData(data);
				if(retpass != 1){
					err = true;
					msg = msg + "パスワードが正しくありません。" + "<br />";
				}else if(retpass == -1){
					err = true;
					msg = msg + "データベース処理で失敗しました。"+ "<br />";
				}
			}
			
			//エラーがないとき
			if(!err)
			{
				//値をArrayListオブジェクトuserDataに設定
				userData.add(customer_id);
				userData.add(password);
			
				//DAOオブジェクトのaddUserDataメソッド実行
				ret = dao.delUserData(userData);
		
				if(ret != 1)
				{
				err = true;
				msg = "データベース処理で失敗しました。";
				System.out.print(msg);
				}
				else
				{
					//ログイン情報をセッションから削除
					session.removeAttribute("customerLogin");
				
					//taikai-comp.jspへのディスパッチオブジェクト作成
					String dispResult = "taikai-comp.jsp";
					RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
			
					resultDisp.forward(request, response);
				}
			}
			else{
				//taikai.jspへのディスパッチオブジェクト作成
				String dispResult = "taikai.jsp";
				RequestDispatcher resultDisp = request.getRequestDispatcher(dispResult);
				
				//文字コード
				response.setContentType("text/html; charset=UTF-8");
			
				//リクエストにメッセージを設定
				request.setAttribute("msg",msg);
					
				resultDisp.forward(request, response);
			}
		}
	}
}