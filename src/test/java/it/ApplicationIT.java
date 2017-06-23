/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package it;

import org.junit.Test;

public class ApplicationIT extends EndpointHelper {

    @Test
    public void testReadExistingWidget() {
        testEndpoint("/byoj/widget/0", "{\"name\":\"Invisibility Cloak\",\"id\":0,\"quantity\":7}");
    }

    @Test
    public void testCreateNewWidget() {
        testEndpoint("/byoj/widget/create?name=Wand&qty=5", "{\"name\":\"Wand\",\"id\":1,\"quantity\":5}");
        testEndpoint("/byoj/widget/1", "{\"name\":\"Wand\",\"id\":1,\"quantity\":5}");
        testEndpoint("/byoj/widget/viaClient/1", "{\"name\":\"Wand\",\"id\":1,\"quantity\":5}");
    }

    @Test
    public void testReadExistingWidgetUsingClientAPI() {
        testEndpoint("/byoj/widget/viaClient/0", "{\"name\":\"Invisibility Cloak\",\"id\":0,\"quantity\":7}");
    }


}
