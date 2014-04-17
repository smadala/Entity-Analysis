package com.ire.people;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;


public class TwitterData {

    User user;
    ResponseList<Status> tweets;

    @Override
    public String toString() {
        return "TwitterData [user=" + user + "]";
    }

    public String getScreenName(){
        return "https://twitter.com/"+user.getScreenName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
