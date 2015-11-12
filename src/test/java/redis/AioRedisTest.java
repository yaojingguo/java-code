package redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class AioRedisTest {

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
    
    JedisTest.verifyPool(sentinelPool);
  }

}
