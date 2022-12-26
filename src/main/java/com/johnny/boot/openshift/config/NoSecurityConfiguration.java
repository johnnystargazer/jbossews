package com.johnny.boot.openshift.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class NoSecurityConfiguration {
	@EnableWebSecurity
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		public void init(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/**");
		}
	}
}