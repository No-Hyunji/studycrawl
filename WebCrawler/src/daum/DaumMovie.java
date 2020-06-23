package daum;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DaumMovie {
	public static void main(String[] args) throws IOException{
	
			String url = "https://movie.daum.net/moviedb/grade?movieId=134684&type=netizen&page=1";
			Document doc = Jsoup.connect(url).get();
			Elements replyList = doc.select("");
			System.out.println(replyList);
		
		
		
			
		
		
	}

}
