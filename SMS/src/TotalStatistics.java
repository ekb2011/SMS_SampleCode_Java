import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TotalStatistics {
	
	public static void main(String[] args) throws Exception {

		//URL
		final String url="https://api-sms.cloud.toast.com/sms/v2.1";
		final String appKeys="/appKeys/{appkey}";
		final String type="/statistics/view";
		final String query="?searchType=DATE";
		final String from="2018-12-20 10:00";
		final String to="2018-12-20 12:00";
		final String encoded_from=URLEncoder.encode(from, "UTF-8");
		final String encoded_to=URLEncoder.encode(to,  "UTF-8");
		
		try {
			
			//creating Connection
			URL obj=new URL(url+appKeys+type+query+"&from="+encoded_from+"&to="+encoded_to);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			
			//getting response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
		
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//print result
			System.out.println(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
