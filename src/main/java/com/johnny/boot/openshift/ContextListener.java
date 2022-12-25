package com.johnny.boot.openshift;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.johnny.boot.openshift.json.LoggerResponseConvert;

@Component
public class ContextListener implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	@Autowired
	private List<HttpMessageConverter<?>> messageConverters;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		List<HandlerMethodReturnValueHandler> all = requestMappingHandlerAdapter
				.getReturnValueHandlers();
		List<HandlerMethodReturnValueHandler> news = new ArrayList<HandlerMethodReturnValueHandler>();
		news.add(new LoggerResponseConvert(messageConverters));
		news.addAll(all);
		requestMappingHandlerAdapter.setReturnValueHandlers(news);
	}
}