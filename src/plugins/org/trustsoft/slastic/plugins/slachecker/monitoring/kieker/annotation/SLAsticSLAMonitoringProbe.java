package org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SLAsticSLAMonitoringProbe {
	// String context();
	int serviceId();
}
