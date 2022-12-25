package com.johnny.boot.openshift.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.johnny.boot.openshift.util.session.SessionUtil;
import com.johnny.boot.openshift.web.session.SessionException;

public class AccessInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			SessionUtil.getSessionData();
			return true;
		} catch (SessionException sessionException) {
			// response.setStatus(401);
			throw sessionException;
			// return false;
		}

	}
}
