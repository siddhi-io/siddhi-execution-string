/*
~   Copyright (c) WSO2 Inc. (http://wso2.com) All Rights Reserved.
~
~   Licensed under the Apache License, Version 2.0 (the "License");
~   you may not use this file except in compliance with the License.
~   You may obtain a copy of the License at
~
~        http://www.apache.org/licenses/LICENSE-2.0
~
~   Unless required by applicable law or agreed to in writing, software
~   distributed under the License is distributed on an "AS IS" BASIS,
~   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
~   See the License for the specific language governing permissions and
~   limitations under the License.
*/

var logo = document.querySelector('.md-logo');
var logoTitle = logo.title;
logo.setAttribute('href', 'https://siddhi.io/')

var header = document.querySelector('.md-header-nav__title');
var headerContent = document.querySelectorAll('.md-header-nav__title span')[1].textContent;
header.innerHTML = '<a class="extension-title" href="/">' + logoTitle + '</a>' + '<a class="extension-title-low">'+headerContent+'</a>'

    