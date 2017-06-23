/**
 * (C) Copyright IBM Corporation 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.byojackson;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 * This class sets up the application path, and explicitly registers the
 * resource class (WidgetResource) and the Jackson provider class
 * (JacksonJaxbJsonProvider).
 */
@ApplicationPath("/byoj")
public class BYOJacksonApp extends Application {

	// This method in unnecessary in most cases since these resource/providers
	// can be automatically discovered and registered.
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
        	classes.add(WidgetResource.class);
        	classes.add(JacksonJaxbJsonProvider.class);
        	return classes;
	}
}
