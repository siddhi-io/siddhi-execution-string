siddhi-execution-string
======================================

The **siddhi-execution-string** is an extension to <a target="_blank" href="https://wso2.github.io/siddhi">Siddhi</a> that 
provides basic string handling capabilities such as con-cat, length, convert to lowercase, and replace all.

For more information, see:

* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string">Source code</a>
* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/releases">Releases</a>
* <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/issues">Issue tracker</a>

## Latest API Docs 

Latest API Docs is <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22">4.0.22</a>.

## How to use 

**Using the extension with <a target="_blank" href="https://github.com/wso2/product-sp">WSO2 Stream Processor</a>**

* You can use this extension with the latest <a target="_blank" href="https://github.com/wso2/product-sp/releases">WSO2 Stream Processor</a> the <a target="_blank" href="http://wso2.com/analytics?utm_source=gitanalytics&utm_campaign=gitanalytics_Jul17">WSO2 Analytics</a> offering, which supports an editor, debugger, and simulator. 

* By default, the latest version of this extension is shipped with WSO2 Stream Processor. If you wish to use an alternative version of this extension, replace the component <a target="_blank" href="https://github.com/wso2-extensions/siddhi-execution-string/releases">jar</a> that is available at the `<STREAM_PROCESSOR_HOME>/lib` directory.

**Using the extension as a <a target="_blank" href="https://wso2.github.io/siddhi/documentation/running-as-a-java-library">java library</a>**

* This extension can be added as a maven dependency to your project along with other Siddhi dependencies.

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

* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#groupconcat-aggregate-function">groupConcat</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#aggregate-function">(Aggregate Function)</a>*<br><div style="padding-left: 1em;"><p>Aggregates the received events by concatenating the keys of those events using a given separator, e.g., comma (,) and hyphen (-), and returns the concatenated key string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#charat-function">charAt</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the char value that exists at the given index position of the input string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#coalesce-function">coalesce</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p> Returns the first input parameter value that is not null of the given argument.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#concat-function">concat</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a string that is the result of concatenating two or more input string values.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#contains-function">contains</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns <code>true</code> if the<code>input.string</code> contains the specified sequence of char values in the <code>search.string</code>. </p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#equalsignorecase-function">equalsIgnoreCase</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a boolean value by comparing two strings lexicographically without considering the letter case.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#filltemplate-function">fillTemplate</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>This extension replaces the templated positions that are marked with an index value in a specified template with the strings provided.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#hex-function">hex</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a hexadecimal string by converting each byte of each character in the input string to two hexadecimal digits.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#length-function">length</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the length of the input string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#lower-function">lower</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Converts the capital letters in the input string to the equivalent simple letters.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#regexp-function">regexp</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a boolean value based on the matchability of the input string and the given regular expression.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#repeat-function">repeat</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Repeats the input string for a specified number of times.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#replaceall-function">replaceAll</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Finds all the substrings of the input string that matches with the given expression, and replaces them with the given replacement string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#replacefirst-function">replaceFirst</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Finds the first substring of the input string that matches with the given regular expression, and replaces itwith the given replacement string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#reverse-function">reverse</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the input string in the reverse order character-wise and string-wise.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#split-function">split</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Splits the  <code>input.string</code> into substrings using the value parsed in the <code>split.string</code> and returns the substring at the position specified in the <code>group.number</code>.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#strcmp-function">strcmp</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Compares two strings lexicographically and returns an integer value. If both strings are equal, 0 is returned. If  the first string is lexicographically greater than the second string, a positive value is returned. If the first string is lexicographically greater than the second string, a negative value is returned.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#substr-function">substr</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a substring of the input string by considering a subset or all of the following factors: starting index, length, regular expression, and regex group number.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#trim-function">trim</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a copy of the input string without the leading and trailing whitespace (if any).</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#unhex-function">unhex</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a string by converting the hexadecimal characters in the input string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#upper-function">upper</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Converts the simple letters in the input string to the equivalent capital/block letters.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.22/#tokenize-stream-processor">tokenize</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#stream-processor">(Stream Processor)</a>*<br><div style="padding-left: 1em;"><p>Splits the input string to tokens using the given regular expression and returns the resultant tokens.</p></div>

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
