/**
 * 
 */
package com.vm.model;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author crpaslaru
 *
 */
public class Versions {
	
	@Field("versionId")
	private String id;
	@Field("name")
	private String name;
	@Field("releaseDate")
	private String releaseDate;
	@Field("isReleased")
	private String isReleased;
	@Field("isArchived")
	private String isArchived;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getIsReleased() {
		return isReleased;
	}
	
	public void setIsReleased(String isReleased) {
		this.isReleased = isReleased;
	}
	
	public String getIsArchived() {
		return isArchived;
	}
	
	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}

	public Versions() {
	}

	public Versions(String id, String name, String releaseDate, String isReleased, String isArchived) {
		this.id = id;
		this.name = name;
		this.releaseDate = releaseDate;
		this.isReleased = isReleased;
		this.isArchived = isArchived;
	}
}
