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
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.executor.function.FunctionExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Map;

import static io.siddhi.query.api.definition.Attribute.Type.OBJECT;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * fillTemplate(string, replacement)
 * Replaces each substring of this string that matches the given expression with the given replacement.
 * Accept Type(s): (STRING,STRING|INT|LONG|FLOAT|DOUBLE|BOOL,STRING|INT|LONG|FLOAT|DOUBLE|BOOL, ...)
 * or (STRING,OBJECT)
 * Return Type(s): STRING
 */

@Extension(
        name = "fillTemplate",
        namespace = "str",
        description = "fillTemplate(string, map) will replace all the keys in the string using values in the map. " +
                "fillTemplate(string, r1, r2 ..) replace all the entries {{1}}, {{2}}, {{3}} with r1 , r2, r3.",
        parameters = {
                @Parameter(name = "template",
                        description = "The string with templated fields that needs to be filled with the given " +
                                "strings. The format of the templated fields should be as follows:\n" +
                                "{{KEY}} where 'KEY' is a STRING if you are using fillTemplate(string, map)\n" +
                                "{{KEY}} where 'KEY' is an INT if you are using fillTemplate(string, r1, r2 ..)\n" +
                                "This KEY is used to map the values",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "replacement.type",
                        description = "A set of arguments with any type string|int|long|double|float|bool.",
                        type = {DataType.STRING, DataType.INT, DataType.LONG, DataType.DOUBLE,
                                DataType.FLOAT, DataType.BOOL},
                        dynamic = true,
                        optional = true,
                        defaultValue = "-"
                ),
                @Parameter(name = "map",
                        description = "A map with key-value pairs to be replaced.",
                        type = {DataType.OBJECT},
                        dynamic = true,
                        optional = true,
                        defaultValue = "-"
                ),
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"template", "replacement.type", "..."}),
                @ParameterOverload(parameterNames = {"template", "map"})
        },
        returnAttributes =
        @ReturnAttribute(
                description = "The string that is returned after the templated positions are filled with the given" +
                        " values.",
                type = DataType.STRING),
        examples = {
                @Example(
                        syntax = "str:fillTemplate(\"{{prize}} > 100 && {{salary}} < 10000\", " +
                                "map:create('prize', 300, 'salary', 10000))",
                        description = "" +
                                "In this example, the template is '{{prize}} > 100 && {{salary}} < 10000\'." +
                                "Here, the templated string {{prize}} is replaced with the value corresponding " +
                                "to the 'prize' key in the given map.\n" +
                                "Likewise salary replace with the salary value of the map"
                ),
                @Example(
                syntax = "str:fillTemplate(\"{{1}} > 100 && {{2}} < 10000\", " +
                        "200, 300)",
                description = "" +
                        "In this example, the template is '{{1}} > 100 && {{2}} < 10000'." +
                        "Here, the templated string {{1}} is replaced with the corresponding " +
                        "1st value 200.\n" +
                        "Likewise {{2}} replace with the 300"
                )
        }

)
public class FillTemplateFunctionExtension extends FunctionExecutor {

    private boolean isTemplateConstant = false;
    private String[] templateSplitArray;
    private static final String SPLIT_TEMPLATE = "\\{\\{|}}";

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount < 2) {
            throw new SiddhiAppValidationException("Invalid number of arguments passed to "
                    + "str:fillTemplate() function. Required at least 2, but found " + executorsCount);
        } else {
            if (expressionExecutors[0].getReturnType() != STRING) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                        + "str:fillTemplate() function, required " + STRING.toString() + ", but found "
                        + expressionExecutors[0].getReturnType().toString());
            } else if (attributeExpressionExecutors[0] instanceof ConstantExpressionExecutor) {
                isTemplateConstant = true;
                ConstantExpressionExecutor constantExpressionExecutor =
                        (ConstantExpressionExecutor) attributeExpressionExecutors[0];
                String constantTemplate = String.valueOf(constantExpressionExecutor.getValue());
                templateSplitArray = constantTemplate.split(SPLIT_TEMPLATE);
            }
            if (expressionExecutors[1].getReturnType() == OBJECT && executorsCount > 2) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                            + "str:fillTemplate() function, only allowed str:fillTemplate(STRING, MAP) or " +
                            "str:fillTemplate(STRING, STRING, ...) formats.");
            }
        }
        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        String sourceString = (String) objects[0];
        int index;
        String key;
        StringBuilder stringBuilder = new StringBuilder();
        if (!isTemplateConstant) {
            templateSplitArray = sourceString.split(SPLIT_TEMPLATE);
        }
        if (objects[1] instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) objects[1];
            for (int i = 1; i < templateSplitArray.length; i = i + 2) {
                key = templateSplitArray[i].trim();
                if (valueMap.get(key) != null) {
                    templateSplitArray[i] = String.valueOf(valueMap.get(key));
                }
            }
        } else {
            for (int i = 1; i < templateSplitArray.length; i = i + 2) {
                index = Integer.parseInt(templateSplitArray[i].trim());
                if (objects[index] != null) {
                    templateSplitArray[i] = String.valueOf(objects[index]);
                }
            }
        }
        for (String s: templateSplitArray) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
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
