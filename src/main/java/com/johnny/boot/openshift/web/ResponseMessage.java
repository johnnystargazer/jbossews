package com.johnny.boot.openshift.web;

public class ResponseMessage {

	public enum State {
		SUCCESS, WARNNING, ERROR
	}

	public ResponseMessage() {
	}

	private State state;
	private String message;
	private Object entity;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getEntity() {
		return entity;
	}

	public ResponseMessage setEntity(Object entity) {
		this.entity = entity;
		return this;
	}

	public State getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	public static ResponseMessage one() {
		return new ResponseMessage();
	}

	public ResponseMessage success() {
		this.state = State.SUCCESS;
		return this;
	}

	public ResponseMessage warnning() {
		this.state = State.WARNNING;
		return this;
	}

	public ResponseMessage error() {
		this.state = State.ERROR;
		return this;
	}

	public ResponseMessage error(String message) {
		this.state = State.ERROR;
		this.message = message;
		return this;
	}

	public ResponseMessage message(String message) {
		this.message = message;
		return this;
	}
}
