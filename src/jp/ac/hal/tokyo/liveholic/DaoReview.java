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
public class DaoReview {
	
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
	public DaoReview()
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
	 * addReviewData()
	 * @param reviewdata
	 * @return ret -1:異常　1：成功
	 */
	@SuppressWarnings("finally")
	public int addReviewData(ArrayList<Object> reviewData)
	{
		//戻り値設定
				int ret = 0;
				
				//プリペアードステートメントSQL定義
				String sql ="insert into REVIEW(customer_id,product_id,contributor,star,review_content,review_date) values(?,?,?,?,?,?);";
				
				//コネクションを作成
				this.getConnection();
				
				try
				{
					//プリペアードステートメントの生成
					ps = conn.prepareStatement(sql); 
					
					//customer_idをプリペアード|ステートメントに設定
					ps.setObject(1, reviewData.get(0));
					//product_idをプリペアードステートメントに設定
					ps.setObject(2, reviewData.get(1));
					//contributorをプリペアードステートメントに設定
					ps.setObject(3, reviewData.get(2));
					//starをプリペアードステートメントに設定
					ps.setObject(4, reviewData.get(3));
					//review_contentをプリペアードステートメントに設定
					ps.setObject(5, reviewData.get(4));
					//review_dateをプリペアードステートメントに設定
					ps.setObject(6, reviewData.get(5));
					
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
	 * getReviewData()
	 * @param liveId
	 * @return ArrayList<Object>
	 * レビュー内容を取り出す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getReviewData(String liveId)
	{
		//戻り値の定義
		ArrayList<Object> ret = new ArrayList<Object>();
		//抽出SQL
		String sql = "select product_name, star, contributor, review_date, review_content "
				+ "from PRODUCTS, REVIEW "
				+ "where live_id = ? "
				+ "and PRODUCTS.product_id = REVIEW.product_id "
				+ "ORDER BY review_date DESC;";
			
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//ステートメント作成
			ps = conn.prepareStatement(sql);
			
			//プリペアードステートメントSQLに
			ps.setObject(1,liveId);
			
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			
			//ResultSetオブジェクトの値をArrayListオブジェクトretに格納
			while(rs.next())
			{
				//ArrayListオブジェクトretにResultSetの値を格納

				//product_nameを格納
				ret.add(rs.getString("product_name"));
				//starを格納
				ret.add(rs.getInt("star"));
				//contributorを格納
				ret.add(rs.getString("contributor"));
				//review_dateを格納
				ret.add(rs.getDate("review_date"));
				//review_contentを格納
				ret.add(rs.getString("review_content"));
				
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
	 * getAvgStar()
	 * @param liveId
	 * @return ret -1:失敗
	 * 
	 */
	@SuppressWarnings("finally")
	public double getAvgStar(String liveId)
	{
		//戻り値の定義
		double ret = 0.0;
		//抽出SQL
		String sql = "select round(AVG(star),1) from PRODUCTS, REVIEW where live_id = ? and PRODUCTS.product_id = REVIEW.product_id;";
					
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//ステートメント作成
			ps = conn.prepareStatement(sql);
					
			//プリペアードステートメントSQLに
			ps.setString(1,liveId);
					
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			//ネクスト
			rs.next();
			//レコード数取得
			ret = rs.getDouble("round(AVG(star),1)");
			
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
	 * FinReviewCheck()
	 * @param customerId
	 * @param productId
	 * @return int
	 * レビュー済みチェック
	 */
	@SuppressWarnings("finally")
	public int FinReviewCheck(String customerId,String productId)
	{
		int ret = 0;
		
		//抽出SQL
		String sql = "select count(*) from REVIEW where customer_id = ? and product_id = ?;";
		
		//コネクション生成
		this.getConnection();
		//データの抽出条件を取得
		try
		{
			//ステートメント作成
			ps = conn.prepareStatement(sql);
							
			//プリペアードステートメントSQLに
			ps.setString(1,customerId);
			ps.setString(2,productId);
							
			//プリペアードステートメント実行
			rs = ps.executeQuery();
			//ネクスト
			rs.next();
			//レコード数取得
			ret = rs.getInt("count(*)");
					
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
}