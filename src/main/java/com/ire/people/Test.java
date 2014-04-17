package com.ire.people;


import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Test {

    public static final String freebaseAPIKey = "AIzaSyCa1FQB7BXbXwFCcI5Tn-VJITWb43910yU";

    private void startUp() {
        System.setProperty("https.proxyHost", "proxy.iiit.ac.in");
        System.setProperty("https.proxyPort", "8080");
    }

    public static void main(String[] args) throws IOException, ParseException {


        Test test = new Test();
        test.startUp();
        FreeBaseSerivce freeBaseSerivce = new FreeBaseSerivce(freebaseAPIKey);
        TwitterService twitterService = new TwitterService();
        while (true) {
            //freeBaseSerivce.topic("/en/barack_obama");
            //twitterService.search();
            break;
        }
    }
}
/*import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
	public static void main(String []args) throws IOException{
		 InetAddress[] addresses = InetAddress.getAllByName("www.googleapis.com");
	      for (InetAddress address : addresses) {
	        if (address.isReachable(10000))
	        {   
	           System.out.println("Connected "+ address + "  "+System.currentTimeMillis());
	        }
	        else
	        {
	           System.out.println("Failed "+address);
	        }
	      }
	}

}*/
