package com.johnny.boot.openshift.util.redis;

import com.johnny.boot.openshift.domain.common.ApplicationConst;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class JedisClient {

	private JedisPool jedisPool;

	public JedisClient() {
		if (jedisPool == null)
			jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.48.252");
	}

	public Jedis getResource() {
		return jedisPool.getResource();
	}

	public static void main(String[] args) {
		JedisClient jedisClient = new JedisClient();
		jedisClient.getResource().subscribe(new JedisPubSub() {

			@Override
			public void onUnsubscribe(String arg0, int arg1) {
				System.out.println("onUnsubscribe " + arg0);

			}

			@Override
			public void onSubscribe(String arg0, int arg1) {
				System.out.println("onSubscribe " + arg1);

			}

			@Override
			public void onPUnsubscribe(String arg0, int arg1) {
				System.out.println("onPUnsubscribe " + arg1);

			}

			@Override
			public void onPSubscribe(String arg0, int arg1) {
				System.out.println("onPSubscribe " + arg1);

			}

			@Override
			public void onPMessage(String arg0, String arg1, String arg2) {
				System.out.println("onPMessage " + arg0 + "--- " + arg1
						+ "--- " + arg2);

			}

			@Override
			public void onMessage(String arg0, String arg1) {
				System.out.println("onMessage " + arg0 + " ---  " + arg1);

			}
		}, ApplicationConst.REDIS_PUBLISH_CHANNEL);
	}
}
