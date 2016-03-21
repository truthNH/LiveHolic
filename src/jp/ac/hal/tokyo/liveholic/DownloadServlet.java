package jp.ac.hal.tokyo.liveholic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
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
		
		//リクエストから値を取得
		String DLflag = request.getParameter("DLflag");
		
		//ダウンロードフォルダパス
		final String FILEPATH = "/Users/truth/Documents/jv23/LIVE_HOLIC/WebContent/music/";

		//メッセージ
		final String DOWNLOADFAIL = "ダウンロードに失敗しました。";
		final String NOTEXISTDLFILE = "ダウンロードするファイルが存在しません。";
		final String SUCCESSDOWNLOAD = "ダウンロードが成功しました。";

		//メッセージ格納ArrayList定義
		ArrayList<String> msg = new ArrayList<String>();

		//ダウンロードするファイル名を取得
		String filename = request.getParameter("filename");
		String fileformat = request.getParameter("fileformat");
		
		
		OutputStream out = null;
		InputStream in = null;

		//ダウンロードファイルの存在チェック
		File file = new File(FILEPATH + filename+"."+fileformat.substring(0,3).toLowerCase());

		if(file.exists())
		{

			try {

				  //response.setContentType("application/octet-stream");
				  response.setContentType("application/force-download");
				  response.setHeader("Content-Disposition","filename=\""+ filename +"."+fileformat.substring(0,3).toLowerCase()+"\"");
				  in = new FileInputStream(FILEPATH + filename + "." + fileformat.substring(0,3).toLowerCase());
				  out = response.getOutputStream();
				  byte[] buff = new byte[1024];
				  int len = 0;

				  while ((len = in.read(buff, 0, buff.length)) != -1) {
				         out.write(buff, 0, len);
				    }

				        msg.add(SUCCESSDOWNLOAD);
				    }
				    finally
				    {
				        if (in != null)
				        {
				           try {
				                in.close();
				            } catch (IOException e) {
				            	e.printStackTrace();
				            }
				        }

				        if (out != null)
				        {
				            try {
				                out.close();
				            }
				            catch (IOException e)
				            {
				            	e.printStackTrace();
				            }
				        }
				        msg.add(DOWNLOADFAIL);
				    }
			    }
			    //ファイルが存在しない場合
			    else
			    {
			    	msg.add(NOTEXISTDLFILE);
			    }
				
				System.out.print(msg+"\n");
			    //転送処理
				String disPage = "";
				if("download".equals(DLflag)){
					disPage = "download.jsp";
				}else if("history".equals(DLflag)){
					disPage = "history.jsp";
				}
			    RequestDispatcher disp = request.getRequestDispatcher(disPage);
			    request.setAttribute("msg", msg);
			    disp.forward(request, response);

		}
}
