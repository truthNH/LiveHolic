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
 * Servlet implementation class AllDownloadServlet
 */
@WebServlet("/AllDownloadServlet")
public class AllDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllDownloadServlet() {
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
		
		 //フォーム送信値の文字コード指定
		 request.setCharacterEncoding("UTF-8");
		 		
		  //セッションを生成
		  HttpSession session = request.getSession(true);
		
		  String[] buySession = (String[]) session.getAttribute("buySession");
		  
		  ArrayList<String> formatdata = new ArrayList<String>();
		  	//DAOオブジェクト生成
			DaoShowProducts dao = new DaoShowProducts();
			//format情報取得
			formatdata = dao.getformat(buySession);
			
		  //DL日付取得
		  Date date = new Date();
		  SimpleDateFormat dld = new SimpleDateFormat("yyyyMMdd-HHmmss");
		  String dldate = dld.format(date);
		  //ダウンロードフォルダパス
		  final String FILEPATH = "/Users/truth/Documents/jv23/LIVE_HOLIC/WebContent/music/";
		  
		  ArrayList<String> fileList = new ArrayList<String>();
		  for(int i = 0; i <buySession.length;i++){
			  fileList.add(FILEPATH + buySession[i] + "." + formatdata.get(i).substring(0,3).toLowerCase());
		  }
		  
		  //fileListのファイルをZipに圧縮してDL
		  downloadZipFile.dlZipFile(
		            response,
		            dldate + ".zip",
		            fileList
		            );
		  
		  //転送処理
			String disPage = "download.jsp";
			
		    RequestDispatcher disp = request.getRequestDispatcher(disPage);
		    disp.forward(request, response);	
	}

}
