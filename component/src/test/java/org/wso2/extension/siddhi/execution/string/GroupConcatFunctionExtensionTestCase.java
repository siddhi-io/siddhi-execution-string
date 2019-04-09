/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

public class GroupConcatFunctionExtensionTestCase {
    static final Logger LOGGER = Logger.getLogger(GroupConcatFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testFunctionExtension1() throws InterruptedException {
        LOGGER.info("TestFunctionExtension1 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream " +
                "select symbol1, str:groupConcat(symbol1) as concatString1," +
                " str:groupConcat(symbol2) as concatString2," +
                " str:groupConcat(symbol3) as concatString3 " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("null", event.getData(2));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("null,$%$6", event.getData(2));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("null,$%$6,8JU^", event.getData(2));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AAA", null, "CCC"});
        inputHandler.send(new Object[]{"123", "$%$6", "789"});
        inputHandler.send(new Object[]{"D533", "8JU^", "XYZ"});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFunctionExtension2() throws InterruptedException {
        LOGGER.info("testFunctionExtension2 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1) as concatString1 " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("AAA,AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("AAA,BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 4) {
                        AssertJUnit.assertEquals("BBB,BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 5) {
                        AssertJUnit.assertEquals("BBB,CCC", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AAA", null, "CCC"});
        inputHandler.send(new Object[]{"AAA", "$%$6", "789"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"CCC", "8JU^", "XYZ"});
        SiddhiTestHelper.waitForEvents(100, 5, count, 60000);
        AssertJUnit.assertEquals(5, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFunctionExtension3() throws InterruptedException {
        LOGGER.info("testFunctionExtension3 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1, '-') as concatString1 " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("AAA-AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("AAA-BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 4) {
                        AssertJUnit.assertEquals("BBB-BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 5) {
                        AssertJUnit.assertEquals("BBB-CCC", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AAA", null, "CCC"});
        inputHandler.send(new Object[]{"AAA", "$%$6", "789"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"CCC", "8JU^", "XYZ"});
        SiddhiTestHelper.waitForEvents(100, 5, count, 60000);
        AssertJUnit.assertEquals(5, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFunctionExtension4() throws InterruptedException {
        LOGGER.info("testFunctionExtension4 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1, '-', true) as concatString1 " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("AAA-BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 4) {
                        AssertJUnit.assertEquals("BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 5) {
                        AssertJUnit.assertEquals("BBB-CCC", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AAA", null, "CCC"});
        inputHandler.send(new Object[]{"AAA", "$%$6", "789"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"CCC", "8JU^", "XYZ"});
        SiddhiTestHelper.waitForEvents(100, 5, count, 60000);
        AssertJUnit.assertEquals(5, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFunctionExtension5() throws InterruptedException {
        LOGGER.info("testFunctionExtension5 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1, '-', false, 'DESC') as concatString1 " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("AAA-AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("BBB-AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 4) {
                        AssertJUnit.assertEquals("BBB-BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 5) {
                        AssertJUnit.assertEquals("CCC-BBB", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AAA", null, "CCC"});
        inputHandler.send(new Object[]{"AAA", "$%$6", "789"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"CCC", "8JU^", "XYZ"});
        SiddhiTestHelper.waitForEvents(100, 5, count, 60000);
        AssertJUnit.assertEquals(5, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFunctionExtension6() throws InterruptedException {
        LOGGER.info("testFunctionExtension6 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1, '-', false, 'ASC') as concatString1 " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("AAA-AAA", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("AAA-BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 4) {
                        AssertJUnit.assertEquals("BBB-BBB", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 5) {
                        AssertJUnit.assertEquals("BBB-CCC", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AAA", null, "CCC"});
        inputHandler.send(new Object[]{"AAA", "$%$6", "789"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"BBB", "8JU^", "XYZ"});
        inputHandler.send(new Object[]{"CCC", "8JU^", "XYZ"});
        SiddhiTestHelper.waitForEvents(100, 5, count, 60000);
        AssertJUnit.assertEquals(5, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testFunctionExtension7() throws InterruptedException {
        LOGGER.info("testFunctionExtension7 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1, '-', false, 'ASC1') as concatString1 " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testFunctionExtension8() throws InterruptedException {
        LOGGER.info("testFunctionExtension8 TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') " +
                "from inputStream#window.length(2)" +
                "select symbol1, str:groupConcat(symbol1, '-', 'ASC', false) as concatString1 " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}
