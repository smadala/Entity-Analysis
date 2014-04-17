package com.ire.people;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterService {
    /*
     * APIkey - Xi2QndLC6yQOOr9r1bYWaQ APISecret-
     * bUB9NlYct7PSB5jkMKdHQFD5xCtFuDOKJp7R68ObY
     *
     * Access token - 305032568-gvy5J67jVVKsb65KuNTbHJZjJlZbu8WarlS0Nw27
     *
     * Access token secret - TsdEG4V04q6rD17JllIvifUWur5RzNoDnQZGyUyhgH8lR
     */
    private static final String TWITTER_CONSUMER_KEY = "Xi2QndLC6yQOOr9r1bYWaQ";
    private static final String TWITTER_SECRET_KEY = "bUB9NlYct7PSB5jkMKdHQFD5xCtFuDOKJp7R68ObY";
    private static final String TWITTER_ACCESS_TOKEN = "305032568-gvy5J67jVVKsb65KuNTbHJZjJlZbu8WarlS0Nw27";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "TsdEG4V04q6rD17JllIvifUWur5RzNoDnQZGyUyhgH8lR";
    private Twitter twitter;

    public TwitterService() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public User search(String keyword) throws TwitterException {

        User user = getUser(keyword);

        System.out.println(user.getName() + " " + user.getFollowersCount() + " " + user.getScreenName());
        System.out.println(twitter.showUser(keyword));
        return user;

		/*
         * try { Query query = new Query("@satya_madala"); QueryResult result;
		 * do { result = twitter.search(query); List<Status> tweets =
		 * result.getTweets(); for (Status tweet : tweets) {
		 * System.out.println("@" + tweet.getUser().getScreenName() + " - " +
		 * tweet.getText()); } } while ((query = result.nextQuery()) != null);
		 * //System.exit(0); } catch (TwitterException te) {
		 * te.printStackTrace(); System.out.println("Failed to search tweets: "
		 * + te.getMessage()); System.exit(-1); }
		 */
    }

    @SuppressWarnings("finally")
    public User getUser(String keyword) {
        int page = 1;
        ResponseList<User> users = null;
        try {

            users = twitter.searchUsers(keyword, page++);
            for (; page < 3; page++) {
                ResponseList<User> auxUser = twitter.searchUsers(keyword, page);
                if (auxUser.isEmpty())
                    break;
                users.addAll(auxUser);
            }
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (users.isEmpty())
                return null;
            int maxFollowers = users.get(0).getFollowersCount();
            User bestUser = users.get(0);
            for (User user : users) {
                if (user.isVerified())
                    return user;
                if (maxFollowers < user.getFollowersCount())
                    bestUser = user;
            }
            return bestUser;
        }
    }

    public TwitterData showUser(String screenName) {
        User user = null;
        try {
            user = twitter.showUser(screenName);

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getTwitterData(user);
    }

    public TwitterData getTwitterData(List<String> names) {
        int i = 0;
        User bestUser = getUser(names.get(i++)), auxUser;
        System.out.println(bestUser);
        while(bestUser == null && i < names.size()){
            bestUser = getUser(names.get(i++));
        }
        if(bestUser == null){
            return null;
        }
        for (; i < names.size() && !bestUser.isVerified() && i < 5; i++) {
            auxUser = getUser(names.get(i));
            System.out.println(names.get(i));
            if (auxUser != null && bestUser.getFollowersCount() < auxUser.getFollowersCount()) {
                bestUser = auxUser;
            }
            System.out.println(bestUser);
        }
        return getTwitterData(bestUser);
    }

    public TwitterData getTwitterData(User user) {
        TwitterData twitterData = new TwitterData();
        twitterData.user = user;
        /*try {
            ResponseList<Status> tweets =twitter.getUserTimeline(user.getId());
            twitterData.tweets=tweets;

        } catch (TwitterException e) {
            e.printStackTrace();
        }*/
        return twitterData;
    }
}
