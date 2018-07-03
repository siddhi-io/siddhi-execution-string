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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * replaceAll(string, regex, replacement)
 * Replaces each substring of this string that matches the given expression with the given replacement.
 * Accept Type(s): (STRING,STRING,STRING)
 * Return Type(s): STRING
 */

@Extension(
        name = "fillTemplate",
        namespace = "str",
        description = "Replaces the templated positions in a given template with provided strings which matches with" +
                "the index in the template.",
        parameters = {
                @Parameter(name = "template",
                        description = "The templated string that needs to be filled with the given strings.",
                        type = {DataType.STRING}),
                @Parameter(name = "replacement.strings",
                        description = "Strings to replace the templated positions in the template.\n" +
                                "There can be any number of strings from the 2nd argument, as comma separated values.",
                        type = {DataType.STRING}),
        },
        returnAttributes =
            @ReturnAttribute(
                description = "String in which templated positions filled with the given strings.",
                type = DataType.STRING),
        examples =
            @Example(
                description = "" +
                        "In this example, the templated string with index 1 ({{1}}) will be replaced with the 1st " +
                        "string value provided ('an example').\n" +
                        "{{2}} will be replaced with the 2nd string provided ('fillTemplate')\n" +
                        "The return string will be 'This is an example for the fillTemplate function'.",
                syntax = "fillTemplate(\"This is {{1}} for the {{2}} function\",  'an example', 'fillTemplate')")
)
public class FillTemplateFunctionExtension extends FunctionExecutor {
    Pattern templatePattern = Pattern.compile("(\\{\\{\\d}})");
    Pattern indexPattern = Pattern.compile("\\d");

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length <= 1) {
            throw new SiddhiAppValidationException("Invalid number of arguments passed to " +
                    "str:fillTemplate() function. " +
                    "Required at least 2, but found " + attributeExpressionExecutors.length);
        }

        for (int i = 0; i < attributeExpressionExecutors.length; i++) {
            if (attributeExpressionExecutors[i].getReturnType() != Attribute.Type.STRING) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the argument no " + (i + 1) +
                        ". All the parameters should be in type " + Attribute.Type.STRING + ", but found " +
                        attributeExpressionExecutors[i].getReturnType().toString());
            }
        }
    }

    @Override
    protected Object execute(Object[] data) {
        String source = (String) data[0];
        Matcher templateMatcher = templatePattern.matcher(source);
        while (templateMatcher.find()) {
            String match = templateMatcher.group(0);
            Matcher indexMatcher = indexPattern.matcher(match);
            if (indexMatcher.find()) {
                int index = Integer.parseInt(indexMatcher.group(0));
                if (index < 1 || index >= data.length) {
                    throw new SiddhiAppRuntimeException("Index given for template elements should be greater than 0 " +
                            "and less than '" + data.length + "'.");
                }
                source = source.replace(match, (String) data[index]);
            }
        }
        return source;
    }

    @Override
    protected Object execute(Object data) {
        //Since the replaceAll function take 3 parameters, this method does not get called.
        // Hence, not implemented.
        return null;
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
