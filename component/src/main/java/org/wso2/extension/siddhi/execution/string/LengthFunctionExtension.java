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

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
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
 * length(string)
 * Returns the length of this string.
 * Accept Type(s): STRING
 * Return Type(s): INT
 */

@Extension(
        name = "length",
        namespace = "str",
        description = "Returns the length of the input string.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to derive the length.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "Outputs the length of the input string provided.",
                type = {DataType.INT}),
        examples = @Example(
                syntax = "length(\"Hello World\")",
                description = "This outputs the length of the provided string. In this scenario, the, " +
                "output is `11` .")

)
public class LengthFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = INT;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:length() function. "
                    + "Required 1. Found " + executorsCount);
        }
        Attribute.Type type = expressionExecutors[0].getReturnType();
        if (type != STRING) {
            throw new SiddhiAppValidationException(
                    "Invalid parameter type found for str:length() function, required " + STRING
                            + ", but found " + type.toString());
        }
        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        return null;
    }

    @Override
    protected Object execute(Object o, State state) {
        if (o == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:length() function. " +
                    "The argument cannot be null");
        }
        return o.toString().length();
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}
