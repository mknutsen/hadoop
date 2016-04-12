package twitter;

import twitter.avro.Tweet;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by mknutsen on 2/18/16.
 */
public abstract class TweetProcessor {

    private final ConfigurationBuilder cb;

    public TweetProcessor() {
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("X6n9muaCgNyCUM6YYus81OcJF")
                .setOAuthConsumerSecret("aHxTHt52ENT0dkk4tu22HKKXf1EdCg9cqqPPOyrVky5DNIw15v")
                .setOAuthAccessToken("1642079071-Uu3yHVcjAlZPIdJvbbPPDBmLFVgPrIyEW7VX4jF")
                .setOAuthAccessTokenSecret("TXmVwO0ed52ZXC1gg5EncJeLFlbKc4S0hbRbGtMo5dmUx");
    }

    public static void main(String[] args) {
        final Properties props = new Properties();
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        props.put("bootstrap.servers", args[0]);
        try {
            if ("consumer".equals(args[2])) {
                new Consumer().consume(args[1], props, Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            } else if ("producer".equals(args[2])) {
                new TweetKafka(props, args[1]).produce();
            } else {
                throw new IllegalArgumentException("either consumer or producer. you put " + args[2] + ".");
            }
        } catch (Exception e) {
            System.err.println("You did something wrong with the inputs.");
            e.printStackTrace();
        }

    }

    public static Tweet statusToTweet(Status status) {
        Tweet.Builder tweetBuilder = Tweet.newBuilder();

        ArrayList<CharSequence> hashtags = new ArrayList<>();
        ArrayList<CharSequence> urls = new ArrayList<>();
        ArrayList<CharSequence> userMentions = new ArrayList<>();

        for (HashtagEntity hashtagEntity : status.getHashtagEntities()) {
            hashtags.add(hashtagEntity.getText());
        }
        for (URLEntity urlEntity : status.getURLEntities()) {
            urls.add(urlEntity.getText());
        }
        for (UserMentionEntity userMentionEntity : status.getUserMentionEntities()) {
            userMentions.add(userMentionEntity.getText());
        }


        tweetBuilder.setHashtags(hashtags);
        tweetBuilder.setUrls(urls);
        tweetBuilder.setUserMentions(userMentions);

        if (status.getGeoLocation() != null) {
            tweetBuilder.setLong$(status.getGeoLocation().getLongitude());
            tweetBuilder.setLat(status.getGeoLocation().getLatitude());
        }

        twitter4j.User user = status.getUser();
        tweetBuilder.setDescription(user.getDescription());
        tweetBuilder.setFollowersCount(user.getFollowersCount());
        tweetBuilder.setGeoEnabled("".equals(user.getLocation()));
        tweetBuilder.setId(user.getId());
        tweetBuilder.setLang(user.getLang());
        tweetBuilder.setLocation(user.getLocation());
        tweetBuilder.setScreenName(user.getScreenName());
        tweetBuilder.setStatusesCount(user.getStatusesCount());

        tweetBuilder.setCreatedAt(status.getCreatedAt().toString());
        tweetBuilder.setFavoriteCount(status.getFavoriteCount());
        tweetBuilder.setId(status.getId());
        tweetBuilder.setRetweetCount(status.getRetweetCount()).setSource(status.getSource());
        tweetBuilder.setText(status.getText());

        return tweetBuilder.build();
    }

    public final void produce() {
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(final Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onStatus(final Status status) {
                sendTweet(statusToTweet(status));
            }

            @Override
            public void onDeletionNotice(final StatusDeletionNotice statusDeletionNotice) {
                System.out.println("deletionNotice: " + statusDeletionNotice);
            }

            @Override
            public void onTrackLimitationNotice(final int numberOfLimitedStatuses) {
                System.out.println("onTrackLimitationNotice: " + numberOfLimitedStatuses);

            }

            @Override
            public void onScrubGeo(final long userId, final long upToStatusId) {
                System.out.println("onscrubgeo: " + userId);

            }

            @Override
            public void onStallWarning(final StallWarning warning) {
                System.out.println("stallwarning: " + warning);

            }
        };
        TwitterStream twitterStream = new TwitterStreamFactory(getConfigurationBuilder().build()).getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter("cats");
        new Scanner(System.in).nextLine();
        if (this instanceof TweetKafka) {
            ((TweetKafka) this).close();
        }
        System.exit(0);
    }

    public abstract void sendTweet(Tweet status);

    public final ConfigurationBuilder getConfigurationBuilder() {
        return cb;
    }
}
