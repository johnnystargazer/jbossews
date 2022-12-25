package com.johnny.boot.openshift.domain.model;

import java.io.Serializable;

public interface RedisEntity extends Serializable {

	String getKey();

	String getObjectKey();

}
