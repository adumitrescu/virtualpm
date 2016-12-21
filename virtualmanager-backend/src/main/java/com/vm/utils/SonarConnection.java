/**
 * 
 */
package com.vm.utils;

import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.Resource;
import org.sonar.wsclient.services.ResourceQuery;
import org.springframework.stereotype.Component;

/**
 * @author crpaslaru
 *
 */
@Component
public class SonarConnection {

	public Resource createSonarConnection() {

		Sonar sonar = Sonar.create("http://194.150.243.50:9000", "admin", "15gYLnRE");
		ResourceQuery query = new ResourceQuery(1L);
		query.setMetrics("sqale_debt_ratio", "sqale_index", "sqale_rating", "coverage", "major_violations",
				"critical_violations", "blocker_violations", "minor_violations", "info_violations", "violations");
		Resource r = (Resource) sonar.find(query);
		return r;
	}

}
