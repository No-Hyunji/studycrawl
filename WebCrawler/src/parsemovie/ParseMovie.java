package parsemovie;

import java.io.BufferedInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParseMovie {
	String key = "f28c13d6acb62923dc7a7bafb2b228c2";
	String today = "";
	String[][] arrBoxOffice = new String[10][4]; 
	// 1. 파싱하고싶은  URL생성(일일박스오피스:하루전날)
	//	URL = 기본URL + key + 날짜
	public String makeURL() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		// System.out.println("포맷 전:"+ (cal.getTime()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		today = sdf.format(cal.getTime());
		// System.out.println("포맷 후:" +today);
		
		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
				+ "searchDailyBoxOfficeList.json"
				+ "?key=" + key
				+ "&targetDt=" + today;
		
		return url;
	}
	private String readUrl(String preUrl) throws Exception{
		BufferedInputStream reader = null;
		
		try {
			URL url = new URL(preUrl);
			reader = new BufferedInputStream(url.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while((i = reader.read(b)) != -1) {
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if(reader != null) {
				reader.close();
			}
			
		}
	}
	
	public String[][] getBoxOffice(String url) throws Exception{
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(readUrl(url));
		JSONObject json = (JSONObject) obj.get("boxOfficeResult");
		// array는 1~10위 까지의 BoxOffice List 담김
		JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");
		
		
		
		for(int i = 0 ; i < array.size(); i++) {
			JSONObject entity = (JSONObject)array.get(i);
			String rank = (String) entity.get("rank");			//순위
			String movieNm = (String) entity.get("movieNm");	//영화제목
			String audiAcc = (String) entity.get("audiAcc");	//누적관객수
			String salesAcc = (String) entity.get("salesAcc");	//누적매출액
			arrBoxOffice[i][0] = rank;
			arrBoxOffice[i][1] = movieNm;
			arrBoxOffice[i][2] = audiAcc;
			arrBoxOffice[i][3] = salesAcc;
			
		}
		return arrBoxOffice;
	}
	
	
	
}

