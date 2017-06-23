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

import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the sample data object that is serialized to JSON and transferred
 * between the client and server.  Note that it uses Jackson annotations
 * to ignore fields or rename them in the JSON representation.
 */
public class Widget {
	static AtomicInteger nextID = new AtomicInteger(0);
	
	@JsonIgnore
	int internalID;
	@JsonProperty("id")
	int publicID;
	@JsonProperty
	String name;
	@JsonProperty("quantity")
	int qty;
	
	@Override
	public String toString() {
		return "Widget " + internalID + " " + publicID + " " + name + " " + qty;
	}
}
