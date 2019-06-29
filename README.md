Siddhi Execution String
======================================

  [![Jenkins Build Status](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-string/badge/icon)](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-string/)
  [![GitHub (pre-)Release](https://img.shields.io/github/release/siddhi-io/siddhi-execution-string/all.svg)](https://github.com/siddhi-io/siddhi-execution-string/releases)
  [![GitHub (Pre-)Release Date](https://img.shields.io/github/release-date-pre/siddhi-io/siddhi-execution-string.svg)](https://github.com/siddhi-io/siddhi-execution-string/releases)
  [![GitHub Open Issues](https://img.shields.io/github/last-commit/siddhi-io/siddhi-execution-string.svg)](https://github.com/siddhi-io/siddhi-execution-string/issues)
  [![GitHub Last Commit](https://img.shields.io/github/issues-raw/siddhi-io/siddhi-execution-string.svg)](https://github.com/siddhi-io/siddhi-execution-string/commits/master)
  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

The **siddhi-execution-string extension** is a <a target="_blank" href="https://siddhi.io/">Siddhi</a> extension that provides basic string handling capabilities such as concat, length, replace all, etc.

For information on <a target="_blank" href="https://siddhi.io/">Siddhi</a> and it's features refer <a target="_blank" href="https://siddhi.io/redirect/docs.html">Siddhi Documentation</a>. 

## Download

* Versions 5.x and above with group id `io.siddhi.extension.*` from <a target="_blank" href="https://mvnrepository.com/artifact/io.siddhi.extension.execution.string/siddhi-execution-string/">here</a>.
* Versions 4.x and lower with group id `org.wso2.extension.siddhi.*` from <a target="_blank" href="https://mvnrepository.com/artifact/org.wso2.extension.siddhi.execution.string/siddhi-execution-string">here</a>.

## Latest API Docs 

Latest API Docs is <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-string/api/4.0.12">4.0.12</a>.

## Features

* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#charat-function">charAt</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the char value as a string value at the specified index.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#coalesce-function">coalesce</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the value of the first of its input parameters that is not null</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#concat-function">concat</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a string that is the result of concatenating two or more string values.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#contains-function">contains</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>This method returns <code>true</code> if the<code>input.string</code> contains the specified sequence of char values in the <code>search.string</code>. </p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#equalsignorecase-function">equalsIgnoreCase</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Compares two strings lexicographically.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#hex-function">hex</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a hexadecimal string representation of str,<br>&nbsp;where each byte of each character in str is converted to two hexadecimal digits</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#length-function">length</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the length of this string.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#lower-function">lower</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Converts the capital letters in the input string to the equivalent simple letters.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#regexp-function">regexp</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns whether  this 'string' matches the given regular expression 'regex' or not.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#repeat-function">repeat</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Repeats a string for a specified number of times.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#replaceall-function">replaceAll</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Replaces each substring of this string that matches the given expression with the given replacement.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#replacefirst-function">replaceFirst</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Replaces the first substring of this string that matches the given expression, with the given replacement.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#reverse-function">reverse</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns the reverse ordered string of the input.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#split-function">split</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Splits the source string by <code>split.string</code> and returns the substring specified via the <code>group.number</code>.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#strcmp-function">strcmp</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Compares two strings lexicographically.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#substr-function">substr</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>This returns a new string that is a substring of this string</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#trim-function">trim</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Returns a copy of the string with leading and trailing whitespace omitted</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#unhex-function">unhex</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p><code>unhex(str)</code> interprets each pair of characters in the argument as a hexadecimal number<br>&nbsp;and converts it to the byte represented by the number</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#upper-function">upper</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#function">(Function)</a>*<br><div style="padding-left: 1em;"><p>Converts the simple letters in the input string to the equivalent capital/block letters.</p></div>
* <a target="_blank" href="https://wso2-extensions.github.io/siddhi-execution-string/api/4.0.12/#tokenize-stream-processor">tokenize</a> *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#stream-processor">(Stream Processor)</a>*<br><div style="padding-left: 1em;"><p>This splits a string into words</p></div>

## Dependencies 

There are no other dependencies needed for this extension. 

## Installation

For installing this extension on various siddhi execution environments refer Siddhi documentation section on <a target="_blank" href="https://siddhi.io/redirect/add-extensions.html">adding extensions</a>.

## Support and Contribution

* We encourage users to ask questions and get support via <a target="_blank" href="https://stackoverflow.com/questions/tagged/siddhi">StackOverflow</a>, make sure to add the `siddhi` tag to the issue for better response.

* If you find any issues related to the extension please report them on <a target="_blank" href="https://github.com/siddhi-io/siddhi-execution-string/issues">the issue tracker</a>.

* For production support and other contribution related information refer <a target="_blank" href="https://siddhi.io/community/">Siddhi Community</a> documentation.