package twitter;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import twitter.avro.Tweet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Created by mknutsen on 2/26/16.
 */
public class TweetKafka extends TweetProcessor {


    private final KafkaProducer<String, byte[]> producer;


    private final String topic;

    public TweetKafka(Properties props, String topic) {
        this.topic = topic;
        producer = new KafkaProducer<String, byte[]>(props);
        System.out.println("producer up");
    }

    public final void close() {
        producer.close();
    }

    @Override
    public void sendTweet(Tweet status) {
        final SpecificDatumWriter<Tweet> avroEventWriter = new SpecificDatumWriter<>(Tweet.getClassSchema());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final EncoderFactory avroEncoderFactory = EncoderFactory.get();

        BinaryEncoder binaryEncoder = avroEncoderFactory.binaryEncoder(stream, null);
        try {
            avroEventWriter.write(status, binaryEncoder);
            binaryEncoder.flush();
            IOUtils.closeQuietly(stream);
            ProducerRecord<String, byte[]> message = new ProducerRecord<String, byte[]>(topic, stream.toByteArray());
            System.out.print("sending... ");
            long time = System.currentTimeMillis();
            Future item = producer.send(message);
            System.out.println("sending took" + (System.currentTimeMillis() - time));
            producer.flush();
            System.out.println("sending and flushing took" + (System.currentTimeMillis() - time));
            item.get(3000, TimeUnit.MILLISECONDS);
            System.out.println(item.isDone() + " " + status + " took " + (System.currentTimeMillis() - time));
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        //            producer.send(new ProducerRecord<String, byte[]>(topic, status.getId().toString(), status.to)));

    }
}
