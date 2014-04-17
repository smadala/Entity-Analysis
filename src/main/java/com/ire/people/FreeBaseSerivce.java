package com.ire.people;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class FreeBaseSerivce {
    private String apiKey;
    private int searchResultsLimit;
    private String searchFilter; //(any type:/people/person)


    public FreeBaseSerivce(String apiKey) {
        this.apiKey = apiKey;
        this.searchResultsLimit = 10;
        this.searchFilter = "(any type:/people/person)";
    }


    public void search(String keyWord) throws IOException, ParseException {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        JSONParser parser = new JSONParser();
        GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
        url.put("query", keyWord);
        url.put("filter", searchFilter);
        url.put("limit", "" + searchResultsLimit);
        url.put("indent", "true");
        url.put("key", apiKey);
        HttpRequest request = requestFactory.buildGetRequest(url);
        HttpResponse httpResponse = request.execute();
        JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
        JSONArray results = (JSONArray) response.get("result");
        for (Object result : results) {
            System.out.println(result);
        }
    }

    public Person topic(String topicId) throws IOException, ParseException {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        JSONParser parser = new JSONParser();
        //String topicId = "/en/bob_dylan";
        GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/topic" + topicId);
        url.put("key", apiKey);
        // url.put("filter", "/people/person");
        HttpRequest request = requestFactory.buildGetRequest(url);
        HttpResponse httpResponse = request.execute();
        JSONObject topic = (JSONObject) parser.parse(httpResponse.parseAsString());
        //System.out.println(JsonPath.read(topic,"$.property['/type/object/name'].values[0].value").toString());
        //     Map<String,Object> values=topic.values();
        topic = (JSONObject) topic.get("property");
        Person person = new Person(topic);
        System.out.println(person);
        //parseJson(topic);
        return person;
    }

    public static void getArray(Object object2) throws ParseException {

        JSONArray jsonArr = (JSONArray) object2;

        for (int k = 0; k < jsonArr.size(); k++) {

            if (jsonArr.get(k) instanceof JSONObject) {
                parseJson((JSONObject) jsonArr.get(k));
            } else {

                //System.out.println(jsonArr.get(k));
            }

        }
    }

    public static void parseJson(JSONObject jsonObject) throws ParseException {

        Set<Object> set = jsonObject.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if (jsonObject.get(obj) instanceof JSONArray) {
                //System.out.println(obj.toString());
                getArray(jsonObject.get(obj));
            } else {
                if (jsonObject.get(obj) instanceof JSONObject) {
                    System.out.println(obj.toString());
                    parseJson((JSONObject) jsonObject.get(obj));
                } else {
                   /* System.out.println(obj.toString() + "\t"
	                        + jsonObject.get(obj));*/
                }
            }
        }
    }
}
