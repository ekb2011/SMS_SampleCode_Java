import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CancelReservation {
	
	public static void main(String[] args) throws Exception {
	
		//URL
		final String url="https://api-sms.cloud.toast.com/sms/v2.1";
		final String appKeys="/appKeys/{appkey}";
		final String type="/reservations/cancel";

		//creating requestBody
		JSONObject requestBody=new JSONObject();
		requestBody.put("updateUser", "");
		JSONArray reservationList=new JSONArray();
		
		//creating reservationObject - info of reserved SMS
		JSONObject reservationObject=new JSONObject();
		reservationObject.put("requestId", "{requestId}");
		reservationObject.put("recipientSeq", 1);
		
		reservationList.add(reservationObject);
		requestBody.put("reservationList", reservationList);
		
		try {

			//creating Connection
			URL obj=new URL(url+appKeys+type);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			
			//adding resquestBody
			OutputStreamWriter wr=new OutputStreamWriter(con.getOutputStream());
			wr.write(requestBody.toString());
			wr.close();
			
			//getting response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
