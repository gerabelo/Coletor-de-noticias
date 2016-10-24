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
		
//		new Thread() {
//			public void run() {
//				//MySQLAccess database = new MySQLAccess();
//				estatistica.moda();
//			}
//		}.start();
		
		String[] sources = MySQLAccess.getSources().split(" ");
		System.out.println("Calculating moda by source...");
		System.out.println("Number of sources: "+sources.length);
		
		if (sources.length > 0) {		
			for(int i=0;i < sources.length;i++) {
				
				String[] parts = sources[i].split("#");
				System.out.println("["+(i+1)+"] processing source: [id:"+parts[0]+"] "+parts[1]);
				estatistica.modaBySource(parts[0]);
			}
		}
		
		//System.out.println("Calculating accumulated moda");
		//estatistica.moda();
	}
	
	public void moda() {
		//System.out.println(data);
		MySQLAccess basededados = new MySQLAccess();
		String[] newsIds = basededados.getNewsIdByDate(data).split(" ");
		//System.out.println(newsIds.length);
		//System.out.println(newsIds[1]);
		String text = ""; 
		
		if (newsIds.length > 0) {
			for (int i = 0;i < newsIds.length;i++) {
				//System.out.println(newsIds[i]);
				text = basededados.getTextFromNewsId(Integer.parseInt(newsIds[i]));
				if (text.length() > 1) { 
					estatistica.computeModa(text);
					text = "";
				}
			}
		}
	}
	
	public void modaBySource(String sourceId) {
		MySQLAccess basededados = new MySQLAccess();
		String[] newsIds = basededados.getNewsIdByDateAndSource(data,sourceId).split(" "); 
		String text = ""; 
		if (newsIds.length > 0) {	
			for (int i = 0;i < newsIds.length;i++) {
				//System.out.println("sourceId: "+sourceId);
				//System.out.println("id: "+i);
				//System.out.println("newsIds.length: "+newsIds.length);
				//System.out.println("newsIds[i]: "+newsIds[i]);
				if (newsIds[i] != "") {
					text = basededados.getTextFromNewsId(Integer.parseInt(newsIds[i]));
					if (text.length() > 1) { 
						estatistica.computeModaBySource(text,sourceId);
						text = "";
					}
				}
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
							query = "INSERT INTO moda (word,counter,dateCreated,status) VALUES ('"+words[i]+"',1,'"+data+"',0)";
							//System.out.println(query);
							MySQLAccess.executeUpdate(query);
						}
					//} else {
						//System.out.println("ignoring "+words[i]);
					//}
				}
		}		
	}
	
	public void computeModaBySource (String population,String sourceId) {
		String query = "";		
		
		String[] words = population.split(" ");		
	    
		for (int i=0;i < words.length ; i++) {
			words[i] = words[i].toLowerCase().replace("'", "`");			
			
				if (!ignoreds.toLowerCase().contains(words[i])) {			
			
 	
						if (MySQLAccess.isInModaTable(words[i],data,sourceId) > 0) {
							MySQLAccess.incrementWordCounter(words[i]);
						} else {
							query = "INSERT INTO moda (word,counter,dateCreated,status,sourceId) VALUES ('"+words[i]+"',1,'"+data+"',0,'"+sourceId+"')";
							//System.out.println(query);
							MySQLAccess.executeUpdate(query);
						}
				}
		}		
	}
}
