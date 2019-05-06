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
 * unhex(str);
 * This is the equivalent of 'unhex' function in mysql 5.0
 * unhex(str) interprets each pair of characters in the argument as a hexadecimal number
 * and converts it to the byte represented by the number.
 * Accept Type(s): STRING
 * Return Type(s): STRING
 */

@Extension(
        name = "unhex",
        namespace = "str",
        description = "Returns a string by converting the hexadecimal characters in the input string.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The hexadecimal input string that needs to be converted to string.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the string value of the hexadecimal value that passed.",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "unhex(\"4d7953514c\")",
                description = "This converts the hexadecimal value to string."
                )
)
public class UnhexFunctionExtension extends FunctionExecutor {
    @Override
    protected StateFactory<State> init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                                SiddhiQueryContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:unhex() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of " +
                    "math:unhex() function, " + "required " + Attribute.Type.STRING + " but found " +
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
        if (data != null) {
            String inputStr = (String) data;
            StringBuilder stringBuilderOut = new StringBuilder();
            for (int i = 0; i < inputStr.length(); i = i + 2) {
                String hexValue = inputStr.substring(i, i + 2);
                int decimalValue = Integer.parseInt(hexValue, 16);
                stringBuilderOut.append(Character.toChars(decimalValue));
            }
            return stringBuilderOut.toString();
        } else {
            throw new SiddhiAppRuntimeException("Input to the math:unhex() function cannot be null");
        }
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.STRING;
    }
}
