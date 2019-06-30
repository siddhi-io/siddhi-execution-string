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
 * repeat(string , times)
 * Repeats a string for a specified number of times.
 * Accept Type(s): (STRING,INT)
 * Return Type(s): STRING
 */

@Extension(
        name = "repeat",
        namespace = "str",
        description = "Repeats the input string for a specified number of times.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string that is repeated the number of times as defined by the user.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "times",
                        description = "The number of times the input.string needs to be repeated .",
                        type = {DataType.INT},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "times"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a value after repeating the string for a specified number of times.",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "repeat(\"StRing 1\", 3)",
                description = "This returns a string value by repeating the string for a specified number" +
                " of times. In this scenario, the output is \"StRing 1StRing 1StRing 1\".")

)
public class RepeatFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = STRING;

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
                    + "str:repeat() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:repeat() function,required " + INT.toString() + ", but found "
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
            throw new SiddhiAppRuntimeException("Invalid input given to str:repeat() function. " + argNumberWord
                    + " argument cannot be null");
        }
        String source = (String) objects[0];

        StringBuilder builder = new StringBuilder();
        int reps = (Integer) objects[1];
        for (int i = 0; i < reps; i++) {
            builder.append(source);
        }
        return builder.toString();
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


