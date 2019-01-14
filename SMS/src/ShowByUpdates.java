import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ShowByUpdates {

	public static void main(String[] args) throws Exception {

		//URL
		final String url="https://api-sms.cloud.toast.com/sms/v2.1";
		final String appKeys="/appKeys/{appkey}";
		final String type="/message-results";
		final String startUpdateDate="2018-12-20";
		final String endUpdateDate="2018-12-20";
		final String startTime="00:00:00";
		final String endTime="10:40:00";
		final String encoded_startdate=URLEncoder.encode(startUpdateDate+' '+startTime, "UTF-8");
		final String encoded_enddate=URLEncoder.encode(endUpdateDate+' '+endTime, "UTF-8");
		
		try {
			
			//creating Connection
			URL obj=new URL(url+appKeys+type+"?startUpdateDate="+encoded_startdate+"&endUpdateDate="+encoded_enddate);
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
