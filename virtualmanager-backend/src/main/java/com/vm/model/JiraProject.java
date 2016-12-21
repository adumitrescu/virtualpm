/**
 * 
 */
package com.vm.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atlassian.jira.rest.client.api.domain.BasicProject;

/**
 * @author crpaslaru
 *
 */
@Document(collection = "jira_project")
public class JiraProject {

	@Field("key")
	private String key;
	@Field("name")
	private String name;
	@Field("url")
	private String url;
	
	public JiraProject(BasicProject project){
		this.key = project.getKey();
		this.name = project.getName();
		this.url = project.getSelf().toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
