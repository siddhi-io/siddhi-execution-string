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
 * substr(sourceText, beginIndex) or substr(sourceText, beginIndex, length) or substr(sourceText, regex)
 * or substr(sourceText, regex, groupNumber)
 * Returns a new string that is a substring of this string.
 * Accept Type(s): (STRING,INT) or (STRING,INT,INT) or (STRING,STRING) or (STRING,STRING,INT)
 * Return Type(s): STRING
 */

@Extension(
        name = "substr",
        namespace = "str",
        description = "Returns a new string that is a substring of this string",
        parameters = {
                @Parameter(name = "input.string",
                        description = "Input string to be processed.",
                        type = {DataType.STRING}),
                @Parameter(name = "begin.index",
                        description = "Staring index to consider for the substring.",
                        type = {DataType.INT}),
                @Parameter(name = "length",
                        description = "Length of the substring.",
                        type = {DataType.INT}),
                @Parameter(name = "regex",
                        description = "Regular expression used to match the input string..",
                        type = {DataType.STRING}),
                @Parameter(name = "group.number",
                        description = "Regex group number",
                        type = {DataType.INT})
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns a new string that is a substring of input.string.",
                type = {DataType.STRING}),
        examples = {
                @Example(description = "This will output the substring based on the given begin.index. In this case, " +
                        "output will be \"efghiJ KLMN\".", syntax = "substr(\"AbCDefghiJ KLMN\", 4)"),
                @Example(description = "This will output the substring based on the given begin.index and length. In " +
                        "this case, output will be \"CDef\".", syntax = "substr(\"AbCDefghiJ KLMN\",  2, 4) "),
                @Example(description = "This will output the substring by applying the regex. In this case, output " +
                        "will be \"WSO2D efghiJ KLMN\".", syntax = "substr(\"WSO2D efghiJ KLMN\", '^WSO2(.*)')"),
                @Example(description = "This will output the substring by applying the regex and considering the " +
                        "group.number. In this case, output will be \" ello\".",
                        syntax = "substr(\"WSO2 cep WSO2 XX E hi hA WSO2 heAllo\",  'WSO2(.*)A(.*)',  2)")
        }
)
public class SubstrFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;
    //state-variables
    private boolean isRegexConstant = false;
    private String regexConstant;
    private Pattern patternConstant;
    private SubstrType substrType;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "str:substr() function, " + "required " + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }
        if (attributeExpressionExecutors.length == 2) {
            if (attributeExpressionExecutors[1].getReturnType() == Attribute.Type.INT) {
                substrType = SubstrType.ONE;
            } else if (attributeExpressionExecutors[1].getReturnType() == Attribute.Type.STRING) {
                substrType = SubstrType.THREE;
                if (attributeExpressionExecutors[1] instanceof ConstantExpressionExecutor) {
                    isRegexConstant = true;
                    regexConstant = (String) ((ConstantExpressionExecutor) attributeExpressionExecutors[1]).getValue();
                    patternConstant = Pattern.compile(regexConstant);
                }
            } else {
                throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                        "str:substr() function, " + "required " + Attribute.Type.STRING + " or " + Attribute.Type.INT +
                        ", but found " + attributeExpressionExecutors[1].getReturnType().toString());
            }
        } else if (attributeExpressionExecutors.length == 3) {
            if (attributeExpressionExecutors[2].getReturnType() != Attribute.Type.INT) {
                throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of " +
                        "str:substr() function, " + "required " + Attribute.Type.INT + ", but found " +
                        attributeExpressionExecutors[2].getReturnType().toString());
            }
            if (attributeExpressionExecutors[1].getReturnType() == Attribute.Type.INT) {
                substrType = SubstrType.TWO;
            } else if (attributeExpressionExecutors[1].getReturnType() == Attribute.Type.STRING) {
                substrType = SubstrType.FOUR;
                if (attributeExpressionExecutors[1] instanceof ConstantExpressionExecutor) {
                    isRegexConstant = true;
                    regexConstant = (String) ((ConstantExpressionExecutor) attributeExpressionExecutors[1]).getValue();
                    patternConstant = Pattern.compile(regexConstant);
                }
            } else {
                throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                        "str:substr() function, " + "required " + Attribute.Type.STRING + " or " + Attribute.Type.INT +
                        ", but found " + attributeExpressionExecutors[1].getReturnType().toString());
            }
        } else {
            throw new SiddhiAppValidationException("Invalid no of Arguments passed to str:substr() function, " +
                    "required 2 or 3, but found "
                    + attributeExpressionExecutors.length);
        }
    }

    @Override
    protected Object execute(Object[] data) {
        int beginIndex;
        int length;
        int groupNo;
        String regex;
        String output = "";
        Pattern pattern;
        Matcher matcher;

        if (data[0] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:substr() function. " +
                    "First argument cannot be null");
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:substr() function. " +
                    "Second argument cannot be null");
        }
        String source = (String) data[0];

        switch (substrType) {
            case ONE:
                beginIndex = (Integer) data[1];
                output = source.substring(beginIndex);
                break;
            case TWO:
                if (data[2] == null) {
                    throw new SiddhiAppRuntimeException("Invalid input given to str:substr() function. " +
                            "Third argument cannot be null");
                }
                beginIndex = (Integer) data[1];
                length = (Integer) data[2];
                output = source.substring(beginIndex, (beginIndex + length));
                break;
            case THREE:
                if (!isRegexConstant) {
                    regex = (String) data[1];
                    pattern = Pattern.compile(regex);
                    matcher = pattern.matcher(source);
                    if (matcher.find()) {
                        output = matcher.group(0);
                    }
                } else {
                    matcher = patternConstant.matcher(source);
                    if (matcher.find()) {
                        output = matcher.group(0);
                    }
                }
                break;
            case FOUR:
                if (data[2] == null) {
                    throw new SiddhiAppRuntimeException("Invalid input given to str:substr() function. " +
                            "Third argument cannot be null");
                }
                groupNo = (Integer) data[2];
                if (!isRegexConstant) {
                    regex = (String) data[1];
                    pattern = Pattern.compile(regex);
                    matcher = pattern.matcher(source);
                    if (matcher.find()) {
                        output = matcher.group(groupNo);
                    }
                } else {
                    matcher = patternConstant.matcher(source);
                    if (matcher.find()) {
                        output = matcher.group(groupNo);
                    }
                }
                break;
        }
        return output;
    }

    @Override
    protected Object execute(Object data) {
        return null;  //Since the substr function takes in at least 2 parameters, this method does not get called.
        // Hence, not implemented.
    }

    @Override
    public void start() {
        //Nothing to start.
    }

    @Override
    public void stop() {
        //Nothing to stop.
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

    /*
    * Sub-string Types are as follows:
    * ONE: str:substr(<string sourceText> , <int beginIndex>)
    * TWO: str:substr(<string sourceText> , <int beginIndex>, <int length>)
    * THREE: str:substr(<string sourceText> , <string regex>)
    * FOUR: str:substr(<string sourceText> , <string regex>, <int groupNumber>)
    * */
    private enum SubstrType {
        ONE, TWO, THREE, FOUR
    }
}
