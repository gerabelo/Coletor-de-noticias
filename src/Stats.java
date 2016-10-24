import java.text.SimpleDateFormat;
import java.util.Date;


public class Stats {

	/**
	 * @param args
	 */
	public static String ignoreds = "";
	public static String data = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date dNow = new Date( );
	    SimpleDateFormat ft = 
	    new SimpleDateFormat ("yyyy/MM/dd");
	    data = ft.format(dNow);
	    
		int newsId = 0;
		int dbSize = MySQLAccess.getNewsByDate(data);
		String text = "";
		String moda = "";		
		ignoreds = MySQLAccess.getIgnoreds();
		
		for (int i = 0;i < dbSize;i++) {
			text = MySQLAccess.getTextFromNewsId(i);
			if (text.length() > 1) { 
				moda = computeModa(text);
				text = "";
			}
		}
		
		
		
	}
	
	public static String computeModa (String population) {
		String result = "";
		String query = "";
		
		
		String[] words = population.split(" ");
		//String[] ignore = ignoreds.split("#");		
		
	    
		for (int i=0;i < words.length ; i++) {
			words[i] = words[i].toLowerCase().replace("'", "`");			
			
				if (!ignoreds.toLowerCase().contains(words[i])) {			
			
					//if (MySQLAccess.isInIgnoredsTable(words[i]) == 0) { removido porque a leitura de disco intensa pode ser desnecessária. 	
						if (MySQLAccess.isInModaTable(words[i],data) > 0) {
							MySQLAccess.incrementWordCounter(words[i]);
						} else {
							query = "INSERT INTO moda (word,counter,dateCreate,status) VALUES ('"+words[i]+"',1,'"+data+"',0)";
							//System.out.println(query);
							MySQLAccess.executeUpdate(query);
						}
					//} else {
						//System.out.println("ignoring "+words[i]);
					//}
				}
		}		
		return result;
	}
	
}
