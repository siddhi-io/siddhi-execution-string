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
import org.wso2.siddhi.core.executor.ConstantExpressionExecutor;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * regexp(string, regex)
 * Tells whether or not this 'string' matches the given regular expression 'regex'.
 * Accept Type(s): (STRING,STRING)
 * Return Type(s): BOOLEAN
 */

@Extension(
        name = "regexp",
        namespace = "str",
        description = "Returns a boolean value based on the matchability of the input string and the given regular " +
                "expression.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to match with the given regular expression.",
                        type = {DataType.STRING}),
                @Parameter(name = "regex",
                        description = "The regular expression  to be matched with the input string.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This extension returns `true` if the given string matches the given regular expression" +
                        " (i.e. regex ). It returns `false` if the string does not match the regular expression.",
                type = {DataType.BOOL}),
        examples = @Example(description = "This returns a boolean value after matching regular expression with " +
                "the given string. In this scenario, it returns \"true\" as the output.",
                syntax = "regexp(\"WSO2 abcdh\", \"WSO(.*h)\")")
)
public class RegexpFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.BOOL;

    //state-variables
    private boolean isRegexConstant = false;
    private String regexConstant;
    private Pattern patternConstant;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:regexp() function, " +
                    "required 2, " + "but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "str:regexp() function, " +
                    "required " + Attribute.Type.STRING + ", but found " + attributeExpressionExecutors[0].
                    getReturnType().toString());
        }
        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "str:regexp() function, " +
                    "required " + Attribute.Type.STRING + ", but found " + attributeExpressionExecutors[1].
                    getReturnType().toString());
        }
        if (attributeExpressionExecutors[1] instanceof ConstantExpressionExecutor) {
            isRegexConstant = true;
            regexConstant = (String) ((ConstantExpressionExecutor) attributeExpressionExecutors[1]).getValue();
            patternConstant = Pattern.compile(regexConstant);
        }
    }

    @Override
    protected Object execute(Object[] data) {
        String regex;
        Pattern pattern;
        Matcher matcher;

        if (data[0] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:regexp() function. First argument " +
                    "cannot be null");
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:regexp() function. Second argument " +
                    "cannot be null");
        }
        String source = (String) data[0];

        if (!isRegexConstant) {
            regex = (String) data[1];
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(source);
            return matcher.matches();

        } else {
            matcher = patternConstant.matcher(source);
            return matcher.matches();
        }
    }

    @Override
    protected Object execute(Object data) {
        return null;  //Since the regexp function takes in 2 parameters, this method does not get called. Hence,
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
