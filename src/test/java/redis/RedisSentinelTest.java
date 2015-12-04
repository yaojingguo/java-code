package redis;

import static redis.JedisTest.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;


// Start a sentinel:
//   src/redis-sentinel my-sentinel.conf --sentinel
//   src/redis-server --port 6379
//   src/redis-server --port 6380

public class RedisSentinelTest {

  @Test
  public void testSentinel() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("10.202.80.117", 17500).toString());
    sentinels.add(new HostAndPort("10.202.80.118", 17500).toString());
    JedisSentinelPool pool = new JedisSentinelPool("master7500", sentinels, "zt1116@ops");
    System.out.println("Current master: " + pool.getCurrentHostMaster().toString());
    verifyPool(pool);
  }

  @Test
  public void testFailOver() {
//    handleFailOver(localSentinel());
    handleFailOver(prodSentinel());
  }
  
  @Test
  public void testSentinelCommands() {

  }

  private void handleFailOver(JedisSentinelPool pool) {
    for (;;) {
      try {
        System.out.println("Current master: " + getMaster(pool));
        verifyPool(pool);
        System.out.printf("SUCCEEDED");
        System.out.println();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private JedisSentinelPool localSentinel() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
    JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
    return pool;
  }

  private JedisSentinelPool prodSentinel() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("172.19.3.10", 17610).toString());
    sentinels.add(new HostAndPort("172.19.3.14", 17610).toString());
    sentinels.add(new HostAndPort("172.19.3.15", 17610).toString());
    JedisPoolConfig poolCfg = new JedisPoolConfig();

    poolCfg.setMaxIdle(8);
    poolCfg.setMinIdle(0);
    poolCfg.setMaxTotal(8);
    poolCfg.setMaxWaitMillis(-1);

    JedisSentinelPool pool = new JedisSentinelPool("master7610", sentinels, poolCfg, "wx@1116@x89i");
    return pool;
  }

  private String getMaster(JedisSentinelPool pool) {
    return pool.getCurrentHostMaster().toString();
  }
}
