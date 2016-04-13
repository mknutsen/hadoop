package twitter.accumulo;

import org.apache.accumulo.core.client.BatchWriter;
import org.apache.accumulo.core.client.BatchWriterConfig;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;
import org.apache.accumulo.core.data.Mutation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.concurrent.TimeUnit;

/**
 * Created by mknutsen on 4/11/16.
 */
public abstract class DataLoad extends Configured implements Tool {

    private static long numTweets = 0; // The total number of tweets inserted

    private final String tableName;

    public DataLoad(String tableName) {
        this.tableName = tableName;
    }

    public static void main(String[] args) throws Exception {
        DataLoad dataLoad;
        switch (args[0]) {
            case "hashtags":
                dataLoad = new HashtagsLoad();
                break;
            case "popular_users":
                dataLoad = new PopularUsersLoad();
                break;
            case "tweets":
                dataLoad = new TweetLoad();
                break;
            case "reverse_index":
                dataLoad = new ReverseIndexLoad();
                break;
            default:
                throw new Exception("Usage: hadoop jar accumulo-writer-example.jar <type> <data_dir>");

        }
        System.exit(ToolRunner.run(new Configuration(), dataLoad, args));
    }

    abstract Mutation processLine(final String rawString);

    public int run(String args[]) throws Exception {

        // Ensure the user enters the path to the twitter data
        if (args.length != 2) {
            System.out.println("Usage: hadoop jar accumulo-writer-example.jar <data_dir>");
            return 1;
        }

        // Set the location of the twitter data
        String dataDir = args[1];

        // Get the names of all of the files containing twitter data
        File[] files = new File(dataDir).listFiles(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.contains("part");
            }
        });

        // Configure the ZooKeeper instance and the Connector objects
        ZooKeeperInstance instance = new ZooKeeperInstance(Constants.INSTANCE, Constants.ZOOKEEPERS);

        Connector connector =
                instance.getConnector(Constants.USER_NAME, new PasswordToken(Constants.USER_PASS.getBytes()));

        System.out.format("Checking for table %s's existance\n", tableName);
        // Create our table if it does not already exist
        if (!connector.tableOperations().exists(tableName)) {
            System.out.format("Creating table %s\n", tableName);
            connector.tableOperations().create(tableName);
        }

        // Set the BatchWriter configurations
        long memBuf = 1000000L; // bytes to store before sending a batch
        long timeout = 1000L; // Milliseconds to wait before sending
        int numThreads = 10; // Threads to use to write

        // Create the BatchWriter
        BatchWriterConfig batchConfig = new BatchWriterConfig();
        batchConfig.setTimeout(timeout, TimeUnit.MILLISECONDS);
        batchConfig.setMaxMemory(memBuf);
        batchConfig.setMaxWriteThreads(numThreads);
        BatchWriter batchWriter = connector.createBatchWriter(tableName, batchConfig);

        // Read all of the files in the data directory
        for (File file : files) {
            System.out.format("Reading from file: %s\n\n", file);
            BufferedReader buffReader = null;

            // A JSON object holding tweet info
            String rawJson;

            // A record to be entered into the database
            Mutation mutation;

            // Create a buffered reader for the input file
            buffReader = new BufferedReader(new FileReader(file));

            // Read the file and insert tweet information into Accumulo
            while ((rawJson = buffReader.readLine()) != null) {
                mutation = processLine(rawJson);
                if (mutation != null) {

                    // Add the mutation to the batch writer
                    batchWriter.addMutation(mutation);

                    // Increment the number of tweets inserted
                    ++numTweets;
                }
            }
            buffReader.close();
        }

        // Send the mutation to Accumulo and release resources
        batchWriter.close();

        // Display how many tweets were inserted into Accumulo
        System.out.format("%d Tweets inserted\n", numTweets);
        return 0;
    }

    public static class Constants {

        // Accumulo Configuration
        public static final String USER_NAME = "root";

        public static final String USER_PASS = "secret";

        public static final String INSTANCE = "default";

        public static final String ZOOKEEPERS = "localhost:2181";

    }
}
