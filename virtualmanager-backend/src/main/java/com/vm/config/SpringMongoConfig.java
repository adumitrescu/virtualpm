package com.vm.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClientOptions.Builder;

@Configuration
@PropertySource(value = { "classpath:mongo.properties" })
public class SpringMongoConfig {

	/**
	 * inject environment
	 */
	@Autowired
	private Environment environment;

	/**
	 * Returns mongo template
	 * 
	 * @return {@link MongoTemplate}
	 * @throws UnknownHostException
	 *             - exception
	 */

	@Bean(name = "mongoTemplate")
	public MongoTemplate getMongoTemplate() throws UnknownHostException {
		Builder mco = new MongoClientOptions.Builder();
		String textUri = environment.getRequiredProperty("mongo.uri");
		MongoClientURI uri = new MongoClientURI(textUri, mco);
		return new MongoTemplate(new SimpleMongoDbFactory(uri));
	}
}
