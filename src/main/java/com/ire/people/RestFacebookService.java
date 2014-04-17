package com.ire.people;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Insight;
import com.restfb.types.Page;


public class RestFacebookService {
    FacebookClient facebookClient;
    private static String appId = "1489219614633500";
    private static final String appSecret = "81b4465e15fcb9784c11ce801f8af6b6";
    private static final String commaSeparetedPermissions = "email,publish_stream,user_birthday";
    private static final String accessToken = "1489219614633500|2ivH_-4k5fcewSKy8jCVA-N6PdY";

    public RestFacebookService() {
        facebookClient = new DefaultFacebookClient(accessToken);
    }

    public Page search(String screenName) {
        /*Connection<Page> publicSearch =
		        facebookClient.fetchConnection("search", Page.class, Parameter.with("q", "sachin tendulkar"),
		          Parameter.with("type", "page"));
	
		for(Page page:publicSearch.getData()){
			System.out.println(page);
		}
		Connection<Insight> insights = facebookClient.fetchConnection(publicSearch.getData().get(0).getId()+"/insights", Insight.class);

		for (Insight insight : insights.getData())
		   System.out.println(insight);*/
		/*String query = "SELECT  name FROM user WHERE uid=220439 or uid=7901103";
	    List<JsonObject> queryResults = facebookClient.executeFqlQuery(query, JsonObject.class);

	    if (queryResults.size() > 0)
	      System.out.println(queryResults.get(0).getString("name"));*/
        Page page = facebookClient.fetchObject(screenName, Page.class);

        System.out.println("Page likes: " + page.getLikes());
        System.out.println(page);

        return page;
    }

}
