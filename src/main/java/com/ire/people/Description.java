package com.ire.people;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Description {

    /*
     { "count" : 23.0,
          "values" : [ { "citation" : { "provider" : "Wikipedia",
                    "statement" : "Description licensed under the Creative Commons Attribution-ShareAlike License (http://en.wikipedia.org/wiki/Wikipedia:Text_of_Creative_Commons_Attribution-ShareAlike_3.0_Unported_License).",
                    "uri" : "http://en.wikipedia.org/wiki/Sachin_Tendulkar"
                  },
                "creator" : "/user/wikirecon_bot",
                "dataset" : "/m/0kj4zz_",
                "lang" : "en",
                "project" : "wikirecon",
                "text" : "Sachin Ramesh Tendulkar is a former Indian cricketer widely acknowledged as the greatest batsman...",
                "value" : "Sachin Ramesh Tendulkar is a former Indian cricketer widely acknowledged as the greatest batsman of the modern generation, popularly holds the title \"God of Cricket\" among his fans. Some commentators, such as former West Indian batsman Brian Lara, have labelled Tendulkar the greatest cricketer of all time. He took up cricket at the age of eleven, made his Test debut against Pakistan at the age of sixteen, and went on to represent Mumbai domestically and India internationally for close to twenty-four years. He is the only player to have scored one hundred international centuries, the first batsman to score a Double Century in a One Day International, and the only player to complete more than 30,000 runs in international cricket. In October 2013, he became the 16th player and first Indian to aggregate 50,000 runs in all recognised cricket.\nIn 2002, Wisden Cricketers' Almanack ranked him the second greatest Test batsman of all time, behind Don Bradman, and the second greatest ODI batsman of all time, behind Viv Richards. Later in his career, Tendulkar was a part of the Indian team that won the 2011 World Cup, his first win in six World Cup appearances for India. He had previously been named \"Player of the Tournament\" at the 2003 edition of the tournament, held in South Africa. In 2013, he was the only Indian cricketer included in an all-time Test World XI named to mark the 150th anniversary of Wisden Cricketers' Almanack."
              } ],
          "valuetype" : "string"
        }
     */
    private String desc;
    private String provider;
    private String providerURL;

    public Description(Object jsonDesc) {
        try {
            JSONObject descJsonObject = (JSONObject) jsonDesc;
            JSONArray values = (JSONArray) descJsonObject.get("values");
            JSONObject firstValue = (JSONObject) values.get(0);
            JSONObject citation = (JSONObject) firstValue.get("citation");
            desc = firstValue.get("value").toString();
            if(citation!=null) {
                provider = citation.get("provider").toString();
                providerURL = citation.get("uri").toString();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Description [desc=" + desc + ", provider=" + provider
                + ", providerURL=" + providerURL + "]";
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderURL() {
        return providerURL;
    }

    public void setProviderURL(String providerURL) {
        this.providerURL = providerURL;
    }
}
