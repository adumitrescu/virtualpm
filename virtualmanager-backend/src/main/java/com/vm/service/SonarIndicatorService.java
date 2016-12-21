package com.vm.service;


import com.vm.model.SonarIndicatorResponse;


public interface SonarIndicatorService {

	void synchSonar();

	SonarIndicatorResponse getSonarIndicators();

}
