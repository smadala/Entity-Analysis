package com.ire.people;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Person {

    String name;
    Description desc;
    List<String> images;
    Map<SocailMedia, String> socialMedia;
    String placeOfBirth;
    String dateOfBirth;
    List<String> alias;

    public static enum SocailMedia {
        TWITTER, FACEBOOK, GOOGLE_PLUS
    }

    List<String> awards;
    String profession;
    String parents;

    public Person(Object properties) {
        JSONObject jsonProp = (JSONObject) properties;
        name = getFirstvalue(jsonProp.get("/type/object/name"));
        desc = new Description(jsonProp.get("/common/topic/description"));
        placeOfBirth = getFirstvalue(jsonProp.get("/people/person/place_of_birth"));
        dateOfBirth = getFirstvalue(jsonProp.get("/people/person/date_of_birth"));
        awards = getAwards(jsonProp.get("/award/award_winner/awards_won"));
        profession = getFirstvalue(jsonProp.get("/people/person/profession"));
        setSocialPresence(jsonProp.get("/common/topic/social_media_presence"));
        setAlias(jsonProp.get("/common/topic/alias"));
        getImages(jsonProp.get("/common/topic/image"));
    }
    private void getImages(Object images){
        this.images=new ArrayList<String>();
        JSONObject imagesJSON;
        if(images==null)
            return;
        imagesJSON = (JSONObject) images;

        JSONArray values=(JSONArray)imagesJSON.get("values");
        for(Object value:values){
            JSONObject valueJSON=(JSONObject)value;
            this.images.add("https://usercontent.googleapis.com/freebase/v1/image"+ (valueJSON.get("id").toString()));
        }
    }

    /*
     * "/people/person/place_of_birth" : { "count" : 1.0,
          "values" : [ { "creator" : "/user/kamalrajp",
                "id" : "/m/04vmp",
                "lang" : "en",
                "text" : "Mumbai",
                "timestamp" : "2007-08-23T06:52:54Z"
              } ],
          "valuetype" : "object"
        },
     */
    private String getFirstvalue(Object object) {
        String firstValue = null;
        if(object == null)
            return null;
        JSONObject objectJSON = (JSONObject) object;
        JSONArray values = (JSONArray) objectJSON.get("values");
        if (values.size() > 0) {
            JSONObject value = (JSONObject) values.get(0);
            firstValue = value.get("text").toString();
        }
        return firstValue;
    }

    /*

     "/award/award_winner/awards_won" : { "count" : 21.0,
          "values" : [ { "creator" : "/user/krsalis",
                "id" : "/m/0dg9qrw",
                "lang" : "en",
                "property" : { "/award/award_honor/award" : { "count" : 1.0,
                        "values" : [ { "creator" : "/user/krsalis",
                              "id" : "/m/024030",
                              "lang" : "en",
                              "text" : "Padma Vibhushan",
                              "timestamp" : "2010-10-06T18:35:50.003Z"
                            } ],
                        "valuetype" : "object"
                      },
                    "/award/award_honor/notes_description" : { "count" : 1.0,
                        "values" : [ { "creator" : "/user/krsalis",
                              "lang" : "en",
                              "text" : "Sports",
                              "timestamp" : "2010-10-06T18:35:54Z",
                              "value" : "Sports"
                            } ],
                        "valuetype" : "string"
                      },
                    "/award/award_honor/year" : { "count" : 1.0,
                        "values" : [ { "creator" : "/user/krsalis",
                              "lang" : "",
                              "text" : "2008",
                              "timestamp" : "2010-10-06T18:35:53.001Z",
                              "value" : "2008"
                            } ],
                        "valuetype" : "datetime"
                      },
                    "/type/object/attribution" : { "count" : 1.0,
                        "values" : [ { "creator" : "/user/krsalis",
                              "id" : "/m/02ws9p2",
                              "lang" : "en",
                              "text" : "krsalis",
                              "timestamp" : "2010-10-06T18:35:50.003Z"
                            } ],
                        "valuetype" : "object"
                      },
                    "/type/object/type" : { "count" : 1.0,
                        "values" : [ { "creator" : "/user/krsalis",
                              "id" : "/award/award_honor",
                              "lang" : "en",
                              "text" : "Award Honor",
                              "timestamp" : "2010-10-06T18:35:50.003Z"
                            } ],
                        "valuetype" : "object"
                      }
                  },
                "text" : "2008 - Sports - Padma Vibhushan - krsalis - Award Honor",
                "timestamp" : "2010-10-06T18:35:57Z"
              }
     */
    private List<String> getAwards(Object awards) {
        List<String> awardDescs = new ArrayList<String>();
        if (awards == null)
            return awardDescs;
        JSONObject awardsJSON = (JSONObject) awards;
        JSONArray awardsList = (JSONArray) awardsJSON.get("values");

        Object props;
        JSONObject propsJSON, awardJSON;
        String awardName, awardYear;
        for (Object award : awardsList) {
            awardJSON = (JSONObject) award;
            propsJSON = (JSONObject) awardJSON.get("property");
            awardName = getFirstvalue(propsJSON.get("/award/award_honor/award"));
            awardYear = getFirstvalue(propsJSON.get("/award/award_honor/year"));
            awardDescs.add(awardName + '(' + awardYear + ')');
        }
        return awardDescs;
    }

    /*
      https://twitter.com/narendramodi
      https://www.facebook.com/narendramodi
     */
    private static final String twitterAddr = "twitter.com/";
    private static final String facebookAdds = "facebook.com/";

    private void setSocialPresence(Object social) {
        socialMedia = new HashMap<SocailMedia, String>();
        if (social == null)
            return;

        JSONObject socialJSON = (JSONObject) social;
        JSONArray values = (JSONArray) socialJSON.get("values");
        for (Object value : values) {
            String media = ((JSONObject) value).get("value").toString();
            if (media.contains(twitterAddr)) {
                int begin = media.indexOf(twitterAddr);
                //		socialMedia.put(SocailMedia.TWITTER, media.substring(begin+twitterAddr.length()));
            } else if (media.contains(facebookAdds)) {
                int begin = media.indexOf(facebookAdds);
                socialMedia.put(SocailMedia.FACEBOOK, media.substring(begin + facebookAdds.length()));
            }
        }
    }

    private void setAlias(Object aliasObj) {
        alias = new ArrayList<String>();
        alias.add(name);
        if(aliasObj == null)
            return;
        JSONObject aliasJSON = (JSONObject) aliasObj;
        JSONArray values = (JSONArray) aliasJSON.get("values");
        for (Object object : values) {
            JSONObject value = (JSONObject) object;
            alias.add(value.get("value").toString());
        }
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", desc=" + desc + ", images=" + images
                + ", socialMedia=" + socialMedia + ", placeOfBirth="
                + placeOfBirth + ", dateOfBirth=" + dateOfBirth + ", alias="
                + alias + ", awards=" + awards + ", profession=" + profession
                + ", parents=" + parents + "]";
    }

    public String getName() {
        return name;
    }

    public Description getDesc() {
        return desc;
    }

    public String getImages() {
        StringBuilder imagesDiv=new StringBuilder();
       // imagesDiv.append("<div id='images' >");
        for(String image:images){
            imagesDiv.append("<image src='"+image+"' >");
        }
     //   imagesDiv.append('<').append('\\').append("div><br>");
        return imagesDiv.toString();
    }

    public Map<SocailMedia, String> getSocialMedia() {
        return socialMedia;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public List<String> getAlias() {
        return alias;
    }

    public String getAwards() {

        return awards.toString().replace('[',' ').replace(']',' ');
    }

    public String getProfession() {
        return profession;
    }

    public String getParents() {
        return parents;
    }

    public static String getTwitterAddr() {
        return twitterAddr;
    }

    public static String getFacebookAdds() {
        return facebookAdds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(Description desc) {
        this.desc = desc;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setSocialMedia(Map<SocailMedia, String> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }
}
