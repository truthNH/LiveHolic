package jp.ac.hal.tokyo.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jp.ac.hal.tokyo.liveholic.*;

public class DaoAdminUser {
	// コネクションオブジェクト定義
	Connection conn;
	// プリペアードステートメント定義
	PreparedStatement ps;
	// ステートメント定義
	Statement st;
	// リザルトセット定義
	ResultSet rs;

	/*
	 * コンストラクタ
	 */
	public DaoAdminUser() {
		conn = null;
		ps = null;
		st = null;
		rs = null;
	}

	/*
	 * getConnection() DBの接続設定定義
	 * @paramなし
	 * @returnなし
	 */
	private void getConnection() {
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
	 * close()
	 * @param 無し
	 * @return 無し
	 */
	private void close() {
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

	/**
	 * chkLogin()メソッド
	 * @param userdataオブジェクト
	 * @return 1:OK 0:NG -1:Fail
	 */
	@SuppressWarnings("finally")
	public int chkLogin(AdminUserBean adminUser) {
		// 戻り値
		int ret = 0;
		// ログイン認証SQL
		String sql = "select count(*) from ADMIN_USER where user = ? and passwd = ?;";
		// コネクション生成
		this.getConnection();
		// プリペアードステートメント作成
		try {
			ps = conn.prepareStatement(sql);
			// ユーザ設定
			ps.setString(1, adminUser.getUser());
			// パスワード設定
			ps.setString(2, adminUser.getPasswd());
			rs = ps.executeQuery();
			// 結果取得
			rs.next();
			ret = rs.getInt("count(*)");
		} catch (SQLException e) {
			e.printStackTrace();
			ret = -1;
		} finally {
			this.close();
			return ret;
		}
	}

	/*
	 * getRequest()
	 * @param なし
	 * @return RequestBeanオブジェクト ライブリクエストを表示させる
	 */
	@SuppressWarnings("finally")
	public ArrayList<RequestBean> getRequest() {
		// 戻り値
		ArrayList<RequestBean> ret = new ArrayList<RequestBean>();
		// DB接続
		this.getConnection();
		// SQL生成
		String sql = "select * from request";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			// レコード分ArrayListに格納する処理を繰り返す
			while (rs.next()) {
				RequestBean request = new RequestBean();
				request.setCustomer_id(rs.getInt("customer_id"));
				request.setLive_name(rs.getString("live_name"));
				request.setAddDateTime(rs.getTimestamp("request_datetime"));
				ret.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
			return ret;
		}
	}
	
	/*
	 * getRequestRank()
	 * @param なし
	 * @return ArrayList<RequestBean>
	 * ライブのリクエストの多い順に結果を返す
	 */
	@SuppressWarnings("finally")
	public ArrayList<RequestBean> getRequestRank() {
		//戻り値
		ArrayList<RequestBean> ret = new ArrayList<RequestBean>();
		//DB接続
		this.getConnection();
		//SQL生成 ユーザの同じライブ名の重複投稿を排除しリクエストのカウントを得る
		String sql = "select live_name ,count(distinct customer_id) as req_count from REQUEST group by live_name "
				+ "order by req_count desc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				RequestBean bean = new RequestBean();
				bean.setLive_name(rs.getString("live_name"));
				bean.setReqCount(rs.getInt("req_count"));
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
	 * delRequest()
	 * @param String live_name
	 * @return int 1:OK -1:NG
	 * 受け取ったリクエストライブ名を消去する
	 */
	@SuppressWarnings("finally")
	public int delRequest(String live_name) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "delete from REQUEST where live_name = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, live_name);
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
	 * getAllUser()
	 * @param なし
	 * @return ArrayListObject<AdminUserBean> 全ユーザ情報を取得する
	 */
	@SuppressWarnings("finally")
	public ArrayList<AdminUserBean> getAllUser() {
		// 戻り値
		ArrayList<AdminUserBean> ret = new ArrayList<AdminUserBean>();
		// DB接続
		this.getConnection();
		// SQL生成
		String sql = "select * from CUSTOMER";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				AdminUserBean bean = new AdminUserBean();
				bean.setCustomer_id(rs.getInt("customer_id"));
				bean.setUser_id(rs.getString("user_id"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setFirst_name(rs.getString("first_name"));
				bean.setLast_name(rs.getString("last_name"));
				bean.setRecode_date(rs.getString("record_date"));
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
	 * deleteUser()
	 * @param String customer_id
	 * @return 1:OK -1:NG
	 */
	@SuppressWarnings("finally")
	public int deleteUser(int customer_id) {
		//戻り値
		int ret = 0;
		//DB接続
		this.getConnection();
		//SQL生成
		String sql = "delete from CUSTOMER where customer_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, customer_id);
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
