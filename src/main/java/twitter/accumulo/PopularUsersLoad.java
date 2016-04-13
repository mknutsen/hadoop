package twitter.accumulo;

import org.apache.accumulo.core.data.Mutation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by mknutsen on 4/11/16.
 */
public class PopularUsersLoad extends DataLoad {

    public PopularUsersLoad() {
        super("popular_users");
    }

    public static void main(String args[]) throws Exception {
        System.exit(ToolRunner.run(new Configuration(), new HashtagsLoad(), args));
    }
    static String specialCharacter = null;
    //   <user id> <screen name> <num followers> <num tweets>
    @Override
    Mutation processLine(final String rawString) {
	System.out.println(rawString);
	
       for(int i=0;i<rawString.length() && specialCharacter==null;i++){
	   if(!(rawString.charAt(i) >= 0 && rawString.charAt(i) <= 9)){
	       specialCharacter = rawString.substring(i,i+1);
	    }
	}   
	
        String[] parts = rawString.split(specialCharacter);
	if(parts.length < 3){
	    return null;
	}
	for(int i=0;i<parts.length;i++){
		System.out.println(parts[i]);
	    }
        Mutation mutation = new Mutation(parts[0]);
        mutation.put("user", "user_id", parts[0]);
        mutation.put("user", "num_followers", parts[1]);
        mutation.put("user", "num_tweets", parts[2]);

        return mutation;
    }
    
}
