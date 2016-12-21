package com.vm.dao;

import java.util.List;

import com.vm.model.SonarIndicator;

public interface SonarIndicatorDao {

	void saveSonarIndicator(SonarIndicator sonarIndicator);

	List<SonarIndicator> getSonarInformation();

}
