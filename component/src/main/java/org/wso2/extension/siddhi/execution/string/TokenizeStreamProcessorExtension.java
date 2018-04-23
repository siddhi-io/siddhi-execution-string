/*
 * Copyright (c)  2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
import org.wso2.siddhi.annotation.util.DataType;
import org.wso2.siddhi.core.config.SiddhiAppContext;
import org.wso2.siddhi.core.event.ComplexEventChunk;
import org.wso2.siddhi.core.event.stream.StreamEvent;
import org.wso2.siddhi.core.event.stream.StreamEventCloner;
import org.wso2.siddhi.core.event.stream.populater.ComplexEventPopulater;
import org.wso2.siddhi.core.exception.SiddhiAppCreationException;
import org.wso2.siddhi.core.exception.SiddhiAppRuntimeException;
import org.wso2.siddhi.core.executor.ConstantExpressionExecutor;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.query.processor.Processor;
import org.wso2.siddhi.core.query.processor.stream.StreamProcessor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.AbstractDefinition;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * tokenize(sourceText, delimiter)
 * Tokenize the source String by delimiters and return as tokens.​ ​
 * Accept Type(s): (STRING, STRING)
 * Return Type(s): STRING
 */
@Extension(
        name = "tokenize",
        namespace = "str",
        description = "This splits a string into words",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input text which should be split.",
                        type = {DataType.STRING}),
                @Parameter(name = "regex",
                        description = "The string value to be used to tokenize the 'input.string'.",
                        type = {DataType.STRING})
        },
        examples = @Example(
                syntax = "define stream inputStream (str string);\n" +
                        "@info(name = 'query1')\n" +
                        "from inputStream#str:tokenize(str , regex)\n" +
                        "select text\n" +
                        "insert into outputStream;",
                description = "This query performs tokenization for the given string.")
)

public class TokenizeStreamProcessorExtension extends StreamProcessor {
    Pattern regex;
    @Override
    protected void process(ComplexEventChunk<StreamEvent> streamEventChunk, Processor nextProcessor,
                           StreamEventCloner streamEventCloner, ComplexEventPopulater complexEventPopulater) {
        while (streamEventChunk.hasNext()) {
            StreamEvent streamEvent = streamEventChunk.next();
            String event = (String) attributeExpressionExecutors[0].execute(streamEvent);
            String[] words = regex.split(event);
            for (String word : words) {
                Object[] data = {word};
                complexEventPopulater.populateComplexEvent(streamEvent, data);
                nextProcessor.process(streamEventChunk);
            }
        }
    }

    /**
     * The initialization method for {@link StreamProcessor}, which will be called before other methods and validate
     * the all configuration and getting the initial values.
     *
     * @param attributeExpressionExecutors are the executors of each attributes in the Function
     * @param configReader                 this hold the {@link StreamProcessor} extensions configuration reader.
     * @param siddhiAppContext             Siddhi app runtime context
     */
    @Override
    protected List<Attribute> init(AbstractDefinition inputDefinition,
                                   ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                                   SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length == 2) {
            if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
                throw new SiddhiAppCreationException("Input string should be of type string. But found "
                        + attributeExpressionExecutors[0].getReturnType());
            }
            if (attributeExpressionExecutors[0] == null) {
                throw new SiddhiAppRuntimeException("Invalid input given to str:tokenize() function. " +
                        "Input.string argument cannot be null");
            }
            if (attributeExpressionExecutors[1].getReturnType() == Attribute.Type.STRING) {
                try {
                    regex = Pattern.compile((String)
                            ((ConstantExpressionExecutor) attributeExpressionExecutors[1]).getValue());
                } catch (PatternSyntaxException pse) {
                    throw new SiddhiAppValidationException("Syntax error in regular-expression pattern : " +
                            pse.getMessage());
                }
            } else {
                throw new SiddhiAppCreationException("Regex should be of type string. But found "
                        + attributeExpressionExecutors[1].getReturnType());
            }
            if (attributeExpressionExecutors[1] == null) {
                throw new SiddhiAppRuntimeException("Invalid input given to str:tokenize() function. " +
                        "Regex argument cannot be null");
            }
        } else {
            throw new IllegalArgumentException(
                    "Invalid no of arguments passed to str:tokenize() function, "
                            + "required 2, but found " + attributeExpressionExecutors.length);
        }
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("token", Attribute.Type.STRING));
        return attributes;
    }

    @Override
    public void start() {
        //Do nothing
    }

    @Override
    public void stop() {
        //Do nothing
    }

    @Override
    public Map<String, Object> currentState() {
        return null;
    }

    @Override
    public void restoreState(Map<String, Object> state) {
        //Do nothing
    }
}
