import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class App {
	static HashMap<String, Integer> mapHashtags = new HashMap<String, Integer>();
	
	static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
	
		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
		
		Collections.sort(sortedEntries, 
		    new Comparator<Entry<K,V>>() {
		        @Override
		        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
		            return e2.getValue().compareTo(e1.getValue());
		        }
		    }
		);
	
		return sortedEntries;
	}
	
	
	private static void _readTweet(String inputTweet) {
		String words[] = inputTweet.split("\\s+");
		for (String word : words){
			if(word.startsWith("#")) {
				word = word.substring(1);
				if(mapHashtags.containsKey(word)) {
					mapHashtags.put(word, mapHashtags.get(word) + 1);
				}
				else {
					mapHashtags.put(word, 1);
				}
			}
		}
	}
	
	private static void _showHashtags() {
		System.out.println("-----------------------------------------------------");
		System.out.println("Top trending hashtags in twitter");
		System.out.println("-----------------------------------------------------");
		List<Map.Entry<String, Integer>> hashtagsList = entriesSortedByValues(mapHashtags);
		int i, lastValue = 0;
		for (i = 0; i < hashtagsList.size() && i < 10; i++) {
			Map.Entry<String, Integer> hashtag = hashtagsList.get(i);
		    System.out.println(hashtag.getKey()+" - used "+hashtag.getValue()+" times");
		    lastValue = hashtag.getValue();
		}
		
		/*
		 * checking if top 11th, 12th... hashtags have same count as top 10th hashtag,
		 * if same: printing them 
		 * else : exit
		 * 
		 */
		
		while(true && i < hashtagsList.size()) {
			Map.Entry<String, Integer> hashtag = hashtagsList.get(i++);
			int currentValue = hashtag.getValue();
			if(lastValue == currentValue) {
				System.out.println(hashtag.getKey()+" - used "+hashtag.getValue()+" times");
			}
			else
				break;
		}
		
	}
	
	public static void main(String[] args) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;
		while(!exit) {
			int choice;
			System.out.println("Please enter your Choice from below:\n"
					+ "1.Enter a tweet\n"
					+ "2.Show top 10 Hashtags\n"
					+ "0.Exit");
			try{
				choice = Integer.parseInt(bufferedReader.readLine());
				switch(choice) {
					case 1:
						System.out.println("Enter Tweet");
						String input = bufferedReader.readLine();
						_readTweet(input);
						break;
					case 2:
						_showHashtags();
						break;
					case 0:
						exit = true;
						break;
				}
				System.out.println("\n");
			}
			catch(Exception e) {
				System.out.println("Oops !, an error occoured");
				e.printStackTrace();
			}
		}
		System.out.println("Thanks for using this app !");
	}
}
