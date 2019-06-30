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
 * charAt(string , index)
 * Returns the char value in 'string' at the specified 'index'.
 * Accept Type(s): (STRING,INT)
 * Return Type(s): STRING
 */
@Extension(
        name = "charAt",
        namespace = "str",
        description = "This function returns the 'char' value that is present at the given index position." +
                " of the input string.",
        parameters = {
                @Parameter(name = "input.value",
                        description = "The input string of which the char value at the given position needs to be " +
                                "returned.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "index",
                        description = "The variable that specifies the index of the char value that needs " +
                                "to be returned.",
                        type = {DataType.INT},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.value", "index"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the character that exists in the location specified by the index.",
                type = {DataType.STRING}),
        examples = @Example(syntax = "charAt(\"WSO2\", 1)", description = "In this case, the functiion returns the " +
                "character that exists at index 1. Hence, it returns 'S'.")
)
public class CharAtFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = STRING;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:charat() function, "
                    + "required 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:charat() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:charat() function,required " + INT.toString() + ", but found "
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
            throw new SiddhiAppRuntimeException("Invalid input given to str:charat() function. "
                    + argNumberWord + " argument cannot be null");
        }

        String source = (String) objects[0];
        Integer index = (Integer) objects[1];

        try {
            return String.valueOf(source.charAt(index));
        } catch (IndexOutOfBoundsException e) {
            throw new SiddhiAppRuntimeException("Index argument "
                    + index + " is negative or not less than the length of the given string " + source, e);
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


