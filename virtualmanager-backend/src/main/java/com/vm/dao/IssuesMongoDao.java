/**
 * 
 */
package com.vm.dao;

import java.util.List;

import com.vm.model.IndicatorResponseModel;
import com.vm.model.Issues;

/**
 * @author crpaslaru
 *
 */
public interface IssuesMongoDao {

	void saveIssues(Issues issues);

	List<IndicatorResponseModel> getAllIssuesGroupPeople();

	List<IndicatorResponseModel> getStoriesIssuesGroupPeople();

	List<IndicatorResponseModel> getTaskIssuesGroupPeople();

	void deleteAllIssues();

	List<Issues> getTimeSpentBug();

	List<Issues> getTimeSpentTask();

	List<Issues> getTimeSpentStories();

	List<Issues> getIndependentIssues();

	List<Issues> getEstimatedVsSpentTime();

	List<IndicatorResponseModel> getBugsVsStatus();

	List<IndicatorResponseModel> getBugsVsResolution();

	Long countBugs();

	Long countChangeRequest();

	Long countTechnicalUserStory();

	Long countUserStory();

	Long countQCId();

}
