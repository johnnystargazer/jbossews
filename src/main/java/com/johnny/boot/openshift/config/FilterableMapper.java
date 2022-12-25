package com.johnny.boot.openshift.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;

public class FilterableMapper extends ObjectMapper {

	public void withFilters(FilterProvider filters) {
		_serializationConfig = _serializationConfig.withFilters(filters);

	}
}
