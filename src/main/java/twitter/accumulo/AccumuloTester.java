package twitter.accumulo;

import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.hadoop.io.Text;

import java.util.Map;

/**
 * Created by mknutsen on 4/12/16.
 */
public class AccumuloTester {

    static ZooKeeperInstance zoo;

    static Connector connector;

    // Total number of rows read
    private static long numRowsRead = 0;

    // Number of rows to display at a time
    private static int numRowsDisplayed = 10;

    static {
        // Create ZooKeeperInstance and Connector objects
        zoo = new ZooKeeperInstance(Constants.INSTANCE, Constants.ZOOKEEPERS);
        try {
            connector = zoo.getConnector(Constants.USER_NAME, new PasswordToken(Constants.USER_PASS.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (!connector.tableOperations().exists(Constants.TWEET_INDEX)) {
            System.err.format("Error: Table %s does not exist", Constants.TWEET_INDEX);
            return;
        }
        Scanner scanner = null;
        try {
            scanner = connector.createScanner(Constants.TWEET_INDEX, new Authorizations());
        } catch (TableNotFoundException e) {
            e.printStackTrace();
            return;
        }
        // Limit the scanner to only fetch the text column from the tweet family
        scanner.fetchColumn(new Text("tweet"), new Text("text"));
        String tweetId; // The tweet Id
        String tweetText; // The tweet Text

        // Process all of the records returned by the scanner
        for (Map.Entry<Key, Value> record : scanner) {


            // Display the tweet ID and text
            System.out.println(record.getKey().getRow() + " " + record.getValue().get().toString());
        }

        // Display the number of rows read
        System.out.format("%d Entries read\n", numRowsRead);
    }

    public static class Constants {

        // Accumulo Configuration
        public static final String USER_NAME = "root";

        public static final String USER_PASS = "secret";

        public static final String INSTANCE = "localAccumulo";

        public static final String ZOOKEEPERS = "localhost:2181";

        public static final String TWEET_INDEX = "reverse_index";
    }
}
