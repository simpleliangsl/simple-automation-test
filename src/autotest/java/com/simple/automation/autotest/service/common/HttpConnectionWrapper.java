package com.simple.automation.autotest.service.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by sliang on 9/17/17.
 */
public class HttpConnectionWrapper {

    public String sendGetRequest(String url, Map<String, String> headers) throws Exception {
        try {
            return sendRequest("GET", url, headers, null);
        } catch (Exception e){
            // TODO: log the errors
            return null;
        }
    }

    public String sendPostRequest(String url, Map<String, String> headers, String body) throws  Exception {
        try {
            return sendRequest("POST", url, headers, body);
        } catch (Exception e) {
            // TODO: log the errors
            return null;
        }
    }

    // General http method
    private String sendRequest(String method, String url, Map<String, String> headers, String body) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        // http method field
        con.setRequestMethod(method);

        // http header field
        if(headers != null) {
            for(String key: headers.keySet()){
                con.setRequestProperty(key, headers.get(key));
            }
        }

        // http body field for post method
        if(method.equals("POST")){
            con.setDoOutput(true);

            if(body != null) {
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(body);
                wr.flush();
                wr.close();
            }
        }

        // failed
        if(con.getResponseCode() != 200) return null;

        // success
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
