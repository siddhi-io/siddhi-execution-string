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

/**
 * upper(string)
 * Converts the simple letters in the input string to the equivalent capital letters.
 * Accept Type(s): STRING
 * Return Type(s): STRING
 */

@Extension(
        name = "upper",
        namespace = "str",
        description = "Converts the simple letters in the input string to the equivalent capital/block letters.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string that should be converted to the upper case (equivalent " +
                                "capital/block letters).",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a string value in upper case by converting the `input.string`",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "upper(\"Hello World\")",
                description = "This converts the simple letters in the `input.string` to the" +
                "equivalent capital letters. In this scenario, the output is \"HELLO WORLD\".")

)
public class UpperFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                                       SiddhiQueryContext siddhiQueryContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:upper() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for str:upper() function, required "
                    + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }
        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        return null;
    }

    @Override
    protected Object execute(Object data, State state) {
        if (data == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:upper() function. " +
                    "The argument cannot be null");
        }
        return data.toString().toUpperCase();
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}
