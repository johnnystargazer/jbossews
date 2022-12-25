package com.johnny.boot.openshift;

import java.net.URL;

public class TestResource {
	public static void main(String[] args) {
		URL xx = TestResource.class.getClassLoader().getResource(
				"/views/common/test/layout.jsp");
		System.out.println(xx);
	}
}
