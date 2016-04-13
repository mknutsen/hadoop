package twitter.accumulo;

import org.apache.accumulo.core.data.Mutation;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import twitter.avro.Tweet;

import java.io.IOException;

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
        BinaryDecoder decode = DecoderFactory.get().binaryDecoder(rawString.getBytes(), null);
        Tweet tweet;

        SpecificDatumReader<Tweet> reading = new SpecificDatumReader<>(Tweet.getClassSchema());
        try {
            tweet = reading.read(null, decode);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Mutation mutation = new Mutation(tweet.getId().toString());
        mutation.put("tweet", "tweet_id", tweet.getId().toString());
        mutation.put("tweet", "location", tweet.getLocation().toString());
        mutation.put("tweet", "description", tweet.getDescription().toString());
        mutation.put("tweet", "followers_count", tweet.getFollowersCount().toString());
        mutation.put("tweet", "geo_enabled", tweet.getGeoEnabled().toString());
        mutation.put("tweet", "lang", tweet.getLang().toString());
        return mutation;
    }
    
}
