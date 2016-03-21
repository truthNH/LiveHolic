package jp.ac.hal.tokyo.liveholic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data Access Object Class
 * @author yusuke.koide
 *
 */
public class DaoRequest {

	//クラス内オブジェクト変数の定義
	//コネクションオブジェクト
	private Connection conn;
	//プリペアードステートメントオブジェクト
	private PreparedStatement ps;
	//ステートメントオブジェクト
	private Statement st;
	//ResultSetオブジェクト
	private ResultSet rs;

	//エラープロパティ
	boolean err;
	//メッセージプロパティ
	String Msg;

	//定数宣言
	private final int UPDATEOK = 1;

	private final int SUCCESSREQUESTADD = 4 ;

	private final int SQLERR = -1;
	//private final int INSERTFAIL = -2;
	//private final int LOGINFAIL = -3;
	private final int REQUESTADDFAIL = -4;

	//メッセージ定数宣言
	private final String SQLERRMSG = "データベース処理に失敗しました。";

	private final String SUCCESSREQUESTADDMSG = "リクエスト送信が完了しました。";
	private final String REQUESTADDFAILMSG = "リクエスト送信に失敗しました。";

	/**
	 *  コンストラクター
	 *
	 */
	public DaoRequest()
	{

		//コネクションオブジェクト
		conn = null;
		//プリペアードステートメントオブジェクト
		ps = null;
		//ステートメントオブジェクト
		st = null;
		//ResultSetオブジェクト
		rs = null;
		err = false;
		Msg = "";
	}

	//メッセージ設定メソッド
	private void setMsg(int msgno,String err)
	{
		//異常系メッセージ
		if(msgno == SQLERR)
		{
			this.Msg = SQLERRMSG + ":" + err;
		}

		else if(msgno == REQUESTADDFAIL)
		{
			this.Msg = REQUESTADDFAILMSG;
		}
		//正常系メッセージ

		else if(msgno == SUCCESSREQUESTADD)
		{
			this.Msg = SUCCESSREQUESTADDMSG;
		}
	}
	//メッセージ取得メソッド
	public String getMsg()
	{
		return this.Msg;
	}

	//エラー取得メソッド
	public boolean getErr()
	{
		return this.err;
	}

	private void settErr(boolean err)

	{
		if(err)
		{
			this.err = err;
		}
	}

	/**
	 *
	 * 	getConnectionメソッド
	 *
	 */
	private void getConnection()
	{
		//データベース接続情報
		String url
		= "jdbc:mysql:///live_holic?user=jv23_user&password=jv23jv23&useUnicode=true&characterEncoding=utf8";

		try
		{

			//MYSQL JDBCドライバ読み込み
			Class.forName("com.mysql.jdbc.Driver");

			//コネクションの作成
			conn = DriverManager.getConnection(url);
			this.settErr(false);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			this.setMsg(SQLERR,e.getMessage());
			this.settErr(true);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			this.setMsg(SQLERR,e.getMessage());
			this.settErr(true);
		}
	}

	/**
	 *
	 * 	close()メソッド
	 *
	 *  コネクション/ステートメント/ResultSetオブジェクトのクローズ
	 *
	 * @param	:void
	 * @return :void
	 *
	 */
	private void close()
	{
		try
		{
			//ResultSetオブジェクトをクローズ
			if(rs != null)
			{
				rs.close();
			}
			//プリペアードステートメントをクローズ
			if(ps != null)
			{
				ps.close();
			}
			//ステートメントをクローズ
			if(st != null)
			{
				st.close();
			}
			//コネクションクローズ
			if(conn != null)
			{
				conn.close();
			}

			this.settErr(false);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			this.setMsg(SQLERR,e.getMessage());
			this.settErr(true);
		}
	}
	
	/**
	 * addRequestData()
	 * @param reviewData
	 * @return なし 
	 */
	
	public void addRequestData(RequestBean requestdata)
	{
			
			//プリペアードステートメントSQL定義
			String sql ="insert into REQUEST(customer_id,live_name,request_datetime) values(?,?,?);";
				
			//コネクションを作成
			this.getConnection();
				
			try
			{
				//プリペアードステートメントの生成
				ps = conn.prepareStatement(sql); 
				//プリペアードステートメント作成
				//customer_id設定
				ps.setInt(1, requestdata.getCustomer_id());
				//live_name設定
				ps.setString(2, requestdata.getLive_name());
				//request_datetime設定
				ps.setString(3, requestdata.getAddDateTime());
					
				//プリペアードステートメント実行
				if(ps.executeUpdate()==UPDATEOK)
				{
					//登録成功
					this.setMsg(SUCCESSREQUESTADD, "");
					this.settErr(false);
				}
				else
				{
					//登録失敗
					this.setMsg(REQUESTADDFAIL, "");
					this.settErr(true);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				this.setMsg(SQLERR, e.getMessage());
			}
			finally
			{
				this.close();
			}
	}
	
	
}