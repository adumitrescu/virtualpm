/**
 * 
 */
package com.vm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sonar.wsclient.services.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vm.dao.SonarIndicatorDao;
import com.vm.model.SonarIndicator;
import com.vm.model.SonarIndicatorResponse;
import com.vm.service.SonarIndicatorService;
import com.vm.utils.SonarConnection;


/**
 * @author crpaslaru
 *
 */
@Service("sonarIndicatorService")
public class SonarIndicatorServiceImpl implements SonarIndicatorService{
	
	@Autowired
	@Qualifier("sonarIndicatorDao")
	private SonarIndicatorDao sonarIndicatorDao;
	
	@Autowired
	private SonarConnection sonarConnection;
	
	private void saveSonarIndicator(SonarIndicator sonarIndicator){
		sonarIndicatorDao.saveSonarIndicator(sonarIndicator);
	}
	
	@Override
	public void synchSonar(){
		Resource res = sonarConnection.createSonarConnection();
		SonarIndicator sonarIndicator =  new SonarIndicator(res);
		saveSonarIndicator(sonarIndicator);
	}
	
	@Override
	public SonarIndicatorResponse getSonarIndicators(){
		SonarIndicatorResponse response = new SonarIndicatorResponse();
		List<Long> infoIssues= new ArrayList<>();
		List<Long> minorIssues= new ArrayList<>();
		List<Long> majorIssues= new ArrayList<>();
		List<Long> criticalIssues= new ArrayList<>();
		List<Long> blockerIssues= new ArrayList<>();
		List<Long> sqaleRating= new ArrayList<>();
		List<Double> tehnicalDebtRatio= new ArrayList<>();
		List<Long> debt= new ArrayList<>();
		List<Long> issues= new ArrayList<>();
		List<Double> coverage= new ArrayList<>();
		for(SonarIndicator indicator : sonarIndicatorDao.getSonarInformation()){
			infoIssues.add(indicator.getInfoIssues());
			minorIssues.add(indicator.getMinorIssues());
			majorIssues.add(indicator.getMajorIssues());
			criticalIssues.add(indicator.getCriticalIssues());
			blockerIssues.add(indicator.getBlockerIssues());
			sqaleRating.add(indicator.getSqaleRating());
			tehnicalDebtRatio.add(indicator.getTehnicalDebtRatio());
			debt.add((long)indicator.getDebt()/60);
			issues.add(indicator.getIssues());
			coverage.add(indicator.getCoverage());
		}
		response.setBlockerIssues(blockerIssues);
		response.setCoverage(coverage);
		response.setCriticalIssues(criticalIssues);
		response.setDebt(debt);
		response.setInfoIssues(infoIssues);
		response.setIssues(issues);
		response.setMajorIssues(majorIssues);
		response.setMinorIssues(minorIssues);
		response.setSqaleRating(sqaleRating);
		response.setTehnicalDebtRatio(tehnicalDebtRatio);
		return response;
	}
	
}
