package com.johnny.boot.openshift.response.websocket;

public class Greeting {
	private String content;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Greeting(String name, String content) {
		this.content = content;
		this.name = name;

	}

	public String getContent() {
		return content;
	}
}
