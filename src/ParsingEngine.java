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
	public static int retries = 10;
	public static int delay = 200;
	public static boolean debug = false;
	
	public static void main(String[] args) throws Exception {
		//start();		
	}


	public static int start(String arg) throws Exception {		
		if (arg == "debug") debug = true; 
		//PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		//System.setOut(out);
		int result = 0;
		int partial = 0;
		int add = 0;
		
		long startTime;
		long estimatedTime;
		int minutes;
		int seconds;
		
		String hash;
		String url;
		String text;
		
		String[] source = MySQLAccess.getSources().split(" ");
		int k = 0;
		
		if (source.length > 0) {		
			for(int i=0;i < source.length;i++) {
				partial = 0;
				add = 0;
				//System.out.println("source[i]:"+source[i]);
				
				startTime = System.currentTimeMillis();
				
				String[] parts = source[i].split("#");
				Elements links = null;
				
				int totalKeyWords = 0;
				int totalLinks = 0;
				
				try {
					
					links = getURL(parts[1]);
					totalLinks = links.size();
					result = result + totalLinks;
					
					if (links != null) {
						for(int j=0;j < totalLinks;j++) {
				    		if (!chkBlackList(links.get(j).text())) {
				    			
				    			if (links.get(j).text().split(" ").length > minimumWordsInAFrase) {
					    			int value = num_of_keyWords(links.get(j).text()); //number of keywords occurrences
					    			
					    			Date dNow = new Date( );
					    		    SimpleDateFormat ft = 
					    		    new SimpleDateFormat ("yyyy/MM/dd hh:mm");
					    		    
					    		    url = links.get(j).attr("abs:href").replace("'", "''").replaceAll("[\\t\\n\\r]"," ");
					    		    text = links.get(j).text().replace("'", "''").replaceAll("[\\t\\n\\r]"," ");
					    		    hash = MySQLAccess.calculaMD5("0\n"+url+"\n"+text+"\n");  
					    		    
					    			String query = "INSERT INTO news (sourceId,url,text,value,dateCreate,hash) VALUES ("
					    					+parts[0]+",'"	    					
					    					+url+"','"
					    					+text+"',"
					    					+value+",'"
					    					+ft.format(dNow)+"','"
					    					+hash+"')";
					    			
					    			//if (value > 0) if (MySQLAccess.executeUpdate(query)) { result++; partial++;}
					    			partial++;
					    			if (MySQLAccess.executeUpdate(query)) { add++; }
					    			Thread.sleep(delay);
					    			
					    			if (debug) System.out.println(k+": "+query);
					    			else System.out.print(".");
					    			k++;
					    			totalKeyWords = totalKeyWords+value; 
				    			}
				    		}
				    	}
					}
				} catch (Exception e) {
					
				}
				estimatedTime = System.currentTimeMillis() - startTime;				
				System.out.println("");
				System.out.print(totalKeyWords+" keywords found in "+partial+" of "+totalLinks+" links in "+source[i]+" "+add+" added");			    
				minutes = (int) (estimatedTime / (1000 * 60));
			    seconds = (int) ((estimatedTime / 1000) % 60);
			    if (partial > 0) {
				    if (minutes > 0) System.out.print(" in "+minutes+" minutes and "+seconds+" seconds");
				    else System.out.print(" in "+seconds+" seconds");
			    }
				System.out.println("");
			}
		}
		return result;
	}
	
	public static boolean chkBlackList(String text) {		
		String blackList = MySQLAccess.getBlackList();
		String[] parts = blackList.split("#");
		
		//System.out.println("blacklist...");
		
		for (int i = 0;i < parts.length;i++) {
			if (text.toLowerCase().contains(parts[i].toLowerCase())) return true;		
		}				
		return false;
	}
	
	public static int num_of_keyWords(String text) {
		int count = 0;
		
		String whiteList = MySQLAccess.getWhiteList();
		String[] parts = whiteList.split("#");
		
		//System.out.println("processando keywords...");		
		for (int i = 0;i < parts.length;i++) {
			
			if (text.toLowerCase().contains(parts[i].toLowerCase())) { count++; if (debug) System.out.println(count+" "+parts[i].toLowerCase()+" ");}		
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
		    	//if (i > 0) System.out.print("retrying..."+i);
		    	if (i > 0) System.out.print("!");
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
