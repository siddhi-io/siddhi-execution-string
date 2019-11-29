/*
 * Copyright (c)  2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import static io.siddhi.query.api.definition.Attribute.Type.LONG;

/**
 * Implementation for charFrequency.
 */

@Extension(
        name = "charFrequency",
        namespace = "str",
        description = "Gives the frequency of a char in `input string`.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to be processed.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "char",
                        description = "The char's number of occurrences to be calculated",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "char"})
        },
        returnAttributes =
                @ReturnAttribute(
                        description = "This returns the number of instances of `char` in the `input.string`",
                        type = {DataType.LONG}
                        ),
        examples = @Example(
                syntax = "str:charFrequency(\"WSO2,ABM,NSFT\", \",\")",
                description = "This counts the number of occurrences of `,` in the given `input.string`. " +
                        "In this scenario, the output will is `2`.")
)
public class CharFrequencyFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                        ConfigReader configReader, SiddhiQueryContext siddhiQueryContext) {
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return LONG;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        boolean arg0IsNull = objects[0] == null;
        boolean arg1IsNull = objects[1] == null;

        if (arg0IsNull || arg1IsNull) {
            String argNumberWord = (arg0IsNull) ? "First" : "Second";
            throw new SiddhiAppRuntimeException("Invalid input given to str:charFrequency() function. "
                    + argNumberWord + " argument cannot be null");
        }

        String countCharacter = ((String) objects[1]);
        if (countCharacter.length() != 1) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:charFrequency() function. `char` " +
                    "parameter is expected to be a character, found '" + countCharacter + "'.");
        }

        char countCh = countCharacter.charAt(0);
        String inputString = ((String) objects[0]);

        return inputString.chars().filter(ch -> ch == countCh).count();
    }

    @Override
    protected Object execute(Object o, State state) {
        // Not possible with parameter overload
        return null;
    }

}
