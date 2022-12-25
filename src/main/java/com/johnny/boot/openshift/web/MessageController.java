package com.johnny.boot.openshift.web;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.LinkDescriptionObject;
import com.fasterxml.jackson.module.jsonSchema.types.SimpleTypeSchema;
import com.johnny.boot.openshift.domain.model.Message;
import com.johnny.boot.openshift.json.HyperSchemaFactoryWrapper;
import com.johnny.boot.openshift.model.MessageModel;
import com.johnny.boot.openshift.repository.MessageRepository;
import com.johnny.boot.openshift.response.Page;
import com.johnny.boot.openshift.util.BeanHelper;
import com.johnny.boot.openshift.util.IpUtil;
import com.johnny.boot.openshift.util.Response;
import com.johnny.boot.openshift.util.data.DynamicSpecifications;
import com.johnny.boot.openshift.util.data.JqGridFilter;
import com.johnny.boot.openshift.util.json.JsonHelper;

/**
 * @author centos
 */
@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String socket(Model model) {
		return ".tiles.message";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void get(@PathVariable("id") Long id, HttpServletResponse response)
			throws IOException {
		try {
			Message entity = messageRepository.getOne(id);
			Response<MessageModel> json = new Response<MessageModel>();
			json.setEntity(BeanHelper.map(entity, MessageModel.class)).code(
					"200");
			JsonHelper.write(response.getOutputStream(), json);
		} catch (Throwable throwable) {
			String msg = ExceptionUtils.getRootCauseMessage(throwable);
			Response<MessageModel> json = new Response<MessageModel>();
			json.error().code("500");
			json.message(msg);
			response.setStatus(500);
			JsonHelper.write(response.getOutputStream(), json);
		}
	}

	static TypeReference<JqGridFilter> type = new TypeReference<JqGridFilter>() {
	};

	static TypeReference<Response<MessageModel>> RESPONSE = new TypeReference<Response<MessageModel>>() {
	};

	@ResponseBody
	@RequestMapping(value = "/schema/", method = RequestMethod.GET)
	public void schema(final HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper m = new ObjectMapper();
		Response<MessageModel> res = new Response<MessageModel>() {

		};
		HyperSchemaFactoryWrapper hyperSchemaFactoryWrapper = new HyperSchemaFactoryWrapper() {
			@Override
			protected void onNoJsonHyperSchema(JsonSchema schema, JavaType type) {
				String url = IpUtil.getRequestUrl(httpServletRequest);
				String startPath = url.substring(0, url.length() - 7);
				SimpleTypeSchema simpleTypeSchema = schema.asSimpleTypeSchema();

				LinkDescriptionObject linkDescriptionObjects = new LinkDescriptionObject();
				linkDescriptionObjects.setRel("self");
				linkDescriptionObjects.setHref(startPath + "{id}/GET");
				linkDescriptionObjects.setMethod("GET");
				linkDescriptionObjects.setEnctype("application/json");
				linkDescriptionObjects.setMediaType("application/json");
				simpleTypeSchema
						.setLinks(new LinkDescriptionObject[] { linkDescriptionObjects });

				simpleTypeSchema
						.setPathStart(url.substring(0, url.length() - 7));
			}
		};
		m.acceptJsonFormatVisitor(m.constructType(res.getClass()),
				hyperSchemaFactoryWrapper);
		JsonSchema jsonSchema = hyperSchemaFactoryWrapper.finalSchema();
		m.writerWithDefaultPrettyPrinter().writeValue(
				response.getOutputStream(), jsonSchema);
		response.getOutputStream().flush();

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ResponseBody
	public Response del(@RequestParam(value = "ids[]") Long[] ids) {
		try {
			for (Long id : ids) {
				messageRepository.delete(id);
			}
			return new Response();

		} catch (Throwable throwable) {
			return new Response();

		}
	}

	@ResponseBody
	@RequestMapping(value = "/{filter}/views/", method = RequestMethod.GET)
	public void views(@PathVariable("filter") String filter,
			Page<Message> page, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {

		JqGridFilter filters = JsonHelper.MAPPER.readValue(filter, type);
		PageRequest pageRequest;
		if (page.isOrderBySetted()) {
			pageRequest = new PageRequest(page.getPageNo() - 1,
					page.getPageSize(), page.sort());
		} else {
			pageRequest = new PageRequest(page.getPageNo() - 1,
					page.getPageSize());
		}

		Specification<Message> spec = DynamicSpecifications.bySearchFilter(
				filters.getFilters(), Message.class);
		org.springframework.data.domain.Page<Message> pageResult = messageRepository
				.findAll(spec, pageRequest);
		Iterator<Message> its = pageResult.iterator();
		while (its.hasNext()) {
			page.getResult().add(its.next());
		}
		page.setTotalCount(pageResult.getTotalElements());
		JsonHelper.write(response.getOutputStream(), page);
	}

}