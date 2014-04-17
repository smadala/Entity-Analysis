package com.ire.people;

import com.restfb.types.Page;

public class Launcher {
    public static final String freebaseAPIKey = "AIzaSyCa1FQB7BXbXwFCcI5Tn-VJITWb43910yU";
    FreeBaseSerivce freeBaseSerivce;
    TwitterService twitterService;
    FacebookService facebookService;
    RestFacebookService rs;
    private static Launcher launcher;

    private Launcher() {
        startUp();
        freeBaseSerivce = new FreeBaseSerivce(freebaseAPIKey);
        twitterService = new TwitterService();
        facebookService = new FacebookService();
        rs = new RestFacebookService();
    }

    private void startUp() {
        System.setProperty("https.proxyHost", "proxy.iiit.ac.in");
        System.setProperty("https.proxyPort", "8080");
    }

    public static Launcher getInstance() {
        if (launcher != null)
            return launcher;
        launcher = new Launcher();
        return launcher;
    }

    public PersonData getDetails(String id) throws Exception {

        //freeBaseSerivce.search("sachin tendulkar");
        ///m/0296q2  modi
        final Person person = freeBaseSerivce.topic(id);
         TwitterData twitterData =null;
        FacebookData facebookData = null;
        if (person.socialMedia.containsKey(Person.SocailMedia.TWITTER)) {
            twitterData = twitterService.showUser(person.socialMedia.get(Person.SocailMedia.TWITTER));

        } else {
            twitterData = twitterService.getTwitterData(person.alias);
        }
        /*Thread twitterThread=new Thread(new TwitterTask(person,twitterData,twitterService));
        Thread facebookThread=new Thread(new FacebookTask(person,facebookData,rs,facebookService));*/

        if (person.socialMedia.containsKey(Person.SocailMedia.FACEBOOK)) {
            Page page = rs.search(person.socialMedia.get(Person.SocailMedia.FACEBOOK));
            facebookData = facebookService.getFacebookData(page.getId());
            //facebookService.getPosts(page.getId());
        } else {
            facebookData = facebookService.getFacebookData(person.alias);
        }
        System.out.println(facebookData);
        /*twitterThread.start();
        facebookThread.start();
        twitterThread.join();
        facebookThread.join();*/
        PersonData personData=new PersonData(person,twitterData,facebookData);
        return personData;
            /*freeBaseSerivce.topic("/en/barack_obama");
			facebookService.search("sachin tendulkar");
			rs.search();*/
    }

    public static class TwitterTask implements Runnable{
        Person person;
        TwitterData twitterData;
        TwitterService twitterService;
        public TwitterTask(Person _person, TwitterData _twitterData,TwitterService twitterService1){
            person=_person;
            twitterData=_twitterData;
            twitterService=twitterService1;
        }
        @Override
        public void run() {
            if (person.socialMedia.containsKey(Person.SocailMedia.TWITTER)) {
                twitterData = twitterService.showUser(person.socialMedia.get(Person.SocailMedia.TWITTER));

            } else {
                twitterData = twitterService.getTwitterData(person.alias);
            }
        }
    }

    public static class FacebookTask implements Runnable{
        Person person;
        FacebookData facebookData;
        RestFacebookService rs;
        FacebookService facebookService;

        public FacebookTask(Person person, FacebookData facebookData, RestFacebookService rs, FacebookService facebookService) {
            this.person = person;
            this.facebookData = facebookData;
            this.rs = rs;
            this.facebookService = facebookService;
        }

        @Override
        public void run() {

            try {
                if (person.socialMedia.containsKey(Person.SocailMedia.FACEBOOK)) {
                    Page page = rs.search(person.socialMedia.get(Person.SocailMedia.FACEBOOK));
                    facebookData = facebookService.getFacebookData(page.getId());
                    //facebookService.getPosts(page.getId());
                } else {
                    facebookData = facebookService.getFacebookData(person.alias);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
