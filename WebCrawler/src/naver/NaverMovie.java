package naver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverMovie {
	public static void main(String[] args) throws IOException {
		
		//페이지네이션 돌면서 1페이지 부터 끝까지 영화 내용, 평점, 작성자, 작성일자 수집
		int page = 1;
		int cnt = 0;
		String prePage = "";
		while(true) {
			String url = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=189633&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page=" + page ;
			Document doc = Jsoup.connect(url).get();
			Elements replyList = doc.select("div.score_result li");
			String nowPage = doc.select("input#page").attr("value");
			System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
			System.out.println("페이지"+nowPage);
			
			if(nowPage.equals(prePage)) {
				break;
			} else {
				prePage = nowPage;
			}
			
			String content = "";
			String writer = "";
			int score = 0;
			String regDt = "";
			for (Element one : replyList) {
				content = one.select("div.score_reple > p > span").get(0).text();
				writer = one.select("div.score_reple a > span").get(0).text();
				score = Integer.parseInt(one.select("div.star_score > em").get(0).text());
				regDt = one.select("div.score_reple  em").get(1).text();
				System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
				System.out.println("내용 : "+content);
				System.out.println("평점 : "+score);
				System.out.println("작성자 : "+writer);
				System.out.println("작성일자 : "+regDt);
				
				cnt += 1;
				
				
				
			}
			page += 1;
		
		}
		
		System.out.println("총" +cnt+"건 수집했습니다.");
	}

}

