/*
 * Copyright (c)  2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.extension.siddhi.execution.string;

import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.extension.siddhi.execution.string.test.util.SiddhiTestHelper;
import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;

import java.util.concurrent.atomic.AtomicInteger;

public class HexFunctionExtensionTestCase {
    protected static SiddhiManager siddhiManager;
    private static final Logger LOGGER = Logger.getLogger(HexFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);

    @BeforeMethod
    public void init() {
        count.set(0);
    }

    @Test
    public void testProcess() throws Exception {
        LOGGER.info("HexFunctionExtension TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string);";

        String eventFuseSiddhiApp = ("@info(name = 'query1') from InValueStream "
                + "select str:hex(inValue) as hexString "
                + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseSiddhiApp);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents,
                                Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                String result;
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    result = (String) event.getData(0);
                    AssertJUnit.assertEquals("4d7953514c", result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"MySQL"});
        SiddhiTestHelper.waitForEvents(1000, 1, count, 60000);
        siddhiAppRuntime.shutdown();
    }
}
