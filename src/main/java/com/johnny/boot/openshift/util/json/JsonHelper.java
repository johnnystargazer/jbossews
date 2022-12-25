package com.johnny.boot.openshift.util.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * @author johnny
 */
public class JsonHelper {
	public final static ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.NON_PRIVATE);
		MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
				true);
		// MAPPER.configure(
		// MapperFeature., false);
		// MAPPER.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
		// false);

		// MAPPER.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,
		// false);
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static void toJsonOnly(Writer resultWriter, Object bean,
			Map<String, String> fieldMapping, Set<String> only) {
		ObjectMapper mapper = new ObjectMapper().setVisibility(
				PropertyAccessor.FIELD, Visibility.ANY);
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				"filter properties by name",
				SimpleBeanPropertyFilter.filterOutAllExcept(only));
		mapper.addMixIn(Object.class, PropertyFilterMixIn.class);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		ObjectWriter writer = mapper.writer(filters);
		try {
			writer.writeValue(resultWriter, bean);
			// writer.writeValueAsString(resultWriter, bean);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void writeWith(Writer resultWriter, Object bean,
			Map<String, String> fieldMapping, String... ignores) {
		ObjectMapper mapper = new ObjectMapper().setVisibility(
				PropertyAccessor.FIELD, Visibility.NON_PRIVATE);

		FilterProvider filters = new SimpleFilterProvider().addFilter(
				"filter properties by name",
				SimpleBeanPropertyFilter.serializeAllExcept(ignores));

		// if (fieldMapping != null && !fieldMapping.isEmpty()) {
		// BasePropertyNamingStrategy basePropertyNamingStrategy = new
		// BasePropertyNamingStrategy(
		// fieldMapping);
		// mapper.setPropertyNamingStrategy(basePropertyNamingStrategy);
		// }

		mapper.addMixIn(Object.class, PropertyFilterMixIn.class);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		ObjectWriter writer = mapper.writer(filters);
		try {

			writer.writeValue(resultWriter, bean);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@JsonFilter("filter properties by name")
	class PropertyFilterMixIn {

	}

	public static void write(OutputStream out, Object value) {
		try {
			MAPPER.writeValue(out, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * @param bean
	 * @return
	 */
	public static String toJsonString(Object bean) {
		try {

			return MAPPER.writeValueAsString(bean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
		try {

			return MAPPER.readValue(jsonString, clazz);

		} catch (Exception e) {
			System.out.println("failed to mapping json " + jsonString);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param bean
	 * @param clazz
	 * @return
	 */
	public static <T> T newfor(Object bean, Class<T> clazz) {
		try {

			return MAPPER.convertValue(bean, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		Test test = new Test();

	}

	public static class Test {
		private String a = "1";
		private String b = "2";
		private String c = "2";

		public String getC() {
			return c;
		}

		public void setC(String c) {
			this.c = c;
		}

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}

	}
}
