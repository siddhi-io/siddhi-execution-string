/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * replaceAll(string, regex, replacement)
 * Replaces each substring of this string that matches the given expression with the given replacement.
 * Accept Type(s): (STRING,STRING,STRING)
 * Return Type(s): STRING
 */

@Extension(
        name = "fillTemplate",
        namespace = "str",
        description = "This extension replaces the templated positions that are marked with an index value in a" +
                " specified template with the strings provided.",
        parameters = {
                @Parameter(name = "template",
                        description = "The string with templated fields that needs to be filled with the given " +
                                "strings. The format of the templated fields should be as follows:\n" +
                                "{{INDEX}} where 'INDEX' is an integer. \n" +
                                "This index is used to map the strings that are used to replace the templated fields.",
                        type = {DataType.STRING}),
                @Parameter(name = "replacement.strings",
                        description = "The strings with which the templated positions in the template need to be" +
                                " replaced.\n" +
                                "The minimum of two arguments need to be included in the execution string. There is" +
                                " no upper limit on the number of arguments allowed to be included.",
                        type = {DataType.STRING, DataType.INT, DataType.LONG, DataType.DOUBLE,
                                DataType.FLOAT, DataType.BOOL}),
        },
        returnAttributes =
            @ReturnAttribute(
                description = "The string that is returned after the templated positions are filled with the given" +
                        " values.",
                type = DataType.STRING),
        examples =
            @Example(
                    syntax = "str:fillTemplate(\"This is {{1}} for the {{2}} function\", " +
                            "'an example', 'fillTemplate')",
                    description = "" +
                        "In this example, the template is 'This is {{1}} for the {{2}} function'." +
                        "Here, the templated string {{1}} is replaced with the 1st " +
                        "string value provided, which is 'an example'.\n" +
                        "{{2}} is replaced with the 2nd string provided, which is 'fillTemplate'\n" +
                        "The complete return string is 'This is an example for the fillTemplate function'.")
)
public class FillTemplateFunctionExtension extends FunctionExecutor {

    private Pattern indexPattern = Pattern.compile("\\d+");

    private Pattern templatePattern = Pattern.compile("(\\{\\{\\d+}})");

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount <= 1) {
            throw new SiddhiAppValidationException("Invalid number of arguments passed to "
                    + "str:fillTemplate() function. Required at least 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:fillTemplate() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        String sourceString = (String) objects[0];
        Matcher templateMatcher = templatePattern.matcher(sourceString);
        String match;
        Matcher indexMatcher;
        int index;
        while (templateMatcher.find()) {
            match = templateMatcher.group(0);
            indexMatcher = indexPattern.matcher(match);
            if (indexMatcher.find()) {
                index = Integer.parseInt(indexMatcher.group(0));
                if (index < 1 || index >= objects.length) {
                    throw new SiddhiAppRuntimeException("Index given for template elements "
                            + "should be greater than 0 and less than '" + objects.length + "'. But found "
                            + index + " in" + " the template '" + sourceString + "'.");
                }
                sourceString = sourceString.replace(match, objects[index].toString());
            }
        }
        return sourceString;
    }

    @Override
    protected Object execute(Object o, State state) {
        return null;
    }

    @Override
    public Attribute.Type getReturnType() {
        return STRING;
    }
}
