import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Driver;

public class MySQLAccess {
	
	private static String connectionUrl = "jdbc:mysql://127.0.0.1/newscrawler?autoReconnect=true&useSSL=false";			
	private static String connectionUser = "root";
	private static String connectionPassword = "123456";
	
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	public static boolean debug = false;
	
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
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
		return null;		
	}
	
	public static boolean executeUpdate(String query) {
		boolean result = false;
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
			result = true;
		} catch (Exception e) {
			System.out.print(",");
			//System.out.println(query);
			//System.out.println("");
			//e.printStackTrace();
			
		} finally {	        
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
		return result;
	}

	public static String getWhiteList() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT word FROM whiteList");
			
			while (rs.next()) {
				result = result+rs.getString("word")+"#";
			}
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
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
				result = result+rs.getString("word")+"#";
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
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
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
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
				result = result+rs.getString("id")+"#"+rs.getString("description")+" ";
			}
			return result;			
		} catch (Exception e) {
			e.printStackTrace();
			return result;			
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
	}
	
	
	public static void removeDuplicates(String arg) {
		
		if (arg == "debug") debug = true;
		
		String query = "";
		String id="";
		String url="";
		String text="";
		String row="";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,url,text FROM news");
			
			
			query = "DELETE FROM duplicates";
			if (debug) System.out.println(query);
			else System.out.print(".");
			executeUpdate(query);
			
			while (rs.next()) {
				id = rs.getString("id");
				url = rs.getString("url");
				text = rs.getString("text");
				row = id+"\n"+url+"\n"+text;
		
				String md5 = calculaMD5(row);
				
				if (chkMD5(md5)) {
					query = "DELETE FROM news WHERE id ="+id;
					if (debug) System.out.println(query);
					else System.out.print("-");
					executeUpdate(query);
					Thread.sleep(200);
				} else {
					query = "INSERT INTO duplicates (newsId,hash) VALUES ("+id+",'"+md5+"')";
					if (debug) System.out.println(query);
					else System.out.print("+");
					executeUpdate(query);
					Thread.sleep(200);
				}
			}
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public static String calculaMD5(String row) throws GeneralSecurityException {		
		String[] parts = row.split("\n");		
		String key = parts[1]+parts[2];
		
		MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(key.getBytes(),0,key.length());
	    
	    BigInteger hash = new BigInteger(1, m.digest());
		String result = hash.toString(16);	
		
		return result;
	}
	
	public static boolean chkMD5(String md5){
		boolean result = false;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT hash FROM duplicates WHERE hash ='"+md5+"'");
			
			if (rs.next()) {
				result = true;
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }		
		return result;		
	}
	
	public static String totalKeyWords() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM whiteList");
			
			while (rs.next()) { result = rs.getString("total"); }

		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }		

		return result;
	}
	
	public static String totalSources() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM sources");

			while (rs.next()) { result = rs.getString("total"); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return result;
	}
	
	public static String getTextFromNewsId(int id) {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT text FROM news WHERE id="+id);

			while (rs.next()) { result = rs.getString("text"); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return result;
	}
	
	public static int getTotalNews() {
		int result = 0;
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM news");

			while (rs.next()) { result = Integer.parseInt(rs.getString("total")); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return result;
	}
	

	public static int getNewsByDate(String data) {
		int result = 0;
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM news WHERE dateCreate='"+data+"'");

			while (rs.next()) { result = Integer.parseInt(rs.getString("total")); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return result;
	}

	public static int isInModaTable(String word, String data) {
		int result = 0;
		String query = "";
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			query = "SELECT count(*) as total FROM moda WHERE word = '"+word+"' AND dateCreate = '"+data+"'";
			rs = stmt.executeQuery(query);

			while (rs.next()) { result = Integer.parseInt(rs.getString("total")); }			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return result;		
	}

	public static boolean incrementWordCounter(String word) {
		String query = "";
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			query = "UPDATE moda SET counter = counter+1 WHERE word='"+word+"'";
			//System.out.println(query);
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {
				e.printStackTrace();
				return false;
		} finally {
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}	        
	    } 		
		//return result;		
	}
	
	public static int isInIgnoredsTable(String word) {
		int result = 0;
		String query = "";
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			query = "SELECT count(*) as total FROM ignoreds WHERE word = '"+word+"'";
			rs = stmt.executeQuery(query);

			while (rs.next()) { result = Integer.parseInt(rs.getString("total")); }			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return result;		
	}
	
	public static String getIgnoreds() {
		String result = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT word FROM ignoreds");
			
			while (rs.next()) {
				result = result+rs.getString("word")+"#";
				//System.out.println(result);
			}			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
	}	


}