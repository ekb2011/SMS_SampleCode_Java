import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ShowMessage {
	public static void main(String[] args) throws Exception {

		final String smsURL="https://api-sms.cloud.toast.com/sms/v2.1/appKeys";
		final String appKeys="/{appkey}/message-results";	
		final String query="?startUpdateDate=2018-12-20 00:00:00&endUpdateDate=2018-12-20 18:00:00";
		try {
			URL obj=new URL(smsURL+appKeys+query);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
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
