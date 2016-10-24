import java.text.SimpleDateFormat;
import java.util.Date;


public class Stats {

	/**
	 * @param args
	 */
	public static String ignoreds = MySQLAccess.getIgnoreds();;
	public static String data = "";
	static Stats estatistica = new Stats();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Date dNow = new Date( );
	    SimpleDateFormat ft = 
	    new SimpleDateFormat ("yyyy/MM/dd");
	    data = ft.format(dNow);
		
		estatistica.moda();
		
		String[] source = MySQLAccess.getSources().split(" ");

		//int k = 0;		
		if (source.length > 0) {		
			for(int i=0;i < source.length;i++) {
				String[] parts = source[i].split("#");
				estatistica.modabyid(parts[0]);
			}
		}
		
		
	}
	
	public void moda() {
		//System.out.println(data);
		String[] newsIds = MySQLAccess.getNewsIdByDate(data).split(" ");
		//System.out.println(newsIds.length);
		//System.out.println(newsIds[1]);
		String text = ""; 
		
		for (int i = 0;i < newsIds.length;i++) {
			//System.out.println(newsIds[i]);
			text = MySQLAccess.getTextFromNewsId(Integer.parseInt(newsIds[i]));
			if (text.length() > 1) { 
				estatistica.computeModa(text);
				text = "";
			}
		}		
	}
	
	public void modabyid(String sourceId) {
		String[] newsIds = MySQLAccess.getNewsIdByDateAndSource(data,sourceId).split(" "); 
		String text = ""; 
		
		for (int i = 0;i < newsIds.length;i++) {
			text = MySQLAccess.getTextFromNewsId(Integer.parseInt(newsIds[i]));
			if (text.length() > 1) { 
				estatistica.computeModa(text);
				text = "";
			}
		}		
	}
	
	public void computeModa (String population) {
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
	}
	
}
