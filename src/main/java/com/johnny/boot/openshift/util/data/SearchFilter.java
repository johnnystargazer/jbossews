package com.johnny.boot.openshift.util.data;

public class SearchFilter {

	public enum Operator {
		eq, cn, gt, lt, ge, le
	}

	public String field;
	public Object data;
	public Operator op;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

}
