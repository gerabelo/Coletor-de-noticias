//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Stats {

	/**
	 * @param args
	 */
	public static MySQLAccess basededados = new MySQLAccess();
	public static String ignoreds = basededados.getIgnoreds();;
	public static String data = "";
	static Stats estatistica = new Stats();
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd");
		basededados.executeUpdate("DELETE FROM moda WHERE dateCreated = '"+data+"'");
		
		if (args.length > 0) {
			//System.out.println("args[0]: "+args[0]+" length:"+args[0].length());
			Date date = null;			
		    ft.setLenient(false);
		    try {
		    	date = ft.parse(args[0]);
		    	data = ft.format(date);
		    	
		    } catch (Exception e) {
		    	
		    }
		} else {
			Date dNow = new Date( );		    
		    data = ft.format(dNow);			
		}
		
//		new Thread() {
//			public void run() {
//				//MySQLAccess database = new MySQLAccess();
//				estatistica.moda();
//			}
//		}.start();
	    MySQLAccess basededados = new MySQLAccess();
		
		String[] sources = basededados.getSources().split(" ");
		int totalSources = sources.length;
		System.out.println("Calculating moda by source...");
		System.out.println("Number of sources: "+totalSources);
		
		if (sources.length > 0) {		
			for(int i=0;i < sources.length;i++) {
				
				String[] parts = sources[i].split("#");
				System.out.println("["+(i+1)+"/"+totalSources+"] processing source: [id:"+parts[0]+"] "+parts[1]);
				estatistica.modaBySource(parts[0]);
			}
		}
		
		//System.out.println("Calculating accumulated moda");
		//estatistica.moda();
	}
	
	public void moda() {
		//System.out.println(data);
		MySQLAccess basededados = new MySQLAccess();
		//basededados.executeUpdate("DELETE FROM moda WHERE dateCreated = '"+data+"'");
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
    	System.out.println(data);

		MySQLAccess basededados = new MySQLAccess();
		//basededados.executeUpdate("DELETE FROM moda WHERE dateCreated = '"+data+"'");
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
		MySQLAccess basededados = new MySQLAccess();
		String[] words = population.split(" ");
		//String[] ignore = ignoreds.split("#");		
		
	    
		for (int i=0;i < words.length ; i++) {
			words[i] = words[i].toLowerCase().replace("'", "`");			
			
				if (!ignoreds.toLowerCase().contains(words[i])) {			
			
					//if (MySQLAccess.isInIgnoredsTable(words[i]) == 0) { removido porque a leitura de disco intensa pode ser desnecessária. 	
						if (basededados.isInModaTable(words[i],data) > 0) {
							basededados.incrementWordCounter(words[i]);
						} else {
							query = "INSERT INTO moda (word,counter,dateCreated,status) VALUES ('"+words[i]+"',1,'"+data+"',0)";
							//System.out.println(query);
							basededados.executeUpdate(query);
						}
					//} else {
						//System.out.println("ignoring "+words[i]);
					//}
				}
		}		
	}
	
	public void computeModaBySource (String text,String sourceId) {
		MySQLAccess basededados = new MySQLAccess();
		sourceId = sourceId.trim();
		String query = "";		
		
		String[] words = text.split(" ");		
	    
		for (int i=0;i < words.length ; i++) {
			words[i] = words[i].toLowerCase().replace("'", "").replace(",", "").replace(".", "").replace(":", "").replace(";", "").replace("\"", "");			
			
				if (!ignoreds.toLowerCase().contains(words[i])) {			
			
 	
						if (basededados.isInModaTable(words[i],data,sourceId) > 0) {
							basededados.incrementWordCounter(words[i],sourceId);
						} else {
							query = "INSERT INTO moda (word,counter,dateCreated,status,sourceId) VALUES ('"+words[i].trim()+"',1,'"+data+"',0,'"+sourceId+"')";
							//System.out.println(query);
							basededados.executeUpdate(query);
						}
				}
		}		
	}
}
