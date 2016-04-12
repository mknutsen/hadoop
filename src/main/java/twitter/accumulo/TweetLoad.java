package twitter.accumulo;

import org.apache.accumulo.core.data.Mutation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by mknutsen on 4/11/16.
 */
public class TweetLoad extends DataLoad {

    public TweetLoad() {
        super("tweets");
    }

    public static void main(String args[]) throws Exception {
        System.exit(ToolRunner.run(new Configuration(), new HashtagsLoad(), args));
    }

    //    Output: File containing many rows of data, one row per user, containing all columns pertaining to a user:
    //    user: id
    //    user: screen_name
    //    user: location
    //    user: description
    //    user: followers_count
    //    user: statuses_count
    //    user: geo_enabled
    //    user: lang
    //    Any additional columns you've selected to include from the Twitter object model
    @Override
    Mutation processLine(final String rawString) {
        String[] parts = rawString.split(" ");
        Mutation mutation = new Mutation(parts[0]);
        mutation.put("tweet", "tweet_id", parts[1]);
        mutation.put("tweet", "location", parts[2]);
        mutation.put("tweet", "description", parts[3]);
        mutation.put("tweet", "followers_count", parts[4]);
        mutation.put("tweet", "geo_enabled", parts[5]);
        mutation.put("tweet", "lang", parts[6]);
        return mutation;
    }
    
}
