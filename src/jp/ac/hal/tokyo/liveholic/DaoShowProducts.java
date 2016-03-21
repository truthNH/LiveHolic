package jp.ac.hal.tokyo.liveholic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoShowProducts {
	//コネクションオブジェクト定義
	Connection conn;
	//プリペアードステートメント定義
	PreparedStatement ps;
	//ステートメント定義
	Statement st;
	//リザルトセット定義
	ResultSet rs;
	
	/*
	 * コンストラクタ
	 */
	public DaoShowProducts()
	{
		conn = null;
		ps = null;
		st = null;
		rs = null;		
	}
	
	/*
	 * getConnection()
	 * DBの接続設定定義
	 * @paramなし
	 * @returnなし
	 */
	private void getConnection() {
		//MySQLへの接続情報設定
		String url = "jdbc:mysql:///live_holic?user=jv23_user&password=jv23jv23&useUnicode=true&characterEncoding=utf8";
		try {
			//ドライバー読み込み
			Class.forName("com.mysql.jdbc.Driver");
			//コネクション作成
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * close()
	 * @param 無し
	 * @return 無し
	 */
	private void close() {
		try {
			//nullかどうかチェック
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * getLiveId()
	 * @param なし
	 * @return ArrayList<Object>
	 * ライブidを全件取得し返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getLiveId() {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select live_id from LIVE";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//ライブIDをretに追加
				ret.add(rs.getString("live_id"));
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getLiveId()
	 * @param String
	 * @return ArrayList<Object>
	 * 受け取ったジャンルのライブidを全件取得し返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getLiveId(String liveGenre) {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select live_id from LIVE where genre_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveGenre);
			rs = ps.executeQuery();
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//ライブIDをretに追加
				ret.add(rs.getString("live_id"));
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	
	
	
	/*
	 * getLiveName()
	 * @param String
	 * @return ArrayList<Object>
	 * ライブ名を取得し返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getLiveName(String liveId) {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select live_name from LIVE where live_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			//SQL実行
			rs = ps.executeQuery();
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//ライブ名をretに追加
				ret.add(rs.getString("live_name"));	
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getLiveArthist()
	 * @param String
	 * @return ArrayList<Object>
	 * ライブ参加サーティストを取得し返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getLiveArthist(String liveId) {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select arthist_name from LIVE, LIVE_ARTHIST, ARTHIST "
				+ "where LIVE.live_id = ?  && LIVE.live_id = LIVE_ARTHIST.live_id "
				+ "&& LIVE_ARTHIST.arthist_id = ARTHIST.arthist_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			rs = ps.executeQuery();
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//アーティスト名をretに追加
				ret.add(rs.getString("arthist_name"));
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getVenueName()
	 * @param String
	 * @return ArrayList<Object>
	 * ライブ会場を取得し返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getVenueName(String liveId) {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select venue_name from LIVE, VENUE where LIVE.venue_id = VENUE.venue_id && live_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			rs = ps.executeQuery();
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//会場名をretに追加
				ret.add(rs.getString("venue_name"));	
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getProductDetails()
	 * @param String
	 * @return ArrayList<Object>
	 * ライブ日付、時間、曲数、レーベル名を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getLiveDetails(String liveId) {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select live_id, live_date, total_length, total_track, label_name, product_introduction from LIVE, LABEL where LIVE.live_id = ? && LIVE.label_id = LABEL.label_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			rs = ps.executeQuery();
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//ライブIDをretに追加
				ret.add(rs.getString("live_id"));
				//ライブ日付をretに追加
				ret.add(rs.getString("live_date"));
				//時間をretに追加
				ret.add(rs.getString("total_length"));
				//曲数をretに追加
				ret.add(rs.getString("total_track"));
				//レーベル名をretに追加
				ret.add(rs.getString("label_name"));
				//商品紹介をretに追加
				ret.add(rs.getString("product_introduction"));
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
		
	/*
	 * getMusicDetails()
	 * @param String
	 * @return ArrayList<Object>
	 * 受け取ったliveIdに紐づけられたミュージック商品の詳細を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getMusicDetails(String liveId) {
		// ArrayList<Object>の宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		// DBへの接続
		getConnection();
		// SQLの生成
		String sql = "select track_number, product_name, format_name, arthist_name, time, price , product_id from PRODUCTS, FORMAT, ARTHIST "
				+ "where live_id = ? && PRODUCTS.product_category_id = 1 && PRODUCTS.format_id = FORMAT.format_id && PRODUCTS.arthist_id = ARTHIST.arthist_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString("track_number"));
				ret.add(rs.getString("product_name"));
				ret.add(rs.getString("format_name"));
				ret.add(rs.getString("arthist_name"));
				ret.add(rs.getString("time"));
				ret.add(rs.getString("price"));
				ret.add(rs.getString("product_id"));
				
			}
		} catch(SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getVideoDetails()
	 * @param String
	 * @return ArrayList<Object>
	 * 受け取ったliveIdに紐づけられたビデオ商品の詳細を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getVideoDetails(String liveId) {
		// ArrayList<Object>の宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		// DBへの接続
		getConnection();
		// SQLの生成
		String sql = "select product_name, format_name, arthist_name, time, price, product_id from PRODUCTS, FORMAT, ARTHIST "
				+ "where live_id = ? && PRODUCTS.product_category_id = 2 && PRODUCTS.format_id = FORMAT.format_id && PRODUCTS.arthist_id = ARTHIST.arthist_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString("product_name"));
				ret.add(rs.getString("format_name"));
				ret.add(rs.getString("arthist_name"));
				ret.add(rs.getString("time"));
				ret.add(rs.getString("price"));
				ret.add(rs.getString("product_id"));
				
			}
		} catch(SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getLiveGoods()
	 * @param String liveId
	 * @return ArrayList<Object> ret
	 * 受け取ったライブIDに紐づけられたライブグッズ商品を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getLiveGoods(String liveId) {
		//ArrayList<Object>の宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select product_name, format_name, arthist_name, price, product_id from PRODUCTS, FORMAT, ARTHIST "
				+ "where live_id = ? && PRODUCTS.product_category_id = 3 && PRODUCTS.format_id = FORMAT.format_id && PRODUCTS.arthist_id = ARTHIST.arthist_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, liveId);
			rs = ps.executeQuery();
			while(rs.next()) {
				ret.add(rs.getString("product_name"));
				ret.add(rs.getString("format_name"));
				ret.add(rs.getString("arthist_name"));
				ret.add(rs.getString("price"));
				ret.add(rs.getString("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	
	/*
	 * getPurchaseProducts()
	 * @param String[]
	 * @return ArrayList<Object>
	 * ライブID,プロダクトID,カテゴリ名,価格,商品名,フォーマット名,アーティスト名を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getPurchaseProducts(String[] args) {
		// ArrayList<Object>の宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		// DBへの接続
		getConnection();
		// SQLの生成
		String sql = "select live_id, product_id, category_name, price, "
				+ "product_id, product_name, format_name, arthist_name "
				+ "from PRODUCTS, FORMAT, ARTHIST, PRODUCT_CATEGORY "
				+ "where PRODUCTS.product_id = ? && PRODUCTS.format_id = FORMAT.format_id "
				+ "&& PRODUCTS.product_category_id = PRODUCT_CATEGORY.product_category_id "
				+ "&& PRODUCTS.arthist_id = ARTHIST.arthist_id";
		try {
			ps = conn.prepareStatement(sql);
			//
			for (int i = 0 ; i < args.length ; i++) {
				ps.setString(1, args[i]);
				rs = ps.executeQuery();
				while (rs.next()) {
					ret.add(rs.getString("live_id"));
					ret.add(rs.getString("product_id"));
					ret.add(rs.getString("category_name"));
					ret.add(rs.getString("price"));
					ret.add(rs.getString("product_id"));
					ret.add(rs.getString("product_name"));
					ret.add(rs.getString("format_name"));
					ret.add(rs.getString("arthist_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getTotalAmount()
	 * @param String[]
	 * @return int
	 * カートにいれた商品の合計額を返す
	 */
	@SuppressWarnings("finally")
	public int getTotalAmount(String[] args) {
		ArrayList<Object> price = new ArrayList<Object>();
		int sum = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select price from PRODUCTS where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0 ; i < args.length ; i++) {
				ps.setString(1, args[i]);
				rs = ps.executeQuery();
				while (rs.next()) {
					price.add(rs.getInt("price"));
				}
			}
			for (int j = 0 ; j < price.size() ; j++) {
				sum += Integer.parseInt(price.get(j).toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return sum;
		}
	}
	
	/*
	 * searchLive()
	 * @param String
	 * @retrun ArrayList<Object>
	 * 検索されたキーワードのライブIDを返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> searchLive(String keyword) {
		//戻り値
		ArrayList<Object> ret = new ArrayList<Object>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select LIVE.live_id from LIVE "
				+ "join(LIVE_ARTHIST join ARTHIST on LIVE_ARTHIST.arthist_id = ARTHIST.arthist_id) "
				+ "on LIVE.live_id = LIVE_ARTHIST.live_id "
				+ "join VENUE on LIVE.venue_id = VENUE.venue_id where arthist.arthist_name like ? "
				+ "|| live_name like ? || venue_name like ? group by live_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString("live_id"));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * searchProduct()
	 * @param String
	 * @return ArrayList<Object>
	 * 検索されたキーワードを含む商品情報を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> searchProduct(String keyword) {
		//戻り値
		ArrayList<Object> ret = new ArrayList<Object>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select PRODUCTS.product_name, LIVE.live_name, category_name, ARTHIST.arthist_name, LIVE.live_id "
				+ "from PRODUCTS join ARTHIST on PRODUCTS.arthist_id = ARTHIST.arthist_id "
				+ "join PRODUCT_CATEGORY on PRODUCTS.product_category_id = PRODUCT_CATEGORY.product_category_id "
				+ "join LIVE on PRODUCTS.live_id = LIVE.live_id "
				+ "where ARTHIST.arthist_name like ? || LIVE.live_name like ? || PRODUCTS.product_name like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString("product_name"));
				ret.add(rs.getString("live_id"));
				ret.add(rs.getString("live_name"));
				ret.add(rs.getString("category_name"));
				ret.add(rs.getString("arthist_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	@SuppressWarnings("finally")
	public int getProceeds(String ProductId) {
		int price = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select price from PRODUCTS where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);

				ps.setString(1, ProductId);
				rs = ps.executeQuery();
				rs.next();
				price = rs.getInt("price");
				
			
		} catch (SQLException e) {
			e.printStackTrace();
			price = -1;
		} finally {
			this.close();
			return price;
		}
	}
	
	/*
	 * getMovieData()
	 * @param String customer_id
	 * @return ArrayList<Object>
	 * 購入したビデオ商品を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getMovieData(String customer_id) {
		//戻り値
		ArrayList<Object> ret = new ArrayList<Object>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select PRODUCTS.live_id, LIVE.live_name, ARTHIST.arthist_name, count(comment),PRODUCTS.product_id "
				+ "from LIVE, PRODUCTS, SALES, SALES_DETAILS, STREAMING, ARTHIST "
				+ "where SALES.customer_id = ? && PRODUCTS.product_category_id = 2 "
				+ "&& SALES.sales_id = SALES_DETAILS.sales_id "
				+ "&& SALES_DETAILS.product_id = PRODUCTS.product_id "
				+ "&& PRODUCTS.product_id = STREAMING.product_id "
				+ "&& PRODUCTS.arthist_id = ARTHIST.arthist_id "
				+ "&& PRODUCTS.live_id = LIVE.live_id "
				+ "group by PRODUCTS.product_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString("live_id"));
				ret.add(rs.getString("live_name"));
				ret.add(rs.getString("arthist_name"));
				ret.add(rs.getString("count(comment)"));
				ret.add(rs.getString("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getMovieDetails()
	 * @param String
	 * @return ArrayList<Object>
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getMovieDetails(String product_id) {
		//戻り値
		ArrayList<Object> ret = new ArrayList<Object>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select product_name, arthist_name from PRODUCTS, ARTHIST "
				+ "where product_id = ? && PRODUCTS.arthist_id = ARTHIST.arthist_id";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getString("product_name"));
				ret.add(rs.getString("arthist_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getSetList()
	 * @param String
	 * @return ArrayList<Object>
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getSetList(String product_id) {
		//戻り値
		ArrayList<Object> ret = new ArrayList<Object>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select tracktime, setlist from LIVE_INFO where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(rs.getInt("tracktime"));
				ret.add(rs.getString("setlist"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/**
	 * getProductsDetails()
	 * @param productId
	 * @return ArrayList<Object>
	 * live_idからlive_id,live_name,arthist_nameを返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getliveDetails(String ProductId) {
		//ArrayListオブジェクトの宣言
		ArrayList<Object> ret = new ArrayList<Object>();
		//コネクション確率
		this.getConnection();
		//SQLを生成
		String sql = "select PRODUCTS.live_id, live_name, product_name, arthist_name "
				+ "from PRODUCTS, LIVE, ARTHIST "
				+ "where product_id = ? "
				+ "and PRODUCTS.live_id = LIVE.live_id "
				+ "and PRODUCTS.arthist_id = ARTHIST.arthist_id;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ProductId);
			rs = ps.executeQuery();
			//ResultSetオブジェクトからデータを取り出す
			while (rs.next()) {
				//ライブIDをretに追加
				ret.add(rs.getString("live_id"));
				//ライブ名をretに追加
				ret.add(rs.getString("live_name"));
				//商品名をretに追加
				ret.add(rs.getString("product_name"));
				//アーティスト名をretに追加
				ret.add(rs.getString("arthist_name"));
				
			}
		} catch (SQLException e) {
			ret = null;
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getLiveRanking()
	 * @param なし
	 * @return ArrayList<String>
	 */
	@SuppressWarnings("finally")
	public ArrayList<String> getLiveRaking() {
		//戻り値
		ArrayList<String> ret = new ArrayList<String>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select count(*) as rank, live_id from sales_details, products "
				+ "where sales_details.product_id = products.product_id group by live_id order by rank desc limit 10";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ret.add(rs.getString("live_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getPurchaseProducts()
	 * @param String[]
	 * @return ArrayList<Object>
	 * フォーマット名を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<String> getformat(String[] args) {
		// ArrayList<Object>の宣言
		ArrayList<String> ret = new ArrayList<String>();
		// DBへの接続
		getConnection();
		// SQLの生成
		String sql = "select format_name "
				+ "from PRODUCTS, FORMAT "
				+ "where product_id = ? "
				+ "and PRODUCTS.format_id = FORMAT.format_id;";
		try {
			ps = conn.prepareStatement(sql);
			//
			for (int i = 0 ; i < args.length ; i++) {
				ps.setString(1, args[i]);
				rs = ps.executeQuery();
				while (rs.next()) {
					ret.add(rs.getString("format_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
}
	
	