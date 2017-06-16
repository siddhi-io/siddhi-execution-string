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

public class SubstrFunctionExtensionTestCase {
    static final Logger LOGGER = Logger.getLogger(SubstrFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    /*
    * Test Case for str:substr(<string sourceText> , <int beginIndex>)
    * */
    @Test
    public void testSubstrFunctionExtension1() throws InterruptedException {
        LOGGER.info("SubstrFunctionExtension TestCase for str:substr(<string sourceText> , <int beginIndex>)");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , str:substr(symbol, 4) as substring " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("efghiJ KLMN", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("yut", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("o", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AbCDefghiJ KLMN", 700f, 100L});
        inputHandler.send(new Object[]{" ertyut", 60.5f, 200L});
        inputHandler.send(new Object[]{"Hello", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    /*
   * Test Case for str:substr(<string sourceText> , <int beginIndex>, <int length>)
   * */
    @Test
    public void testSubstrFunctionExtension2() throws InterruptedException {
        LOGGER.info("SubstrFunctionExtension TestCase for str:substr(<string sourceText> , " +
                "<int beginIndex>, <int length>)");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , " +
                "str:substr(symbol, 2, 4) as substring " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("CDef", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("rtyu", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("lloo", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"AbCDefghiJ KLMN", 700f, 100L});
        inputHandler.send(new Object[]{" ertyut", 60.5f, 200L});
        inputHandler.send(new Object[]{"Helloooo", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    /*
   * Test Case for str:substr(<string sourceText> , <string regex>)
   * */
    @Test
    public void testSubstrFunctionExtension3() throws InterruptedException {
        LOGGER.info("SubstrFunctionExtension TestCase for str:substr(<string sourceText> , <string regex>)");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol , " +
                "str:substr(symbol, '^WSO2(.*)') as substring " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("WSO2D efghiJ KLMN", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"WSO2D efghiJ KLMN", 700f, 100L});
        inputHandler.send(new Object[]{" ertWSO2yut", 60.5f, 200L});
        inputHandler.send(new Object[]{"Helloooo", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }

    /*
   * Test Case for str:substr(<string sourceText> , <string regex>, <int groupNumber>)
   * */
    @Test
    public void testSubstrFunctionExtension4() throws InterruptedException {
        LOGGER.info("SubstrFunctionExtension TestCase for str:substr(<string sourceText> , " +
                "<string regex>, <int groupNumber>)");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol string, price long, volume long);";
        String query = ("@info(name = 'query1') from inputStream select symbol ," +
                " str:substr(symbol, 'WSO2(.*)A(.*)', 2) as substring " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals(" ello", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("o", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("llo", event.getData(1));
                        eventArrived = true;
                    }
                }
            }
        });

        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("inputStream");
        siddhiAppRuntime.start();
        inputHandler.send(new Object[]{"hello hi hWSO2 hiA ello", 700f, 100L});
        inputHandler.send(new Object[]{"WSO2 hiA WSO2 hellAo", 60.5f, 200L});
        inputHandler.send(new Object[]{"WSO2 cep WSO2 XX E hi hA WSO2 heAllo", 60.5f, 200L});
        SiddhiTestHelper.waitForEvents(100, 3, count, 60000);
        AssertJUnit.assertEquals(3, count.get());
        AssertJUnit.assertTrue(eventArrived);
        siddhiAppRuntime.shutdown();
    }
}
