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
import org.wso2.siddhi.core.executor.ConstantExpressionExecutor;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.query.selector.attribute.aggregator.AttributeAggregator;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.SiddhiAppValidationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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
        description = "Aggregates the received events by concatenating the keys of those events using a given " +
                "separator, e.g., comma (,) and hyphen (-), and returns the concatenated key string.",
        parameters = {
                @Parameter(name = "key",
                        description = "The string that need to be aggregated.",
                        type = {DataType.STRING}),
                @Parameter(name = "separator",
                        description = "The separator that separates each string key getting aggregated.",
                        type = {DataType.STRING}, optional = true, defaultValue = ","),
                @Parameter(name = "distinct",
                        description = "To only have distinct string keys in the the aggregation.",
                        type = {DataType.STRING}, optional = true, defaultValue = "false"),
                @Parameter(name = "order",
                        description = "Accepts 'ASC' or 'DESC' strings to sort the string keys " +
                                "by ascending or descending order.",
                        type = {DataType.STRING}, optional = true, defaultValue = "No order"),
        },
        returnAttributes = @ReturnAttribute(
                description = "Returns a string that is the result of the concatenated keys " +
                        "separated by the given separator",
                type = {DataType.STRING}),
        examples = {
                @Example(description = "This returns a string that is the result of the " +
                        "concatenated keys separated by the given separator. \n" +
                        "When we send events having values for the `key` `'A'`, `'B'`, `'S'`, `'C'`, `'A'` it will" +
                        " return `\"A,B,S,C,A\"` as the output",
                        syntax = "from InputStream#window.time(5 min)\n" +
                                "select str:groupConcat(\"key\") as groupedKeys\n" +
                                "input OutputStream;"),
                @Example(description = "This returns a string that is the result of the " +
                        "concatenated keys separated by the given separator. \n" +
                        "When we send events having values for the `key` `'A'`, `'B'`, `'S'`, `'C'`, `'A'` it will" +
                        " return `\"A-B-C-S\"` as the output",
                        syntax = "from InputStream#window.time(5 min)\n" +
                                "select groupConcat(\"key\",\"-\",true,\"ASC\") as groupedKeys\n" +
                                "input OutputStream;")
        }

)
public class GroupConcatFunctionExtension extends AttributeAggregator {

    private Attribute.Type returnType = Attribute.Type.STRING;
    private Map<Object, Integer> dataSet;
    private boolean distinct = false;
    private boolean canDistroy = true;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
        if (attributeExpressionExecutors.length < 1) {
            throw new SiddhiAppValidationException("str:groupConcat() function requires at mandatory `key` attribute,"
                    + " but found no attributes.");
        } else if (attributeExpressionExecutors.length > 4) {
            throw new SiddhiAppValidationException("str:groupConcat() function requires only `key`, `separator`, " +
                    "`distinct`, and `order` as attributes,"
                    + " but found " + attributeExpressionExecutors.length + " attributes");
        }

        if (attributeExpressionExecutors.length >= 2) {
            if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
                throw new SiddhiAppValidationException("str:groupConcat() function's second attribute `separator` " +
                        "should be a `STRING`, but found " + attributeExpressionExecutors[1].getReturnType() + " .");
            }
        }
        if (attributeExpressionExecutors.length >= 3) {
            if (attributeExpressionExecutors[2].getReturnType() != Attribute.Type.BOOL) {
                throw new SiddhiAppValidationException("str:groupConcat() function's third attribute `distinct` " +
                        "should be a constant `BOOL`, but found " + attributeExpressionExecutors[2].getReturnType()
                        + " .");
            }
            if (!(attributeExpressionExecutors[2] instanceof ConstantExpressionExecutor)) {
                throw new SiddhiAppValidationException("str:groupConcat() function's third attribute `distinct` " +
                        "should be a constant, but found a variable attribute.");
            }
            distinct = (Boolean) ((ConstantExpressionExecutor) attributeExpressionExecutors[2]).getValue();
        }
        if (attributeExpressionExecutors.length >= 4) {
            if (attributeExpressionExecutors[3].getReturnType() != Attribute.Type.STRING) {
                throw new SiddhiAppValidationException("str:groupConcat() function's forth attribute `order` " +
                        "should be a constant `STRING` having `'ASC'` or `'DESC'` values, " +
                        "but found " + attributeExpressionExecutors[3].getReturnType() + " .");
            }
            if (!(attributeExpressionExecutors[3] instanceof ConstantExpressionExecutor)) {
                throw new SiddhiAppValidationException("str:groupConcat() function's forth attribute `order` " +
                        "should be a constant having `'ASC'` or `'DESC'`, but found a variable attribute.");
            }
            String value = (String) ((ConstantExpressionExecutor) attributeExpressionExecutors[3]).getValue();
            if ("ASC".equalsIgnoreCase(value)) {
                dataSet = new TreeMap<Object, Integer>();
            } else if ("DESC".equalsIgnoreCase(value)) {
                dataSet = new TreeMap<Object, Integer>(Collections.reverseOrder());
            } else {
                throw new SiddhiAppValidationException("str:groupConcat() function's forth attribute `order` " +
                        "should be a constant `'ASC'` or `'DESC'`, but found '" + value + "'.");
            }
        } else {
            dataSet = new HashMap<>();
        }
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

    @Override
    public Object processAdd(Object o) {
        addString((String) o);
        return constructConcatString(",");
    }

    @Override
    public Object processAdd(Object[] objects) {
        addString((String) objects[0]);
        return constructConcatString((String) objects[1]);
    }

    @Override
    public Object processRemove(Object o) {
        removeString((String) o);
        return constructConcatString(",");
    }

    @Override
    public Object processRemove(Object[] objects) {
        removeString((String) objects[0]);
        return constructConcatString((String) objects[1]);
    }

    private void addString(String data) {
        Integer count = dataSet.get(data);
        if (count == null) {
            dataSet.put(data, 1);
        } else {
            dataSet.put(data, ++count);
        }
        canDistroy = false;
    }

    private void removeString(String data) {
        Integer count = dataSet.get(data);
        if (count == 1) {
            dataSet.remove(data);
        } else if (count > 1) {
            dataSet.put(data, --count);
        }
        if (dataSet.size() == 0) {
            canDistroy = true;
        }
    }

    private Object constructConcatString(String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        if (distinct) {
            for (Iterator<Object> iterator = dataSet.keySet().iterator(); iterator.hasNext(); ) {
                Object key = iterator.next();
                stringBuilder.append(key);
                if (iterator.hasNext()) {
                    stringBuilder.append(separator);
                }
            }
        } else {
            for (Iterator<Map.Entry<Object, Integer>> iterator = dataSet.entrySet().iterator();
                 iterator.hasNext(); ) {
                Map.Entry<Object, Integer> entry = iterator.next();
                for (int i = 0; i < entry.getValue(); i++) {
                    stringBuilder.append(entry.getKey());
                    if (i != entry.getValue() - 1) {
                        stringBuilder.append(separator);
                    }
                }
                if (iterator.hasNext()) {
                    stringBuilder.append(separator);
                }
            }
        }
        return stringBuilder.toString();
    }


    @Override
    public boolean canDestroy() {
        return canDistroy;
    }

    @Override
    public Object reset() {
        dataSet.clear();
        canDistroy = true;
        return "";
    }

    @Override
    public Map<String, Object> currentState() {
        HashMap<String, Object> state = new HashMap<>();
        state.put("dataSet", dataSet);
        return state;
    }

    @Override
    public void restoreState(Map<String, Object> state) {
        dataSet = (Map<Object, Integer>) state.get("dataSet");
    }
}
