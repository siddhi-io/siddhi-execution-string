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
package io.siddhi.extension.execution.string;

import io.siddhi.annotation.Example;
import io.siddhi.annotation.Extension;
import io.siddhi.annotation.Parameter;
import io.siddhi.annotation.ParameterOverload;
import io.siddhi.annotation.ReturnAttribute;
import io.siddhi.annotation.util.DataType;
import io.siddhi.core.config.SiddhiQueryContext;
import io.siddhi.core.event.ComplexEventChunk;
import io.siddhi.core.event.stream.MetaStreamEvent;
import io.siddhi.core.event.stream.StreamEvent;
import io.siddhi.core.event.stream.StreamEventCloner;
import io.siddhi.core.event.stream.holder.StreamEventClonerHolder;
import io.siddhi.core.event.stream.populater.ComplexEventPopulater;
import io.siddhi.core.exception.SiddhiAppCreationException;
import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.core.executor.ConstantExpressionExecutor;
import io.siddhi.core.executor.ExpressionExecutor;
import io.siddhi.core.query.processor.ProcessingMode;
import io.siddhi.core.query.processor.Processor;
import io.siddhi.core.query.processor.stream.StreamProcessor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.AbstractDefinition;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static io.siddhi.query.api.definition.Attribute.Type.BOOL;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * tokenize(sourceText, delimiter)
 * Tokenize the source String by delimiters and return as tokens.​ ​
 * Accept Type(s): (STRING, STRING)
 * Return Type(s): STRING
 */
@Extension(
        name = "tokenize",
        namespace = "str",
        description = "This function splits the input string into tokens using a given regular expression and " +
                "returns the split tokens.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string which needs to be split.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "regex",
                        description = "The string value which is used to tokenize the 'input.string'.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "distinct",
                        description = "This flag is used to return only distinct values.",
                        type = {DataType.BOOL},
                        optional = true,
                        dynamic = true,
                        defaultValue = "false")
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"input.string", "regex"}),
                @ParameterOverload(parameterNames = {"input.string", "regex", "distinct"})
        },
        returnAttributes = {
                @ReturnAttribute(
                        name = "token",
                        description = "The attribute which contains a single token.",
                        type = {DataType.STRING}
                )
        },
        examples = @Example(
                syntax = "define stream inputStream (str string);\n" +
                        "@info(name = 'query1')\n" +
                        "from inputStream#str:tokenize(str , ',')\n" +
                        "select token\n" +
                        "insert into outputStream;",
                description = "This query performs tokenization on the given string. If the str is " +
                        "\"Android,Windows8,iOS\", then the string is split into 3 events " +
                        "containing the `token` attribute values, i.e., " +
                        "`Android`, `Windows8` and `iOS`."
        )
)

public class TokenizeStreamProcessorExtension extends StreamProcessor<TokenizeStreamProcessorExtension.ExtensionState> {

    private Pattern regex;

    private boolean distinct = false;

    private static final List<Attribute> ATTRIBUTES;

    static {
        ATTRIBUTES = new ArrayList<>();
        ATTRIBUTES.add(new Attribute("token", STRING));
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    protected void process(ComplexEventChunk<StreamEvent> complexEventChunk,
                           Processor processor,
                           StreamEventCloner streamEventCloner,
                           ComplexEventPopulater complexEventPopulater,
                           ExtensionState extensionState) {
        ExpressionExecutor executor = attributeExpressionExecutors[0];

        while (complexEventChunk.hasNext()) {
            StreamEvent event = complexEventChunk.next();
            String[] words = regex.split((String) executor.execute(event));
            // If the "distinct" flag is set true, remove all duplicate entries from the words list.
            if (this.distinct) {
                Set<String> distinctWords = new LinkedHashSet<>();
                Collections.addAll(distinctWords, words);
                words = distinctWords.toArray(new String[0]);
            }

            for (String word : words) {
                Object[] data = {word};
                complexEventPopulater.populateComplexEvent(event, data);
                processor.process(complexEventChunk);
            }
        }
    }

    /**
     * The initialization method for {@link StreamProcessor}, which will be called before other methods and validate
     * the all configuration and getting the initial values.
     */
    @Override
    @SuppressWarnings("fallthrough")
    protected StateFactory<ExtensionState> init(MetaStreamEvent metaStreamEvent,
                                                AbstractDefinition abstractDefinition,
                                                ExpressionExecutor[] expressionExecutors,
                                                ConfigReader configReader,
                                                StreamEventClonerHolder streamEventClonerHolder,
                                                boolean b0,
                                                boolean b1,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        switch (executorsCount) {
            case 3:
                ExpressionExecutor executor3 = expressionExecutors[2];

                if (isType(executor3, BOOL)) {
                    this.distinct = (Boolean) ((ConstantExpressionExecutor) executor3).getValue();
                } else {
                    throw new SiddhiAppCreationException("Third attribute 'distinct' should be of type boolean. "
                            + "But found " + executor3.getReturnType());
                }
            /* Fall through */
            case 2:
                ExpressionExecutor executor1 = expressionExecutors[0];
                ExpressionExecutor executor2 = expressionExecutors[1];

                if (executor1 == null || executor2 == null) {
                    throw new SiddhiAppRuntimeException("Invalid input given to str:tokenize() function. "
                            + "Input.string argument cannot be null");
                }

                if (!isType(executor1, STRING)) {
                    throw new SiddhiAppCreationException("Input string should be of type string. But found "
                            + executor1.getReturnType());
                }
                if (isType(executor2, STRING)) {
                    try {
                        regex = Pattern.compile((String) ((ConstantExpressionExecutor) executor2).getValue());
                    } catch (PatternSyntaxException e) {
                        throw new SiddhiAppValidationException("Syntax error in regular-expression pattern : "
                                + e.getMessage());
                    }
                } else {
                    throw new SiddhiAppCreationException("Input string should be of type string. But found "
                            + executor2.getReturnType());
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid no of arguments passed to str:tokenize() function, "
                        + "required 2 or 3, but found " + executorsCount);
        }
        return () -> new ExtensionState();
    }

    private boolean isType(ExpressionExecutor executor, Attribute.Type type) {
        return executor.getReturnType() == type;
    }

    @Override
    public List<Attribute> getReturnAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public ProcessingMode getProcessingMode() {
        return ProcessingMode.BATCH;
    }

    static class ExtensionState extends State {
        @Override
        public boolean canDestroy() {
            return false;
        }

        @Override
        public Map<String, Object> snapshot() {
            return null;
        }

        @Override
        public void restore(Map<String, Object> map) {
        }
    }
}
