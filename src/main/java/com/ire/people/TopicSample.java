package com.ire.people;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.FileInputStream;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TopicSample {
    public static Properties properties = new Properties();

    public static void main(String[] args) {
        try {
            //System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
            //System.setProperty("http.proxyPort", "8080");
        /* System.setProperty("https.proxyHost", "proxy.iiit.ac.in");
         System.setProperty("https.proxyPort", "8080");*/
            properties.load(new FileInputStream("freebase.properties"));
            HttpTransport httpTransport = new NetHttpTransport();
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
            JSONParser parser = new JSONParser();
            String topicId = "/en/bob_dylan";
            GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/topic" + topicId);
            url.put("key", "AIzaSyCa1FQB7BXbXwFCcI5Tn-VJITWb43910yU");
            HttpRequest request = requestFactory.buildGetRequest(url);
            HttpResponse httpResponse = request.execute();
            JSONObject topic = (JSONObject) parser.parse(httpResponse.parseAsString());
            System.out.println(topic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}