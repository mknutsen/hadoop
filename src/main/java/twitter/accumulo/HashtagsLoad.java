package twitter.accumulo;

import org.apache.accumulo.core.data.Mutation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by mknutsen on 4/11/16.
 */
public class HashtagsLoad extends DataLoad {
    
    public HashtagsLoad() {
        super("hashtags");
    }

    public static void main(String args[]) throws Exception {
        System.exit(ToolRunner.run(new Configuration(), new HashtagsLoad(), args));
    }

    //File containing many rows of data, columns <tweet id> <hashtag> <count>, ordered by count descending
    @Override
    Mutation processLine(final String rawString) {


        String[] parts = rawString.split("[^\\w']+");

        Mutation mutation = new Mutation(parts[0]);
        mutation.put("hashtag", "tweet_id", parts[0]);
        mutation.put("hashtag", "hashtag", parts[1]);
        mutation.put("hashtag", "count", parts[2]);
        return mutation;

    }
}
