package com.ire.people;

import java.util.List;

import javax.print.attribute.ResolutionSyntax;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Offer;
import facebook4j.Page;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;


public class FacebookService {
    private Facebook facebook;
    private static String appId = "1489219614633500";
    private static final String appSecret = "81b4465e15fcb9784c11ce801f8af6b6";
    private static final String commaSeparetedPermissions = "email,publish_stream,user_birthday";
    private static final String accessToken = "1489219614633500|2ivH_-4k5fcewSKy8jCVA-N6PdY";

    public FacebookService() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId("1489219614633500")
                .setOAuthAppSecret("81b4465e15fcb9784c11ce801f8af6b6")
                .setOAuthAccessToken("8244d2c593252c898dfeb717a703357f")
                .setOAuthPermissions("");
        FacebookFactory ff = new FacebookFactory();
        facebook = ff.getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthPermissions(commaSeparetedPermissions);
        facebook.setOAuthAccessToken(new AccessToken(accessToken, null));

    }

    public FacebookData search(String keyword) throws Exception {
        Page page = null;
        try {

            List<Page> pages = facebook.searchPages(keyword, new Reading().limit(10));
            for (Page p : pages) {
                System.out.println(p.getLink());
            }

        } catch (FacebookException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getFacebookData(page);
    }

    @SuppressWarnings("finally")
    public Page getUser(String keyword) throws Exception {
        ResponseList<Page> pages = null;
        Page bestPage = null;
        try {

            pages = facebook.searchPages(keyword, new Reading().limit(10));
            int maxLikes = 0;

            Page detailedPage = null;
            //	pages = facebook.searchPages(bestPage.getId(), new Reading().limit(10));
            for (Page page : pages) {
                detailedPage = facebook.getPage(page.getId());
                if (maxLikes < detailedPage.getLikes()) {
                    bestPage = detailedPage;
                    maxLikes = detailedPage.getLikes();
                }
				/*if (detailedPage.isPublished()!= null && detailedPage.isPublished())
					return detailedPage;*/
                //	if (maxFollowers < user.getFollowersCount())
                //	bestUser = user;
                //System.out.println(detailedPage.getId()+" "+detailedPage.getName() + " "+ detailedPage.getLikes() +"  "+detailedPage.isPublished());
                System.out.println(detailedPage);

            }
        } catch (FacebookException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return bestPage;
        }
    }

    public FacebookData getFacebookData(List<String> names) throws Exception {
        Page bestPage = getUser(names.get(0)), auxPage;
        for (int i = 1; i < names.size() && i < 4; i++) {
            auxPage = getUser(names.get(i));
            if(auxPage ==null || bestPage == null)
                continue;
            if (bestPage.getLikes() < auxPage.getLikes()) {
                bestPage = auxPage;
            }
        }

        return getFacebookData(bestPage);
    }

    private FacebookData getFacebookData(Page page) {
        FacebookData facebookData = new FacebookData();
        facebookData.page = page;
        return facebookData;
    }

    public FacebookData getFacebookData(String pageId) throws FacebookException {
        Page page=facebook.getPage(pageId);
        return getFacebookData(page);
    }

    public void getPosts(String pageId) throws FacebookException {
        ResponseList<Post> posts = facebook.getFeed(pageId, new Reading().limit(3));
        for (Post post : posts) {
            System.out.println(post.getMessage() + "  image: " + post.getPicture() + " " + post.getMetadata());
        }
    }
}
