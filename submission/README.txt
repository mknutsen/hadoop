Create topic:
kafka-topics.sh --zookeeper localhost:2181 --create --partitions 1 --replication-factor 1 --topic tweets

To compile avrò schema:
java -jar avro-tools.jar compile schema entity.avsc coordinate.avsc user.avsc tweet.avsc .
then make sure the file structure is src/main/java/twitter/avro/file.java

To build:
gradle build

This will build zips that contain the libs and scripts to run the program in build/distributions

in the bin folder of that zip:
./hadoop localhost:9092 tweets producer
or
./hadoop localhost:9092 tweets consumer seconds number_of_messages

This project was created in Java 1.7 with Gradle 2.9

the files will be found by calling hdfs dfs -ls -R /in/

I used:
http://apache-avro.679487.n3.nabble.com/How-to-declare-an-optional-field-td4025089.html
http://avro.apache.org/docs/1.8.0/gettingstartedjava.html
https://github.com/adamjshook/hadoop-demos/tree/master/kafka
http://stackoverflow.com/questions/16284399/purge-kafka-queue
https://cwiki.apache.org/confluence/display/AVRO/FAQ
http://mail-archives.apache.org/mod_mbox/avro-user/200912.mbox/%3C15da8a100911302145sef2b715r38c38edde92ace76@mail.gmail.com%3E
http://stackoverflow.com/questions/8298308/how-to-encode-decode-kafka-messages-using-avro-binary-encoder
http://docs.confluent.io/1.0/schema-registry/docs/serializer-formatter.html