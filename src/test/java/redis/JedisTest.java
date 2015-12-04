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
      String key1 = "XXX-foo";
      jedis.set(key1, "bar");
      String foobar = jedis.get(key1);
      assertThat(foobar).isEqualTo("bar");

      String key2 = "XXX-sose";
      jedis.zadd(key2, 0, "car");
      jedis.zadd(key2, 0, "bike");
      Set<String> sose = jedis.zrange(key2, 0, -1);
      assertThat(sose).containsExactly("car", "bike");
    }
  }
}
