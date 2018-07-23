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
 * replaceFirst(string, regex, replacement)
 * Replaces the first substring of this string that matches the given expression with the given replacement.
 * Accept Type(s): (STRING,STRING,STRING)
 * Return Type(s): STRING
 */

@Extension(
        name = "replaceFirst",
        namespace = "str",
        description = "Finds the first substring of the input string that matches with the given regular " +
                "expression, and replaces it" +
                "with the given replacement string.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string that should be replaced.",
                        type = {DataType.STRING}),
                @Parameter(name = "regex",
                        description = "The regular expression with which the input string should be matched.",
                        type = {DataType.STRING}),
                @Parameter(name = "replacement.string",
                        description = "The string with which the first substring of input string that matches the" +
                                " regular expression should be replaced.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a string after replacing the first substring that matches the given" +
                        " regular expression with the string specified as the replacement",
                type = {DataType.STRING}),
        examples = @Example(description = "This returns a string after replacing the first substring with the " +
                "given replacement string. In this scenario, the output is \"hello XXXX hello\".",
                syntax = "replaceFirst(\"hello WSO2 A hello\",  'WSO2(.*)A', 'XXXX')")
)
public class ReplaceFirstFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 3) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:replaceFirst() function, " +
                    "required 3, " + "but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "str:replaceFirst() function, " + "required " + Attribute.Type.STRING + ", " +
                    "but found " + attributeExpressionExecutors[0].getReturnType().toString());
        }
        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "str:replaceFirst() function, " + "required " + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[1].getReturnType().toString());
        }
        if (attributeExpressionExecutors[2].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of " +
                    "str:replaceFirst() function, " + "required " + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[2].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:replaceFirst() function. " +
                    "First argument cannot be null");
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:replaceFirst() function. " +
                    "Second argument cannot be null");
        }
        if (data[2] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:replaceFirst() function. " +
                    "Third argument cannot be null");
        }
        String source = (String) data[0];
        String regex = (String) data[1];
        String replacement = (String) data[2];
        return source.replaceFirst(regex, replacement);
    }

    @Override
    protected Object execute(Object data) {
        return null;  //Since the replaceFirst function take 3 parameters, this method does not get called.
        // Hence, not implemented.
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
