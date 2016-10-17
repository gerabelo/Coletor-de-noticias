import java.text.SimpleDateFormat;
import java.util.Date;


public class Home {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		long startTime;
		int totalNews;
		long startRemoveDuplicatesTime;
		long estimatedTime;
		long estimatedParsingTime;
		int minutes;
		int seconds;
		int crawlingMinutes;
		int crawlingSeconds;
		Date dNow;
		
		
		while(true) {
			startTime = System.currentTimeMillis();
			
			totalNews = ParsingEngine.start("silence");			
			
			estimatedParsingTime = System.currentTimeMillis() - startTime;
		    
			crawlingMinutes = (int) (estimatedParsingTime / (1000 * 60));
		    crawlingSeconds = (int) ((estimatedParsingTime / 1000) % 60);		    
		    
		    System.out.println("crawling takes "+crawlingMinutes+" minutes and "+crawlingSeconds+" seconds");			
			
			MySQLAccess.removeDuplicates("silence");		
			
			estimatedTime = System.currentTimeMillis() - startTime;
			//long estimatedRemoveDuplicatesTime = endTime - startRemoveDuplicatesTime;
			
			minutes = (int) (estimatedTime / (1000 * 60));
		    seconds = (int) ((estimatedTime / 1000) % 60);
		    //int milliseconds = (int) (estimatedTime % 1000);			
		    
		    System.out.println("");
		    System.out.println("All tasks completed in "+minutes+" minutes, "+seconds+" seconds");
			
			String query = "";
			String runTime = minutes+":"+seconds;
			
			String totalSources = MySQLAccess.totalSources();
			String totalKeyWords  = MySQLAccess.totalKeyWords();		
	
			String crawlingTime =crawlingMinutes+":"+crawlingSeconds;
			
			dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd hh:mm");
		    
		    query = "INSERT INTO stats (runTime,crawlingTime,totalSources,totalKeyWords,totalNews,dateCreate) VALUES ('"
					+runTime+"','"+crawlingTime+"','"+totalSources+"','"+totalKeyWords+"','"+totalNews+"','"+ft.format(dNow)+"')";
			
		    //System.out.println(query);
		    
		    MySQLAccess.executeUpdate(query);
		}
	}

}
