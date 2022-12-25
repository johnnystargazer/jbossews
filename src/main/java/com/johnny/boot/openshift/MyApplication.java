package com.johnny.boot.openshift;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

//@SpringBootApplication
//@Component
//@ComponentScan
//@Configuration
//@EnableAutoConfiguration
public class MyApplication extends SpringBootServletInitializer implements
		WebApplicationInitializer {
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {

		System.out.println("starting..");
		application.sources(Application.class);
		// Customize the application or call application.sources(...) to add
		// sources
		// Since our example is itself a @Configuration class we actually don't
		// need to override this method.
		return application;
	}
}