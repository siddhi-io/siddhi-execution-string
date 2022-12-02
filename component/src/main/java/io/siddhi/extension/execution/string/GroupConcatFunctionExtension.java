/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
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
import io.siddhi.core.query.processor.ProcessingMode;
import io.siddhi.core.query.selector.attribute.aggregator.AttributeAggregatorExecutor;
import io.siddhi.core.util.config.ConfigReader;
import io.siddhi.core.util.snapshot.state.State;
import io.siddhi.core.util.snapshot.state.StateFactory;
import io.siddhi.query.api.definition.Attribute;
import io.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

import static io.siddhi.query.api.definition.Attribute.Type.BOOL;
import static io.siddhi.query.api.definition.Attribute.Type.STRING;

/**
 * groupConcat(string1, 'separator', bool, 'ASC/DESC')
 * Returns concated string for all the events separated by the given separator.
 * Accept Type(s): STRING. The string that need to be aggregated.
 * Accept Type(s): STRING. The separator that separates each string that gets aggregated.
 * Accept Type(s): BOOL. To only have distinct string keys in the the aggregation..
 * Accept Type(s): STRING. Accepts 'ASC' or 'DESC' strings to sort the string keys by ascending or descending order.
 * Return Type(s): STRING
 */
@Extension(
        name = "groupConcat",
        namespace = "str",
        description = "This function aggregates the received events by concatenating the keys in those events " +
                "using a separator, e.g.,a comma (,) or a hyphen (-), and returns the concatenated key string.",
        parameters = {
                @Parameter(name = "key",
                        description = "The string that needs to be aggregated.",
                        type = {DataType.STRING},
                        dynamic = true),
                @Parameter(name = "separator",
                        description = "The separator that separates each string key after concatenating the keys.",
                        type = {DataType.STRING}, optional = true, defaultValue = ",", dynamic = true),
                @Parameter(name = "distinct",
                        description = "This is used to only have distinct values in the concatenated " +
                                "string that is returned.",
                        type = {DataType.BOOL}, optional = true, defaultValue = "false", dynamic = true),
                @Parameter(name = "order",
                        description = "This parameter accepts 'ASC' or 'DESC' strings to sort the string keys " +
                                "in either ascending or descending order respectively.",
                        type = {DataType.STRING}, optional = true, defaultValue = "No order", dynamic = true),
        },
        parameterOverloads = {
                @ParameterOverload(parameterNames = {"key"}),
                @ParameterOverload(parameterNames = {"key", "..."}),
                @ParameterOverload(parameterNames = {"key", "separator", "distinct"}),
                @ParameterOverload(parameterNames = {"key", "separator", "distinct", "order"})
        },
        returnAttributes = @ReturnAttribute(
                description = "This returns a string,with keys from multiple events concatenated and, " +
                        "separated by a given separator.",
                type = {DataType.STRING}),
        examples = {
                @Example(
                        syntax = "from InputStream#window.time(5 min)\n" +
                                "select str:groupConcat(\"key\") as groupedKeys\n" +
                                "input OutputStream;",
                        description = "When we input events having values for the `key` as `'A'`, " +
                                "`'B'`, `'S'`, `'C'`, `'A'`, it" +
                                " returns `\"A,B,S,C,A\"` to the 'OutputStream'."),
                @Example(
                        syntax = "from InputStream#window.time(5 min)\n" +
                                "select groupConcat(\"key\",\"-\",true,\"ASC\") as groupedKeys\n" +
                                "input OutputStream;",
                        description = "When we input events having values for the `key` as `'A'`, `'B'`, `'S'`, " +
                                "`'C'`, `'A'`, specify the seperator as hyphen and choose the order to be " +
                                "ascending, the function returns `\"A-B-C-S\"` to the 'OutputStream'.")
        }

)
public class GroupConcatFunctionExtension
        extends AttributeAggregatorExecutor<GroupConcatFunctionExtension.ExtensionState> {

    private static final String KEY_DATA_MAP = "dataMap";
    private boolean distinct = false;
    private String order = null;

    @Override
    @SuppressWarnings("fallthrough")
    protected StateFactory<ExtensionState> init(ExpressionExecutor[] expressionExecutors,
                                                ProcessingMode processingMode,
                                                boolean b,
                                                ConfigReader configReader,
                                                SiddhiQueryContext siddhiQueryContext) {
        int executorsCount = expressionExecutors.length;

        if (executorsCount < 1) {
            throw new SiddhiAppValidationException("str:groupConcat() function requires at "
                    + "mandatory `key` attribute, but found no attributes.");
        }
        if (executorsCount > 4) {
            throw new SiddhiAppValidationException("str:groupConcat() function requires only "
                    + "`key`, `separator`, `distinct`, and `order` as attributes, but found "
                    + attributeExpressionExecutors.length + " attributes");
        }

        switch (executorsCount) {
            case 4: {
                ExpressionExecutor executor = expressionExecutors[3];
                if (!isType(executor, STRING)) {
                    throw new SiddhiAppValidationException("str:groupConcat() function's forth attribute `order` "
                            + "should be a constant `STRING` having `'ASC'` or `'DESC'` values, "
                            + "but found " + executor.getReturnType() + " .");
                }
                if (!isConstantAttribute(executor)) {
                    throw new SiddhiAppValidationException("str:groupConcat() function's forth attribute `order` "
                            + "should be a constant having `'ASC'` or `'DESC'`, but found a variable attribute.");
                }
                order = ((String) ((ConstantExpressionExecutor) executor).getValue()).toUpperCase();
            }
            /* Fall through */
            case 3: {
                ExpressionExecutor executor = expressionExecutors[2];
                if (!isType(executor, BOOL)) {
                    throw new SiddhiAppValidationException("str:groupConcat() function's third attribute `distinct` "
                            + "should be a constant `BOOL`, but found " + executor.getReturnType() + " .");
                }
                if (!isConstantAttribute(executor)) {
                    throw new SiddhiAppValidationException("str:groupConcat() function's third attribute `distinct` "
                            + "should be a constant, but found a variable attribute.");
                }
                distinct = (Boolean) ((ConstantExpressionExecutor) executor).getValue();
            }
            /* Fall through */
            case 2: {
                ExpressionExecutor executor = expressionExecutors[1];
                if (!isType(executor, STRING)) {
                    throw new SiddhiAppValidationException("str:groupConcat() function's second attribute `separator` "
                            + "should be a `STRING`, but found " + executor.getReturnType() + " .");
                }
            }
        }

        return () -> new ExtensionState();
    }

    private boolean isType(ExpressionExecutor executor, Attribute.Type type) {
        return executor.getReturnType() == type;
    }

    private boolean isConstantAttribute(ExpressionExecutor executor) {
        return executor instanceof ConstantExpressionExecutor;
    }

    @Override
    public Object processAdd(Object o, ExtensionState extensionState) {
        if (o == null) {
            return extensionState.currentValue();
        }
        addString(String.valueOf(o), extensionState);
        return constructConcatString(",", extensionState);
    }

    @Override
    public Object processAdd(Object[] objects, ExtensionState extensionState) {
        addString(String.valueOf(objects[0]), extensionState);
        return constructConcatString(String.valueOf(objects[1]), extensionState);
    }

    @Override
    public Object processRemove(Object o, ExtensionState extensionState) {
        removeString(String.valueOf(0), extensionState);
        return constructConcatString(",", extensionState);
    }

    @Override
    public Object processRemove(Object[] objects, ExtensionState extensionState) {
        removeString(String.valueOf(objects[0]), extensionState);
        return constructConcatString(String.valueOf(objects[1]), extensionState);
    }

    private void addString(String data, ExtensionState extensionState) {
        Integer count = extensionState.dataMap.get(data);
        extensionState.dataMap.put(data, (count == null) ? 1 : count + 1);
    }

    private void removeString(String data, ExtensionState extensionState) {
        Integer count = extensionState.dataMap.get(data);
        if (count == 1) {
            extensionState.dataMap.remove(data);
        } else if (count > 1) {
            extensionState.dataMap.put(data, count - 1);
        }
    }

    private Object constructConcatString(String separator, ExtensionState extensionState) {
        StringJoiner joiner = new StringJoiner(separator);

        if (distinct) {
            for (Object key : extensionState.dataMap.keySet()) {
                joiner.add(String.valueOf(key));
            }
            return joiner.toString();
        }

        for (Map.Entry<Object, Integer> entry : extensionState.dataMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                joiner.add(String.valueOf(entry.getKey()));
            }
        }

        return joiner.toString();
    }

    @Override
    public Object reset(ExtensionState extensionState) {
        extensionState.dataMap.clear();
        return "";
    }

    @Override
    public Attribute.Type getReturnType() {
        return STRING;
    }

    class ExtensionState extends State {

        private final Map<String, Object> state;
        private Map<Object, Integer> dataMap;

        private ExtensionState() {
            this.state = new HashMap<>();
            this.dataMap = new LinkedHashMap<>();
            if (order != null) {
                switch (order) {
                    case "ASC":
                        this.dataMap = new TreeMap<>();
                        break;
                    case "DESC":
                        dataMap = new TreeMap<>(Collections.reverseOrder());
                        break;
                    default:
                        throw new SiddhiAppValidationException("str:groupConcat() function's forth attribute `order` "
                                + "should be a constant `'ASC'` or `'DESC'`, but found '" + order + "'.");
                }
            }
            state.put(KEY_DATA_MAP, dataMap);
        }

        @Override
        public boolean canDestroy() {
            return dataMap.isEmpty();
        }

        @Override
        public Map<String, Object> snapshot() {
            return state;
        }

        @Override
        public void restore(Map<String, Object> map) {
            dataMap = (Map<Object, Integer>) map.get(KEY_DATA_MAP);
        }

        private Object currentValue() {
            if (dataMap.isEmpty()) {
                return "";
            }
            return constructConcatString(",", this);
        }
    }
}
