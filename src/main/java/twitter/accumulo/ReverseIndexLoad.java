package twitter.accumulo;

import org.apache.accumulo.core.data.Mutation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by mknutsen on 4/11/16.
 */
public class ReverseIndexLoad extends DataLoad {

    public ReverseIndexLoad() {
        super("reverse_index");
    }

    //    Columns are <word> <tweet id>.
    public static void main(String args[]) throws Exception {
        System.exit(ToolRunner.run(new Configuration(), new HashtagsLoad(), args));
    }
    private static String specialCharacter=null;
    @Override
    Mutation processLine(final String rawString) {
        for(int i=0;i<rawString.length() && specialCharacter==null;i++){
	    if(!(rawString.charAt(i) >= 0 && rawString.charAt(i) <= 9)){
		specialCharacter = rawString.substring(i,i+1);
            }
        }

        String[] parts = rawString.split(specialCharacter);
        Mutation mutation = new Mutation(parts[1]);
        mutation.put("word", "word", parts[0]);
        mutation.put("word", "tweet_id", parts[1]);
        return mutation;
    }
}
