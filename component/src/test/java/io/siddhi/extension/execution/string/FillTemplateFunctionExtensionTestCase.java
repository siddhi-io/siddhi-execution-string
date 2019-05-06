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

public class FillTemplateFunctionExtensionTestCase {
    static final Logger LOGGER = Logger.getLogger(FillTemplateFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testFillTemplate1() throws InterruptedException {
        LOGGER.info("FillTemplateFunctionExtension TestCase, where the arguments are valid strings");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (template string, symbol string, volume string, " +
                "price string);";

        String template = "The stock price of {{1}}. Volume of {{1}} is {{3}} and the stock price is {{2}}";
        String query = (
                "@info(name = 'query1') from inputStream select " +
                        "str:fillTemplate(template, symbol, price, volume) as msg " +
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
                        AssertJUnit.assertEquals("The stock price of WSO2. Volume of WSO2 is 100 and the stock price " +
                                "is " + "111.11", event.getData(0));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("The stock price of IBM. Volume of IBM is 200 and the stock price " +
                                "is " + "222.22", event.getData(0));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("The stock price of GOOGLE. Volume of GOOGLE is 300 and the stock " +
                                "price " +
                                "is " + "333.33", event.getData(0));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{template, "WSO2", "100", "111.11"});
        inputHandler.send(new Object[]{template, "IBM", "200", "222.22"});
        inputHandler.send(new Object[]{template, "GOOGLE", "300", "333.33"});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFillTemplate2() throws InterruptedException {
        LOGGER.info("FillTemplateFunctionExtension TestCase, where the arguments are valid strings");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (template string, symbol string, volume long, " +
                "price float);";

        String template = "The stock price of {{1}}. Volume of {{1}} is {{3}} and the stock price is {{2}}";
        String query = (
                "@info(name = 'query1') from inputStream select " +
                        "str:fillTemplate(template, symbol, price, volume) as msg " +
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
                        AssertJUnit.assertEquals("The stock price of WSO2. Volume of WSO2 is 100 and the stock price " +
                                "is " + "111.11", event.getData(0));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("The stock price of IBM. Volume of IBM is 200 and the stock price " +
                                "is " + "222.22", event.getData(0));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("The stock price of GOOGLE. Volume of GOOGLE is 300 and the stock " +
                                "price " +
                                "is " + "333.33", event.getData(0));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{template, "WSO2", 100L, 111.11f});
        inputHandler.send(new Object[]{template, "IBM", 200L, 222.22f});
        inputHandler.send(new Object[]{template, "GOOGLE", 300L, 333.33});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testFillTemplate3() throws InterruptedException {
        LOGGER.info("FillTemplateFunctionExtension TestCase, where number of arguments are invalid");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (template string, symbol string, volume string, " +
                "price string);";

        String query = (
                "@info(name = 'query1') from inputStream select " +
                        "str:fillTemplate(template) as msg " +
                        "insert into outputStream;"
        );

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
        siddhiAppRuntime.start();
        siddhiAppRuntime.shutdown();
    }

    @Test
    public void testFillTemplate4() throws InterruptedException {
        LOGGER.info("FillTemplateFunctionExtension TestCase, indexes in template are invalid");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (template string, symbol string, volume string, " +
                "price string);";

        String query = (
                "@info(name = 'query1') from inputStream select " +
                        "str:fillTemplate(template, symbol, price, volume) as msg " +
                        "insert into outputStream;"
        );

        String template = "The stock price of {{1}}. Volume of {{1}} is {{4}} and the stock price is {{2}}";

        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{template, "WSO2", "100", "111.11"});
        siddhiAppRuntime.shutdown();
    }
}
