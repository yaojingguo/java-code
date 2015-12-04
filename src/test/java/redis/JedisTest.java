package redis;

import static com.google.common.truth.Truth.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
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
    Jedis jedis = jedis();
    jedis.set("foo", "bar");
    String value = jedis.get("foo");
    assertThat(value).isEqualTo("bar");
  }

  @Test
  public void testTransaction() {
    try (Jedis j = jedis();) {
      Transaction t = jedis().multi();
      String bar = "bar";
      t.set("fool", bar);
      Response<String> result1 = t.get("fool");

      t.zadd("foo", 1, "barowitch");
      t.zadd("foo", 0, "barinsky");
      t.zadd("foo", 0, "barikoviev");
      Response<Set<String>> sose = t.zrange("foo", 0, -1);
      t.exec();

      String foolbar = result1.get();
      assertThat(foolbar).isEqualTo(bar);
      assertThat(sose.get().size()).isEqualTo(3);
    }
  }


  private Jedis jedis() {
    Jedis jedis = new Jedis(host);
    return jedis;
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
