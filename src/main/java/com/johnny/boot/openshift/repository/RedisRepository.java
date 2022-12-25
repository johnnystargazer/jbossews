package com.johnny.boot.openshift.repository;

import java.util.List;

import com.johnny.boot.openshift.domain.model.RedisEntity;

public interface RedisRepository<V extends RedisEntity> {

	void put(V obj);

	V get(V key);

	void delete(V key);

	List<V> getObjects();
}