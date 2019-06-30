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

import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * equalsIgnoreCase(string, compareTo)
 * Compares two strings lexicographically.
 * Accept Type(s): (STRING,STRING)
 * Return Type(s): BOOL
 */

@Extension(
        name = "equalsIgnoreCase",
        namespace = "str",
        description = "This returns a boolean value by comparing two strings lexicographically without " +
                "considering the letter case.",
        parameters = {
                @Parameter(name = "arg1",
                        description = "The first input string argument.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "arg2",
                        description = "The second input string argument. This is compared with the first argument.",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"arg1", "arg2"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a boolean output as `true` if both `arg1` and `arg2` are equal without " +
                        "comparing the case.", type = {DataType.BOOL}),
        examples = @Example(
                 syntax = "equalsIgnoreCase(\"WSO2\", \"wso2\")",
                 description = "This returns a boolean value as the output. In this scenario, it " +
                "returns \"true\". ")
)
public class EqualsIgnoreCaseFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.BOOL;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 2) {
            throw new SiddhiAppValidationException(
                    "Invalid no of arguments passed to str:equalsIgnoreCase() function, "
                            + "required 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:equalsIgnoreCase() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:equalsIgnoreCase() function,required " + STRING.toString() + ", but found "
                    + executor2.getReturnType().toString());
        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        if (objects[0] == null) {
            throw new SiddhiAppRuntimeException(
                    "Invalid input given to str:equalsIgnoreCase() function. First argument cannot be null");
        }
        String source = (String) objects[0];
        String compareTo = (String) objects[1];
        return source.equalsIgnoreCase(compareTo);
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
