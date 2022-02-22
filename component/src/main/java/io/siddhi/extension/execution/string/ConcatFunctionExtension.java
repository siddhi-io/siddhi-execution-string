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
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * concat(string1, string2, ..., stringN)
 * Returns a string that is the result of concatenating two or more string values.
 * Accept Type(s): STRING. There should be at least two arguments.
 * Return Type(s): STRING
 */

@Extension(
        name = "concat",
        namespace = "str",
        description = "This function returns a string value that is obtained as a result of " +
                "concatenating two or more input string values.",
        parameters = {
                @Parameter(name = "arg",
                        description = "This can have two or more STRING, DOUBLE, BOOL, FLOAT, INT, LONG typed" +
                                " input parameters.",
                        type = {DataType.STRING, DataType.DOUBLE, DataType.BOOL,
                                DataType.FLOAT, DataType.INT, DataType.LONG},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"arg", "..."})
        },
        returnAttributes = @ReturnAttribute(
                description = "This is the string that is returned on concatenating the given input arguments.",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "concat(\"D533\", \"8JU^\", \"XYZ\")",
                description = "This returns a string value by concatenating two or more given arguments. " +
                        "In the example shown above, it returns \"D5338JU^XYZ\".")
)
public class ConcatFunctionExtension extends FunctionExecutor {

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount < 2) {
            throw new SiddhiAppValidationException("str:concat() function requires at least two arguments, "
                    + "but found only " + executorsCount);
        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        StringBuilder builder = new StringBuilder();
        for (Object o : objects) {
            if (o != null) {
                builder.append(o);
            }
        }
        return builder.toString();
    }

    @Override
    protected Object execute(Object o, State state) {
        return o;
    }

    @Override
    public Attribute.Type getReturnType() {
        return STRING;
    }
}
