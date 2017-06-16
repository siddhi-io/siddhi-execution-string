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
 * split(sourceText, splitCharacter, returnedOutputPosition)
 * Splits the source String by splitCharacter and return the string in the index given by returnedOutputPosition​ ​
 * Accept Type(s): (STRING, STRING, INT)
 * Return Type(s): STRING
 */

@Extension(
        name = "split",
        namespace = "str",
        description = "Splits the source String by splitCharacter and return the string in the index" +
                " given by returnedOutputPosition .",
        returnAttributes = @ReturnAttribute(
                description = "TBD",
                type = {DataType.STRING}),
        examples = @Example(description = "TBD", syntax = "TBD")
)
public class SplitFunctionExtension extends FunctionExecutor {

    private Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length != 3) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:split() function, " +
                    "required 3, but found " + attributeExpressionExecutors.length);
        }

        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
                    "str:split() function, " + "required " + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[0].getReturnType().toString());
        }


        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "str:split() function, " + "required " + Attribute.Type.STRING + ", but found " +
                    attributeExpressionExecutors[1].getReturnType().toString());
        }

        if (attributeExpressionExecutors[2].getReturnType() != Attribute.Type.INT) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
                    "str:split() function, " + "required " + Attribute.Type.INT + ", but found " +
                    attributeExpressionExecutors[2].getReturnType().toString());
        }

    }

    @Override
    protected Object execute(Object[] data) {
        if (data[0] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:split() function. " +
                    "First argument cannot be null");
        }
        if (data[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:split() function. " +
                    "Second argument cannot be null");
        }
        if (data[2] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:split() function. " +
                    "Third argument cannot be null");
        }
        String source = (String) data[0];
        String regex = (String) data[1];
        int index = (Integer) data[2];
        String splitStrArray[] = source.split(regex);
        try {
            return splitStrArray[index];
        } catch (IndexOutOfBoundsException e) {
            throw new SiddhiAppRuntimeException("Index argument " + index + " is negative or not less than the " +
                    "length of the given string " + source, e);
            //Runtime Exception was captured to avoid checking whether index < splitStrArray.length
            // for performance reasons.​
        }
    }

    @Override
    protected Object execute(Object data) {
        return null;  //Since the split function takes in 3 parameters, this method does not get called.
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
}
