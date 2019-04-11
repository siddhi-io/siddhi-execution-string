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

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.query.output.callback.QueryCallback;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.util.EventPrinter;
import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.extension.siddhi.execution.string.test.util.SiddhiTestHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class UnhexFunctionExtensionTestCase {
    protected static SiddhiManager siddhiManager;
    private static final Logger LOGGER = Logger.getLogger(UnhexFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);

    @BeforeMethod
    public void init() {
        count.set(0);
    }

    @Test
    public void testProcess() throws Exception {
        LOGGER.info("UnhexFunctionExtension TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string);";

        String eventFuseSiddhiApp = ("@info(name = 'query1') from InValueStream "
                + "select str:unhex(inValue) as unhexString "
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
                    AssertJUnit.assertEquals("MySQL", result);
                }
            }
        });
        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"4d7953514c"});
        SiddhiTestHelper.waitForEvents(1000, 1, count, 60000);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testProcess1() throws Exception {
        LOGGER.info("UnhexFunctionExtension TestCase with no arguments");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select str:unhex() as unhexString "
                + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testProcess2() throws Exception {
        LOGGER.info("UnhexFunctionExtension TestCase with invalid datatype");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue int);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select str:unhex(inValue) as unhexString "
                + "insert into OutMediationStream;");
        siddhiManager.createSiddhiAppRuntime(inValueStream + eventFuseExecutionPlan);
    }

    @Test
    public void testProcess3() throws Exception {
        LOGGER.info("UnhexFunctionExtension TestCase with null value");

        siddhiManager = new SiddhiManager();
        String inValueStream = "define stream InValueStream (inValue string);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InValueStream "
                + "select str:unhex(inValue) as unhexString "
                + "insert into OutMediationStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime
                (inValueStream + eventFuseExecutionPlan);

        InputHandler inputHandler = siddhiAppRuntime
                .getInputHandler("InValueStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{null});
        siddhiAppRuntime.shutdown();
    }
}
