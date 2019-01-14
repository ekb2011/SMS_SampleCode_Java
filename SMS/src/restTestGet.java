	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;
	import java.net.HttpURLConnection;
	import java.net.URL;

	import org.json.simple.JSONArray;
	import org.json.simple.JSONObject;

public class restTestGet {


		public static void main(String[] args) throws Exception {
			final String smsURL="https://api-sms.cloud.toast.com/sms/v2.1/appKeys";
			final String appKeys="/{appkey}/sender/sms/";
			final String requestId="{requestId}";
			URL obj=new URL(smsURL+appKeys+requestId);
			HttpURLConnection con=(HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			
			int resCode=con.getResponseCode();

			System.out.println(resCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			System.out.println(response.toString());

	}
}
