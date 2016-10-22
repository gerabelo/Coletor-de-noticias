
public class ModaCleaner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ignoreds = MySQLAccess.getIgnoreds();
		String[] words = ignoreds.split("#");
		
		for (int i=0;i < words.length ; i++) {
			MySQLAccess.executeUpdate("DELETE FROM moda WHERE word='"+words[i]+"'");
		}

	}

}
