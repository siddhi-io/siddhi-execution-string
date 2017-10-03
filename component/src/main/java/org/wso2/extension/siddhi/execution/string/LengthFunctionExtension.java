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
 * length(string)
 * Returns the length of this string.
 * Accept Type(s): STRING
 * Return Type(s): INT
 */

@Extension(
        name = "length",
        namespace = "str",
        description = "Returns the length of this string.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to derive the length.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "Outputs the length of the input string provided.",
                type = {DataType.INT}),
        examples = @Example(description = "This outputs the length of the provided string. In this scenario, the, " +
                "output is `11` .", syntax = "length(\"Hello World\")")
)
public class LengthFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.INT;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:length() function. " +
                    "Required 1. Found " + attributeExpressionExecutors.length);
        } else if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException(
                    "Invalid parameter type found for str:length() function, required " + Attribute.Type.STRING +
                            ", " + "but found " + attributeExpressionExecutors[0].getReturnType());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;  //Since the length function takes in only 1 parameter, this method does not get called.
        // Hence, not implemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:length() function. " +
                    "The argument cannot be null");
        }
        return data.toString().length();
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
