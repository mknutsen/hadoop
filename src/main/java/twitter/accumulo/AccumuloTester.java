package twitter.accumulo;

import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.ZooKeeperInstance;
import org.apache.accumulo.core.client.security.tokens.PasswordToken;

import java.util.SortedSet;

/**
 * Created by mknutsen on 4/12/16.
 */
public class AccumuloTester {

    public static void main(String[] args) throws Exception {
        ZooKeeperInstance instance = new ZooKeeperInstance(DataLoad.Constants.INSTANCE, DataLoad.Constants.ZOOKEEPERS);
        Connector connector = instance.getConnector                       (DataLoad.Constants.USER_NAME,
                new PasswordToken(DataLoad.Constants.USER_PASS.getBytes()));
        SortedSet set = connector.tableOperations().list();
        System.out.println(set);
        for (Object a : set) {
            System.out.println(a.toString());
        }
    }
}
