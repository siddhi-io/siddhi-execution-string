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
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

/**
 * coalesce(arg1,arg2,...,argN)
 * returns the value of the first of its input parameters that is not NULL
 * Accept Type(s): Arguments can be of any type, given that the argument count is more than zero and all the
 * arguments are of the same type.
 * Return Type(s): Same type as the input
 */

@Extension(
        name = "coalesce",
        namespace = "str",
        description = " This returns the first input parameter value of the given argument, that is not null.",
        parameters = {
                @Parameter(name = "argn",
                        description = "It can have one or more input parameters in any data type." +
                                " However, all the specified " +
                                "parameters are required to be of the same type.",
                        type = {DataType.INT, DataType.LONG, DataType.DOUBLE, DataType.FLOAT,
                                DataType.STRING, DataType.BOOL, DataType.OBJECT})
        },
        returnAttributes = @ReturnAttribute(
                description = "This holds the first input parameter that is not null.",
                type = {DataType.INT, DataType.LONG, DataType.DOUBLE, DataType.FLOAT,
                        DataType.STRING, DataType.BOOL, DataType.OBJECT}),
        examples = @Example(
                syntax = "coalesce(null, \"BBB\", \"CCC\")",
                description = "This returns the first input parameter that is not null. " +
                "In this example, it returns \"BBB\".")
)
public class CoalesceFunctionExtension extends FunctionExecutor {

    private Attribute.Type returnType;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount == 0) {
            throw new SiddhiAppValidationException("str:coalesce() function requires at least one argument, "
                    + "but found only " + executorsCount);
        }
        Attribute.Type expectedType = expressionExecutors[0].getReturnType();

        for (ExpressionExecutor expressionExecutor : expressionExecutors) {
            Attribute.Type type = expressionExecutor.getReturnType();
            if (type != expectedType) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the "
                        + executorsCount + "'th argument of str:coalesce() function, required "
                        + type + ", but found " + type.toString());
            }
        }
        returnType = expectedType;

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        for (Object o : objects) {
            if (o != null) {
                return o;
            }
        }
        return null;
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
