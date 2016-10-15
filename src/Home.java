
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Home {
	
	public static void main(String args[]) throws Exception {		
		MySQLAccess dao = new MySQLAccess();
		dao.writeDataBase("select * from sources");        
	}
}