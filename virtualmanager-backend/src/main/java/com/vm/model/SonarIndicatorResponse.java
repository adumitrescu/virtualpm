/**
 * 
 */
package com.vm.model;

import java.util.List;

/**
 * @author crpaslaru
 *
 */
public class SonarIndicatorResponse {

	private List<Long> infoIssues;
	private List<Long> minorIssues;
	private List<Long> majorIssues;
	private List<Long> criticalIssues;
	private List<Long> blockerIssues;
	private List<Long> sqaleRating;
	private List<Double> tehnicalDebtRatio;
	private List<Long> debt;
	private List<Long> issues;
	private List<Double> coverage;

	public List<Long> getInfoIssues() {
		return infoIssues;
	}

	public void setInfoIssues(List<Long> infoIssues) {
		this.infoIssues = infoIssues;
	}

	public List<Long> getMinorIssues() {
		return minorIssues;
	}

	public void setMinorIssues(List<Long> minorIssues) {
		this.minorIssues = minorIssues;
	}

	public List<Long> getMajorIssues() {
		return majorIssues;
	}

	public void setMajorIssues(List<Long> majorIssues) {
		this.majorIssues = majorIssues;
	}

	public List<Long> getCriticalIssues() {
		return criticalIssues;
	}

	public void setCriticalIssues(List<Long> criticalIssues) {
		this.criticalIssues = criticalIssues;
	}

	public List<Long> getBlockerIssues() {
		return blockerIssues;
	}

	public void setBlockerIssues(List<Long> blockerIssues) {
		this.blockerIssues = blockerIssues;
	}

	public List<Long> getSqaleRating() {
		return sqaleRating;
	}

	public void setSqaleRating(List<Long> sqaleRating) {
		this.sqaleRating = sqaleRating;
	}

	public List<Double> getTehnicalDebtRatio() {
		return tehnicalDebtRatio;
	}

	public void setTehnicalDebtRatio(List<Double> tehnicalDebtRatio) {
		this.tehnicalDebtRatio = tehnicalDebtRatio;
	}

	public List<Long> getDebt() {
		return debt;
	}

	public void setDebt(List<Long> debt) {
		this.debt = debt;
	}

	public List<Long> getIssues() {
		return issues;
	}

	public void setIssues(List<Long> issues) {
		this.issues = issues;
	}

	public List<Double> getCoverage() {
		return coverage;
	}

	public void setCoverage(List<Double> coverage) {
		this.coverage = coverage;
	}

}
