package com.johnny.boot.openshift.util.data;

import java.util.List;

/**
 * <p>
 * JqGridFilter.java
 * <li>Introduce</li> First draft at 26 Nov 2011
 * 
 * @author <a href="mailto:johnnywalee@gmail.com">Johnny</a>
 */

public class JqGridFilter {
	private String groupOp;
	private List<SearchFilter> rules;

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public void setRules(List<SearchFilter> rules) {
		this.rules = rules;
	}

	public List<SearchFilter> getFilters() {
		return rules;
	}

}
