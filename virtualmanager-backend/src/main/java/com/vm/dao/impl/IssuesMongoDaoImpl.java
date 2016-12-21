package com.vm.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vm.dao.IssuesMongoDao;
import com.vm.model.IndicatorResponseModel;
import com.vm.model.Issues;

@Repository("issuesMongoDao")
@PropertySource(value = { "classpath:mongo.properties" })
public class IssuesMongoDaoImpl implements IssuesMongoDao {

	/**
	 * Injects MongoTemplate
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	// @Autowired
	// private Environment environment;

	@Override
	public void saveIssues(Issues issues) {
		mongoTemplate.save(issues);
	}
	
	@Override
	public void deleteAllIssues(){
		mongoTemplate.dropCollection(Issues.class);
	}

	@Override
	public List<IndicatorResponseModel> getAllIssuesGroupPeople() {
		Aggregation agg = newAggregation(Aggregation.group("assigneeName").count().as("count"), Aggregation.project(
				"count").and("assigneeName").previousOperation());

		AggregationResults<IndicatorResponseModel> aggResults = mongoTemplate.aggregate(agg, Issues.class,
				IndicatorResponseModel.class);
		return aggResults.getMappedResults();
	}
	
	@Override
	public List<IndicatorResponseModel> getStoriesIssuesGroupPeople() {
		Aggregation agg = newAggregation(Aggregation.match(Criteria.where("typeId").in("10100","10001")), group("assigneeName").count().as(
				"count"), project("count").and("assigneeName").previousOperation());

		AggregationResults<IndicatorResponseModel> aggResults = mongoTemplate.aggregate(agg, Issues.class,
				IndicatorResponseModel.class);
		return aggResults.getMappedResults();
	}
	
	@Override
	public List<IndicatorResponseModel> getTaskIssuesGroupPeople() {
		Aggregation agg = newAggregation(Aggregation.match(Criteria.where("typeId").is("10003")), group("assigneeName").count().as(
				"count"), project("count").and("assigneeName").previousOperation());

		AggregationResults<IndicatorResponseModel> aggResults = mongoTemplate.aggregate(agg, Issues.class,
				IndicatorResponseModel.class);
		return aggResults.getMappedResults();
	}
	
	@Override
	public List<IndicatorResponseModel> getBugsVsStatus() {
		Aggregation agg = newAggregation(Aggregation.match(Criteria.where("typeName").regex("Bug")), group("status").count().as(
				"count"), project("count").and("label").previousOperation());

		AggregationResults<IndicatorResponseModel> aggResults = mongoTemplate.aggregate(agg, Issues.class,
				IndicatorResponseModel.class);
		return aggResults.getMappedResults();
	}
	
	@Override
	public List<IndicatorResponseModel> getBugsVsResolution() {
		Aggregation agg = newAggregation(Aggregation.match(Criteria.where("typeName").regex("Bug")), group("resolution").count().as(
				"count"), project("count").and("label").previousOperation());

		AggregationResults<IndicatorResponseModel> aggResults = mongoTemplate.aggregate(agg, Issues.class,
				IndicatorResponseModel.class);
		return aggResults.getMappedResults();
	}
	
	@Override
	public List<Issues> getTimeSpentBug(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeName").regex("Bug"));
		return mongoTemplate.find(query, Issues.class);
	}
	
	@Override
	public List<Issues> getTimeSpentTask(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeId").is("10003"));
		return mongoTemplate.find(query, Issues.class);
	}
	
	@Override
	public List<Issues> getTimeSpentStories(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeName").regex("Story"));
		return mongoTemplate.find(query, Issues.class);
	}
	
	@Override
	public List<Issues> getIndependentIssues(){
		Query query = new Query();
		query.addCriteria(Criteria.where("summary").regex("Independent issues"));
		return mongoTemplate.find(query, Issues.class);
	}
	
	@Override
	public List<Issues> getEstimatedVsSpentTime(){
		Query query = new Query();
		return mongoTemplate.find(query, Issues.class);
	}
	
	@Override
	public Long countBugs(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeName").is("Bug"));
		return mongoTemplate.count(query, Issues.class);
	}
	
	@Override
	public Long countChangeRequest(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeName").regex("Change Request"));
		return mongoTemplate.count(query, Issues.class);
	}
	
	@Override
	public Long countTechnicalUserStory(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeName").regex("Technical User Story"));
		return mongoTemplate.count(query, Issues.class);
	}
	
	@Override
	public Long countUserStory(){
		Query query = new Query();
		query.addCriteria(Criteria.where("typeName").regex("User Story"));
		return mongoTemplate.count(query, Issues.class);
	}
	
	@Override
	public Long countQCId(){
		Query query = new Query();
		query.addCriteria(Criteria.where("customFieldQC").ne(null));
		return mongoTemplate.count(query, Issues.class);
	}

}
