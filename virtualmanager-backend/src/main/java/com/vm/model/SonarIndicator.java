/**
 * 
 */
package com.vm.model;

import java.util.Date;

import org.sonar.wsclient.services.Resource;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author crpaslaru
 *
 */
@Document(collection = "sonar")
public class SonarIndicator {

	@Field("sqaleRating")
	private Long sqaleRating;
	@Field("tehnicalDebtRatio")
	private Double tehnicalDebtRatio;
	@Field("debt")
	private Long debt;
	@Field("issues")
	private Long issues;
	@Field("blockerIssues")
	private Long blockerIssues;
	@Field("criticalIssues")
	private Long criticalIssues;
	@Field("majorIssues")
	private Long majorIssues;
	@Field("minorIssues")
	private Long minorIssues;
	@Field("infoIssues")
	private Long infoIssues;
	@Field("coverage")
	private Double coverage;
	@Field("currentDate")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date currentDate;

	public SonarIndicator() {

	}

	public SonarIndicator(Resource res) {
		this.tehnicalDebtRatio = res.getMeasure("sqale_debt_ratio").getValue();
		this.debt = res.getMeasure("sqale_index").getLongValue();
		this.sqaleRating = res.getMeasure("sqale_rating").getLongValue();
		this.coverage = res.getMeasure("coverage").getValue();
		this.majorIssues = res.getMeasure("major_violations").getLongValue();
		this.criticalIssues = res.getMeasure("critical_violations").getLongValue();
		this.blockerIssues = res.getMeasure("blocker_violations").getLongValue();
		this.minorIssues = res.getMeasure("minor_violations").getLongValue();
		this.infoIssues = res.getMeasure("info_violations").getLongValue();
		this.issues = res.getMeasure("violations").getLongValue();
		this.currentDate = new Date();
	}

	public Long getSqaleRating() {
		return sqaleRating;
	}

	public void setSqaleRating(Long sqaleRating) {
		this.sqaleRating = sqaleRating;
	}

	public Double getTehnicalDebtRatio() {
		return tehnicalDebtRatio;
	}

	public void setTehnicalDebtRatio(Double tehnicalDebtRatio) {
		this.tehnicalDebtRatio = tehnicalDebtRatio;
	}

	public Long getDebt() {
		return debt;
	}

	public void setDebt(Long debt) {
		this.debt = debt;
	}

	public Long getIssues() {
		return issues;
	}

	public void setIssues(Long issues) {
		this.issues = issues;
	}

	public Long getBlockerIssues() {
		return blockerIssues;
	}

	public void setBlockerIssues(Long blockerIssues) {
		this.blockerIssues = blockerIssues;
	}

	public Long getCriticalIssues() {
		return criticalIssues;
	}

	public void setCriticalIssues(Long criticalIssues) {
		this.criticalIssues = criticalIssues;
	}

	public Long getMajorIssues() {
		return majorIssues;
	}

	public void setMajorIssues(Long majorIssues) {
		this.majorIssues = majorIssues;
	}

	public Long getMinorIssues() {
		return minorIssues;
	}

	public void setMinorIssues(Long minorIssues) {
		this.minorIssues = minorIssues;
	}

	public Long getInfoIssues() {
		return infoIssues;
	}

	public void setInfoIssues(Long infoIssues) {
		this.infoIssues = infoIssues;
	}

	public Double getCoverage() {
		return coverage;
	}

	public void setCoverage(Double coverage) {
		this.coverage = coverage;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
