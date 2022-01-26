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

package io.siddhi.extension.execution.string;

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.query.output.callback.QueryCallback;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.util.EventPrinter;
import io.siddhi.extension.execution.string.test.util.SiddhiTestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class EqualsIgnoreCaseFunctionExtensionTestCase {
    private static final Logger LOGGER = LogManager.getLogger(EqualsIgnoreCaseFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testContainsFunctionExtension() throws InterruptedException {
        LOGGER.info("EqualsIgnoreCaseFunctionExtensionTestCase TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, "
                + "volume long);";
        String query = ("@info(name = 'query1') " + "from inputStream "
                + "select symbol , str:equalsIgnoreCase(symbol, 'WSO2') as isEqualIgnoreCase "
                + "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager
                .createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event inEvent : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals(false, inEvent.getData(1));
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals(true, inEvent.getData(1));
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals(true, inEvent.getData(1));
                    }
                    if (count.get() == 4) {
                        AssertJUnit.assertEquals(false, inEvent.getData(1));
                    }
                    eventArrived = true;
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"wso2", 60.5f, 200L});
        inputHandler.send(new Object[]{"", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 4, count, 60000);
        AssertJUnit.assertEquals(4, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testContainsFunctionExtensionWithOneArgument() throws InterruptedException {
        LOGGER.info("EqualsIgnoreCaseFunctionExtensionTestCase TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, "
                + "volume long);";
        String query = ("@info(name = 'query1') " + "from inputStream "
                + "select symbol , str:equalsIgnoreCase(symbol) as isEqualIgnoreCase "
                + "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testContainsFunctionExtensionWithInvalidDataType() throws InterruptedException {
        LOGGER.info("EqualsIgnoreCaseFunctionExtensionTestCase TestCase with invalid datatype");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, "
                + "volume long);";
        String query = ("@info(name = 'query1') " + "from inputStream "
                + "select symbol , str:equalsIgnoreCase(price, 'WSO2') as isEqualIgnoreCase "
                + "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testContainsFunctionExtensionWithInvalidDataType1() throws InterruptedException {
        LOGGER.info("EqualsIgnoreCaseFunctionExtensionTestCase TestCase with invalid datatype");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, "
                + "volume long);";
        String query = ("@info(name = 'query1') " + "from inputStream "
                + "select symbol , str:equalsIgnoreCase(symbol, 1) as isEqualIgnoreCase "
                + "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}
