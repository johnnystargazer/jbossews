package com.johnny.boot.openshift.util.redis;

import javax.annotation.Resource;

import com.johnny.boot.openshift.domain.common.ApplicationConst;
import com.johnny.boot.openshift.domain.model.Message;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {
	@Resource(name = "template")
	private RedisTemplate redisTemplate;

	@RequestMapping("redis/")
	@ResponseBody
	public void publish(Message message, Model model) {
		redisTemplate.convertAndSend(ApplicationConst.REDIS_PUBLISH_CHANNEL,
				message.getContent());

	}
}
