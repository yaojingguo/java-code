package redis;

import static com.google.common.truth.Truth.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

public class AioRedisTest {

  @SuppressWarnings("all")
  @Test
  public void testConnectAndAccess() {
    Set sentinels = new HashSet();
    sentinels.add(new HostAndPort("10.202.80.117", 17411).toString());
    sentinels.add(new HostAndPort("10.202.80.118", 17411).toString());
    JedisPoolConfig poolCfg = new JedisPoolConfig();

    poolCfg.setMaxIdle(8);
    poolCfg.setMinIdle(0);
    poolCfg.setMaxTotal(8);
    poolCfg.setMaxWaitMillis(-1);

    JedisSentinelPool sentinelPool = new JedisSentinelPool("master7411",
                                                           sentinels,
                                                           poolCfg,
                                                           "ybg_yjg987");

    accessRedis(sentinelPool);
  }

  public static void accessRedis(Pool<Jedis> pool) {
    try (Jedis jedis = pool.getResource()) {
      jedis.select(0); // Redis database index
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
