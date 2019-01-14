import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RegisterNumberByDocument{

	public static void main(String[] args) throws Exception {

	//URL
	final String url="https://api-sms.cloud.toast.com/sms/v2.1";
	final String appKeys="/appKeys/{appkey}";
	final String type="/requests/sendNos";
	
	//creating requestBody
	JSONObject requestBody=new JSONObject();

	//creating sendNos
	JSONArray sendNos=new JSONArray();
	sendNos.add("01041002071");
	
	//creating fileIds
	JSONArray fileIds=new JSONArray();
	fileIds.add("Integer of fileId");
	
	requestBody.put("sendNos", sendNos);
	requestBody.put("fileIds", fileIds);
	requestBody.put("comment", "test Register");
		
	try {

		//creating Connection
		URL obj=new URL(url+appKeys+type);
		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "multipart/form-data;charset=UTF-8");

		//adding requestBody
		OutputStreamWriter wr=new OutputStreamWriter(con.getOutputStream(), "UTF-8");
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