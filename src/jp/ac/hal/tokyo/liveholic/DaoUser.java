package jp.ac.hal.tokyo.liveholic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Data Access Object Calss
 * @author yusuke.koide
 *
 */
public class DaoUser {
	
	//クラス内で使用するオブジェクト定義（属性）
	//コネクションオブジェクト定義
	Connection conn;
	//プリペアードステートメントオブジェクト定義
	PreparedStatement ps;
	//ステートメントオブジェクト定義
	Statement st;
	//ResultSetオブジェクト定義
	ResultSet rs;

	
	/**
	 * 
	 * コンストラクター
	 * 
	 * 
	 */
	public DaoUser()
	{
		//コネクションオブジェクト初期化
		conn = null;
		//プリペアードステートメント初期化
		ps = null;
		//ステートメント初期化
		st = null;
		//ResultSet初期化
		rs = null;
	}
	
	/**
	 * 
	 * getConnection()メソッド
	 * 
	 * @param 無し
	 * @return 無し
	 * 
	 */
	private void getConnection()
	{
		//MySQLへの接続情報の設定
		String url = "jdbc:mysql:///live_holic?user=jv23_user&password=jv23jv23&useUnicode=true&charactorEncoding=utf8";
		
		try{
			//ドライバの読み込み
			Class.forName("com.mysql.jdbc.Driver");
			
			//コネクションを作成
			conn = DriverManager.getConnection(url);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * close()
	 * 
	 * @param 無し
	 * @return 無し
	 * 
	 * コネクｋション・プリペアードステートメント・ステートメント
	 * ResultSetオブジェクトのクロージング処理
	 */
	private void close()
	{
		try
		{
			//ResultSetオブジェクトのクローズ
			if(rs != null)
			{
				rs.close();
			}
			
			//プリペアードステートメントのクローズ
			if(ps != null)
			{
				ps.close();
			}
			//ステートメントのクローズ
			if(st != null)
			{
				st.close();
			}
			
			//コネクションのクローズ
			if(conn != null)
			{
				conn.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * confUserData
	 * @param useridData
	 * @return　
	 * user_id重複確認用
	 * 
	 */
	@SuppressWarnings("finally")
	public int confUserData(ArrayList<Object> useridData){
		//戻り値設定
		int ret = 0;
		
		//プリペアードステートメントSQL定義
		String sql ="select count(*) from CUSTOMER where user_id=?;";
		
		//コネクションを作成
		this.getConnection();
		
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql);
			
			//ユーザIDをプリペアード|ステートメントに設定
			ps.setString(1, (String)useridData.get(0));
			
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			//ネクスト
			rs.next();
			//レコード数取得
			ret = rs.getInt("count(*)");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			ret = -1;
		}
		finally
		{
			//クローズ処理
			this.close();
			return ret;
		}
	}
	
	/**
	 * confEmailData
	 * @param emailData
	 * @return
	 * email重複確認用
	 * 
	 */
	@SuppressWarnings("finally")
	public int confEmailData(ArrayList<Object> emailData){
		//戻り値設定
		int ret = 0;
		
		//プリペアードステートメントSQL定義
		String sql ="select count(*) from CUSTOMER where email=?;";
		
		//コネクションを作成
		this.getConnection();
		
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql);
			
			//ユーザIDをプリペアード|ステートメントに設定
			ps.setString(1, (String)emailData.get(0));
			
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			//ネクスト
			rs.next();
			//レコード数取得
			ret = rs.getInt("count(*)");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			ret = -1;
		}
		finally
		{
			//クローズ処理
			this.close();
			return ret;
		}
	}
	/**
	 * 
	 * @param emailData
	 * @return ret -1:異常　1:成功
	 * 
	 */
	@SuppressWarnings("finally")
	public int confpasswordData(ArrayList<Object> data){
		//戻り値設定
		int ret = 0;
		
		//プリペアードステートメントSQL定義
		String sql ="select count(*) from CUSTOMER where customer_id=? and password=? ;";
		
		//コネクションを作成
		this.getConnection();
		
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql);
			
			//ユーザIDをプリペアード|ステートメントに設定
			ps.setString(1, (String)data.get(0));
			ps.setString(2, (String)data.get(1));
			
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			//ネクスト
			rs.next();
			//レコード数取得
			ret = rs.getInt("count(*)");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			ret = -1;
		}
		finally
		{
			//クローズ処理
			this.close();
			return ret;
		}
	}
	
	/**
	 * 
	 * addUserData
	 * 
	 * @param ArrayList<Objcet> userData
	 * @return ret(-1:異常 1:成功）
	 * 新規登録用
	 */
	@SuppressWarnings("finally")
	public int addUserData(ArrayList<Object> userData)
	{

		//戻り値設定
		int ret = 0;
		
		//プリペアードステートメントSQL定義
		String sql ="insert into CUSTOMER(user_id,password,email,last_name,first_name,birth_date,sex,postal_code,address,record_date) values(?,?,?,?,?,?,?,?,?,?);";
		
		//コネクションを作成
		this.getConnection();
		
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql); 
			
			//ユーザIDをプリペアード|ステートメントに設定
			ps.setString(1, (String)userData.get(0));
			//パスワードをプリペアードステートメントに設定
			ps.setString(2, (String)userData.get(1));
			//メールアドレスをプリペアードステートメントに設定
			ps.setString(3, (String)userData.get(2));
			//氏名（姓）をプリペアードステートメントに設定
			ps.setString(4, (String)userData.get(3));
			//氏名（名）をプリペアードステートメントに設定
			ps.setString(5, (String)userData.get(4));
			//生年月日をプリペアードステートメントに設定
			ps.setString(6, (String)userData.get(5));
			//性別をプリペアードステートメントに設定
			ps.setInt(7, (Integer)userData.get(6));
			//郵便番号をプリペアードステートメントに設定
			ps.setString(8, (String)userData.get(7));
			//住所をプリペアードステートメントに設定
			ps.setString(9, (String)userData.get(8));
			//登録日時をプリペアードステートメントに設定
			ps.setString(10, (String)userData.get(9));
			
			//プリペアードステートメント実行
			ret = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			ret = -1;
		}
		finally
		{
			//クローズ処理
			this.close();
			return ret;
		}
	}
	
	/**
	 * checkLogin()メソッド
	 * 
	 * @Param UserDataBeanオブジェクト
	 * @return 
	 * ログイン用
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> checkLogin(ArrayList<Object> userData)
	{
		//戻り値の定義
		ArrayList<Object> ret = new ArrayList<Object>();
		//抽出SQL
		String sql = "";
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//抽出条件をArrayListオブジェクトから取得
			sql = "select customer_id from CUSTOMER where user_id=? and password=?;";
				
			//ステートメント作成
			ps = conn.prepareStatement(sql);
				
			//プリペアードステートメントSQLに
			ps.setObject(1,userData.get(0));
			ps.setObject(2,userData.get(1));
				
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			rs.next();
			//ArrayListオブジェクトretにResultSetの値を格納
			//customer_idを格納
			ret.add(rs.getString("customer_id"));
		}
		
		catch(SQLException e)
		{
			//エラーの場合は戻り値にnullを設定
			ret = null;
			e.printStackTrace();
		}
		finally
		{
			//オブジェクトのクローズ処理
			this.close();
			return ret;
		}
	}
	
	/**
	 *  getUserData
	 * @param data
	 * @return
	 * ユーザデータ取得用
	 */
		
	@SuppressWarnings("finally")
	public ArrayList<Object> getUserData(String customerId)
	{
		//戻り値の定義
		ArrayList<Object> ret = new ArrayList<Object>();
		//抽出SQL
		String sql = "";
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//データの抽出条件を取得
			if(customerId != null)
			{
				//抽出条件をArrayListオブジェクトから取得
				sql = "select * from CUSTOMER where customer_id=?;";
				
				//ステートメント作成
				ps = conn.prepareStatement(sql);
				
				//プリペアードステートメントSQLに
				ps.setObject(1,customerId);
				
				//プリペアードステートメント実行
				rs = ps.executeQuery();
			
			}
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			while(rs.next())
			{
				//ArrayListオブジェクトretにResultSetの値を格納

				//user_idを格納
				ret.add(rs.getString("user_id"));
				//emailを格納
				ret.add(rs.getString("email"));
				//last_nameを格納
				ret.add(rs.getString("last_name"));
				//first_nameを格納
				ret.add(rs.getString("first_name"));
				//birth_dateを格納
				Date myD = rs.getDate("birth_date");
				Calendar cal = Calendar.getInstance();
				cal.setTime(myD);
				cal.add(Calendar.MONTH, 1);
				ret.add(cal.get(Calendar.YEAR)+"年"+cal.get(Calendar.MONTH)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日");
				//sexを格納
				if(rs.getInt("sex") == 0){
					ret.add("男");
				}else{
					ret.add("女");
				}
				//postal_codeを格納
				ret.add(rs.getString("postal_code"));
				//addressを格納
				ret.add(rs.getString("address"));
				//record_dateを格納
				ret.add(rs.getString("record_date"));
			}
		}
		
		catch(SQLException e)
		{
			//エラーの場合は戻り値にnullを設定
			ret = null;
			e.printStackTrace();
		}
		finally
		{
			//オブジェクトのクローズ処理
			this.close();
			return ret;
		}
	}
	
	/**
	 * aletUserData()
	 * @param userData
	 * @return -1:異常　1:正常
	 * アカウント情報変更用
	 */
	@SuppressWarnings("finally")
	public int altUserData(ArrayList<Object> userData)
	{
		//戻り値設定
		int ret = 0;
				
		//プリペアードステートメントSQL定義
		String sql ="update CUSTOMER set password = ?,email = ?,postal_code = ?,address = ? where customer_id = ?;";
				
		//コネクションを作成
		this.getConnection();
				
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql); 
					
			//passwordをプリペアード|ステートメントに設定
			ps.setString(1, (String)userData.get(0));
			//emailをプリペアードステートメントに設定
			ps.setString(2, (String)userData.get(1));
			//postal_codeをプリペアードステートメントに設定
			ps.setString(3, (String)userData.get(2));
			//addressをプリペアードステートメントに設定
			ps.setString(4, (String)userData.get(3));
			//customer_idをプリペアードステートメントに設定
			ps.setString(5, (String)userData.get(4));
			
			//プリペアードステートメント実行
			ret = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			ret = -1;
		}
		finally
		{
			//クローズ処理
			this.close();
			return ret;
		}
			
	}
	
	/**
	 * deltUserData()
	 * @param userData
	 * @return -1:異常　1:正常
	 * アカウント情報変更用
	 */
	@SuppressWarnings("finally")
	public int delUserData(ArrayList<Object> userData)
	{
		//戻り値設定
		int ret = 0;
				
		//プリペアードステートメントSQL定義
		String sql ="delete from CUSTOMER where customer_id = ? and password = ?;";
				
		//コネクションを作成
		this.getConnection();
				
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql); 
					
			//customer_idをプリペアード|ステートメントに設定
			ps.setObject(1, userData.get(0));
			//emailをプリペアードステートメントに設定
			ps.setObject(2, userData.get(1));
			
			//プリペアードステートメント実行
			ret = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			ret = -1;
		}
		finally
		{
			//クローズ処理
			this.close();
			return ret;
		}
			
	}
}
