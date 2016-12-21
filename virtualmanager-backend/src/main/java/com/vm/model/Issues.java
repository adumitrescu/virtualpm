/**
 * 
 */
package com.vm.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atlassian.jira.rest.client.api.domain.BasicComponent;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Version;

/**
 * @author crpaslaru
 *
 */
@Document(collection = "issues")
public class Issues {

	@Field("key")
	private String key;
	@Field("assigneeName")
	private String assigneeName;
	@Field("assigneeEmail")
	private String assigneeEmail;
	@Field("assigneeDisplayName")
	private String assigneeDisplayName;
	@Field("creationDate")
	private String creationDate;
	@Field("reporterName")
	private String reporterName;
	@Field("reporterEmail")
	private String reporterEmail;
	@Field("reporterDisplayName")
	private String reporterDisplayName;
	@Field("status")
	private String status;
	@Field("resolution")
	private String resolution;
	@Field("typeId")
	private String typeId;
	@Field("typeName")
	private String typeName;
	@Field("summary")
	private String summary;
	@Field("originalEstimateTime")
	private String originalEstimateTime;
	@Field("remainingEstimateTime")
	private String remainingEstimateTime;
	@Field("timeSpentEstimateTime")
	private String timeSpentEstimateTime;
	@Field("aggregateTimeSpent")
	private String aggregateTimeSpent;
	@Field("aggregateTimeOriginalEstimate")
	private String aggregateTimeOriginalEstimate;
	@Field("aggregateTimeEstimate")
	private String aggregateTimeEstimate;
	@Field("customFieldQC")
	private String customFieldQC;
	@Field("affectedVersions")
	private List<Versions> affectedVersions;
	@Field("components")
	private List<Components> components;
	
	public Issues(){
		
	}

	public Issues(Issue issue) {
		this.key = issue.getKey();
		if (issue.getAssignee() != null) {
			this.assigneeName = issue.getAssignee().getName();
			this.assigneeEmail = issue.getAssignee().getEmailAddress();
			this.assigneeDisplayName = issue.getAssignee().getDisplayName();
		}
		this.creationDate = issue.getCreationDate().toString();
		this.reporterName = issue.getReporter().getName();
		this.reporterEmail = issue.getReporter().getEmailAddress();
		this.reporterDisplayName = issue.getReporter().getDisplayName();
		this.status = issue.getStatus().getName();
		if(issue.getResolution()!=null){
			this.resolution = issue.getResolution().getName();
		}
		this.typeId = issue.getIssueType().getId().toString();
		this.typeName = issue.getIssueType().getName();
		this.summary = issue.getSummary();
		if (issue.getField("timespent").getValue() != null) {
			this.timeSpentEstimateTime = issue.getField("timespent").getValue().toString();
		}
		if (issue.getField("aggregatetimespent").getValue() != null) {    
			this.aggregateTimeSpent = issue.getField("aggregatetimespent").getValue().toString();
		}
		if (issue.getField("aggregatetimeoriginalestimate").getValue() != null) {   
			this.aggregateTimeOriginalEstimate = issue.getField("aggregatetimeoriginalestimate").getValue().toString();
		}
		if (issue.getField("aggregatetimeestimate").getValue() != null) {   
			this.aggregateTimeEstimate = issue.getField("aggregatetimeestimate").getValue().toString();
		}
		if (issue.getField("timeoriginalestimate").getValue() != null) {
			this.originalEstimateTime = issue.getField("timeoriginalestimate").getValue().toString();
		}
		if (issue.getField("timeestimate").getValue() != null) {
			this.remainingEstimateTime = issue.getField("timeestimate").getValue().toString();
		}
		if(issue.getField("customfield_10800").getValue()!=null){
			this.customFieldQC = issue.getField("customfield_10800").getValue().toString();
		}
		this.affectedVersions = transformToVersions(issue.getAffectedVersions());
		this.components = transformToComponents(issue.getComponents());
	}

	private List<Versions> transformToVersions(Iterable<Version> version) {
		List<Versions> list = new ArrayList<>();
		for (Iterator<Version> iter = version.iterator(); iter.hasNext();) {
			Version vers = iter.next();
			Versions versions = new Versions(vers.getId().toString(), vers.getName(), vers.getReleaseDate().toString(),
					String.valueOf(vers.isReleased()), String.valueOf(vers.isArchived()));
			list.add(versions);
			iter.remove();
		}
		return list;
	}

	private List<Components> transformToComponents(Iterable<BasicComponent> iterable) {
		List<Components> list = new ArrayList<>();
		Iterator<BasicComponent> iterator = iterable.iterator();
		for (Iterator<BasicComponent> iter = iterator; iterator.hasNext();) {
			BasicComponent comp = iter.next();
			Components components = new Components(comp.getId().toString(), comp.getName(), comp.getDescription());
			list.add(components);
			iter.remove();
		}
		return list;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getAssigneeEmail() {
		return assigneeEmail;
	}

	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}

	public String getAssigneeDisplayName() {
		return assigneeDisplayName;
	}

	public void setAssigneeDisplayName(String assigneeDisplayName) {
		this.assigneeDisplayName = assigneeDisplayName;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public String getReporterDisplayName() {
		return reporterDisplayName;
	}

	public void setReporterDisplayName(String reporterDisplayName) {
		this.reporterDisplayName = reporterDisplayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Versions> getAffectedVersions() {
		return affectedVersions;
	}

	public void setAffectedVersions(List<Versions> affectedVersions) {
		this.affectedVersions = affectedVersions;
	}

	public List<Components> getComponents() {
		return components;
	}

	public void setComponents(List<Components> components) {
		this.components = components;
	}

	
	public String getOriginalEstimateTime() {
		return originalEstimateTime;
	}

	
	public void setOriginalEstimateTime(String originalEstimateTime) {
		this.originalEstimateTime = originalEstimateTime;
	}

	
	public String getRemainingEstimateTime() {
		return remainingEstimateTime;
	}

	
	public void setRemainingEstimateTime(String remainingEstimateTime) {
		this.remainingEstimateTime = remainingEstimateTime;
	}

	
	public String getTimeSpentEstimateTime() {
		return timeSpentEstimateTime;
	}

	
	public void setTimeSpentEstimateTime(String timeSpentEstimateTime) {
		this.timeSpentEstimateTime = timeSpentEstimateTime;
	}

	
	public String getAggregateTimeSpent() {
		return aggregateTimeSpent;
	}

	
	public void setAggregateTimeSpent(String aggregateTimeSpent) {
		this.aggregateTimeSpent = aggregateTimeSpent;
	}

	
	public String getAggregateTimeOriginalEstimate() {
		return aggregateTimeOriginalEstimate;
	}

	
	public void setAggregateTimeOriginalEstimate(String aggregateTimeOriginalEstimate) {
		this.aggregateTimeOriginalEstimate = aggregateTimeOriginalEstimate;
	}

	
	public String getAggregateTimeEstimate() {
		return aggregateTimeEstimate;
	}

	
	public void setAggregateTimeEstimate(String aggregateTimeEstimate) {
		this.aggregateTimeEstimate = aggregateTimeEstimate;
	}

	
	public String getResolution() {
		return resolution;
	}

	
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	
	public String getCustomFieldQC() {
		return customFieldQC;
	}

	
	public void setCustomFieldQC(String customFieldQC) {
		this.customFieldQC = customFieldQC;
	}
}
