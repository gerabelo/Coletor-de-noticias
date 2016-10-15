import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Driver;

public class MySQLAccess {
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	private static String connectionUrl = "jdbc:mysql://127.0.0.1/webbot?autoReconnect=true&useSSL=false";			
	private static String connectionUser = "root";
	private static String connectionPassword = "123456";
	
	public static void main(String args[]) {}
	
	public static ResultSet executeQuery(String query) {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String url = rs.getString("url");
//
//				System.out.println("ID: " + id + ", url: " + url);
//			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return rs;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}
	
	public static void executeUpdate(String query) {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String url = rs.getString("url");
//
//				System.out.println("ID: " + id + ", url: " + url);
//			}			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {			
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}

	public static String getWhiteList() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT word FROM whiteList");
			
			while (rs.next()) {
				result = result+rs.getString("word");
			}
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}	
	
	public static String getBlackList() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT word FROM blackList");
			
			while (rs.next()) {
				result = result+rs.getString("word");
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public static String getSources() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, url FROM sources");
			
			while (rs.next()) {
				result = result+rs.getString("id")+"#"+rs.getString("url")+" ";
				//System.out.println(result);
			}			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }			
		}
	}	

	public static String getCategories() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, description FROM categories");
			
			while (rs.next()) {
				result = result+rs.getString("id")+":"+rs.getString("description")+" ";
			}
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
			return result;			
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }

		}
	}
}