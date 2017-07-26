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

import org.wso2.siddhi.annotation.Example;
import org.wso2.siddhi.annotation.Extension;
import org.wso2.siddhi.annotation.Parameter;
import org.wso2.siddhi.annotation.ReturnAttribute;
import org.wso2.siddhi.annotation.util.DataType;
import org.wso2.siddhi.core.config.SiddhiAppContext;
import org.wso2.siddhi.core.exception.SiddhiAppRuntimeException;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;

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
        description = "`unhex(str)` interprets each pair of characters in the argument as a hexadecimal number\n" +
                " and converts it to the byte represented by the number",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The hexadecimal input string that needs to be converted to string.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the string value of the hexadecimal value that passed",
                type = {DataType.STRING}),
        examples = @Example(description = "This converts the hexadecimal value to string",
                syntax = "unhex(\"4d7953514c\")")
)
public class UnhexFunctionExtension extends FunctionExecutor {
    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to math:unhex() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of " +
                    "math:unhex() function, " + "required " + Attribute.Type.STRING + " but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;  //Since the unhex function takes in only 1 parameter, this method does not get called.
        // Hence, not implemented.
    }

    @Override
    protected Object execute(Object data) {
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
    public void start() {
        //Nothing to start.
    }

    @Override
    public void stop() {
        //Nothing to stop.
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.STRING;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;    //No need to maintain a state.
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }
}
