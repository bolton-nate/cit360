/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http360;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author natebolton
 */
public class Http360 {

    private final String USER_AGENT = "Mozilla/5.0";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Http360 http = new Http360();

        System.out.println("Testing 1 - Send Http GET request");
        try {
            http.sendGet();
        } catch (Exception ex) {
            Logger.getLogger(Http360.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\nTesting 2 - Send Http POST request");
        try {
            http.sendPost();
        } catch (Exception ex) {
            Logger.getLogger(Http360.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	// HTTP GET request
	private void sendGet() throws Exception {

            String url = "http://www.google.com/search?q=ekacademy.org";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            StringBuffer response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            //print result
            System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception {

            String url = "http://posttestserver.com/post.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            // Send post request
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            StringBuffer response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            //print result
            System.out.println(response.toString());

	}
    
}
