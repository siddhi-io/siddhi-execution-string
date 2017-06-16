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

public class RepeatFunctionExtensionTestCase {
    static final Logger LOGGER = Logger.getLogger(RepeatFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testRepeatFunctionExtension() throws InterruptedException {
        LOGGER.info("RepeatFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , " +
                        "str:repeat(symbol, 3) as symbolRepeated3Times " +
                        "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("StRing 1StRing 1StRing 1", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("StrInG 2StrInG 2StrInG 2", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("Str 3Str 3Str 3", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"StRing 1", 700f, 100L});
        inputHandler.send(new Object[]{"StrInG 2", 60.5f, 200L});
        inputHandler.send(new Object[]{"Str 3", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testRepeatFunctionExtensionVariableIndex() throws InterruptedException {
        LOGGER.info("RepeatFunctionExtension - VariableIndex - TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, times int, volume long);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , " +
                        "str:repeat(symbol, times) as symbolRepeatedIndexTimes " +
                        "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("StRing 1StRing 1StRing 1", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("StrInG 2StrInG 2", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("Str 3Str 3Str 3Str 3Str 3Str 3", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"StRing 1", 3, 100L});
        inputHandler.send(new Object[]{"StrInG 2", 2, 200L});
        inputHandler.send(new Object[]{"Str 3", 6, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }
}
