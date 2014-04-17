package com.ire.people;

/**
 * Created by satya on 4/6/14.
 */
public class PersonData {
    private Person person;
    TwitterData twitterData;
    FacebookData facebookData;

    public PersonData(Person person, TwitterData twitterData, FacebookData facebookData) {
        this.person = person;
        this.twitterData = twitterData;
        this.facebookData = facebookData;
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "person=" + person +
                ", twitterData=" + twitterData +
                ", facebookData=" + facebookData +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TwitterData getTwitterData() {
        return twitterData;
    }

    public void setTwitterData(TwitterData twitterData) {
        this.twitterData = twitterData;
    }

    public FacebookData getFacebookData() {
        return facebookData;
    }

    public void setFacebookData(FacebookData facebookData) {
        this.facebookData = facebookData;
    }
}
