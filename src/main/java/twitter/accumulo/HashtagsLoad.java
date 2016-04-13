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
    private static String specialCharacter = null;
    //File containing many rows of data, columns <tweet id> <hashtag> <count>, ordered by count descending
    @Override
    Mutation processLine(final String rawString) {
	for(int i=0;i<rawString.length() && specialCharacter==null;i++){
	    if(!(rawString.charAt(i) >= 0 && rawString.charAt(i) <= 9)){
		specialCharacter = rawString.substring(i,i+1);
            }
        }

        String[] parts = rawString.split("[^\\w']+");
	for(int i =0;i<parts.length;i++) System.out.println(parts[i]);
        Mutation mutation = new Mutation(parts[0]);
        mutation.put("hashtag", "tweet_id", parts[0]);
        mutation.put("hashtag", "hashtag", parts[1]);
        mutation.put("hashtag", "count", parts[2]);
        return mutation;
    }
}
