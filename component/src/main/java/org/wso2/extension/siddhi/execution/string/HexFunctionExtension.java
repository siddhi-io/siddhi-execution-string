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
 * hex(a)
 * Returns a hexadecimal string representation of str,
 * where each byte of each character in str is converted to two hexadecimal digits.
 * Accept Type(s): STRING
 * Return Type(s): STRING
 */

@Extension(
        name = "hex",
        namespace = "str",
        description = "This function returns a hexadecimal string by converting each byte of each character" +
                " in the input string to two hexadecimal digits.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to derive the hexadecimal value.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "The hexadecimal value of the input string that is passed to the function.",
                type = {DataType.STRING}),
        examples = @Example(
                syntax = "hex(\"MySQL\") ",
                description = "This returns the hexadecimal value of the input.string. " +
                "In this scenario, the output is \"4d7953514c\"."
                )
)
public class HexFunctionExtension extends FunctionExecutor {

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:hex() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (attributeType != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the argument of str:hex() " +
                    "function, " + "required " + Attribute.Type.STRING + "but found " + attributeType.toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;  //Since the hex function takes in only 1 parameter, this method does not get called. Hence,
        // not implemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data != null) {
            char[] chars = ((String) data).toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(Integer.toHexString((int) c));
            }
            return sb.toString();
        } else {
            throw new SiddhiAppRuntimeException("Input to the str:hex() function cannot be null");
        }
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
