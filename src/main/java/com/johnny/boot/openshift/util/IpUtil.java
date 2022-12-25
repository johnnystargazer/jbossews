package com.johnny.boot.openshift.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class IpUtil {
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getRequestUrl(HttpServletRequest request) {
		String x = request.getHeader("X-Forwarded-Host");
		if (StringUtils.hasLength(x)) {

			String url = request.getScheme() + "://" + x
					+ request.getRequestURI();
			return url;

		} else {
			return request.getRequestURL().toString();
		}
	}
}
