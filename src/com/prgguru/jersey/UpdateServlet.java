package com.prgguru.jersey;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		System.out.println("hello");
		
		String updatelink = (String) request.getParameter("updatelink");
		String indexoflink = (String)request.getParameter("indexoflink");
		String version = 	(String)request.getParameter("version");
		String success = "Not Successfully Updated";
		try {
			success  = DBConnection.updateUpdateMyApp(updatelink,indexoflink,version);
			if(success.equals("Successfully Updated")){
				sendGcmToTopic();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append(success).append(request.getContextPath());
		
	}
	
	public static void sendGcmToTopic() throws JSONException {
        
        try {
            // Prepare JSON containing the GCM message content. What to send and where to send.
            JSONObject jGcmData = new JSONObject();
            String urlBollyWood = "NA";
            urlBollyWood = "https://7db034b7a5c1141914d840db300fd46be9227dd3.googledrive.com/host/0B_KqRgJQ-c2MM3pMb3kyRXNZeGs/updatelink.png";
            
            JSONObject jData = new JSONObject();
            jData.put("title", "New Update Available Now");
            jData.put("message", "MusicIndia Update Available Now");
            jData.put("urlBollyWood", urlBollyWood);
            jData.put("tikerText", "Click on Updates to Download Latest Update of the app");
            // Where to send GCM message.
            jGcmData.put("to", "/topics/music_india");
            
            // What to send in GCM message.
            jGcmData.put("data", jData);
            String API_KEY = "AIzaSyB-7pw9ncZRnE-xIgr5vct_4bWp-IM6oMU";
            // Create connection to send GCM Message request.
            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
           // String resp = IOUtils.toString(inputStream);
            //System.out.println(resp);
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
        } catch (IOException e) {
            System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
        }
    }

}
