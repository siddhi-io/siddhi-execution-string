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

public class ConcatFunctionExtensionTestCase {
    static final Logger LOGGER = Logger.getLogger(ConcatFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @BeforeMethod
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testCoalesceFunctionExtension() throws InterruptedException {
        LOGGER.info("ConcatFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') from inputStream select symbol1 , " +
                "str:concat(symbol1,symbol2,symbol3) as concatString " +
                "insert into outputStream;");
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);

        siddhiAppRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                for (Event event : inEvents) {
                    count.incrementAndGet();
                    if (count.get() == 1) {
                        AssertJUnit.assertEquals("AAACCC", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 2) {
                        AssertJUnit.assertEquals("123$%$6789", event.getData(1));
                        eventArrived = true;
                    }
                    if (count.get() == 3) {
                        AssertJUnit.assertEquals("D5338JU^XYZ", event.getData(1));
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

    @Test(expectedExceptions = SiddhiAppCreationException.class)
    public void testCoalesceFunctionExtensionWithOneArgument() throws InterruptedException {
        LOGGER.info("ConcatFunctionExtension TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (symbol1 string, symbol2 string, symbol3 string);";
        String query = ("@info(name = 'query1') from inputStream select symbol1 , "
                + "str:concat(symbol1) as concatString " +
                "insert into outputStream;");
       siddhiManager.createSiddhiAppRuntime(inStreamDefinition + query);
    }
}
