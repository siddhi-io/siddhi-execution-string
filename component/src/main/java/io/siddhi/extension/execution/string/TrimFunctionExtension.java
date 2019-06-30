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

import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * trim(string)
 * Returns a copy of the string, with leading and trailing whitespace omitted.
 * Accept Type(s): STRING
 * Return Type(s): STRING
 */

@Extension(
        name = "trim",
        namespace = "str",
        description = "Returns a copy of the input string without the leading and trailing whitespace (if any).",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string that needs to be trimmed.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a string value after removing the leading and trailing whitespaces",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "trim(\"  AbCDefghiJ KLMN  \")",
                description = "This returns a copy of the `input.string` with the leading and/or " +
                "trailing white-spaces omitted. In this scenario, the output is \"AbCDefghiJ KLMN\".")

)
public class TrimFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = STRING;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:trim() function, "
                    + "required 1, but found " + executorsCount);
        }
        Attribute.Type type = expressionExecutors[0].getReturnType();
        if (type != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of str:trim() "
                    + "function, " + "required " + STRING.toString() + "but found " + type.toString());
        }
        return null;
    }

    @Override
    protected Object execute(Object data) {
        if (data == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:trim() function. " +
                    "The argument cannot be null");
        }
        return data.toString().trim();
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        return null;
    }

    @Override
    protected Object execute(Object o, State state) {
        if (o == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:trim() function."
                    + " The argument cannot be null");
        }
        return o.toString().trim();
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }
}
