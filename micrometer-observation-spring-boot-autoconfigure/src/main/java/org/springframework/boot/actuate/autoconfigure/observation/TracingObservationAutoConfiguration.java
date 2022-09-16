/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.autoconfigure.observation;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.handler.TracingObservationHandler;

import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for the Micrometer Observation API.
 *
 * @author Moritz Halbritter
 * @since 3.0.0
 */
@AutoConfiguration(after = CompositeMeterRegistryAutoConfiguration.class, before = ObservationAutoConfiguration.class)
@ConditionalOnClass(ObservationRegistry.class)
public class TracingObservationAutoConfiguration {

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(TracingObservationHandler.class)
	static class TracingConfiguration {

		// TODO: If moved back the name was tracingObservationHandlerGrouping
		@Bean
		@Primary // TODO: Should be removed when tracing removed from Boot
		TracingObservationHandlerGrouping micrometerTracingObservationHandlerGrouping() {
			return new TracingObservationHandlerGrouping();
		}

	}

}
