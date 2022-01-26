/*
 * Copyright (c)  2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
import io.siddhi.core.util.SiddhiTestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenizerStreamProcessorExtensionTestCase {
    private static final Logger LOGGER = LogManager.getLogger(TokenizerStreamProcessorExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testTokenizerExtension() throws InterruptedException {
        LOGGER.info("TokenizerStreamProcessorExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (data string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(data, \"\\s|\\'|,\")" +
                "insert all events into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertArrayEquals(new Object[]{"You're a good girl", "You"},
                                inEvents[0].getData());
                        eventArrived = true;
                    }
                    if (count.get() == 6) {
                        AssertJUnit.assertArrayEquals(new Object[]{"Android,Windows8,iOS,Symbian", "Android"},
                                inEvents[0].getData());
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"You're a good girl"});
        inputHandler.send(new Object[]{"Android,Windows8,iOS,Symbian"});
        SiddhiTestHelper.waitForEvents(100, 9, count, 60000);
        AssertJUnit.assertEquals(eventArrived, true);
        AssertJUnit.assertEquals(9, count.get());
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testTokenizerExtensionWithInvalidSyntaxForRegex() {
        LOGGER.info("TokenizerStreamProcessorExtension with invalid syntax for Regex");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (data string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(data, \"\\s|\\'|,|*\")" +
                "insert all events into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testTokenizerExtensionWithInvalidNoOfArguments() {
        LOGGER.info("TokenizerExtension TestCase with invalid number of arguments");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (text2 string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(text2)" +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testTokenizerExtensionWithInvalidDataTypeforText() {
        LOGGER.info("TokenizerExtension TestCase with invalid data type for text");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (text2 double);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(text2, ' ') " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testTokenizerExtensionWithInvalidDataTypeforDelimiter() {
        LOGGER.info("TokenizerExtension TestCase with invalid data type for delimiter");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (text2 double);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(text2, 1) " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }

    @Test
    public void testTokenizerExtensionWithNullValue() throws InterruptedException {
        LOGGER.info("TokenizerExtension TestCase with null value");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (text2 string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(text2, ' ')  " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{null});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testTokenizerExtensionWithDistincts() throws InterruptedException {
        LOGGER.info("TokenizerExtension TestCase with distinct flag");
        count.set(0);
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (data string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(data, ',', true) " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            String[] expectedValues = new String[]{"Android", "iOS", "Symbian"};

            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                count.incrementAndGet();
                AssertJUnit.assertArrayEquals(new Object[]{"Android,iOS,Android,Symbian",
                        expectedValues[count.get() - 1]}, inEvents[0].getData());
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"Android,iOS,Android,Symbian"});
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testTokenizerExtensionWithoutDistincts() throws InterruptedException {
        LOGGER.info("TokenizerExtension TestCase without distinct flag");
        count.set(0);
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (data string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(data, ',') " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            String[] expectedValues = new String[]{"Android", "iOS", "Android", "Symbian"};

            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                count.incrementAndGet();
                AssertJUnit.assertArrayEquals(new Object[]{"Android,iOS,Android,Symbian",
                        expectedValues[count.get() - 1]}, inEvents[0].getData());
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"Android,iOS,Android,Symbian"});
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testTokenizerExtensionWithInvalidDataTypeForDistinct() {
        LOGGER.info("TokenizerExtension TestCase with invalid data type for distinct");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (data string);";
        String query = ("@info(name = 'query1') from inputStream#str:tokenize(data, ',', 'abc') " +
                "insert into outputStream;");
        siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}
