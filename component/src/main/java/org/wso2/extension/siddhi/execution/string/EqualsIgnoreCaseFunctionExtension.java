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
 * equalsIgnoreCase(string, compareTo)
 * Compares two strings lexicographically.
 * Accept Type(s): (STRING,STRING)
 * Return Type(s): BOOL
 */

@Extension(
        name = "equalsIgnoreCase",
        namespace = "str",
        description = "Compares two strings lexicographically.",
        parameters = {
                @Parameter(name = "arg1",
                        description = "The first input string argument.",
                        type = {DataType.STRING}),
                @Parameter(name = "arg2",
                        description = "The second input string argument. This is compared with the first argument.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a boolean output as `true` if both `arg1` and `arg2` are equal without " +
                        "comparing the case.", type = {DataType.BOOL}),
        examples = @Example(description = "This returns a boolean value as the output. In this scenario, it " +
                "returns \"true\". ", syntax = "equalsIgnoreCase(\"WSO2\", \"wso2\")")
)
public class EqualsIgnoreCaseFunctionExtension extends FunctionExecutor {
    Attribute.Type returnType = Attribute.Type.BOOL;

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

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException(
                    "Invalid no of arguments passed to str:equalsIgnoreCase() function, " + "required 2, but found "
                            + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException(
                    "Invalid parameter type found for the first argument of str:equalsIgnoreCase() function, "
                            + "required " + Attribute.Type.STRING + ", but found "
                            + attributeExpressionExecutors[0].getReturnType().toString());
        }
        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException(
                    "Invalid parameter type found for the second argument of str:equalsIgnoreCase()) function, "
                            + "required " + Attribute.Type.STRING + ", but found "
                            + attributeExpressionExecutors[1].getReturnType().toString());
        }

    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] == null) {
            throw new SiddhiAppRuntimeException(
                    "Invalid input given to str:equalsIgnoreCase() function. First argument cannot be null");
        }
        String source = (String) data[0];
        String compareTo = (String) data[1];
        return source.equalsIgnoreCase(compareTo);
    }

    @Override
    protected Object execute(Object data) {
        return null;
    }

}
