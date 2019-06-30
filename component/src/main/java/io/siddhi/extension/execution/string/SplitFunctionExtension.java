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
 * split(sourceText, splitCharacter, returnedOutputPosition)
 * Splits the source String by splitCharacter and return the string in the index given by returnedOutputPosition​ ​
 * Accept Type(s): (STRING, STRING, INT)
 * Return Type(s): STRING
 */

@Extension(
        name = "split",
        namespace = "str",
        description = "Splits the  `input.string` into substrings using the value parsed in the `split.string` and " +
                "returns the substring at the position specified in the " +
                "`group.number`.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to be replaced.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "split.string",
                        description = "The string value to be used to split the `input.string`.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "group.number",
                        description = "The index of the split group",
                        type = {DataType.INT},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "split.string", "group.number"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the substring after splitting the input.string",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "split(\"WSO2,ABM,NSFT\", \",\", 0)",
                description = "This splits the given `input.string` by given `split.string` and " +
                "returns the string in the index given by group.number. In this scenario, " +
                "the output will is \"WSO2\".")
)
public class SplitFunctionExtension extends FunctionExecutor {

    private Attribute.Type returnType = STRING;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 3) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:split() function, "
                    + "required 3, but found " + executorsCount);
        }

        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];
        ExpressionExecutor executor3 = expressionExecutors[2];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:split() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:split() function,required " + STRING.toString() + ", but found "
                    + executor2.getReturnType().toString());
        }
        if (executor3.getReturnType() != INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of "
                    + "str:split() function,required " + INT.toString() + ", but found "
                    + executor3.getReturnType().toString());
        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        boolean arg0IsNull = objects[0] == null;
        boolean arg1IsNull = objects[1] == null;
        boolean arg2IsNull = objects[2] == null;

        if (arg0IsNull || arg1IsNull || arg2IsNull) {
            String argNumberWord;
            if (arg0IsNull) {
                argNumberWord = "First";
            } else if (arg1IsNull) {
                argNumberWord = "Second";
            } else {
                argNumberWord = "Third";
            }
            throw new SiddhiAppRuntimeException("Invalid input given to str:split() function. " + argNumberWord
                    + " argument cannot be null");
        }
        String source = (String) objects[0];
        String regex = (String) objects[1];
        int index = (Integer) objects[2];
        String[] splitStrArray = source.split(regex);
        try {
            return splitStrArray[index];
        } catch (IndexOutOfBoundsException e) {
            throw new SiddhiAppRuntimeException("Index argument " + index + " is negative or not less than the " +
                    "length of the given string " + source, e);
            //Runtime Exception was captured to avoid checking whether index < splitStrArray.length
            // for performance reasons.​
        }
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
