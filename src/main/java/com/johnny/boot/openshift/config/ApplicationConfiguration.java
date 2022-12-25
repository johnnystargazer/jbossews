package com.johnny.boot.openshift.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring4.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring4.web.view.ThymeleafTilesView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.resourceresolver.SpringResourceResourceResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.johnny.boot.openshift.application.Receiver;
import com.johnny.boot.openshift.application.SimpleConfig;
import com.johnny.boot.openshift.domain.common.ApplicationConst;
import com.johnny.boot.openshift.util.redis.OpenJedisConnectionFactory;
import com.johnny.boot.openshift.web.interceptor.AccessInterceptor;

@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

		 	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AccessInterceptor()).addPathPatterns(
				"/message/**");
	}

	@Bean
	public ThymeleafProperties thymeleafProperties() {
		return new ThymeleafProperties();
	}

	@Bean
	public SpringResourceResourceResolver thymeleafResourceResolver() {
		return new SpringResourceResourceResolver();
	}

	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	@Bean
	public ObjectMapper objectMapper() {
		FilterableMapper mapper = (FilterableMapper) new FilterableMapper()
				.setVisibility(PropertyAccessor.FIELD, Visibility.NON_PRIVATE);
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				"FN",
				SimpleBeanPropertyFilter.serializeAllExcept(new String[] {
						"unknownFields", "defaultInstanceForType",
						"messageTypes", "file", "options", "allFields",
						"enumTypes", "descriptorForType", "parserForType",
						"serializedSize", "nameBytes", "initialized",
						"initializationErrorString" }));
		mapper.addMixIn(Object.class, PropertyFilterMixIn.class);
		mapper.withFilters(filters);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper;
	}

	// @Autowired
	// @Bean(name = "simSessionFactory")
	// public SessionFactor sessionFactory(
	// EntityManagerFactory factory) {
	// return factory.getSessionFactory();
	// }

	@Bean
	public ITemplateResolver defaultTemplateResolver(
			ThymeleafProperties thymeleafProperties) {
		TemplateResolver resolver = new TemplateResolver();
		resolver.setResourceResolver(thymeleafResourceResolver());
		resolver.setPrefix(thymeleafProperties.getPrefix());
		resolver.setSuffix(thymeleafProperties.getSuffix());
		resolver.setTemplateMode(thymeleafProperties.getMode());
		Charset charset = thymeleafProperties.getEncoding();
		// Character x = thymeleafProperties.getEncoding();
		resolver.setCharacterEncoding(charset.toString());
		resolver.setCacheable(thymeleafProperties.isCache());
		return resolver;
	}

	@Autowired
	private ITemplateResolver iTemplateResolver;

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.addTemplateResolver(iTemplateResolver);
		// for (ITemplateResolver templateResolver : this.templateResolvers) {

		// }
		// for (IDialect dialect : this.dialects) {
		engine.addDialect(new TilesDialect());
		// }
		return engine;
	}

	// @Configuration
	// @ConditionalOnMissingBean(SpringTemplateEngine.class)
	// protected static class ThymeleafDefaultConfiguration {
	//
	// @Autowired
	// private final Collection<ITemplateResolver> templateResolvers =
	// Collections
	// .emptySet();
	//
	// @Autowired(required = false)
	// private final Collection<IDialect> dialects = Collections.emptySet();
	//
	//
	//
	// }

	@Bean
	public ViewResolver freeMarkerviewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setOrder(0);
		resolver.setPrefix("");
		resolver.setContentType("");
		resolver.setSuffix(".ftl");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		result.setTemplateLoaderPath("/WEB-INF/templates/");
		return result;
	}

	@Bean
	public ThymeleafTilesConfigurer tilesConfigurer() {
		final ThymeleafTilesConfigurer configurer = new ThymeleafTilesConfigurer();
		String[] defs = { "classpath:tiles-defs.xml" };
		configurer.setDefinitions(defs);
		return configurer;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setViewClass(ThymeleafTilesView.class);
		resolver.setTemplateEngine(templateEngine());
		resolver.setOrder(0);
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new CharacterEncodingFilter());
		return filterRegistrationBean;
	}

	@Autowired
	private RedisProperties properties;

	@Bean
	public OpenJedisConnectionFactory getJedisConnectionFactory() {
		OpenJedisConnectionFactory jcf = new OpenJedisConnectionFactory();
		jcf.setHostName(properties.getHost());
		jcf.setPassword(properties.getPassword());
		jcf.setPort(properties.getPort());
		jcf.afterPropertiesSet();
		return jcf;

	}

	@Bean(name = "normal_template")
	public RedisTemplate getRedisTemplate() {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(getJedisConnectionFactory());
		return template;
	}

	@Bean
	RedisMessageListenerContainer container(
			RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic(
				ApplicationConst.REDIS_PUBLISH_CHANNEL));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	Receiver receiver(CountDownLatch latch) {
		return new Receiver(latch);
	}

	@ConfigurationProperties(prefix = "com.over")
	@Bean
	SimpleConfig simpleConfig() {
		return new SimpleConfig();
	}

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}

	@Bean
	CacheManager cacheManager() {
		return new RedisCacheManager(template(getJedisConnectionFactory()));
	}

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {

		return new StringRedisTemplate(connectionFactory);
	}

	// @Bean
	// public Jedis initJedis() {
	// try {
	// return getJedisConnectionFactory().fetchJedisConnector();
	// } catch (Throwable throwable) {
	// return null;
	// }
	//
	// // return new JedisClient().getResource();
	// }

	/**
	 * リクエストのエンコーディングをUTF-8にします。
	 */
	private class CharacterEncodingFilter implements Filter {
		protected String encoding;

		public void init(FilterConfig filterConfig) throws ServletException {
			encoding = "UTF-8";
		}

		public void doFilter(ServletRequest servletRequest,
				ServletResponse servletResponse, FilterChain filterChain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			request.setCharacterEncoding(encoding);
			filterChain.doFilter(servletRequest, servletResponse);
		}

		@Override
		public void destroy() {
		}
	}

	@JsonFilter("FN")
	class PropertyFilterMixIn {

	}
}