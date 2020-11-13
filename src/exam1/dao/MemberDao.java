package exam1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;

import exam1.dto.Article;
import exam1.dto.Member;

public class MemberDao {
	private List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();

	}

	public int join(String loginId, String loginPw, String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int memberId = 0;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			String sql = "insert into member(loginId, loginPw, name) values(?, ?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			pstmt.setString(3, name);

			pstmt.executeUpdate();

			String confSql = "SELECT * FROM member ORDER BY id DESC LIMIT 1";
			rs = conn.prepareStatement(confSql).executeQuery();
			while (rs.next()) {
				memberId = rs.getInt("id");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB접속실패 " + e);
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberId;
	}

	public Member getMemberid(int id) {
		List<Member> members = new ArrayList<>();
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			String sql = "insert into member(loginId, loginPw, name) values(?, ?,?)";

			pstmt = conn.prepareStatement(sql);

			if (rs.next()) {

				int memberId = rs.getInt("id");
				String loginId = rs.getString("loginId");
				String loginPw = rs.getString("loginPw");

			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB접속실패 " + e);
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return member;
	}

}
