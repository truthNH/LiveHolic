package jp.ac.hal.tokyo.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoManager {
	
	/*
	 * getConnection() DBの接続設定定義
	 * 
	 * @param Connectionオブジェクト
	 * 
	 * @returnなし
	 */
	public void getConnection(Connection conn) {
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
	 * 
	 * @param ResultSet rs, PreparedStatement ps, Statement st,　Connection conn
	 * 
	 * @return 無し
	 */
	public void close(ResultSet rs, PreparedStatement ps, Statement st,
			Connection conn) {
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
}
