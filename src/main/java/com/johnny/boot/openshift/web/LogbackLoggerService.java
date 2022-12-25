package com.johnny.boot.openshift.web;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusListenerAsList;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;

@Component
public class LogbackLoggerService implements LoggerContextListener,
		LoggerService {

	public static final String EMPTY = "";
	private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
	private LoggerContext loggerContext = (LoggerContext) LoggerFactory
			.getILoggerFactory();
	boolean started;

	public LogbackLoggerService() {
		loggerContext.addListener(this);
	}

	public void reloadDefaultConfiguration() {
		ContextInitializer ci = new ContextInitializer(loggerContext);
		URL url = ci.findURLOfDefaultConfigurationFile(true);
		reloadByURL(url);
	}

	public void reloadByFileName(@PathVariable("fileName") String fileName) {
		File f = new File(fileName);
		if (f.exists() && f.isFile()) {
			URL url;
			try {
				url = f.toURI().toURL();
				reloadByURL(url);
			} catch (MalformedURLException e) {
				throw new RuntimeException(
						"Unexpected MalformedURLException occured. See nexted cause.",
						e);
			}

		} else {
			String errMsg = "Could not find [" + fileName + "]";
			throw new LoggerException(errMsg);
		}
	}

	private void addStatusListener(StatusListener statusListener) {
		StatusManager sm = loggerContext.getStatusManager();
		sm.add(statusListener);
	}

	private void removeStatusListener(StatusListener statusListener) {
		StatusManager sm = loggerContext.getStatusManager();
		sm.remove(statusListener);
	}

	public void reloadByURL(@PathVariable("url") URL url) {
		StatusListenerAsList statusListenerAsList = new StatusListenerAsList();

		addStatusListener(statusListenerAsList);
		loggerContext.reset();
		addStatusListener(statusListenerAsList);

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);

			configurator.doConfigure(url);
		} catch (JoranException joranException) {
			throw new LoggerException("reload fail ", joranException);

		} finally {
			removeStatusListener(statusListenerAsList);

			StatusPrinter.print(statusListenerAsList.getStatusList());

		}
	}

	@RequestMapping(value = "/logger/{loggerName}/{level}/", method = RequestMethod.POST)
	@ResponseBody
	public void setLoggerLevel(@PathVariable("loggerName") String loggerName,
			@PathVariable("level") String levelStr) {
		if (loggerName == null) {
			return;
		}
		if (levelStr == null) {
			return;
		}
		loggerName = loggerName.trim();
		levelStr = levelStr.trim();

		LoggerContext lc = (LoggerContext) loggerContext;

		Logger logger = lc.getLogger(loggerName);
		if ("null".equalsIgnoreCase(levelStr)) {
			logger.setLevel(null);
		} else {
			Level level = Level.toLevel(levelStr, null);
			if (level != null) {
				logger.setLevel(level);
			}
		}
	}

	public String getLoggerLevel(String loggerName) {
		if (loggerName == null) {
			return EMPTY;
		}

		loggerName = loggerName.trim();

		LoggerContext lc = (LoggerContext) loggerContext;
		Logger logger = lc.exists(loggerName);
		if (logger != null && logger.getLevel() != null) {
			return logger.getLevel().toString();
		} else {
			return EMPTY;
		}
	}

	public String getLoggerEffectiveLevel(
			@PathVariable("loggerName") String loggerName) {
		if (loggerName == null) {
			return EMPTY;
		}

		loggerName = loggerName.trim();

		LoggerContext lc = (LoggerContext) loggerContext;
		Logger logger = lc.exists(loggerName);
		if (logger != null) {
			return logger.getEffectiveLevel().toString();
		} else {
			return EMPTY;
		}
	}

	public List<String> getLoggerList() {
		LoggerContext lc = (LoggerContext) loggerContext;
		List<String> strList = new ArrayList<String>();
		Iterator<Logger> it = lc.getLoggerList().iterator();
		while (it.hasNext()) {
			Logger log = it.next();
			strList.add(log.getName());
		}
		return strList;
	}

	public List<String> getStatuses() {
		List<String> list = new ArrayList<String>();
		Iterator<Status> it = loggerContext.getStatusManager()
				.getCopyOfStatusList().iterator();
		while (it.hasNext()) {
			list.add(it.next().toString());
		}
		return list;
	}

	public void onStop(LoggerContext context) {

		stop();
	}

	public void onLevelChange(Logger logger, Level level) {
		logger.info("set log level {} to {}", logger, level);
	}

	public void onReset(LoggerContext context) {
		logger.info("reset context ");
	}

	public boolean isResetResistant() {
		return true;
	}

	private void clearFields() {

		loggerContext = null;
	}

	private void stop() {
		started = false;
		clearFields();
	}

	public void onStart(LoggerContext context) {
		// nop
	}
}
