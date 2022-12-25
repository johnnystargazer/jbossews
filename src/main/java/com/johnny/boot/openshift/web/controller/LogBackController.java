package com.johnny.boot.openshift.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.johnny.boot.openshift.json.WarpResponse;
import com.johnny.boot.openshift.web.LoggerException;
import com.johnny.boot.openshift.web.LoggerService;

@Controller
@WarpResponse
@RequestMapping("/logger")
public class LogBackController {
	@Autowired
	private LoggerService loggerService;

	@RequestMapping(value = "/reload/", method = RequestMethod.PUT)
	public void reloadDefaultConfiguration() throws LoggerException {
		loggerService.reloadDefaultConfiguration();
	}

	@RequestMapping(value = "/reload-file/{fileName}/", method = RequestMethod.PUT)
	public void reloadByFileName(@PathVariable("fileName") String fileName)
			throws LoggerException, FileNotFoundException {
		loggerService.reloadByFileName(fileName);
	}

	@RequestMapping(value = "/reload-file/", method = RequestMethod.POST)
	public void reloadByFileName(
			MultipartHttpServletRequest multipartHttpServletRequest)
			throws IOException {

		Map<String, MultipartFile> files = multipartHttpServletRequest
				.getFileMap();
		if (files.values().size() == 1) {
			MultipartFile file = files.values().iterator().next();

			File f = File.createTempFile("log", "xml");
			f.deleteOnExit();
			file.transferTo(f);
			loggerService.reloadByURL(f.toURI().toURL());
		}

	}

	@RequestMapping("/reload-url/{url}/")
	public void reloadByURL(@PathVariable("url") URL url)
			throws LoggerException {
		loggerService.reloadByURL(url);
	}

	@RequestMapping(value = "/level/{loggerName}/{level}/", method = RequestMethod.POST)
	public void setLoggerLevel(@PathVariable("loggerName") String loggerName,
			@PathVariable("level") String levelStr) {
		loggerService.setLoggerLevel(loggerName, levelStr);
	}

	@RequestMapping(value = "/level/{loggerName}/", method = RequestMethod.GET)
	public String getLoggerLevel(@PathVariable("loggerName") String loggerName) {
		return loggerService.getLoggerLevel(loggerName);
	}

	@RequestMapping(value = "/effective-level/{loggerName}/", method = RequestMethod.GET)
	public String getLoggerEffectiveLevel(
			@PathVariable("loggerName") String loggerName) {
		return loggerService.getLoggerEffectiveLevel(loggerName);
	}

	@RequestMapping(value = "/logger-list/", method = RequestMethod.GET)
	public List<String> getLoggerList() {
		return loggerService.getLoggerList();
	}

	@RequestMapping(value = "/statuses/", method = RequestMethod.GET)
	public List<String> getStatuses() {
		return loggerService.getStatuses();
	}

}
