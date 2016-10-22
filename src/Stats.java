
public class Stats {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int newsId = 0;
		int dbSize = MySQLAccess.getTotalNews();
		String text = "";
		String moda = "";		
		
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
		
		for (int i=0;i < words.length ; i++) {
			words[i] = words[i].toLowerCase().replace("'", "`");
			if (MySQLAccess.isInIgnoredsTable(words[i]) == 0) {	
				if (MySQLAccess.isInModaTable(words[i]) > 0) {
					MySQLAccess.incrementWordCounter(words[i]);
				} else {
					query = "INSERT INTO moda (word,counter) VALUES ('"+words[i]+"',1)";
					System.out.println(query);
					MySQLAccess.executeUpdate(query);
				}
			} else {
				System.out.println("ignoring "+words[i]);
			}
		}		
		return result;
	}
	
}
