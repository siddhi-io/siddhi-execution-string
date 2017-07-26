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

import org.apache.log4j.Logger;
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
 * contains(inputSequence, searchingSequence)
 * This method returns true if and only if this string contains the specified sequence of char values.
 * searchingSequence - the sequence to search for. eg: "WSO2"
 * inputSequence - the input string eg: "21 products are produced by WSO2 currently"
 * Accept Type(s) for contains(inputSequence, searchingSequence);
 * inputSequence : STRING
 * searchingSequence : STRING
 * Return Type(s): BOOLEAN
 */

@Extension(
        name = "contains",
        namespace = "str",
        description = "This method returns `true` if the`input.string` contains the specified sequence of char " +
                "values in the `search.string`. ",
        parameters = {
                @Parameter(name = "input.string",
                        description = "Input string value.",
                        type = {DataType.STRING}),
                @Parameter(name = "search.string",
                        description = "The string value to be searched for in the `input.string`.",
                        type = {DataType.STRING})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the boolean output as `true` if the `input.string` contains the " +
                        "`search.string`. Otherwise, it returns `false`.", type = {DataType.BOOL}),
        examples = @Example(description = "This returns a boolean value as the output. In this case, it returns" +
                "`true`.", syntax = "contains(\"21 products are produced by WSO2 currently\", \"WSO2\")")
)
public class ContainsFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.BOOL;

    private static final Logger LOGGER = Logger.getLogger(ContainsFunctionExtension.class);

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:contains() function, " +
                    "required 2, but found " + attributeExpressionExecutors.length);
        }
        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "str:contains() function, required " + Attribute.Type.STRING + ", " +
                    "but found " + attributeExpressionExecutors[0].getReturnType().toString());
        }
        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "str:contains() function, required " + Attribute.Type.STRING + ", " +
                    "but found " + attributeExpressionExecutors[1].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.warn("Invalid input given to str:contains() function. First argument cannot be null, " +
                        "returning false");
            }
            return false;
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:contains() function. Second " +
                    "argument cannot be null");
        }
        String source = (String) data[0];
        String sequenceToSearch = (String) data[1];
        return source.contains(sequenceToSearch);

    }

    @Override
    protected Object execute(Object data) {
        return null;
        //Since the contains function takes in 2 parameters, this method does not get called. Hence,
        // not implemented.
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

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
