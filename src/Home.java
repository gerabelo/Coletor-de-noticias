import java.text.SimpleDateFormat;
import java.util.Date;


public class Home {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		long startTime = System.currentTimeMillis();
		
		int totalNews = ParsingEngine.start();
		
		long startRemoveDuplicatesTime = System.currentTimeMillis();
		
		MySQLAccess.removeDuplicates();	
		
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		long estimatedParsingTime = startRemoveDuplicatesTime - startTime;
		//long estimatedRemoveDuplicatesTime = endTime - startRemoveDuplicatesTime;
		
		int minutes = (int) (estimatedTime / (1000 * 60));
	    int seconds = (int) ((estimatedTime / 1000) % 60);
	    //int milliseconds = (int) (estimatedTime % 1000);	    
		
	    int crawlingMinutes = (int) (estimatedParsingTime / (1000 * 60));
	    int crawlingSeconds = (int) ((estimatedParsingTime / 1000) % 60);
	    
		System.out.println("completed in "+minutes+" minutes, "+seconds+" seconds");
		
		String query = "";
		String runTime = minutes+":"+seconds;
		
		String totalSources = MySQLAccess.totalSources();
		String totalKeyWords  = MySQLAccess.totalKeyWords();		

		String crawlingTime =crawlingMinutes+":"+crawlingSeconds;
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd hh:mm");
	    
	    query = "INSERT INTO stats (runTime,crawlingTime,totalSources,totalKeyWords,totalNews,dateCreate) VALUES ('"
				+runTime+"','"+crawlingTime+"','"+totalSources+"','"+totalKeyWords+"','"+totalNews+"','"+ft.format(dNow)+"')";
		
	    System.out.println(query);
	    
	    MySQLAccess.executeUpdate(query);
		
	}

}
