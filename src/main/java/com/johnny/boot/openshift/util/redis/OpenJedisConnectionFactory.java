package com.johnny.boot.openshift.util.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;

public class OpenJedisConnectionFactory extends JedisConnectionFactory {
	@Override
	public Jedis fetchJedisConnector() {
		return super.fetchJedisConnector();
	}
}
