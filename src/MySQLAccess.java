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
	
	/**
	 * Realiza consultas genéricas à base e retorna os dados selecionados.
	 * @param query
	 * @return
	 */
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
	
	
	/**
	 * Realiza alterações e retorna true em caso de sucesso.
	 * @param query
	 * @return success
	 */
	public static boolean executeUpdate(String query) {
		boolean success = false;
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
			System.out.print(",");
			//System.out.println(query);
			//System.out.println("");
			//e.printStackTrace();
			
		} finally {	        
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
		return success;
	}

	/**
	 * Retorna string com as 'keywords' separadas por '#'.
	 * @return
	 */
	public static String getWhiteList() {
		String whiteList = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT word FROM whiteList");
			
			while (rs.next()) {
				whiteList = whiteList+rs.getString("word")+"#";
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
		return whiteList;
	}	
	
	/**
	 * Retorna string com 'palavras a ignorar', separadas por '#'.
	 * @return return blackList;
	 */
	public static String getBlackList() {
		String blackList = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT word FROM blackList");
			
			while (rs.next()) {
				blackList = blackList+rs.getString("word")+"#";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
		return blackList;
	}
	
	/**
	 * Retorna string com as 'id' e 'url' da fonte de notícias, separadas por #, em grupos separados por espaço ' '.
	 * @return allSources
	 */
	public static String getSources() {
		String allSources = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id, url FROM sources"); //deleção logica? status = 1 
			
			while (rs.next()) {
				allSources = allSources+rs.getString("id")+"#"+rs.getString("url")+" ";
				//System.out.println(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }
		return allSources;
	}	

	/**
	 * @deprecated
	*/
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
	
	
	/**
	 * @deprecated
	*/
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
	
	/**
	 * Retorna string com o Hash calculado para uma dada string. 
	 * Utilizada na eliminação de duplicações. 
	 * Embora o seu cálculo demande processamento o hash ocupa pouco espaço em disco.
	 * @param row
	 * @return md5
	 * @throws GeneralSecurityException
	 */
	public static String calculaMD5(String row) throws GeneralSecurityException {		
		String[] parts = row.split("\n");		
		String key = parts[1]+parts[2];
		
		MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(key.getBytes(),0,key.length());
	    
	    BigInteger hash = new BigInteger(1, m.digest());
		String md5 = hash.toString(16);	
		
		return md5;
	}
	
	/**
	 * Verifica se o md5 já existe.
	 * @param md5
	 * @return colision
	 */
	public static boolean chkMD5(String md5){
		boolean colision = false;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT hash FROM duplicates WHERE hash ='"+md5+"'");//deleção lógica? status = 0
			
			if (rs.next()) {
				colision = true;
				return colision;
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }		
		return colision;		
	}
	
	/**
	 * Retorna o numero de Keywords no formato string.
	 * @return numberOfKeyWords
	 */
	public static String totalKeyWords() {
		String numberOfKeyWords = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM whiteList");
			
			while (rs.next()) { numberOfKeyWords = rs.getString("total"); }

		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    }		

		return numberOfKeyWords;
	}

	/**
	 * Retorna a quantidade de 'fontes de noticia' no formato string.
	 * @return numberOfSources
	 */
	public static String totalSources() {
		String numberOfSources = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM sources");

			while (rs.next()) { numberOfSources = rs.getString("total"); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return numberOfSources;
	}
	
	/**
	 * Retorna o conteúdo do campo 'Text', no formato string, referente ao 'id' passado, no formato int.
	 * @param id
	 * @return textFromNewsId
	 */
	public String getTextFromNewsId(int id) {
		String textFromNewsId = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT text FROM news WHERE id="+id);

			while (rs.next()) { textFromNewsId = rs.getString("text"); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return textFromNewsId;
	}
	
	
	/**
	 * Retorna a quantidade de notícias armazenadas, no formato int.
	 * @return numberOfNews
	 */
	public static int getTotalNews() {
		int numberOfNews = 0;
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM news");

			while (rs.next()) { numberOfNews = Integer.parseInt(rs.getString("total")); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return numberOfNews;
	}
	
	/**
	 * Retorna a quantidade de notícias para uma determinada data, passada como parâmetro no formato string.
	 * 
	 * @param data
	 * @return numberOfNewsByDate
	 */
	public static int getNumberOfNewsByDate(String data) {
		int numberOfNewsByDate = 0;
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM news WHERE dateCreate='"+data+"'");

			while (rs.next()) { numberOfNewsByDate = Integer.parseInt(rs.getString("total")); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return numberOfNewsByDate;
	}

	public  String getNewsIdByDate(String data) {
		String newsIdByDate = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			String query = "SELECT id FROM news WHERE dateCreate like '"+data+"%'";
			rs = stmt.executeQuery(query); //deleção logica? status = 1 
			//System.out.println(query);
			while (rs.next()) {
				newsIdByDate = newsIdByDate+rs.getString("id")+" ";
				//System.out.println(result);
			}			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return newsIdByDate;
	}

	public String getNewsIdByDateAndSource(String data,String sourceId) {
		String newsIdByDateAndSource = "";
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			String query = "SELECT id FROM news WHERE dateCreate like '"+data.trim()+"%' AND sourceId="+sourceId.trim();;
			rs = stmt.executeQuery(query); //deleção logica? status = 1 
			
			while (rs.next()) {
				newsIdByDateAndSource = newsIdByDateAndSource+rs.getString("id")+" ";
				//System.out.println(result);
			}			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return newsIdByDateAndSource;
	}

	/**
	 * 
	 * @param data
	 * @param sourceId
	 * @return
	 */
	public static int getNumberOfNewsByDateAndSource(String data,String sourceId) {
		int numberOfNewsByDateAndSource = 0;
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();			
			
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) as total FROM news WHERE dateCreate='"+data.trim()+"' AND sourceId ="+sourceId.trim());

			while (rs.next()) { numberOfNewsByDateAndSource = Integer.parseInt(rs.getString("total")); }		
			
			
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
	        if (rs != null) try { rs.close(); } catch (SQLException logOrIgnore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException logOrIgnore) {}
	        if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
	    } 
		
		return numberOfNewsByDateAndSource;
	}

	/**
	 * 
	 * @param word
	 * @param data
	 * @return
	 */
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

	/**
	 * 
	 * @param word
	 * @return
	 */
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
	
	/**
	 * 
	 * @param word
	 * @return
	 */
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
	
	/**
	 * 
	 * @return
	 */
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