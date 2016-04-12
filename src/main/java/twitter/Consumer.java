package twitter;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import twitter.avro.Tweet;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by mknutsen on 2/26/16.
 */
public class Consumer {

    private final Queue<Tweet> tweets;

    private boolean lock = false;

    public Consumer() {
        tweets = new ArrayDeque<>();
    }

    public static void runCommand(String command) {
        System.out.println("running: " + command);
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            System.out.print("Here is the standard output of the command: ");
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            System.out.println();

            // read any errors from the attempted command
            System.out.print("Here is the standard error of the command (if any): ");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            System.out.println("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consume(final String topic, final Properties props, final int numMessages, final int secondsPassed) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                final DatumWriter<Tweet> userDatumWriter = new SpecificDatumWriter<>(Tweet.getClassSchema());
                long lastWriteTime = System.currentTimeMillis();
                while (true) {
                    if (tweets.size() > numMessages ||
                            ((System.currentTimeMillis() - lastWriteTime) > secondsPassed * 1000 &&
                                    tweets.size() > 0)) {
                        lock();
                        System.out.println                                                                     (
                                "writing " + tweets.size() + " " + (System.currentTimeMillis() - lastWriteTime));
                        writeToFile(userDatumWriter);

                        System.out.println                                                                          (
                                "done writing " + tweets.size() + " " + (System.currentTimeMillis() - lastWriteTime));
                        unlock();
                        lastWriteTime = System.currentTimeMillis();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        System.out.println("Consumer started... Ctrl+C to quit");
        boolean consume = true;
        BinaryDecoder decode = null;
        SpecificDatumReader<Tweet> reading = new SpecificDatumReader<>(Tweet.getClassSchema());
        Tweet tweet = null;

        while (consume) {
            ConsumerRecords<String, byte[]> records = consumer.poll(10);
            for (ConsumerRecord<String, byte[]> record : records) {
                try {
                    decode = DecoderFactory.get().binaryDecoder(record.value(), decode);
                    tweet = reading.read(tweet, decode);
                    System.out.println("got a tweet ");
                    lock();
                    tweets.add(tweet);
                    unlock();
                    System.out.println("added " + tweet.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.close();
    }

    private void unlock() {
        lock = false;
    }

    private void lock() {
        while (lock == true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock = true;
    }

    private void writeToFile(DatumWriter<Tweet> userDatumWriter) {
        try {

            String fileName = "tweet-" + System.currentTimeMillis() + ".avro";
            DataFileWriter<Tweet> dataFileWriter = new DataFileWriter<>(userDatumWriter);
            dataFileWriter.create(Tweet.getClassSchema(), new File(fileName));
            while (!tweets.isEmpty()) {
                dataFileWriter.append(tweets.remove());
            }
            dataFileWriter.flush();
            dataFileWriter.close();

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            String fileLoc = "/in/tweets/";
            fileLoc += year + (("" + month).length() == 1 ? "/0" : "/") + month +
                    (("" + day).length() == 1 ? "/0" : "/") + day + (("" + hour).length() == 1 ? "/0" : "/") +
                    hour + "/";
            System.out.println("The file is at: " + fileLoc + fileName);
            runCommand("hdfs dfs -mkdir -p " + fileLoc);
            runCommand("hdfs dfs -moveFromLocal " + fileName + " " + fileLoc + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
