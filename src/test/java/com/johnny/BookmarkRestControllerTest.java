package com.johnny;

import java.io.File;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnny.boot.openshift.Application;
import com.johnny.boot.openshift.repository.AccountRepository;
import com.johnny.boot.openshift.web.ResponseMessage;
import com.johnny.boot.openshift.web.ResponseMessage.State;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
public class BookmarkRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AccountRepository accountRepository;
	private ObjectMapper MAPPER;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext).build();
		MAPPER = new ObjectMapper();
		MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.NON_PRIVATE);
		MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

	}

	private ResponseMessage getConecnt(String url, Object... params)
			throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.get(url, params));
		MvcResult result = resultActions.andExpect(
				MockMvcResultMatchers.status().is(200)).andReturn();

		ResponseMessage responseMessage = MAPPER.readValue(result.getResponse()
				.getContentAsString(), ResponseMessage.class);
		return responseMessage;
	}

	private ResponseMessage postConecnt(String url, Object... params)
			throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.post(url, params));
		MvcResult result = resultActions.andExpect(
				MockMvcResultMatchers.status().is(200)).andReturn();

		ResponseMessage responseMessage = MAPPER.readValue(result.getResponse()
				.getContentAsString(), ResponseMessage.class);
		Assert.assertEquals(State.SUCCESS, responseMessage.getState());
		return responseMessage;
	}

	private ResponseMessage fileUpload(String url, File f) throws Exception {

		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
				.fileUpload(url).file(
						new MockMultipartFile("data", "aa.xml", "text/plain",
								Files.readAllBytes(f.toPath()))));
		MvcResult result = resultActions.andExpect(
				MockMvcResultMatchers.status().is(200)).andReturn();

		ResponseMessage responseMessage = MAPPER.readValue(result.getResponse()
				.getContentAsString(), ResponseMessage.class);
		Assert.assertEquals(State.SUCCESS, responseMessage.getState());
		return responseMessage;
	}

	@Test
	public void testSET() throws Exception {

		Assert.assertEquals("VOID",
				postConecnt("/logger/level/com.johnny/NULL/").getEntity());
		Assert.assertEquals("EMPTY", getConecnt("/logger/level/com.johnny/")
				.getEntity());
		Assert.assertEquals("VOID",
				postConecnt("/logger/level/com.johnny/DEBUG/").getEntity());
		Assert.assertEquals("DEBUG", getConecnt("/logger/level/com.johnny/")
				.getEntity());

	}

	@Test
	public void uploadFlle() throws Exception {
		fileUpload("/logger/reload-file/", new File("logback.xml"));
		Assert.assertEquals("WARN",
				getConecnt("/logger/level/org.springframework.web/")
						.getEntity());

	}
}