package com.johnny.boot.openshift.repository;

import java.util.ArrayList;
import java.util.List;

import com.johnny.boot.openshift.domain.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements RedisRepository<User> {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	public RedisTemplate<String, User> getRedisTemplate() {
		return redisTemplate;
	}

	@Override
	public void put(User user) {
		redisTemplate.opsForHash()
				.put(user.getObjectKey(), user.getKey(), user);
	}

	@Override
	public void delete(User key) {
		redisTemplate.opsForHash().delete(key.getObjectKey(), key.getKey());
	}

	@Override
	public User get(User key) {
		return (User) redisTemplate.opsForHash().get(key.getObjectKey(),
				key.getKey());
	}

	@Override
	public List<User> getObjects() {
		List<User> users = new ArrayList<User>();
		for (Object user : redisTemplate.opsForHash().values(User.OBJECT_KEY)) {
			users.add((User) user);
		}
		return users;
	}
}