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
package io.siddhi.extension.execution.string.substrexecutors;

import java.util.regex.Pattern;

/**
 * SubstrExecutor class is the abstract view of substr execution strategy.
 *
 * @param <T1> the type of the first argument after source.
 * @param <T2> the type of the second argument after source.
 */
public abstract class SubstrExecutor<T1, T2> {

    final Pattern pattern;

    SubstrExecutor() {
        pattern = null;
    }

    SubstrExecutor(Pattern pattern) {
        this.pattern = pattern;
    }

    boolean hasPattern() {
        return pattern != null;
    }

    public abstract boolean canIgnoreArg2();

    @SuppressWarnings("unchecked")
    public T1 castArg1(Object arg1) {
        if (arg1 == null) {
            return null;
        }
        return (T1) arg1;
    }

    @SuppressWarnings("unchecked")
    public T2 castArg2(Object arg2) {
        if (arg2 == null) {
            return null;
        }
        return (T2) arg2;
    }

    public abstract String execute(String source, T1 arg1, T2 arg2);
}
