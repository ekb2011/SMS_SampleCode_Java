import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

public class AttachmentUpload{

	public static void main(String[] args) throws Exception {

	//URL
	final String smsURL="https://api-sms.cloud.toast.com/sms/v2.1";
	final String appKeys="/appKeys/{appkey}";	
	final String type="/attachfile/binaryUpload";
	
	//requestBody Creation
	JSONObject requestBody=new JSONObject();
	requestBody.put("fileName", "testFile.jpg");
	requestBody.put("createUser", "");	
	requestBody.put("fileBody", "");
	
	try {

		//creating Connection
		URL obj=new URL(smsURL+appKeys+type);
		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		
		//adding requestBody
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