package com.johnny.boot.openshift.web;

import java.net.URL;
import java.util.List;

public interface LoggerService {

	void reloadDefaultConfiguration();

	void reloadByFileName(String fileName);

	void reloadByURL(URL url);

	void setLoggerLevel(String loggerName, String levelStr);

	String getLoggerLevel(String loggerName);

	String getLoggerEffectiveLevel(String loggerName);

	List<String> getLoggerList();

	List<String> getStatuses();
}
