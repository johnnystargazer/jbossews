package com.johnny.boot.openshift.util.session;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.johnny.boot.openshift.web.session.SessionData;
import com.johnny.boot.openshift.web.session.SessionException;

public class SessionUtil {
	public static final String DATA = "USER_DATA";

	public static SessionData getSessionData() throws SessionException {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		SessionData sessionData = (SessionData) sra.getRequest().getSession()
				.getAttribute(DATA);
		if (sessionData == null) {
			throw new SessionException();
		}
		return sessionData;

	}

	public static void persis(SessionData sessionData) {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		sra.getRequest().getSession().setAttribute(DATA, sessionData);

	}

}
