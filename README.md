siddhi-execution-string
======================================

The **siddhi-execution-string extension** is an extension to <a target="_blank" href="https://wso2.github.io/siddhi">Siddhi</a> that 
provides basic string handling capabilities such as con-cat, length, convert to lowercase, replace all, etc.

Find some useful links below:

* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string">Source code</a>
* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/releases">Releases</a>
* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/issues">Issue tracker</a>

## Latest API Docs 

Latest API Docs is <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT">4.0.3-SNAPSHOT</a>.

## How to use 

**Using the extension in <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a>**

* You can use this extension in the latest <a target="_blank" href="https://github.com/wso2/product-sp/releases">WSO2 Stream Processor</a> that is a part of <a target="_blank" href="http://wso2.com/analytics?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 Analytics</a> offering, with editor, debugger and simulation support. 

* This extension is shipped by default with WSO2 Stream Processor, if you wish to use an alternative version of this extension you can replace the component <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/releases">jar</a> that can be found in the `<STREAM_PROCESSOR_HOME>/lib` directory.

**Using the extension as a <a target="_blank" href="https://wso2.github.io/siddhi/documentation/running-as-a-java-library">java library</a>**

* This extension can be added as a maven dependency along with other Siddhi dependencies to your project.

```
     <dependency>
        <groupId>org.wso2.extension.siddhi.execution.string</groupId>
        <artifactId>siddhi-execution-string</artifactId>
        <version>x.x.x</version>
     </dependency>
```

## Jenkins Build Status

---

|  Branch | Build Status |
| :------ |:------------ | 
| master  | [![Build Status](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-string/badge/icon)](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-string/) |

---

## Features

* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#hex-function">hex</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns a hexadecimal string representation of str,<br>&nbsp;where each byte of each character in str is converted to two hexadecimal digits</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#coalesce-function">coalesce</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns the value of the first of its input parameters that is not null</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#split-function">split</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Splits the source string by <code>split.string</code> and returns the substring specified via the <code>group.number</code>.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#replacefirst-function">replaceFirst</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Replaces the first substring of this string that matches the given expression, with the given replacement.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#concat-function">concat</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns a string that is the result of concatenating two or more string values.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#reverse-function">reverse</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns the reverse ordered string of the input.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#lower-function">lower</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Converts the capital letters in the input string to the equivalent simple letters.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#strcmp-function">strcmp</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Compares two strings lexicographically.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#length-function">length</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns the length of this string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#regexp-function">regexp</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns whether  this 'string' matches the given regular expression 'regex' or not.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#upper-function">upper</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Converts the simple letters in the input string to the equivalent capital/block letters.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#repeat-function">repeat</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Repeats a string for a specified number of times.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#equalsignorecase-function">equalsIgnoreCase</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Compares two strings lexicographically.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#substr-function">substr</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>This returns a new string that is a substring of this string</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#trim-function">trim</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns a copy of the string with leading and trailing whitespace omitted</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#charat-function">charAt</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Returns the char value as a string value at the specified index.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#unhex-function">unhex</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p><code>unhex(str)</code> interprets each pair of characters in the argument as a hexadecimal number<br>&nbsp;and converts it to the byte represented by the number</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#contains-function">contains</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>This method returns <code>true</code> if the<code>input.string</code> contains the specified sequence of char values in the <code>search.string</code>. </p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.3-SNAPSHOT/#replaceall-function">replaceAll</a> *(<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#functions">Function</a>)*<br><div style="padding-left: 1em;"><p>Replaces each substring of this string that matches the given expression with the given replacement.</p></div>

## How to Contribute
 
  * Please report issues at <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/issues">GitHub Issue Tracker</a>.
  
  * Send your contributions as pull requests to <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/tree/master">master branch</a>. 
 
## Contact us 

 * Post your questions with the <a target="_blank" href="http://stackoverflow.com/search?q=siddhi">"Siddhi"</a> tag in <a target="_blank" href="http://stackoverflow.com/search?q=siddhi">Stackoverflow</a>. 
 
 * Siddhi developers can be contacted via the mailing lists:
 
    Developers List   : [dev@wso2.org](mailto:dev@wso2.org)
    
    Architecture List : [architecture@wso2.org](mailto:architecture@wso2.org)
 
## Support 

* We are committed to ensuring support for this extension in production. Our unique approach ensures that all support leverages our open development methodology and is provided by the very same engineers who build the technology. 

* For more details and to take advantage of this unique opportunity contact us via <a target="_blank" href="http://wso2.com/support?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">http://wso2.com/support/</a>. 
