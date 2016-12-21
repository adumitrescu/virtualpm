package com.vm.service;

import java.util.List;

import com.vm.model.IndicatorResponseModel;
import com.vm.model.Issues;


public interface IssuesMongoService {

	void saveIssues(Issues issues);

	List<IndicatorResponseModel> getBugsVsStatus();

	void deleteAllissues();

	Long getTimeSpentBug();

	Long getTimeSpentTask();

	Long getTimeSpentStories();

	List<IndicatorResponseModel> getTimeSpentIndicator();

	Long getIndependentIssues();

	List<IndicatorResponseModel> getEstimatedVsSpentTime();

	List<IndicatorResponseModel> getBugsVsResolution();

	List<IndicatorResponseModel> dashboardJiraIndicator();

}
