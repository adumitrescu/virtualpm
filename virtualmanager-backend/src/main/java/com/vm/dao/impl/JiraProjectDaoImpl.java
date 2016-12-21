/**
 * 
 */
package com.vm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.vm.dao.JiraProjectDao;
import com.vm.model.JiraProject;


/**
 * @author crpaslaru
 *
 */
@Repository("jiraProjectDao")
@PropertySource(value = { "classpath:mongo.properties" })
public class JiraProjectDaoImpl implements JiraProjectDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveProject(JiraProject jiraProject){
		mongoTemplate.save(jiraProject);
	}
}
