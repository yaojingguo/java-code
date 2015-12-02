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
  public void testJedis() {
    Jedis jedis = new Jedis(host);
    jedis.set("foo", "bar");
    String value = jedis.get("foo");
    assertThat(value).isEqualTo("bar");
  }

  @Test
  public void testPool() {
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
}
