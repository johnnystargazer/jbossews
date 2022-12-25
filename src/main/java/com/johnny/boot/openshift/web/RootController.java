package com.johnny.boot.openshift.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.johnny.boot.openshift.application.SimpleConfig;
import com.johnny.boot.openshift.domain.model.Message;
import com.johnny.boot.openshift.repository.MessageRepository;
import com.johnny.boot.openshift.response.websocket.Greeting;
import com.johnny.boot.openshift.util.Response;
import com.johnny.boot.openshift.util.session.SessionUtil;
import com.johnny.boot.openshift.web.session.SessionData;
import com.johnny.boot.openshift.web.websocket.ChatMessage;

@Component
@Controller
public class RootController {
	@Autowired
	private SimpleConfig simpleConfig;
	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	private MessageRepository messageRepository;

	@RequestMapping(value = "/")
	public String index(Model model) {
		System.out.println(simpleConfig.getName());
		model.addAttribute("entity", simpleConfig);
		return ".tiles.index";
	}

	@RequestMapping(value = "/charts")
	public String charts(Model model) {
		model.addAttribute("entity", simpleConfig);
		return ".tiles.charts";
	}

	public static final String WEB_SOCKET_SESSION_USERNAME = "username";

	@MessageMapping("/hello")
	public void greeting(ChatMessage message,
			SimpMessageHeaderAccessor headerAccessor) throws Exception {
		SessionData name = (SessionData) headerAccessor.getSessionAttributes()
				.get(WEB_SOCKET_SESSION_USERNAME);
		Message m = new Message();
		m.setUser(name.getUsername());
		m.setContent(message.getContent());
		m.setTime(new Date());
		m.setSource("Websocket");
		messageRepository.save(m);
		template.convertAndSend("/topic/greetings",
				new Greeting(name.getUsername(), message.getContent()));
	}

	@RequestMapping(value = "/socket-client")
	public String socket(Model model) {
		return ".tiles.socket-client";
	}

	static Map<String, String> uses = Maps.newHashMap();
	{
		uses.put("johnny", "520");
		uses.put("test", "test1");
		uses.put("johnnywalee@gmail.com", "abcd1234");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Response login(@RequestParam("username") String name,
			@RequestParam("passsword") String password,
			HttpServletRequest httpServletRequest) {
		String pwd = uses.get(name);
		if (pwd == null || !pwd.equals(password)) {
			return new Response().code("401");

		} else {
			HttpSession session = httpServletRequest.getSession(true);
			SessionData sessionData = new SessionData();
			if (name.equals("johnny")) {
				sessionData.setUsername("miu miu");
			} else if (name.equals("johnnywalee@gmail.com")) {
				sessionData.setUsername("主");
			} else {
				sessionData.setUsername("visitor");
			}
			SessionUtil.persis(sessionData);
			return new Response().code("200");

		}

	}
}
