package com.johnny.boot.openshift.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Cacheable(value = "${project.name}-messageCache")
	public String application(String key) {

		System.out.println("execute");
		return "xxxx";
	}

}
