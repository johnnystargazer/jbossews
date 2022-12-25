package com.johnny.boot.openshift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "com.johnny")
@EnableCaching
@EnableAutoConfiguration(exclude = { ThymeleafAutoConfiguration.class,
		FreeMarkerAutoConfiguration.class })
@EnableTransactionManagement
@ImportResource({ "classpath*:applicationContext.xml" })



@Configuration
//



public class Application {
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory =
				new TomcatEmbeddedServletContainerFactory();
		return factory;
	}
	public static void main(String[] args) throws Exception {
		System.setProperty("https.protocols", "TLSv1");

		SpringApplication.run(Application.class, args);
	}

}
