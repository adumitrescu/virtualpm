/**
 * 
 */
package com.vm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.vm.dao.SonarIndicatorDao;
import com.vm.model.SonarIndicator;


/**
 * @author crpaslaru
 *
 */
@Repository("sonarIndicatorDao")
@PropertySource(value = { "classpath:mongo.properties" })
public class SonarIndicatorDaoImpl implements SonarIndicatorDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveSonarIndicator(SonarIndicator sonarIndicator){
		mongoTemplate.save(sonarIndicator);
	}
	
	@Override
	public List<SonarIndicator> getSonarInformation(){
		return mongoTemplate.findAll(SonarIndicator.class);
	}
}

