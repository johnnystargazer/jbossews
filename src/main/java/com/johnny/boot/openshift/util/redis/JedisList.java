package com.johnny.boot.openshift.util.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Redisアクセス用クライアントクラス。 <br/>
 * 
 * @author centos
 */
public class JedisList {

	public static void main(String[] args) {
		JedisClient jedisClient = new JedisClient();
		Jedis jedis = jedisClient.getResource();
		// jedis.del("list:1");

		Set<String> arr = jedis.keys("*");
		System.out.println(arr.size());
		System.out.println(arr);
		// List<String> xxx = jedis.blpop(1000000, "list:1");
		// if (xxx != null) {
		// for (String x : xxx) {
		// System.out.println(x);
		// }
		//
		// }
		// arr = jedis.lrange("list:1", 0, 100);
		// System.out.println(arr);

	}
}
