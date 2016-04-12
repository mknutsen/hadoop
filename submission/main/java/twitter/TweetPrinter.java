package twitter;

import twitter.avro.Tweet;

/**
 * Created by mknutsen on 2/26/16.
 */
public class TweetPrinter extends TweetProcessor {
    

    public final void sendTweet(final Tweet status) {
        System.out.println(status);
    }
}
