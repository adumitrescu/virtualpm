package com.vm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vm.dao.IssuesMongoDao;
import com.vm.model.IndicatorResponseModel;
import com.vm.model.Issues;
import com.vm.service.IssuesMongoService;


@Service
public class IssuesMongoServiceImpl implements IssuesMongoService {

	@Autowired
	private IssuesMongoDao issuesMongoDao;

	@Override
	public void saveIssues(Issues issues) {
		issuesMongoDao.saveIssues(issues);
	}

	@Override
	public void deleteAllissues() {
		issuesMongoDao.deleteAllIssues();
	}

	@Override
	public List<IndicatorResponseModel> getBugsVsStatus() {
		return issuesMongoDao.getBugsVsStatus();
	}
	
	@Override
	public List<IndicatorResponseModel> getBugsVsResolution(){
		return issuesMongoDao.getBugsVsResolution();
	}

	@Override
	public Long getTimeSpentBug() {
		List<Issues> list = issuesMongoDao.getTimeSpentBug();
		Long result = 0L;
		for (Issues issues : list) {
			if (issues.getTimeSpentEstimateTime() != null) {
				result += new Long(issues.getTimeSpentEstimateTime());
			}
		}
		return result / 3600;
	}

	@Override
	public Long getTimeSpentTask() {
		List<Issues> list = issuesMongoDao.getTimeSpentTask();
		Long result = 0L;
		for (Issues issues : list) {
			if (issues.getTimeSpentEstimateTime() != null) {
				result += new Long(issues.getTimeSpentEstimateTime());
			}
		}
		return result / 3600;
	}

	@Override
	public Long getTimeSpentStories() {
		List<Issues> list = issuesMongoDao.getTimeSpentStories();
		Long result = 0L;
		for (Issues issues : list) {
			if (issues.getAggregateTimeSpent() != null) {
				result += new Long(issues.getAggregateTimeSpent());
			}
		}
		return result / 3600;
	}

	@Override
	public Long getIndependentIssues() {
		List<Issues> list = issuesMongoDao.getIndependentIssues();
		Long result = 0L;
		for (Issues issues : list) {
			if (issues.getAggregateTimeSpent() != null) {
				result += new Long(issues.getAggregateTimeSpent());
			}
		}
		return result / 3600;
	}

	@Override
	public List<IndicatorResponseModel> getTimeSpentIndicator() {
		List<IndicatorResponseModel> list = new ArrayList<>();
		IndicatorResponseModel bugsResponse = new IndicatorResponseModel();
		bugsResponse.setLabel("BUGS");
		bugsResponse.setCount(getTimeSpentBug());
		list.add(bugsResponse);
		IndicatorResponseModel storiesResponse = new IndicatorResponseModel();
		storiesResponse.setLabel("STORIES");
		storiesResponse.setCount(getTimeSpentStories());
		list.add(storiesResponse);
		IndicatorResponseModel independentIssuesResponse = new IndicatorResponseModel();
		independentIssuesResponse.setLabel("Independent Issues");
		independentIssuesResponse.setCount(getIndependentIssues());
		list.add(independentIssuesResponse);
		return list;
	}

	@Override
	public List<IndicatorResponseModel> getEstimatedVsSpentTime() {
		List<Issues> issues = issuesMongoDao.getEstimatedVsSpentTime();
		Long estimatedTime = 0L;
		Long spentTime = 0L;
		for (Issues issue : issues) {
			if (issue.getAggregateTimeOriginalEstimate() != null) {
				estimatedTime += new Long(issue.getAggregateTimeOriginalEstimate());
			}
			if (issue.getAggregateTimeOriginalEstimate() == null && issue.getOriginalEstimateTime() != null) {
				estimatedTime += new Long(issue.getOriginalEstimateTime());
			}
			if (issue.getAggregateTimeSpent() != null) {
				spentTime += new Long(issue.getAggregateTimeSpent());
			}
			if (issue.getAggregateTimeSpent() == null && issue.getTimeSpentEstimateTime() != null) {
				spentTime += new Long(issue.getTimeSpentEstimateTime());
			}
		}
		List<IndicatorResponseModel> list = new ArrayList<>();
		IndicatorResponseModel estimatedResponse = new IndicatorResponseModel();
		estimatedResponse.setLabel("Estimated time");
		estimatedResponse.setCount(estimatedTime / 3600);
		list.add(estimatedResponse);
		IndicatorResponseModel spentTimeResponse = new IndicatorResponseModel();
		spentTimeResponse.setLabel("Spent time");
		spentTimeResponse.setCount(spentTime / 3600);
		list.add(spentTimeResponse);
		return list;
	}
	
	@Override
	public List<IndicatorResponseModel> dashboardJiraIndicator(){
		Long bugs = issuesMongoDao.countBugs();
		Long changeReq = issuesMongoDao.countChangeRequest();
		Long qcId = issuesMongoDao.countQCId();
		Long techUserStory = issuesMongoDao.countTechnicalUserStory();
		Long userStory = issuesMongoDao.countUserStory();
		List<IndicatorResponseModel> list = new ArrayList<>();
		IndicatorResponseModel bugInd = new IndicatorResponseModel();
		bugInd.setCount(bugs);
		bugInd.setLabel("Bug");
		IndicatorResponseModel changeReqInd = new IndicatorResponseModel();
		changeReqInd.setCount(changeReq);
		changeReqInd.setLabel("Change Request");
		IndicatorResponseModel qcIdInd = new IndicatorResponseModel();
		qcIdInd.setCount(qcId);
		qcIdInd.setLabel("QC Id");
		IndicatorResponseModel techUserStoryInd = new IndicatorResponseModel();
		techUserStoryInd.setCount(techUserStory);
		techUserStoryInd.setLabel("Technical User Story");
		IndicatorResponseModel userStoryInd = new IndicatorResponseModel();
		userStoryInd.setCount(userStory);
		userStoryInd.setLabel("User Story");
		list.add(bugInd);
		list.add(userStoryInd);
		list.add(techUserStoryInd);
		list.add(changeReqInd);
		list.add(qcIdInd);
		return list;
	}
}
