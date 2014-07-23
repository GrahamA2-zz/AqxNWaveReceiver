package uk.co.aquamatix;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
	    PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(Application.class, args);
	}
}
