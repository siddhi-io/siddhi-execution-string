/*
 * Copyright (c)  2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
import io.siddhi.annotation.ParameterOverload;
import io.siddhi.annotation.ReturnAttribute;
import io.siddhi.annotation.util.DataType;
import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;

import static io.siddhi.query.api.definition.Attribute.Type.INT;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * locate(string, strin)
 * Check the a particular string exists in a given string. Optionally a starting point to start the search can be given.
 * Accept Type(s): (STRING,STRING, INT)
 * Return Type(s): INT
 */

@Extension(
        name = "locate",
        namespace = "str",
        description = "This function returns the position of one string within another. " +
                "Optionally, the user can give the starting index to be search from.",
        parameters = {
                @Parameter(name = "string.to.search.for",
                        description = "The string to be searched for. eg: @wso2.com",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "string.to.search.in",
                        description = "The string to be searched in. eg: john@wso2.com",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "searching.start.position",
                        description = "The character position in the string to begin the search.",
                        type = {DataType.INT},
                        optional = true,
                        defaultValue = "0",
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"string.to.search.for", "string.to.search.in"}),
                @ParameterOverload(parameterNames = {"string.to.search.for", "string.to.search.in",
                        "searching.start.position"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns an index value of the starting position of the searched string in the " +
                        "given string.",
                type = {DataType.INT}),
        examples = @Example(
                syntax = "define stream inputStream (str string);\n" +
                        "@info(name = 'query1')\n" +
                        "from inputStream#str:locate(str , '@wso2.com')\n" +
                        "select indexNo\n" +
                        "insert into outputStream;",
                description = "This query searches the starting index of '@wso2.com' in str. If the str is " +
                        "\"streamin@wso2.com\", then the function will return 8."
        )
)
public class LocateFunctionExtension extends FunctionExecutor {
    private Attribute.Type returnType = INT;
    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;
        switch (executorsCount) {
            case 3:
                ExpressionExecutor executor3 = expressionExecutors[2];
                if (!isType(executor3, INT)) {
                    throw new SiddhiAppCreationException("Third attribute 'searching.start.position' " +
                            "should be of type int. But found " + executor3.getReturnType());
                }
                break;
            case 2:
                ExpressionExecutor executor1 = expressionExecutors[0];
                ExpressionExecutor executor2 = expressionExecutors[1];
                if (executor1 == null || executor2 == null) {
                    throw new SiddhiAppRuntimeException("Invalid input given to str:locate() function. "
                            + "Input.string argument cannot be null");
                }
                if (!isType(executor1, STRING)) {
                    throw new SiddhiAppCreationException("Input string should be of type string in the 1st argument. " +
                            "But found " + executor1.getReturnType());
                }
                if (!isType(executor2, STRING)) {
                    throw new SiddhiAppCreationException("Input string should be of type string in the 2nd argument. " +
                            "But found " + executor2.getReturnType());
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid no of arguments passed to str:locate() function, "
                        + "required 2 or 3, but found " + executorsCount);
        }
        return null;
    }

    private boolean isType(ExpressionExecutor executor, Attribute.Type type) {
        return executor.getReturnType() == type;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        boolean arg0IsNull = objects[0] == null;
        boolean arg1IsNull = objects[1] == null;
        if (arg0IsNull || arg1IsNull) {
            String argNumberWord = (arg0IsNull) ? "First" : "Second";
            throw new SiddhiAppRuntimeException("Invalid input given to str:locate() function. " + argNumberWord
                    + " argument cannot be null");
        }
        String stringToBeSearchedFor = (String) objects[0];
        String stringToBeSearched = (String) objects[1];
        int  positionInTheStringToBeginSearch;
        if (objects.length == 3) {
            positionInTheStringToBeginSearch = (Integer) objects[2];
            stringToBeSearched = stringToBeSearched.substring(positionInTheStringToBeginSearch);
            return stringToBeSearched.indexOf(stringToBeSearchedFor) + positionInTheStringToBeginSearch;
        }
        return stringToBeSearched.indexOf(stringToBeSearchedFor);
    }

    @Override
    protected Object execute(Object o, State state) {
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}
