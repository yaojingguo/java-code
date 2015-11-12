package redis;

import static com.google.common.truth.Truth.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

@SuppressWarnings("all")
public class JedisTest {
  private static String host = "localhost";

  private JedisPool pool;

  @Before
  public void setUp() {
    pool = new JedisPool(new JedisPoolConfig(), host);
  }

  @After
  public void tearDown() {
    pool.destroy();
  }

  @Test
  public void first() {
    Jedis jedis = new Jedis(host);
    jedis.set("foo", "bar");
    String value = jedis.get("foo");
    assertThat(value).isEqualTo("bar");
  }

  @Test
  public void second() {
    verifyPool(pool);
  }

  public static void verifyPool(Pool<Jedis> pool) {
    try (Jedis jedis = pool.getResource()) {
      jedis.set("foo", "bar");
      String foobar = jedis.get("foo");
      assertThat(foobar).isEqualTo("bar");
      jedis.zadd("sose", 0, "car");
      jedis.zadd("sose", 0, "bike");
      Set<String> sose = jedis.zrange("sose", 0, -1);
      assertThat(sose).containsExactly("car", "bike");
    }
  }
  
  @Test
  public void third() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("10.202.80.117", 17500).toString());
    sentinels.add(new HostAndPort("10.202.80.118", 17500).toString());
    JedisSentinelPool sentinelPool = new JedisSentinelPool("master7500", sentinels, "zt1116@ops");
    System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
    verifyPool(sentinelPool);
  }
  
  private static void verifyPool2(Pool<Jedis> pool) {
    try (Jedis jedis = pool.getResource()) {
      jedis.set("foo", "bar");
      String foobar = jedis.get("foo");
      assertThat(foobar).isEqualTo("bar");
      
      jedis.zadd("sose", 0, "car");
      jedis.zadd("sose", 0, "bike");
      Set<String> sose = jedis.zrange("sose", 0, -1);
      assertThat(sose).containsExactly("car", "bike");
    }
  }
  
  public static void main(String[] args) {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("127.0.0.1", 26379).toString());
    JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
    System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
    verifyPool2(sentinelPool);
  }
}
