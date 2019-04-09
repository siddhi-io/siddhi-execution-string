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

public class CharAtFunctionExtensionTestCase {
    static final Logger LOGGER = Logger.getLogger(CharAtFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testCharAtFunctionExtension() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,1) as charAt " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("B", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("S", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("Y", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L});
        inputHandler.send(new Object[]{"XYZ", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testCharAtFunctionExtensionVariableIndex() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension Test Case for Variable Index scenario");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long, times int);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,times) as charAt, " +
                "times " + "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("B", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("W", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("Z", event.getData(1));
                        eventArrived = true;
                    }
                }

            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L, 1});
        inputHandler.send(new Object[]{"WSO2", 60.5f, 200L, 0});
        inputHandler.send(new Object[]{"XYZ", 60.5f, 200L, 2});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testCharAtFunctionExtensionWithInvalidNoOfArguments() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase ,with invalid number of arguments");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,1,2) as charAt " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testCharAtFunctionExtensionWithInvalidDataType() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase with invalid data type");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(price,1) as charAt " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testCharAtFunctionExtensionWithInvalidDataType1() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase with invalid data type");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,price) as charAt " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test
    public void testCharAtFunctionExtensionWithNullValues() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase with null value");
        SiddhiManager siddhiManager = new SiddhiManager();
        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,1) as charAt " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime
                (inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{null, 700f, 100L, 1});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testCharAtFunctionExtensionWithNullValues1() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase with null value");
        SiddhiManager siddhiManager = new SiddhiManager();
        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long, time int);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,time) as charAt " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime
                (inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L, null});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testCharAtFunctionExtensionForIndexOutOfBoundException() throws InterruptedException {
        LOGGER.info("CharAtFunctionExtension TestCase for IndexoutOfBoundException case");
        SiddhiManager siddhiManager = new SiddhiManager();
        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:charAt(symbol,3) as charAt " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime
                (inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"IBM", 700f, 100L, 1});
        siddhiAppRuntime.shutdown();
    }
}
