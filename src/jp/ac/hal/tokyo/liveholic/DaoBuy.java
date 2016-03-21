package jp.ac.hal.tokyo.liveholic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Data Access Object Calss
 * @author yusuke.koide
 *
 */
public class DaoBuy {
	
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
	public DaoBuy()
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
	 * getPaymentData()
	 * @return ArrayList<Object>
	 * Paymentテーブル情報を取得
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getPaymentData()
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
				sql = "select * from PAYMENT;";
				
				//ステートメント作成
				ps = conn.prepareStatement(sql);
				
				//プリペアードステートメント実行
				rs = ps.executeQuery();
			
			
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			while(rs.next())
			{

				//payment_idを格納
				ret.add(rs.getString("payment_id"));
				//payment_methodを格納
				ret.add(rs.getString("payment_method"));
				
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
	 * getPaymentMethod()
	 * @param paymentId
	 * @return　ret
	 * payment_idから支払い方法情報を取得
	 */
	@SuppressWarnings("finally")
	public String getPaymentMethod(String paymentId)
	{
		//戻り値の定義
		String ret = "";
		//抽出SQL
		String sql = "";
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			
			//抽出条件をArrayListオブジェクトから取得
			sql = "select payment_method from PAYMENT where payment_id=?;";
				
			//ステートメント作成
			ps = conn.prepareStatement(sql);
				
			//プリペアードステートメントSQLに
			ps.setString(1,paymentId);
				
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			//ネクスト
			rs.next();
			ret = rs.getString("payment_method");
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
	 * addSalesData()
	 * @param salesdata
	 * @return -1:異常　1：正常
	 * SALSテーブルに購入情報を挿入
	 */
	@SuppressWarnings("finally")
	public int addSalesData(ArrayList<Object> salesData)
	{
		
		//戻り値の定義
		int ret = 0;
		//プリペアードステートメントSQL定義
		String sql ="insert into SALES(sales_date,customer_id,payment_id,total_sales) values(?,?,?,?);";
		//コネクションを作成
		this.getConnection();
		
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql);
			
			//日付をプリペアード|ステートメントに設定
			ps.setString(1, (String)salesData.get(0));
			//顧客IDをプリペアードステートメントに設定
			ps.setString(2, (String)salesData.get(1));
			//支払IDをプリペアードステートメントに設定
			ps.setString(3, (String)salesData.get(2));
			//売上合計をプリペアードステートメントに設定
			ps.setInt(4, (Integer)salesData.get(3));
			
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
	 * getSalesId()
	 * @param salesData
	 * @return
	 * SalesId取得用
	 */
	@SuppressWarnings("finally")
	public int getSalesId(ArrayList<Object> salesData)
	{
		//戻り値の定義
		int ret = 0;
		//抽出SQL
		String sql = "";
		
		
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//抽出条件をArrayListオブジェクトから取得
			sql = "select sales_id from SALES where sales_date=? and customer_id=? and payment_id=? and total_sales=?;";
		
			//ステートメント作成
			ps = conn.prepareStatement(sql);
				
			//プリペアードステートメントSQLに
			ps.setString(1,(String)salesData.get(0));
			ps.setString(2,(String)salesData.get(1));
			ps.setString(3,(String)salesData.get(2));
			ps.setInt(4,(Integer)salesData.get(3));
			//プリペアードステートメント実行
			rs = ps.executeQuery();
		
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			rs.next();
			
			//sales_idを格納
			ret = rs.getInt("sales_id");
		}
		
		catch(SQLException e)
		{
			//エラーの場合は戻り値にnullを設定
			ret = -1;
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
	 * addSalesDetailsData()
	 * @param data
	 * @return
	 * 売上詳細テーブルにデータを挿入
	 */
	@SuppressWarnings("finally")
	public int addSalesDetailsData(ArrayList<Object> data)
	{
		//戻り値の定義
		int ret = 0;
		//プリペアードステートメントSQL定義
		String sql ="insert into SALES_DETAILS(sales_id,product_id,proceeds) values(?,?,?);";
		//コネクションを作成
		this.getConnection();
		try
		{
			//プリペアードステートメントの生成
			ps = conn.prepareStatement(sql);
		
			for(int i = 0; i < data.size(); i++)
			{
				
				//売上IDをプリペアード|ステートメントに設定
				ps.setInt(1, (Integer)data.get(i++));
				//商品IDをプリペアードステートメントに設定
				ps.setString(2, (String)data.get(i++));
				//売上金額プリペアードステートメントに設定
				ps.setInt(3, (Integer)data.get(i));
			
				//プリペアードステートメント実行
				ret += ps.executeUpdate();
			}
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
	 * getSalesData()
	 * @param customerId
	 * @return ArrayList<Object>
	 *  購入履歴表示用
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getSalesData(String customerId)
	{
		//戻り値の定義
		ArrayList<Object> ret = new ArrayList<Object>();
		//抽出SQL
		String sql = "select sales_date,live_id,PRODUCTS.product_id,product_name,arthist_name,category_name,format_name,proceeds,payment_method "
				+ "from SALES, SALES_DETAILS,PRODUCTS,ARTHIST,PRODUCT_CATEGORY,FORMAT,PAYMENT "
				+ "where customer_id = ? "
				+ "and SALES.sales_id = SALES_DETAILS.sales_id "
				+ "and PRODUCTS.product_id = SALES_DETAILS.product_id "
				+ "and PRODUCTS.arthist_id = ARTHIST.arthist_id "
				+ "and PRODUCTS.product_category_id = PRODUCT_CATEGORY.product_category_id "
				+ "and PRODUCTS.format_id = FORMAT.format_id "
				+ "and SALES.payment_id = PAYMENT.payment_id "
				+ "ORDER BY sales_date DESC;";
			
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//ステートメント作成
			ps = conn.prepareStatement(sql);
			
			//プリペアードステートメントSQLに
			ps.setObject(1,customerId);
			
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			while(rs.next())
			{
				//ArrayListオブジェクトretにResultSetの値を格納

				//sales_dateを格納
				ret.add(rs.getTimestamp("sales_date"));
				//live_idを格納
				ret.add(rs.getString("live_id"));
				//product_idを格納
				ret.add(rs.getString("PRODUCTS.product_id"));
				//product_nameを格納
				ret.add(rs.getString("product_name"));
				//arthist_nameを格納
				ret.add(rs.getString("arthist_name"));
				//category_nameを格納
				ret.add(rs.getString("category_name"));
				//format_nameを格納
				ret.add(rs.getString("format_name"));
				//proceedsを格納
				ret.add(rs.getInt("proceeds"));
				//payment_nameを格納
				ret.add(rs.getString("payment_method"));
				
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
	
	@SuppressWarnings("finally")
	public int checkCart(int customer_id,String cartproId)
	{
		//戻り値の定義
		int ret = 0;
		//抽出SQL
		String sql = "select count(PRODUCTS.product_id) from PRODUCTS, SALES, SALES_DETAILS "
				+ "where customer_id = ? and PRODUCTS.product_id = ? "
				+ "and SALES.sales_id = SALES_DETAILS.sales_id "
				+ "and PRODUCTS.product_id = SALES_DETAILS.product_id;";
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//ステートメント作成
			ps = conn.prepareStatement(sql);
			
			
				//プリペアードステートメントSQLに
				ps.setObject(1,customer_id);
				ps.setObject(2,cartproId);
				
				//プリペアードステートメント実行
				rs = ps.executeQuery();
			
				//ResultSetオブジェクトの値をretに格納
				rs.next();
				ret = rs.getInt("count(PRODUCTS.product_id)");
				
		}
		
		catch(SQLException e)
		{
			//エラーの場合は戻り値にnullを設定
			ret = -1;
			e.printStackTrace();
		}
		finally
		{
			//オブジェクトのクローズ処理
			this.close();
			return ret;
		}
	} 
	
	@SuppressWarnings("finally")
	public ArrayList<Object> getProductName(ArrayList<Object> cartDoubleData)
	{
		//戻り値の定義
		ArrayList<Object> ret = new ArrayList<Object>();
		//抽出SQL
		String sql = "select product_name from PRODUCTS where product_id = ?;";
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//ステートメント作成
			ps = conn.prepareStatement(sql);
			
			for(int i = 0; i < cartDoubleData.size();i++){
				//プリペアードステートメントSQLに
				ps.setObject(1,cartDoubleData.get(i));
				
				//プリペアードステートメント実行
				rs = ps.executeQuery();
			
				//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
				rs.next();
				//ArrayListオブジェクトretにResultSetの値を格納
				//customer_idを格納
				ret.add(rs.getString("product_name"));
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
}
