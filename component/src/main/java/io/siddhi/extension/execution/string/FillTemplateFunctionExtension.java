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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.siddhi.query.api.definition.Attribute.Type.OBJECT;
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
        description = "This extension replaces the templated positions that are marked as " +
                "{{KEY}} from a VALUE. This VALUE retrieved from the given map object. " +
                "The VALUE corresponding to the KEY in the given map.",
        parameters = {
                @Parameter(name = "template",
                        description = "The string with templated fields that needs to be filled with the given " +
                                "strings. The format of the templated fields should be as follows:\n" +
                                "{{KEY}} where 'KEY' is a STRING. \n" +
                                "This KEY is used to map the strings that are given in the map object.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "replacement.map",
                        description = "A map with key-value pairs to be replaced.",
                        type = {DataType.OBJECT},
                        dynamic = true),
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"template", "replacement.map"})
        },
        returnAttributes =
        @ReturnAttribute(
                description = "The string that is returned after the templated positions are filled with the given" +
                        " values.",
                type = DataType.STRING),
        examples =
        @Example(
                syntax = "str:fillTemplate(\"{{prize}} > 100 && {{salary}} < 10000\", " +
                        "map:create('prize', 300, 'salary', 10000))",
                description = "" +
                        "In this example, the template is '{{prize}} > 100'." +
                        "Here, the templated string {{prize}} is replaced with the value corresponding " +
                        "to the 'prize' key in the given map.\n" +
                        "Likewise salary replace with the salary value of the map")
)
public class FillTemplateFunctionExtension extends FunctionExecutor {

    private Pattern indexPattern = Pattern.compile("\\w+");

    private Pattern templatePattern = Pattern.compile("(\\{\\{\\w+}})");

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 2) {
            throw new SiddhiAppValidationException("Invalid number of arguments passed to "
                    + "str:fillTemplate() function. Required exactly 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:fillTemplate() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != OBJECT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:fillTemplate() function, required " + OBJECT.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        String sourceString = (String) objects[0];
        Map<String, Object> valueMap = null;
        if (objects[1] instanceof HashMap) {
            valueMap = (HashMap<String, Object>) objects[1];
        }
        Matcher templateMatcher = templatePattern.matcher(sourceString);
        String match;
        Matcher indexMatcher;
        String key = "";
        while (templateMatcher.find()) {
            match = templateMatcher.group(0);
            indexMatcher = indexPattern.matcher(match);
            if (indexMatcher.find()) {
                key = indexMatcher.group(0);
                if (key == null || key.equals("")) {
                    throw new SiddhiAppRuntimeException("Key given for template elements "
                            + "should be greater not null or empty. But found "
                            + " in" + " the template '" + sourceString + "'.");
                }
                if (valueMap != null && valueMap.get(key) != null) {
                    sourceString = sourceString.replace(match, valueMap.get(key).toString());
                }
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
