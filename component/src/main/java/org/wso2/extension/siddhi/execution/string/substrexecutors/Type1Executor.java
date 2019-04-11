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
package org.wso2.extension.siddhi.execution.string.substrexecutors;

/**
 * Type1Executor returns a new string that is a substring of given
 * string. Type1Executor begins with the character at the specified
 * index and extends to the end of this string.
 *
 * str:substr([string sourceText] , [int beginIndex]).
 */
public class Type1Executor extends SubstrExecutor<Integer, Object> {

    @Override
    public boolean canIgnoreArg2() {
        return true;
    }

    @Override
    public String execute(String source, Integer begin, Object arg2) {
        return source.substring(begin);
    }
}
