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
import io.siddhi.core.config.SiddhiAppContext;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;

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
                        type = {DataType.STRING}),
                @Parameter(name = "index",
                        description = "The variable that specifies the index of the char value that needs " +
                                "to be returned.",
                        type = {DataType.INT})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the character that exists in the location specified by the index.",
                type = {DataType.STRING}),
        examples = @Example(syntax = "charAt(\"WSO2\", 1)", description = "In this case, the functiion returns the " +
                "character that exists at index 1. Hence, it returns 'S'.")
)
public class CharAtFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected void init(ExpressionExecutor[] expressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:charat() function, " +
                    "required 2, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "str:charat() function, " +
                    "required " + Attribute.Type.STRING + ", but found " + attributeExpressionExecutors[0].
                    getReturnType().toString());
        }
        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "str:charat() function, " +
                    "required " + Attribute.Type.INT + ", but found " + attributeExpressionExecutors[1].
                    getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:charat() function. First argument " +
                    "cannot be null");
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:charat() function. Second argument " +
                    "cannot be null");
        }
        String source = (String) data[0];
        int index = (Integer) data[1];
        try {
            return String.valueOf(source.charAt(index));
        } catch (IndexOutOfBoundsException e) {
            throw new SiddhiAppRuntimeException("Index argument " + index +
                    " is negative or not less than the length of the given string " + source, e);
        }
    }

    @Override
    protected Object execute(Object data) {
        return null;  //Since the charAt function takes in 2 parameters, this method does not get called. Hence,
        // not implemented.
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;    //No need to maintain a state.
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}


