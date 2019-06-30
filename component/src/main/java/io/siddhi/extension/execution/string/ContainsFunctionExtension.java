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
import org.apache.log4j.Logger;

import static io.siddhi.query.api.definition.Attribute.Type.BOOL;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

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
        description = "This function returns `true` if the`input.string` contains the specified sequence of char " +
                "values in the `search.string`. ",
        parameters = {
                @Parameter(name = "input.string",
                        description = "Input string value.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "search.string",
                        description = "The string value to be searched for in the `input.string`.",
                        type = {DataType.STRING},
                        dynamic = true)
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "search.string"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns the boolean output as `true` if the `input.string` contains the " +
                        "`search.string`. Otherwise, it returns `false`.", type = {DataType.BOOL}),
        examples = @Example(
                syntax = "contains(\"21 products are produced by WSO2 currently\", \"WSO2\")",
                description = "This returns a boolean value as the output. In this case, it returns" +
                "`true`.")

)
public class ContainsFunctionExtension extends FunctionExecutor {

    private static final Logger LOGGER = Logger.getLogger(ContainsFunctionExtension.class);

    Attribute.Type returnType = BOOL;

    @Override
    protected StateFactory<State> init(ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount != 2) {
            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:contains() function, "
                    + "required 2, but found " + executorsCount);
        }
        ExpressionExecutor executor1 = expressionExecutors[0];
        ExpressionExecutor executor2 = expressionExecutors[1];

        if (executor1.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of "
                    + "str:contains() function, required " + STRING.toString() + ", but found "
                    + executor1.getReturnType().toString());

        }
        if (executor2.getReturnType() != STRING) {
            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of "
                    + "str:contains() function,required " + STRING.toString() + ", but found "
                    + executor2.getReturnType().toString());
        }

        return null;
    }

    @Override
    protected Object execute(Object[] objects, State state) {
        if (objects[0] == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.warn("Invalid input given to str:contains() function. First argument cannot be null, " +
                        "returning false");
            }
            return false;
        }
        if (objects[1] == null) {
            throw new SiddhiAppRuntimeException("Invalid input given to str:contains() function. Second " +
                    "argument cannot be null");
        }
        String source = (String) objects[0];
        String sequenceToSearch = (String) objects[1];
        return source.contains(sequenceToSearch);
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
