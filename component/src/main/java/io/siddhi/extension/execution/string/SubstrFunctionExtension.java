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
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.extension.execution.string.substrexecutors.SubstrExecutor;
import io.siddhi.extension.execution.string.substrexecutors.Type1Executor;
import io.siddhi.extension.execution.string.substrexecutors.Type2Executor;
import io.siddhi.extension.execution.string.substrexecutors.Type3Executor;
import io.siddhi.extension.execution.string.substrexecutors.Type4Executor;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.regex.Pattern;

import static io.siddhi.query.api.definition.Attribute.Type.INT;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * substr(sourceText, beginIndex) or substr(sourceText, beginIndex, length) or substr(sourceText, regex)
 * or substr(sourceText, regex, groupNumber)
 * Returns a new string that is a substring of this string.
 * Accept Type(s): (STRING,INT) or (STRING,INT,INT) or (STRING,STRING) or (STRING,STRING,INT)
 * Return Type(s): STRING
 */

@Extension(
        name = "substr",
        namespace = "str",
        description = "Returns a substring of the input string by considering a subset or all of the following " +
                "factors: starting index, length, regular expression, and regex group number.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to be processed.",
                        type = {DataType.STRING}),
                @Parameter(name = "begin.index",
                        description = "Starting index to consider for the substring.",
                        type = {DataType.INT}),
                @Parameter(name = "length",
                        description = "The length of the substring.",
                        type = {DataType.INT}),
                @Parameter(name = "regex",
                        description = "The regular expression that should be matched with the input string.",
                        type = {DataType.STRING}),
                @Parameter(name = "group.number",
                        description = "The regex group number",
                        type = {DataType.INT})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a new string that is a substring of the `input.string`.",
                type = {DataType.STRING}),
        examples = {
                @Example(
                        syntax = "substr(\"AbCDefghiJ KLMN\", 4)",
                        description = "This outputs the substring based on the given `begin.index`. In this " +
                                "scenario, the output is \"efghiJ KLMN\"."),
                @Example(
                        syntax = "substr(\"AbCDefghiJ KLMN\",  2, 4) ",
                        description = "This outputs the substring based on the given `begin.index` and length. In " +
                                "this scenario, the output is \"CDef\"."),

                @Example(
                        syntax = "substr(\"WSO2D efghiJ KLMN\", '^WSO2(.*)')",
                        description = "This outputs the substring by applying the regex. In this scenario, the " +
                                "output is \"WSO2D efghiJ KLMN\"."),
                @Example(
                        syntax = "substr(\"WSO2 cep WSO2 XX E hi hA WSO2 heAllo\",  'WSO2(.*)A(.*)',  2)",
                        description = "This outputs the substring by applying the regex and considering the " +
                                "`group.number`. In this scenario, the output is \" ello\"."
                )
        }
)
public class SubstrFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = STRING;

    private SubstrExecutor substrExecutor;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        ExpressionExecutor executor1 = expressionExecutors[0];
        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:substr() function, required " + STRING + ", but found " + executor1.getReturnType());
        }

        switch (executorsCount) {
            case 2: {
                ExpressionExecutor executor2 = expressionExecutors[1];

                switch (executor2.getReturnType()) {
                    case INT:
                        substrExecutor = new Type1Executor();
                        break;
                    case STRING:
                        substrExecutor = new Type3Executor();
                        if (isConstantAttribute(executor2)) {
                            String regex = (String) ((ConstantExpressionExecutor) executor2).getValue();
                            substrExecutor = new Type3Executor(Pattern.compile(regex));
                        }
                        break;
                    default:
                        throw new SiddhiAppValidationException("Invalid parameter type found for the second "
                                + "argument of str:substr() function, required " + STRING + " or " + INT
                                + ", but found " + executor2.getReturnType().toString());
                }
                break;
            }
            case 3: {
                ExpressionExecutor executor2 = expressionExecutors[1];
                ExpressionExecutor executor3 = expressionExecutors[2];

                if (executor3.getReturnType() != INT) {
                    throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of "
                            + "str:substr() function, " + "required " + INT + ", but found "
                            + executor3.getReturnType().toString());
                }

                switch (executor2.getReturnType()) {
                    case INT:
                        substrExecutor = new Type2Executor();
                        break;
                    case STRING:
                        substrExecutor = new Type4Executor();
                        if (isConstantAttribute(executor2)) {
                            String regex = (String) ((ConstantExpressionExecutor) executor2).getValue();
                            substrExecutor = new Type4Executor(Pattern.compile(regex));
                        }
                        break;
                    default:
                        throw new SiddhiAppValidationException("Invalid parameter type found for the second argument "
                                + "of str:substr() function, required " + STRING + " or " + INT
                                + ", but found " + executor2.getReturnType().toString());
                }
                break;
            }
            default:
                throw new SiddhiAppValidationException("Invalid no of Arguments passed to str:substr() function, "
                        + "required 2 or 3, but found " + executorsCount);
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
            throw new SiddhiAppRuntimeException("Invalid input given to str:substr() function. " + argNumberWord
                    + " argument cannot be null");
        }
        if (!substrExecutor.canIgnoreArg2() && objects.length >= 3 && objects[2] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:substr() function. Third"
                    + " argument cannot be null");
        }

        //noinspection unchecked
        return substrExecutor.execute((String) objects[0],
                substrExecutor.castArg1(objects[1]),
                substrExecutor.canIgnoreArg2() ? null : substrExecutor.castArg2(objects[2]));
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
