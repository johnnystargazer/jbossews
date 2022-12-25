package com.johnny.boot.openshift.config;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.johnny.boot.openshift.util.session.SessionUtil;
import com.johnny.boot.openshift.web.RootController;
import com.johnny.boot.openshift.web.session.SessionData;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/hello").withSockJS()
				.setInterceptors(new HttpSessionIdHandshakeInterceptor());
	}

}

class HttpSessionIdHandshakeInterceptor implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest()
					.getSession(false);
			if (session != null) {
				attributes.put("web-session", session.getId());
				SessionData sessionData = (SessionData) session
						.getAttribute(SessionUtil.DATA);
				attributes.put(RootController.WEB_SOCKET_SESSION_USERNAME,
						sessionData);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}
}