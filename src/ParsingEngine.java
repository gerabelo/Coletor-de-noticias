import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ParsingEngine {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static int minimumWordsInAFrase = 5;
	public static int retries = 5;
	public static int delay = 200;
	
	public static void main(String[] args) throws Exception {
		//start();		
	}


	public static int start() throws Exception {
		//PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		//System.setOut(out);
		int result = 0;
		
		String[] source = MySQLAccess.getSources().split(" ");
		int k = 0;
		
		if (source.length > 0) {		
			for(int i=0;i < source.length;i++) {
				System.out.println("source[i]:"+source[i]);
				String[] parts = source[i].split("#");
				Elements links = null;
				
				links = getURL(parts[1]);
				
				if (links != null) {
					for(int j=0;j < links.size();j++) {
			    		if (!chkBlackList(links.get(j).text())) {
			    			
			    			if (links.get(j).text().split(" ").length > minimumWordsInAFrase) {
				    			int value = num_of_keyWords(links.get(j).text()); //number of keywords occurrences
				    			
				    			Date dNow = new Date( );
				    		    SimpleDateFormat ft = 
				    		    new SimpleDateFormat ("yyyy/MM/dd hh:mm");		    		      
				    		      
				    			String query = "INSERT INTO news (sourceId,url,text,value,dateCreate) VALUES ("
				    					+parts[0]+",'"	    					
				    					+links.get(j).attr("abs:href").replace("'", "''").replaceAll("[\\t\\n\\r]"," ")+"','"
				    					+links.get(j).text().replace("'", "''").replaceAll("[\\t\\n\\r]"," ")+"',"
				    					+value+",'"
				    					+ft.format(dNow)+"')";
				    			
				    			if (value > 0) if (MySQLAccess.executeUpdate(query)) result++;
				    			//MySQLAccess.executeUpdate(query);
				    			Thread.sleep(delay);
				    			System.out.println(k+++": "+query);
			    			}
			    		}
			    	}
				}
			}
		}
		return result;
	}
	
	public static boolean chkBlackList(String text) {		
		String blackList = MySQLAccess.getBlackList();
		String[] parts = blackList.split(" ");
		
		//System.out.println("blacklist...");
		
		for (int i = 0;i < parts.length;i++) {
			if (text.toLowerCase().contains(parts[i].toLowerCase())) return true;		
		}				
		return false;
	}
	
	public static int num_of_keyWords(String text) {
		int count = 0;
		
		String whiteList = MySQLAccess.getWhiteList();
		String[] parts = whiteList.split(" ");
		
		//System.out.println("processando keywords...");		
		for (int i = 0;i < parts.length;i++) {
			
			if (text.toLowerCase().contains(parts[i].toLowerCase())) { count++; System.out.println(count+" "+parts[i].toLowerCase()+" ");}		
		}		
		
		return count;
	}
	
	
	public String getTitle(String url) throws Exception {
	    Document document;
	    try {
	        document = Jsoup.connect(url).get();

	        String title = document.title();
	 
	        return title;
	    } catch (IOException e) {
	    	//e.printStackTrace();
	    	return null;
	    }		
	}	
	
	public String getMetaData(String url) throws Exception {
		Document document;
		try {
			document = Jsoup.connect(url).get();
		 
			//String description = document.select("meta[name=description]").get(0).attr("content");		 
			String keywords = document.select("meta[name=keywords]").first().attr("content");

			return keywords;
		 
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		}		
	}
	
	public static Elements getURL(String url) {
	    Document document = null;
	    Elements links = null;
	    
	    int i = 0;	    
	    boolean sucess = false;
	    
	    while (i < retries) {
		    try {
		    	if (i > 0) System.out.println("retrying..."+i);
	   			if (!url.startsWith("http://") && !url.startsWith("https://"))	url = "http://"+url;	    			    
		    	
		    	document = Jsoup.connect(url)
		    			.userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
	                    .followRedirects(false)
	                    .timeout(10000)
	                    .get();		 
		    	sucess = true;
		    	break;
		    } catch (SocketTimeoutException ex){
	        } catch (MalformedURLException ep){
	        } catch (IOException e) {	        
	        } finally {
	        	i++;
	        }
	    }
	    
	    if (sucess) links = document.select("a[href]");
	    return links;
	}
	
	public static String getDescription(String url) throws Exception {
		Document document;
		try {
			document = Jsoup.connect(url).get();
		 
			String description = document.select("meta[name=description]").get(0).attr("content");
			
			return description;
		 
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		}		
	}
}
