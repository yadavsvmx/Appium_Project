package FSAID.FSAAID.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

import FSAID.FSAAID.initiator.Initiator;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;

public class ApiServices 
{
	Initiator init = null;
	private final static String USER_AGENT = "Mozilla/5.0";
	public static String sAccessToken = null;
	public static String sWORecordID = null;
	public static String sWorkOrderName = null;
	public ApiServices appServices = null;
	
	public void getAccessToken() throws IOException
	{
		 init = new Initiator();
		URL url = new URL("https://pqt--verifayap1.cs95.my.salesforce.com/services/oauth2/token?");
        HttpsURLConnection httpsUrlCon = (HttpsURLConnection) url.openConnection();
		httpsUrlCon.setRequestMethod("POST");
		httpsUrlCon.setRequestProperty("User-Agent", USER_AGENT);
		httpsUrlCon.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		String urlParameters = "grant_type=password"
				+ "&client_id="+"3MVG9Yb5IgqnkB4q9NigcSeGWemKzpRIALbcmn.XW5YL5O4cKHLGso73pffctTiVPAprdzwtXgHQSJVZjebCb"
				+ "&client_secret="+"6375454291261602889"
				+ "&username="+init.adminUn
				+ "&password="+init.adminPwd;
		httpsUrlCon.setDoOutput(true);
		
		DataOutputStream dataOpStream = new DataOutputStream(httpsUrlCon.getOutputStream());
		dataOpStream.writeBytes(urlParameters);
		dataOpStream.flush();
		dataOpStream.close();
		int responseCode = httpsUrlCon.getResponseCode();
		BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
               bufferedReader = new BufferedReader(new InputStreamReader(httpsUrlCon.getInputStream(),  StandardCharsets.UTF_8));
               while ((line =bufferedReader.readLine())!=null){
                     //TOOL-1905 and TOOL-1906 not validating String(line) 
                     stringBuilder.append(line);
               }
        } catch (IOException e) {
               e.printStackTrace();
        } finally {
               if (bufferedReader != null) {
                     try {
                            bufferedReader.close();
                     } catch (IOException e) {
                            e.printStackTrace();
                     }
               }
        }
        JSONObject json = new JSONObject(stringBuilder.toString());
		sAccessToken = (String) json.get("access_token");
		System.out.println("AccessToken: "+sAccessToken);
		httpsUrlCon.disconnect();
		
	}
	

public  String getWOORecordID(String sWOJson) throws IOException
{
	//sWOJson = "{\"SVMXC__City__c\":\"Bangalore\"}";
	
	URL url = new URL("https://pqt--verifayap1.cs95.my.salesforce.com/services/data/v42.0/sobjects/SVMXC__Service_Order__c?"
			+ "Username="+init.adminUn
			+ "&Password="+init.adminPwd);
    HttpsURLConnection httpsUrlCon = (HttpsURLConnection) url.openConnection();
    httpsUrlCon.setDoOutput(true);
 	httpsUrlCon.setRequestMethod("POST");
	httpsUrlCon.setRequestProperty("Content-Type", "application/json");
	httpsUrlCon.setRequestProperty("Authorization", "OAuth "+sAccessToken);
	
	OutputStream os = httpsUrlCon.getOutputStream();
    os.write(sWOJson.getBytes());
    os.flush();
	
    BufferedReader bufferedReader = null;
    StringBuilder stringBuilder = new StringBuilder();
    String line;
    try {
           bufferedReader = new BufferedReader(new InputStreamReader(httpsUrlCon.getInputStream()));
           while ((line =bufferedReader.readLine())!=null){
                 stringBuilder.append(line);
           }
    } catch (IOException e) {
           e.printStackTrace();
    } finally {
           if (bufferedReader != null) {
                 try {
                        bufferedReader.close();
                 } catch (IOException e) {
                        e.printStackTrace();
                 }
           }
    }

    JSONObject json = new JSONObject(stringBuilder.toString());
    sWORecordID = (String) json.get("id");
    System.out.println("API Status: "+json);

    System.out.println("WORecordID: "+sWORecordID);
    return sWORecordID;
}

	
	public String  getWOName(String sWORecordID) throws IOException
	{
	String sURL = "https://pqt--verifayap1.cs95.my.salesforce.com/services/data/v42.0/query?q=SELECT+Name+from+SVMXC__Service_Order__c+WHERE+Id=\'"+sWORecordID+"\'";
	URL url = new URL(sURL);
	System.out.println(sURL);
	HttpsURLConnection httpsUrlCon = (HttpsURLConnection) url.openConnection();
	httpsUrlCon.setDoOutput(true);
	httpsUrlCon.setRequestMethod("GET");
	httpsUrlCon.setRequestProperty("Authorization", "OAuth "+sAccessToken);
	httpsUrlCon.setRequestProperty("Username",init.adminUn);
	httpsUrlCon.setRequestProperty("Password", init.adminPwd);
	
	
	
	BufferedReader bufferedReader = null;
	StringBuilder stringBuilder = new StringBuilder();
	String line;
	try {
	   bufferedReader = new BufferedReader(new InputStreamReader(httpsUrlCon.getInputStream(),StandardCharsets.UTF_8));
	   while ((line =bufferedReader.readLine())!=null){
	         stringBuilder.append(line);
	   }
	} catch (IOException e) {
	   e.printStackTrace();
	} finally {
	   if (bufferedReader != null) {
	         try {
	                bufferedReader.close();
	         } catch (IOException e) {
	                e.printStackTrace();
	         }
	   }
	}
	
	JSONObject json = new JSONObject(stringBuilder.toString());
	
	JSONArray msg = (JSONArray) json.get("records");
	Iterator iterator = msg.iterator();
	while (iterator.hasNext()) {
         JSONObject value = (JSONObject) iterator.next();
         System.out.println((String) value.get("Name"));
         
         sWorkOrderName=(String) value.get("Name");
     }
	
	return sWorkOrderName;
	}
  
	


}