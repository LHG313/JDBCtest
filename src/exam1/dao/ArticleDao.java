package exam1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import exam1.dto.Article;

public class ArticleDao {

	public List<Article> getArticlesFromDB() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Article> articles = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			String sql = "select * from article order by id desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Article article = new Article();

				article.id = rs.getInt("id");
				article.regDate = rs.getString("regDate");
				article.title = rs.getString("title");
				article.body = rs.getString("body");
				article.nickname = rs.getString("nickname");
				article.hit = rs.getInt("hit");

				articles.add(article);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB접속실패" + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
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
		return articles;
	}

	public int add(String title, String body, String nickname, int hit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int articleId = 0;
		Calendar cal = new GregorianCalendar();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			String sql = "insert into article(title, body, nickname, hit, regDate) values(?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			Timestamp ts = new Timestamp(cal.getTimeInMillis());

			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setString(3, nickname);
			pstmt.setInt(4, hit);
			pstmt.setTimestamp(5, ts);

			pstmt.executeUpdate();

			String confSql = "SELECT * FROM article ORDER BY id desc LIMIT 1";
			rs = conn.prepareStatement(confSql).executeQuery();
			while (rs.next()) {
				articleId = rs.getInt("id");
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
		return articleId;
	}

	public void remove(int inputedId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			String sql = "DELETE FROM article WHERE id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputedId);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB접속실패" + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
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
	}

	public void modify(int inputedId, String title, String body) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int articleId = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");

			String sql = "UPDATE article set title = ?, body = ? WHERE id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setInt(3, inputedId);
			int r = pstmt.executeUpdate();
			if (r == 0) {
				System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
				return;
			} else if (r == 1) {
				System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);
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

	}

	public Article getAnArticleFromDB(int inputedId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article anArticle = new Article();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/a1?serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");
			String sql = "select * from article where id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, inputedId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				anArticle.id = rs.getInt("id");
				anArticle.regDate = rs.getString("regDate");
				anArticle.title = rs.getString("title");
				anArticle.body = rs.getString("body");
				anArticle.nickname = rs.getString("nickname");
				anArticle.hit = rs.getInt("hit");

			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB접속실패 " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
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
		return anArticle;
	}

}
