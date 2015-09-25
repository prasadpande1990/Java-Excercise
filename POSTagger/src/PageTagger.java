import java.io.IOException;
import java.util.Scanner;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageTagger {

	private MaxentTagger postagger;
	/* 
	 PageTagger()- Constructor loads the tagger model stored in the english-left3words-distsim.tagger file
	 Note: Make sure to inclure the english-left3words-distsim.tagger.props at the same path along with model file
	 */
	PageTagger() {
		postagger = new MaxentTagger("StanfordTagger\\english-left3words-distsim.tagger");
	}
	
	/*
	 	String tagText(String text) - Accepts the HTML parsed string and using MaxEnt tagString method, tag the words and returns tagged string.
	 	Input: String
	 	Return type: String
	 */
	public String tagText(String text) {
		String taggedString = postagger.tagString(text.toString());
		return taggedString;
	}
	
	/*
	 	String getString(String url) - Accepts the web page URL and returns the String of HTML body contents with all 
	 										HTML tags removed using Jsoup HTML parser  
	 	Input: String (Web page URL that needs to be parsed)
	 	Returns: String 
	 */
	public String getString (String url) {
		Document doc = null;
		try {
			Connection con = Jsoup.connect(url).maxBodySize(0);
			doc = con.get();
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
		return doc.body().text();
	}
	
	/*
	 	main method - On calling, main method ask for URL of the web page whose contents need 
	 					to be tagged and prints the tagged string on the console.
	 */
	public static void main(String[] args) throws IOException {
		PageTagger tag = new PageTagger();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the url");
		String url = scan.next();
		if (url != null) {
			String text = tag.tagText(tag.getString(url));
			System.out.println(text);
		}
	}
}