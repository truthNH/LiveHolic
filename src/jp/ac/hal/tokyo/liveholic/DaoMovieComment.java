package jp.ac.hal.tokyo.liveholic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoMovieComment {

	// コネクションオブジェクト定義
	Connection conn;
	// プリペアードステートメント定義
	PreparedStatement ps;
	// ステートメント定義
	Statement st;
	// リザルトセット定義
	ResultSet rs;
	
	public DaoMovieComment() {
		//コネクションオブジェクト初期化
		conn = null;
		//プリペアードステートメント初期化
		ps = null;
		//ステートメント初期化
		st = null;
		//ResultSet初期化
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
	 * setComment() 
	 * @param Beanオブジェクト
	 * @return int 1:OK 0:NG
	 */
	@SuppressWarnings("finally")
	public int setComment(VideoBean bean) {
		// 戻り値
		int ret = 0;
		// コネクション生成
		this.getConnection();
		// SQL生成
		String sql = "insert into STREAMING(sec, comment, product_id) values(?,?,?)";

		try {
			ps = conn.prepareStatement(sql);
			// プレースホルダー設定
			ps.setFloat(1, bean.getSec());
			ps.setString(2, bean.getComment());
			ps.setString(3, bean.getProduct_id());
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			ret = 0;
		} finally {
			this.close();
			return ret;
		}
	}

	/*
	 * getComment()
	 * @param String product_id
	 * @return ArrayList<Object>
	 */
	@SuppressWarnings("finally")
	public ArrayList<Object> getComment(String product_id) {
		// 戻り値設定
		ArrayList<Object> ret = new ArrayList<Object>();
		// コネクション生成
		this.getConnection();
		// SQL生成
		String sql = "select id, sec, comment from STREAMING where product_id = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				// Beanオブジェクト生成
				VideoBean beanCommentData = new VideoBean();
				// オブジェクトにResultSetの値を格納していく
				beanCommentData.setId(rs.getInt("id"));
				beanCommentData.setSec(rs.getFloat("sec"));
				beanCommentData.setComment(rs.getString("comment"));
				// オブジェクトをリストに格納
				ret.add(beanCommentData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getCommentNumber(String product_id)
	 * @param String product_id
	 * @return int 
	 */
	@SuppressWarnings("finally")
	public int getCommentNumber(String product_id) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "select count(*) from STREAMING where product_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_id);
			rs = ps.executeQuery();
			rs.next();
			ret = rs.getInt("count(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
}
