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
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.siddhi.query.api.definition.Attribute.Type.BOOL;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

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
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "regex",
                        description = "The regular expression  to be matched with the input string.",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "regex"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This extension returns `true` if the given string matches the given regular expression" +
                        " (i.e. regex ). It returns `false` if the string does not match the regular expression.",
                type = {DataType.BOOL}),
        examples = @Example(
                syntax = "regexp(\"WSO2 abcdh\", \"WSO(.*h)\")",
                description = "This returns a boolean value after matching regular expression with " +
                "the given string. In this scenario, it returns \"true\" as the output.")

)
public class RegexpFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = BOOL;

    private Pattern pattern = null;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:regexp() function, "
                    + "required 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:regexp() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:regexp() function,required " + STRING.toString() + ", but found "
                    + executor2.getReturnType().toString());
        }

        if (isConstantAttribute(executor2)) {
            pattern = Pattern.compile((String) ((ConstantExpressionExecutor) executor2).getValue());
        }

        return null;
    }

    private boolean isConstantAttribute(ExpressionExecutor executor) {
        return executor instanceof ConstantExpressionExecutor;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        boolean arg0IsNull = objects[0] == null;
        boolean arg1IsNull = objects[1] == null;

        if (arg0IsNull || arg1IsNull) {
            String argNumberWord = (arg0IsNull) ? "First" : "Second";
            throw new SiddhiAppRuntimeException("Invalid input given to str:regexp() function. " + argNumberWord
                    + " argument cannot be null");
        }
        String source = (String) objects[0];

        Pattern pattern;
        if (this.pattern == null) {
            pattern = Pattern.compile((String) objects[1]);
        } else {
            pattern = this.pattern;
        }
        Matcher matcher = pattern.matcher(source);
        return  matcher.matches();
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
