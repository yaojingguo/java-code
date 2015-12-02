package redis;

import static redis.JedisTest.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisSentinelPool;

public class RedisSentinelTest {
  @Test
  public void third() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("10.202.80.117", 17500).toString());
    sentinels.add(new HostAndPort("10.202.80.118", 17500).toString());
    JedisSentinelPool sentinelPool = new JedisSentinelPool("master7500", sentinels, "zt1116@ops");
    System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
    verifyPool(sentinelPool);
  }


  @Test
  public void testFailOver() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
    JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
    System.out.println("Current master: " + getMaster(sentinelPool));
    verifyPool(sentinelPool);
  }

  private void handleFailOver(JedisSentinelPool pool) {
    for (;;) {
      try {
        verifyPool(pool);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private String getMaster(JedisSentinelPool pool) {
    return pool.getCurrentHostMaster().toString();
  }
}
