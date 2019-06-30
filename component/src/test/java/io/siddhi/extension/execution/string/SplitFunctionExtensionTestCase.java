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
import org.apache.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class SplitFunctionExtensionTestCase {

    private static final Logger LOGGER = Logger.getLogger(SplitFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testSplitFunctionExtension() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, " +
                "volume long);";
        String query = ("@info(name = 'query1') " + "from inputStream " + "select symbol , " +
                "str:split(symbol, '_', 1) as splitText " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("IBM", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("WSO2", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("XYZ", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"Prod_IBM", 700f, 100L});
        inputHandler.send(new Object[]{"Prod_WSO2_", 60.5f, 200L});
        inputHandler.send(new Object[]{"Prod_XYZ", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(500, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSplitFunctionExtension2() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase, where both splitCharacter and index are variables.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , " +
                        "str:split(symbol, splitCharacter, index) as splitText " +
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
                        AssertJUnit.assertEquals("DELL", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("CEP", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("SIDDHI engine", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"DELL/IBM/HP/", "/", 0});
        inputHandler.send(new Object[]{"WSO2 CEP and IS", " ", 1});
        inputHandler.send(new Object[]{"WSO2_CEP_SIDDHI engine", "_", 2});
        SiddhiTestHelper.waitForEvents(500, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testSplitFunctionExtension3() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol ,"
                        + " str:split(symbol, splitCharacter) as splitText " +
                        "insert into outputStream;"
        );
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testSplitFunctionExtension4() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol int, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , "
                        + "str:split(symbol, splitCharacter, index) as splitText " +
                        "insert into outputStream;"
        );
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testSplitFunctionExtension5() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter int, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , str:split(symbol, splitCharacter, index) "
                        + "as splitText " +
                        "insert into outputStream;"
        );
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testSplitFunctionExtension6() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index string);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , "
                        + "str:split(symbol, splitCharacter, index) as splitText " +
                        "insert into outputStream;"
        );
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test
    public void testSplitFunctionExtension7() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase, with null value.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , "
                        + "str:split(symbol, splitCharacter, index) as splitText " +
                        "insert into outputStream;"
        );
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{null, "/", 0});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSplitFunctionExtension8() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase, with null value.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , "
                        + "str:split(symbol, splitCharacter, index) as splitText " +
                        "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"DELL/IBM/HP/", null, 0});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSplitFunctionExtension9() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase with null value.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , "
                        + "str:split(symbol, splitCharacter, index) as splitText " +
                        "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"DELL/IBM/HP/", "/", null});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testSplitFunctionExtension10() throws InterruptedException {
        LOGGER.info("SplitFunctionExtensionTestCase TestCase.");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, splitCharacter string, index int);";

        String query = (
                "@info(name = 'query1') from inputStream select symbol , "
                        + "str:split(symbol, splitCharacter, index) as splitText " +
                        "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"DELL/IBM/HP/", "/", 5});
        siddhiAppRuntime.shutdown();
    }
}
