/**
 * 
 */
package com.vm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author crpaslaru
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.vm")
@Import(AppConfigBackend.class)
public class AppConfig  extends WebMvcConfigurerAdapter{


	/**
	 * Property sources configurer
	 * 
	 * @return PropertySourcesPlaceholderConfigurer
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
