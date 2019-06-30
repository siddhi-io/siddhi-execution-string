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

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
import io.siddhi.annotation.ParameterOverload;
import io.siddhi.annotation.ReturnAttribute;
import io.siddhi.annotation.util.DataType;
import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import static io.siddhi.query.api.definition.Attribute.Type.INT;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * strcmp(string, compareTo)
 * Compares two strings lexicographically.
 * Accept Type(s): (STRING,STRING)
 * Return Type(s): INT
 */

@Extension(
        name = "strcmp",
        namespace = "str",
        description = "Compares two strings lexicographically and returns an integer value. If both strings are " +
                "equal, 0 is returned. If  the first string is lexicographically greater than the second string, a " +
                "positive value is returned. If the first string is lexicographically greater than the second " +
                "string, a negative value is returned.",
        parameters = {
                @Parameter(name = "arg1",
                        description = "The first input string argument.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "arg2",
                        description = "The second input string argument that should be compared with the first " +
                                "argument lexicographically.",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"arg1", "arg2"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns an integer value after comparing `arg1` with the `arg2` string " +
                        "lexicographically.",
                type = {DataType.INT}),
        examples = @Example(
                syntax = "strcmp(\"AbCDefghiJ KLMN\", 'Hello')",
                description = "This compares two strings lexicographically and outputs an integer value."
                )
)
public class StrcmpFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = INT;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:repeat() function, "
                    + "required 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:strcmp() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:strcmp() function,required " + STRING.toString() + ", but found "
                    + executor2.getReturnType().toString());
        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        boolean arg0IsNull = objects[0] == null;
        boolean arg1IsNull = objects[1] == null;

        if (arg0IsNull || arg1IsNull) {
            String argNumberWord = (arg0IsNull) ? "First" : "Second";
            throw new SiddhiAppRuntimeException("Invalid input given to str:strcmp() function. " + argNumberWord
                    + " argument cannot be null");
        }
        String source = (String) objects[0];
        String compareTo = (String) objects[1];
        return source.compareTo(compareTo);
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
