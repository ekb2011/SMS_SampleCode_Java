import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SendSMSTagPOST{

	public static void main(String[] args) throws Exception {

	//URL
	final String url="https://api-sms.cloud.toast.com/sms/v2.1";
	final String appKeys="/appKeys/{appkey}";	
	final String type="/tag-sender/sms";

	//requestBody Creation
	JSONObject requestBody=new JSONObject();
	requestBody.put("body", "태그 테스트");
	requestBody.put("sendNo", "");	
	requestBody.put("senderGroupingKey", "SenderGroupingKey");

	//recipientArray Creation
	JSONArray recipientArray=new JSONArray();
	JSONArray tagArray=new JSONArray();
	tagArray.add("aKYyaCa0");		
	JSONObject recipientList=new JSONObject();
	recipientList.put("recipientNo", "");
	recipientList.put("recipientGroupingKey", "RecipientGroupingKey");
	recipientArray.add(recipientList);
	requestBody.put("recipientList", recipientArray);
	
	requestBody.put("tagExpression", tagArray);
	
	try {

		//creating Connection
		URL obj=new URL(url+appKeys+type);
		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		
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