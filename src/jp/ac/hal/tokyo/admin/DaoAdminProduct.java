package jp.ac.hal.tokyo.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jp.ac.hal.tokyo.liveholic.*;

public class DaoAdminProduct {

	// コネクションオブジェクト定義
	Connection conn;
	// プリペアードステートメントオブジェクト定義
	PreparedStatement ps;
	// ステートメントオブジェクト定義
	Statement st;
	// ResultSetオブジェクト定義
	ResultSet rs;
	
	/*
	 * getConnection() DBの接続設定定義
	 * @param なし
	 * @return　なし
	 */
	public void getConnection() {
		// MySQLへの接続情報設定
		String url = "jdbc:mysql:///live_holic?user=jv23_user&password=jv23jv23&useUnicode=true&characterEncoding=utf8";
		try {
			// ドライバー読み込み
			Class.forName("com.mysql.jdbc.Driver");
			// コネクション作成
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * close() DBのクローズ処理
	 * @param 無し
	 * @return 無し
	 */
	public void close() {
		try {
			// nullかどうかチェック
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
	 * setVenueName()
	 * @param AdminproductBeanオブジェクト
	 * @return なし
	 * 入力されたライブ会場名が既に存在する場合insertしない　存在しない場合insert実行
	 */
	public void insertVenueName(AdminProductBean bean) {
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "insert into venue(venue_name) select * from(select ?) as tmp "
				+ "where not exists(select venue_name from venue where venue_name = ?) limit 1";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getVenue_name()); //ライブ会場取得
			ps.setString(2, bean.getVenue_name()); //ライブ会場取得
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	/*
	 * insertArthistName()
	 * @param AdminproductBeanオブジェクト
	 * @return なし
	 * 入力されたアーティスト名が既に存在する場合insertしない　存在しない場合insert実行
	 */
	public void insertArthistName(AdminProductBean bean) {
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "insert into arthist(arthist_name) select * from(select ?) as tmp "
				+ "where not exists(select arthist_name from arthist where arthist_name = ?) limit 1";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getArthist_name()); //アーティスト名取得
			ps.setString(2, bean.getArthist_name()); //アーティスト名取得
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	/*
	 * insertLabelName()
	 * @param AdminproductBeanオブジェクト
	 * @return なし
	 * 入力されたレコード会社名が既に存在する場合insertしない　存在しない場合insert実行
	 */
	public void insertLabelName(AdminProductBean bean) {
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "insert into label(label_name) select * from(select ?) as tmp "
				+ "where not exists(select label_name from label where label_name = ?) limit 1";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getLabel_name()); //レコード会社名取得
			ps.setString(2, bean.getLabel_name()); //レコード会社名取得
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	/*
	 * insertLive()
	 * @param AdminProductBeanオブジェクト
	 * @return int 1:OK -1:エラー
	 * LIVEテーブルに入力された値をinsertする
	 */
	@SuppressWarnings("finally")
	public int insertLive(AdminProductBean bean) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "insert into LIVE"
				+ "(live_id, live_name, genre_id, venue_id, total_length, "
				+ "live_date, label_id, product_introduction, total_track) values("
				+ "?, ?, ?, (select venue_id from venue where venue_name = ?), ?, ?, "
				+ "(select label_id from label where label_name = ?), ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getLive_id()); //ライブID
			ps.setString(2, bean.getLive_name()); //ライブ名
			ps.setString(3, bean.getGenre_id()); //ジャンルID
			ps.setString(4, bean.getVenue_name()); //会場名
			ps.setString(5, bean.getTotal_length()); //時間
			ps.setString(6, bean.getLive_date()); //ライブ日付
			ps.setString(7, bean.getLabel_name()); //レコード会社
			ps.setString(8, bean.getProduct_introduction()); //商品紹介
			ps.setInt(9, bean.getTotal_track()); //曲数
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * insertLiveArthist()
	 * @param AdeminProductBeanオブジェクト
	 * @return int 1:OK -1:エラー
	 * LIVEテーブルとARTHISTテーブルを紐づけるLIVE_ARTHISTテーブルにinsert
	 */
	@SuppressWarnings("finally")
	public int insertLiveArthist(AdminProductBean bean) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "insert into LIVE_ARTHIST values(?, (select arthist_id from arthist where arthist_name = ?))";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getLive_id());
			ps.setString(2, bean.getArthist_name());
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getLiveInfo()
	 * @param なし
	 * @return ArrayList<AdminProductBean>
	 * 全ライブ情報を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<AdminProductBean> getLiveInfo() {
		//戻り値
		ArrayList<AdminProductBean> liveInfo = new ArrayList<AdminProductBean>();
		//DB接続
		this.getConnection();
		//SQL生成 テーブルを結合し全ライブ情報を返す
		String sql = "select LIVE.live_id, live_name, arthist_name, LIVE.genre_id, total_length, "
				+ "venue_name, label_name, live_date, product_introduction, total_track "
				+ "from LIVE join(LIVE_ARTHIST join ARTHIST on LIVE_ARTHIST.arthist_id = ARTHIST.arthist_id) "
				+ "on LIVE.live_id = LIVE_ARTHIST.live_id "
				+ "join VENUE on LIVE.venue_id = VENUE.venue_id "
				+ "join LABEL on LIVE.label_id = LABEL.label_id "
				+ "group by LIVE.live_id "
				+ "order by LIVE.live_id desc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				// AdminProductBeanオブジェクトを生成
				AdminProductBean bean = new AdminProductBean();
				bean.setLive_id(rs.getString("LIVE.live_id")); //ライブID取得
				bean.setLive_name(rs.getString("live_name")); //ライブ名取得
				bean.setArthist_name(rs.getString("arthist_name")); //アーティスト名取得
				bean.setGenre_id(rs.getString("genre_id")); //ジャンルID取得
				bean.setTotal_length(rs.getString("total_length")); //時間取得
				bean.setVenue_name(rs.getString("venue_name")); //ライブ会場名取得
				bean.setLabel_name(rs.getString("label_name")); //レコード会社取得
				bean.setLive_date(rs.getString("live_date")); //ライブ日付取得
				bean.setProduct_introduction(rs.getString("product_introduction")); //商品紹介取得
				bean.setTotal_track(rs.getInt("total_track"));  //曲数取得
				//ArrayListにbeanを格納していく
				liveInfo.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return liveInfo;
		}
	}
	
	/*
	 * updateLive()
	 * @param AdminProductBean
	 * @return int
	 * ライブテーブルを更新する 1:OK -1:NG
	 */
	@SuppressWarnings("finally")
	public int updateLive(AdminProductBean bean) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成 アップデート処理
		String sql = "update LIVE set live_name = ?, "
				+ "genre_id = ?, total_length = ?, venue_id = (select venue_id from VENUE where venue_name = ?), "
				+ "label_id = (select label_id from LABEL where label_name = ?) ,"
				+ "live_date = ?, product_introduction = ?, total_track = ? where live_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getLive_name()); //ライブ名をセット
			ps.setString(2, bean.getGenre_id()); //ジャンルIDをセット
			ps.setString(3, bean.getTotal_length()); //時間をセット
			ps.setString(4, bean.getVenue_name()); //ライブ会場名をセット
			ps.setString(5, bean.getLabel_name()); //レコード会社名をセット
			ps.setString(6, bean.getLive_date()); //ライブ日付をセット
			ps.setString(7, bean.getProduct_introduction()); //商品紹介をセット
			ps.setInt(8, bean.getTotal_track()); //曲数をセット
			ps.setString(9, bean.getLive_id()); //ライブIDをセット
			// Update実行
			ret = ps.executeUpdate(); 
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * updateLiveArthist()
	 * @param AdeminProductBeanオブジェクト
	 * @return int 1:OK -1:エラー
	 * LIVEテーブルとARTHISTテーブルを紐づけるLIVE_ARTHISTテーブルをUpdate
	 */
	@SuppressWarnings("finally")
	public int updateLiveArthist(AdminProductBean bean) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成 LIVEテーブルとARTHISTテーブルを紐づけるLIVE_ARTHISTテーブルをUpdate
		String sql = "update LIVE_ARTHIST set live_id = ?, "
				+ "arthist_id = (select arthist_id from arthist where arthist_name = ?) "
				+ "where live_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getLive_id());
			ps.setString(2, bean.getArthist_name());
			ps.setString(3, bean.getLive_id());
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getMovie()
	 * @param なし
	 * @return ArrayList<AdminProductBean>
	 * 動画情報を全て取得する
	 */
	@SuppressWarnings("finally")
	public ArrayList<AdminProductBean> getMovie() {
		//戻り値
		ArrayList<AdminProductBean> ret = new ArrayList<AdminProductBean>();
		//DB接続
		this.getConnection();
		//SQL生成 カテゴリムービーを全件取得
		String sql = "select PRODUCTS.product_id, PRODUCTS.product_name, count(*) "
				+ "from PRODUCTS join STREAMING on PRODUCTS.product_id = STREAMING.product_id "
				+ "where product_category_id = 2 "
				+ "group by PRODUCTS.product_id";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				//beanオブジェクト生成
				AdminProductBean bean = new AdminProductBean();
				bean.setProduct_id(rs.getString("product_id"));
				bean.setProduct_name(rs.getString("product_name"));
				bean.setComment_num(rs.getInt("count(*)"));
				ret.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getAdminComment()
	 * @param String product_id
	 * @return ArrayList<VideoBean>
	 * 引数で渡されたproduct_idのコメントデータを全て取得する
	 */
	@SuppressWarnings("finally")
	public ArrayList<VideoBean> getAdminComment(String product_id) {
		//戻り値
		ArrayList<VideoBean> ret = new ArrayList<VideoBean>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select * from STREAMING where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				VideoBean bean = new VideoBean();
				bean.setId(rs.getInt("id"));
				bean.setSec(rs.getFloat("sec"));
				bean.setComment(rs.getString("comment"));
				ret.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * deleteComment()
	 * @param int id (コメントID)
	 * @return ret 1:OK -1:NG
	 * 引数で受け取ったIDのコメントを消去する
	 */
	@SuppressWarnings("finally")
	public int deleteComment(int id) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "delete from streaming where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getAdminProductDetails()
	 * @param String live_id
	 * @return ArrayList<AdminProductBean>
	 * ライブIDに紐付けられた商品を全て取得
	 */
	@SuppressWarnings("finally")
	public ArrayList<AdminProductBean> getAdminProductDetails(String live_id) {
		//戻り値
		ArrayList<AdminProductBean> ret = new ArrayList<AdminProductBean>();
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select product_id, product_name, live_id, PRODUCTS.product_category_id as category, PRODUCTS.format_id as format, "
				+ "arthist_name, time, price, track_number, release_data "
				+ "from PRODUCTS join PRODUCT_CATEGORY on PRODUCT_CATEGORY.product_category_id = PRODUCTS.product_category_id "
				+ "join FORMAT on PRODUCTS.format_id = FORMAT.format_id "
				+ "join ARTHIST on PRODUCTS.arthist_id = ARTHIST.arthist_id "
				+ "where PRODUCTS.live_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, live_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				AdminProductBean bean = new AdminProductBean();
				bean.setProduct_id(rs.getString("product_id"));
				bean.setProduct_name(rs.getString("product_name"));
				bean.setLive_id(rs.getString("live_id"));
				bean.setProduct_category(rs.getString("category"));
				bean.setFormat(rs.getString("format"));
				bean.setArthist_name(rs.getString("arthist_name"));
				bean.setTime(rs.getString("time"));
				bean.setPrice(rs.getString("price"));
				bean.setTrack_number(rs.getString("track_number"));
				bean.setRelease_data(rs.getString("release_data"));
				ret.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * updateProducts()
	 * @param String product_id
	 * @reutrn int ret
	 * 商品情報の更新
	 */
	@SuppressWarnings("finally")
	public int updateProducts(AdminProductBean bean) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "update PRODUCTS set product_name = ?, "
				+ "arthist_id = (select arthist_id from ARTHIST where arthist_name = ?), "
				+ "product_category_id = ?, time = ?, format_id = ?, price = ? "
				+ "where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getProduct_name());
			ps.setString(2, bean.getArthist_name());
			ps.setInt(3, Integer.parseInt(bean.getProduct_category()));
			ps.setString(4, bean.getTime());
			ps.setInt(5, Integer.parseInt(bean.getFormat()));
			ps.setInt(6, Integer.parseInt(bean.getPrice()));
			ps.setString(7, bean.getProduct_id());
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * insertProducts()
	 * @param AdminProductBean bean
	 * @return int 1:OK -1:NG
	 * 商品登録を実行する　戻り値が1の場合成功　-1の場合失敗
	 */
	@SuppressWarnings("finally")
	public int insertProducts(AdminProductBean bean) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "insert into PRODUCTS values(?, ?, ?, ?, (select arthist_id from ARTHIST where arthist_name = ?), "
				+ "?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getProduct_id());;
			ps.setString(2, bean.getProduct_name());;
			ps.setString(3, bean.getLive_id());;
			ps.setString(4, bean.getProduct_category());;
			ps.setString(5, bean.getArthist_name());;
			ps.setString(6, bean.getTime());;
			ps.setString(7, bean.getFormat());;
			ps.setString(8, bean.getPrice());;
			ps.setString(9, bean.getTrack_number());
			ps.setString(10, bean.getRelease_data());
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * deleteProduct()
	 * @param String 
	 * @return int
	 */
	@SuppressWarnings("finally")
	public int deleteProduct(String product_id) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "delete from PRODUCTS where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_id);
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}
}
