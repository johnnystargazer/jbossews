package com.johnny.boot.openshift.util.redis;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import redis.clients.jedis.Jedis;

/**
 * Redisアクセス用クライアントクラス。 <br/>
 * 
 * @author centos
 */
public class JedisKey {

	public static void main(String[] args) {
		JedisClient jedisClient = new JedisClient();
		Jedis jedis = jedisClient.getResource();

		jedis.set("user:1:name", UUID.randomUUID().toString());
		jedis.set("user:1:password", UUID.randomUUID().toString());
		jedis.set("user:1:age", UUID.randomUUID().toString());
		Set<String> sets = jedis.keys("user:1:*");
		List<String> x = jedis.mget(jedis.keys("user:1:*").toArray(
				new String[sets.size()]));
		for (String result : x) {
			System.out.println(result);
		}

		jedis.mset("user:2:name", "cvcv", "user:2:password", "21313");
		x = jedis.mget(jedis.keys("user:2:*").toArray(new String[2]));
		for (String result : x) {
			System.out.println(result);
		}
	}
}
