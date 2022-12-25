package com.johnny.boot.openshift.util;

/**
 * 
 * @author johnny
 * 
 */

public class Response<T> {

	public enum State {
		SUCCESS, WARNNING, ERROR
	}

	public Response() {
	}

	private State state;
	private String message;
	private T entity;
	private String code;

	public String getCode() {
		return code;
	}

	public Response code(String code) {
		this.code = code;
		return this;
	}

	public T getEntity() {
		return entity;
	}

	public Response setEntity(T entity) {
		this.entity = entity;
		return this;
	}

	public State getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	public static Response one() {
		return new Response();
	}

	public Response success() {
		this.state = State.SUCCESS;
		return this;
	}

	public Response warnning() {
		this.state = State.WARNNING;
		return this;
	}

	public Response error() {
		this.state = State.ERROR;
		return this;
	}

	public Response error(String message) {
		this.state = State.ERROR;
		this.message = message;
		return this;
	}

	public Response message(String message) {
		this.message = message;
		return this;
	}
}
