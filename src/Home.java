import java.text.SimpleDateFormat;
import java.util.Date;


public class Home {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//System.out.println(MySQLAccess.totalSources());
		ParsingEngine robot = new ParsingEngine();
		
		long startTime;
		int totalNews;
		//long startRemoveDuplicatesTime;
		long estimatedTime;
		
		int minutes;
		int seconds;
		
		Date dNow;
		
		while(true) {
			startTime = System.currentTimeMillis();
			
			totalNews = robot.start("silence");			
			
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

			dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm");
		    
		    query = "INSERT INTO stats (runTime,totalSources,totalKeyWords,totalNews,dateCreated) VALUES ('"
					+runTime+"','"+totalSources+"','"+totalKeyWords+"','"+totalNews+"','"+ft.format(dNow)+"')";
			
		    //System.out.println(query);
		    
		    MySQLAccess.executeUpdate(query);
		}
		
	}

}
