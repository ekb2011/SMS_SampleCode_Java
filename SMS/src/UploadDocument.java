import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UploadDocument{

	public static void main(String[] args) throws Exception {

	//URL
	final String url="https://api-sms.cloud.toast.com/sms/v2.1";
	final String appKeys="/appKeys/{appkey}";
	final String type="/requests/attachFiles/authDocuments";
		
	final String boundary="==="+System.currentTimeMillis()+"===";
	try {

		
		//creating Connection
		URL obj=new URL(url+appKeys+type);
		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary"+boundary);

		
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