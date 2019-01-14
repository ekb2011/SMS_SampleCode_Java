import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SendAuthPOST{
	
	//6-digit Number Creation
	private int certNumLength = 6;
    public String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        int range = (int)Math.pow(10,certNumLength);
        int trim = (int)Math.pow(10, certNumLength-1);
        int result = random.nextInt(range)+trim;
        if(result>range){
            result = result - trim;
        }   
        return String.valueOf(result);
    }
    public int getCertNumLength() {
        return certNumLength;
    }
    public void setCertNumLength(int certNumLength) {
        this.certNumLength = certNumLength;
    }
    
	public static void main(String[] args) throws Exception {

	//URL
	final String url="https://api-sms.cloud.toast.com/sms/v2.1";
	final String appKeys="/appKeys/{appkey}";
	final String type="/sender/auth/sms";
	
	//set 6-digit Number
	SendAuthPOST ge =new SendAuthPOST();
	ge.setCertNumLength(6);
	
	//requestBody Creation
	JSONObject requestBody=new JSONObject();
	requestBody.put("title", "MMS test");
	requestBody.put("body", "Authorization Code is: ["+ge.excuteGenerate()+"]");
	requestBody.put("sendNo", "");	
	requestBody.put("senderGroupingKey", "SenderGroupingKey");
	
	//recipientArray Creation
	JSONArray recipientArray=new JSONArray();
	JSONObject recipientList=new JSONObject();
	recipientList.put("recipientNo", "");
	recipientList.put("recipientGroupingKey", "RecipientGroupingKey");
	recipientArray.add(recipientList);
	
	requestBody.put("recipientList", recipientArray);
	
	try {
		
		//creating Connection
		URL obj=new URL(url+appKeys+type);
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