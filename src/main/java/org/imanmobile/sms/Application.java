package org.imanmobile.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
//@PropertySources(value = { @PropertySource(value = { "file:./application.properties" }) })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
