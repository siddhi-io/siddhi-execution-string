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
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * replaceAll(string, regex, replacement)
 * Replaces each substring of this string that matches the given expression with the given replacement.
 * Accept Type(s): (STRING,STRING,STRING)
 * Return Type(s): STRING
 */

@Extension(
        name = "replaceAll",
        namespace = "str",
        description = "Finds all the substrings of the input string that matches with the given expression, and " +
                "replaces them with the given replacement string.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to be replaced.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "regex",
                        description = "The regular expression to be matched with the input string.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "replacement.string",
                        description = "The string with which each substring that matches the given expression should" +
                                " be replaced.",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "regex", "replacement.string"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This replaces each substring of the given string (i.e. str) that matches the given " +
                        "regular expression (i.e. regex) with the string specified as the replacement " +
                        "(i.e. replacement).", type = {DataType.STRING}),
        examples = @Example(
                syntax = "replaceAll(\"hello hi hello\",  'hello', 'test')",
                description = "This returns a string after replacing the substrings of the input string" +
                        " with the replacement string. In this scenario, the output is \"test hi test\" .")
)
public class ReplaceAllFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = STRING;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                       ConfigReader configReader,
                                       SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 3) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:replaceAll() function, "
                    + "required 3, but found " + executorsCount);
        }

        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];
        ExpressionExecutor executor3 = expressionExecutors[2];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:replaceAll() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:replaceAll() function,required " + STRING.toString() + ", but found "
                    + executor2.getReturnType().toString());
        }
        if (executor3.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of "
                    + "str:replaceAll() function,required " + STRING.toString() + ", but found "
                    + executor3.getReturnType().toString());
        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        boolean arg0IsNull = objects[0] == null;
        boolean arg1IsNull = objects[1] == null;
        boolean arg2IsNull = objects[2] == null;

        if (arg0IsNull || arg1IsNull || arg2IsNull) {
            String argNumberWord;
            if (arg0IsNull) {
                argNumberWord = "First";
            } else if (arg1IsNull) {
                argNumberWord = "Second";
            } else {
                argNumberWord = "Third";
            }
            throw new SiddhiAppRuntimeException("Invalid input given to str:replaceAll() function. " + argNumberWord
                    + " argument cannot be null");
        }
        String source = (String) objects[0];
        String regex = (String) objects[1];
        String replacement = (String) objects[2];
        return source.replaceAll(regex, replacement);
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
