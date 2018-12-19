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
 * lower(string)
 * Converts the capital letters in the input string to the equivalent simple letters.
 * Accept Type(s): STRING
 * Return Type(s): STRING
 */

@Extension(
        name = "lower",
        namespace = "str",
        description = "Converts the capital letters in the input string to the equivalent simple letters.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to convert to the lower case (i.e., equivalent simple " +
                                "letters).",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns a string value in lower case by converting the input.string .",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "lower(\"WSO2 cep \")",
                description = "This converts the capital letters in the input.string to the " +
                "equivalent simple letters. In this scenario, the output is \"wso2 cep \".")

)
public class LowerFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:lower() function, " +
                    "required 1, " + "but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for str:lower() function, required "
                    + Attribute.Type.STRING + ", but found " + attributeExpressionExecutors[0].
                    getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;   //Since the lower function takes in only 1 parameter, this method does not get called. Hence,
        // not implemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:lower() function. " +
                    "The argument cannot be null");
        }
        return data.toString().toLowerCase();
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
