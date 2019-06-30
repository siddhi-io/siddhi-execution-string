# API Docs - v5.0.3

!!! Info "Tested Siddhi Core version: *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/">5.0.2-SNAPSHOT</a>*"
    It could also support other Siddhi Core minor versions.

## Core

### and *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the results of AND operation for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> and(<BOOL> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be AND operation.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from cscStream#window.lengthBatch(10)
select and(isFraud) as isFraudTransaction
insert into alertStream;
```
<p style="word-wrap: break-word">This will returns the result for AND operation of isFraud values as a boolean value for event chunk expiry by window length batch.</p>

### avg *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Calculates the average for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<DOUBLE> avg(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that need to be averaged.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream#window.timeBatch
 select avg(temp) as avgTemp
 insert into barStream;
```
<p style="word-wrap: break-word">avg(temp) returns the average temp value for all the events based on their arrival and expiry.</p>

### count *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the count of all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<LONG> count()
<LONG> count(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">This function accepts one parameter. It can belong to any one of the available types.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream#window.timeBatch(10 sec)
select count() as count
insert into barStream;
```
<p style="word-wrap: break-word">This will return the count of all the events for time batch in 10 seconds.</p>

### distinctCount *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">This returns the count of distinct occurrences for a given arg.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<LONG> distinctCount(<INT|LONG|DOUBLE|FLOAT|STRING> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The object for which the number of distinct occurences needs to be counted.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select distinctcount(pageID) as count
insert into barStream;
```
<p style="word-wrap: break-word">distinctcount(pageID) for the following output returns '3' when the available values are as follows.<br>&nbsp;"WEB_PAGE_1"<br>&nbsp;"WEB_PAGE_1"<br>&nbsp;"WEB_PAGE_2"<br>&nbsp;"WEB_PAGE_3"<br>&nbsp;"WEB_PAGE_1"<br>&nbsp;"WEB_PAGE_2"<br>&nbsp;The three distinct occurences identified are 'WEB_PAGE_1', 'WEB_PAGE_2', and 'WEB_PAGE_3'.</p>

### max *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the maximum value for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT> max(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be compared to find the maximum value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream#window.timeBatch(10 sec)
select max(temp) as maxTemp
insert into barStream;
```
<p style="word-wrap: break-word">max(temp) returns the maximum temp value recorded for all the events based on their arrival and expiry.</p>

### maxForever *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">This is the attribute aggregator to store the maximum value for a given attribute throughout the lifetime of the query regardless of any windows in-front.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT> maxForever(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be compared to find the maximum value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from inputStream
select maxForever(temp) as max
insert into outputStream;
```
<p style="word-wrap: break-word">maxForever(temp) returns the maximum temp value recorded for all the events throughout the lifetime of the query.</p>

### min *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the minimum value for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT> min(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be compared to find the minimum value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from inputStream
select min(temp) as minTemp
insert into outputStream;
```
<p style="word-wrap: break-word">min(temp) returns the minimum temp value recorded for all the events based on their arrival and expiry.</p>

### minForever *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">This is the attribute aggregator to store the minimum value for a given attribute throughout the lifetime of the query regardless of any windows in-front.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT> minForever(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be compared to find the minimum value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from inputStream
select minForever(temp) as max
insert into outputStream;
```
<p style="word-wrap: break-word">minForever(temp) returns the minimum temp value recorded for all the events throughoutthe lifetime of the query.</p>

### or *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the results of OR operation for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> or(<BOOL> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be OR operation.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from cscStream#window.lengthBatch(10)
select or(isFraud) as isFraudTransaction
insert into alertStream;
```
<p style="word-wrap: break-word">This will returns the result for OR operation of isFraud values as a boolean value for event chunk expiry by window length batch.</p>

### stdDev *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the calculated standard deviation for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<DOUBLE> stdDev(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that should be used to calculate the standard deviation.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from inputStream
select stddev(temp) as stdTemp
insert into outputStream;
```
<p style="word-wrap: break-word">stddev(temp) returns the calculated standard deviation of temp for all the events based on their arrival and expiry.</p>

### sum *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Returns the sum for all the events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<LONG|DOUBLE> sum(<INT|LONG|DOUBLE|FLOAT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The value that needs to be summed.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from inputStream
select sum(volume) as sumOfVolume
insert into outputStream;
```
<p style="word-wrap: break-word">This will returns the sum of volume values as a long value for each event arrival and expiry.</p>

### unionSet *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">Union multiple sets. <br>&nbsp;This attribute aggregator maintains a union of sets. The given input set is put into the union set and the union set is returned.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> unionSet(<OBJECT> set)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">set</td>
        <td style="vertical-align: top; word-wrap: break-word">The java.util.Set object that needs to be added into the union set.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from stockStream 
select createSet(symbol) as initialSet 
insert into initStream 

from initStream#window.timeBatch(10 sec) 
select unionSet(initialSet) as distinctSymbols 
insert into distinctStockStream;
```
<p style="word-wrap: break-word">distinctStockStream will return the set object which contains the distinct set of stock symbols received during a sliding window of 10 seconds.</p>

### UUID *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Generates a UUID (Universally Unique Identifier).</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> UUID()
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from TempStream
select convert(roomNo, 'string') as roomNo, temp, UUID() as messageID
insert into RoomTempStream;
```
<p style="word-wrap: break-word">This will converts a room number to string, introducing a message ID to each event asUUID() returns a34eec40-32c2-44fe-8075-7f4fde2e2dd8<br><br>from TempStream<br>select convert(roomNo, 'string') as roomNo, temp, UUID() as messageID<br>insert into RoomTempStream;</p>

### cast *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Converts the first parameter according to the cast.to parameter. Incompatible arguments cause Class Cast exceptions if further processed. This function is used with map extension that returns attributes of the object type. You can use this function to cast the object to an accurate and concrete type.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> cast(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> to.be.caster, <STRING> cast.to)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">to.be.caster</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies the attribute to be casted.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">cast.to</td>
        <td style="vertical-align: top; word-wrap: break-word">A string constant parameter expressing the cast to type using one of the following strings values: int, long, float, double, string, bool.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select symbol as name, cast(temp, 'double') as temp
insert into barStream;
```
<p style="word-wrap: break-word">This will cast the fooStream temp field value into 'double' format.</p>

### coalesce *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the value of the first input parameter that is not null, and all input parameters have to be on the same type.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> coalesce(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg, <INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> ...)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">This function accepts one or more parameters. They can belong to any one of the available types. All the specified parameters should be of the same type.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select coalesce('123', null, '789') as value
insert into barStream;
```
<p style="word-wrap: break-word">This will returns first null value 123.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select coalesce(null, 76, 567) as value
insert into barStream;
```
<p style="word-wrap: break-word">This will returns first null value 76.</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
from fooStream
select coalesce(null, null, null) as value
insert into barStream;
```
<p style="word-wrap: break-word">This will returns null as there are no notnull values.</p>

### convert *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Converts the first input parameter according to the convertedTo parameter.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT|STRING|BOOL> convert(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL> to.be.converted, <STRING> converted.to)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">to.be.converted</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies the value to be converted.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">converted.to</td>
        <td style="vertical-align: top; word-wrap: break-word">A string constant parameter to which type the attribute need to be converted  using one of the following strings values: 'int', 'long', 'float', 'double', 'string', 'bool'.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select convert(temp, 'double') as temp
insert into barStream;
```
<p style="word-wrap: break-word">This will convert fooStream temp value into 'double'.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select convert(temp, 'int') as temp
insert into barStream;
```
<p style="word-wrap: break-word">This will convert fooStream temp value into 'int' (value = "convert(45.9, 'int') returns 46").</p>

### createSet *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Includes the given input parameter in a java.util.HashSet and returns the set. </p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> createSet(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL> input)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input</td>
        <td style="vertical-align: top; word-wrap: break-word">The input that needs to be added into the set.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from stockStream 
select createSet(symbol) as initialSet 
insert into initStream;
```
<p style="word-wrap: break-word">For every incoming stockStream event, the initStream stream will produce a set object having only one element: the symbol in the incoming stockStream.</p>

### currentTimeMillis *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the current timestamp of siddhi application in milliseconds.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<LONG> currentTimeMillis()
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select symbol as name, currentTimeMillis() as eventTimestamp 
insert into barStream;
```
<p style="word-wrap: break-word">This will extract current siddhi application timestamp.</p>

### default *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks if the 'attribute' parameter is null and if so returns the value of the 'default' parameter</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> default(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> attribute, <INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> default)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">attribute</td>
        <td style="vertical-align: top; word-wrap: break-word">The attribute that could be null.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">default</td>
        <td style="vertical-align: top; word-wrap: break-word">The default value that will be used when 'attribute' parameter is null</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from TempStream
select default(temp, 0.0) as temp, roomNum
insert into StandardTempStream;
```
<p style="word-wrap: break-word">This will replace TempStream's temp attribute with default value if the temp is null.</p>

### eventTimestamp *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the timestamp of the processed event.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<LONG> eventTimestamp()
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select symbol as name, eventTimestamp() as eventTimestamp 
insert into barStream;
```
<p style="word-wrap: break-word">This will extract current events timestamp.</p>

### ifThenElse *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Evaluates the 'condition' parameter and returns value of the 'if.expression' parameter if the condition is true, or returns value of the 'else.expression' parameter if the condition is false. Here both 'if.expression' and 'else.expression' should be of the same type.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> ifThenElse(<BOOL> condition, <INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> if.expression, <INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> else.expression)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">condition</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies the if then else condition value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">if.expression</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies the value to be returned if the value of the condition parameter is true.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">else.expression</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies the value to be returned if the value of the condition parameter is false.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@info(name = 'query1')
from sensorEventStream
select sensorValue, ifThenElse(sensorValue>35,'High','Low') as status
insert into outputStream;
```
<p style="word-wrap: break-word">This will returns High if sensorValue = 50.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
@info(name = 'query1')
from sensorEventStream
select sensorValue, ifThenElse(voltage < 5, 0, 1) as status
insert into outputStream;
```
<p style="word-wrap: break-word">This will returns 1 if voltage= 12.</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
@info(name = 'query1')
from userEventStream
select userName, ifThenElse(password == 'admin', true, false) as passwordState
insert into outputStream;
```
<p style="word-wrap: break-word">This will returns  passwordState as true if password = admin.</p>

### instanceOfBoolean *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks whether the parameter is an instance of Boolean or not.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> instanceOfBoolean(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The parameter to be checked.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select instanceOfBoolean(switchState) as state
insert into barStream;
```
<p style="word-wrap: break-word">This will return true if the value of switchState is true.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select instanceOfBoolean(value) as state
insert into barStream;
```
<p style="word-wrap: break-word">if the value = 32 then this will returns false as the value is not an instance of the boolean.</p>

### instanceOfDouble *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks whether the parameter is an instance of Double or not.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> instanceOfDouble(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The parameter to be checked.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select instanceOfDouble(value) as state
insert into barStream;
```
<p style="word-wrap: break-word">This will return true if the value field format is double ex : 56.45.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select instanceOfDouble(switchState) as state
insert into barStream;
```
<p style="word-wrap: break-word">if the switchState = true then this will returns false as the value is not an instance of the double.</p>

### instanceOfFloat *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks whether the parameter is an instance of Float or not.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> instanceOfFloat(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The parameter to be checked.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select instanceOfFloat(value) as state
insert into barStream;
```
<p style="word-wrap: break-word">This will return true if the value field format is float ex : 56.45f.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select instanceOfFloat(switchState) as state
insert into barStream;
```
<p style="word-wrap: break-word">if the switchState = true then this will returns false as the value is an instance of the boolean not a float.</p>

### instanceOfInteger *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks whether the parameter is an instance of Integer or not.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> instanceOfInteger(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The parameter to be checked.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select instanceOfInteger(value) as state
insert into barStream;
```
<p style="word-wrap: break-word">This will return true if the value field format is integer.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select instanceOfInteger(switchState) as state
insert into barStream;
```
<p style="word-wrap: break-word">if the switchState = true then this will returns false as the value is an instance of the boolean not a long.</p>

### instanceOfLong *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks whether the parameter is an instance of Long or not.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> instanceOfLong(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The parameter to be checked.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select instanceOfLong(value) as state
insert into barStream;
```
<p style="word-wrap: break-word">This will return true if the value field format is long ex : 56456l.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select instanceOfLong(switchState) as state
insert into barStream;
```
<p style="word-wrap: break-word">if the switchState = true then this will returns false as the value is an instance of the boolean not a long.</p>

### instanceOfString *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Checks whether the parameter is an instance of String or not.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> instanceOfString(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">The parameter to be checked.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream
select instanceOfString(value) as state
insert into barStream;
```
<p style="word-wrap: break-word">This will return true if the value field format is string ex : 'test'.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream
select instanceOfString(switchState) as state
insert into barStream;
```
<p style="word-wrap: break-word">if the switchState = true then this will returns false as the value is an instance of the boolean not a string.</p>

### maximum *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the maximum value of the input parameters.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT> maximum(<INT|LONG|DOUBLE|FLOAT> arg, <INT|LONG|DOUBLE|FLOAT> ...)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">This function accepts one or more parameters. They can belong to any one of the available types. All the specified parameters should be of the same type.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@info(name = 'query1') from inputStream
select maximum(price1, price2, price3) as max
insert into outputStream;
```
<p style="word-wrap: break-word">This will returns the maximum value of the input parameters price1, price2, price3.</p>

### minimum *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the minimum value of the input parameters.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT> minimum(<INT|LONG|DOUBLE|FLOAT> arg, <INT|LONG|DOUBLE|FLOAT> ...)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word">This function accepts one or more parameters. They can belong to any one of the available types. All the specified parameters should be of the same type.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@info(name = 'query1') from inputStream
select maximum(price1, price2, price3) as max
insert into outputStream;
```
<p style="word-wrap: break-word">This will returns the minimum value of the input parameters price1, price2, price3.</p>

### sizeOfSet *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the size of an object of type java.util.Set.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT> sizeOfSet(<OBJECT> set)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">set</td>
        <td style="vertical-align: top; word-wrap: break-word">The set object. This parameter should be of type java.util.Set. A set object may be created by the 'set' attribute aggregator in Siddhi. </td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from stockStream 
select initSet(symbol) as initialSet 
insert into initStream; 

;from initStream#window.timeBatch(10 sec) 
select union(initialSet) as distinctSymbols 
insert into distinctStockStream; 

from distinctStockStream 
select sizeOfSet(distinctSymbols) sizeOfSymbolSet 
insert into sizeStream;
```
<p style="word-wrap: break-word">The sizeStream stream will output the number of distinct stock symbols received during a sliding window of 10 seconds.</p>

### pol2Cart *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#stream-function">(Stream Function)</a>*
<p style="word-wrap: break-word">The pol2Cart function calculating the cartesian coordinates x & y for the given theta, rho coordinates and adding them as new attributes to the existing events.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
pol2Cart()
pol2Cart(<DOUBLE> theta, <DOUBLE> rho)
pol2Cart(<DOUBLE> theta, <DOUBLE> rho, <DOUBLE> z)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">theta</td>
        <td style="vertical-align: top; word-wrap: break-word">The theta value of the coordinates.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">rho</td>
        <td style="vertical-align: top; word-wrap: break-word">The rho value of the coordinates.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">z</td>
        <td style="vertical-align: top; word-wrap: break-word">z value of the cartesian coordinates.</td>
        <td style="vertical-align: top">If z value is not given, drop the third parameter of the output.</td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from PolarStream#pol2Cart(theta, rho)
select x, y 
insert into outputStream ;
```
<p style="word-wrap: break-word">This will return cartesian coordinates (4.99953024681082, 0.06853693328228748) for theta: 0.7854 and rho: 5.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from PolarStream#pol2Cart(theta, rho, 3.4)
select x, y, z 
insert into outputStream ;
```
<p style="word-wrap: break-word">This will return cartesian coordinates (4.99953024681082, 0.06853693328228748, 3.4)for theta: 0.7854 and rho: 5 and z: 3.4.</p>

### log *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#stream-processor">(Stream Processor)</a>*
<p style="word-wrap: break-word">The logger logs the message on the given priority with or without processed event.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
log()
log(<STRING> log.message)
log(<BOOL> is.event.logged)
log(<STRING> log.message, <BOOL> is.event.logged)
log(<STRING> priority, <STRING> log.message)
log(<STRING> priority, <STRING> log.message, <BOOL> is.event.logged)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">priority</td>
        <td style="vertical-align: top; word-wrap: break-word">The priority/type of this log message (INFO, DEBUG, WARN, FATAL, ERROR, OFF, TRACE).</td>
        <td style="vertical-align: top">INFO</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">log.message</td>
        <td style="vertical-align: top; word-wrap: break-word">This message will be logged.</td>
        <td style="vertical-align: top"><siddhi app name> :</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">is.event.logged</td>
        <td style="vertical-align: top; word-wrap: break-word">To log the processed event.</td>
        <td style="vertical-align: top">true</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from fooStream#log("INFO", "Sample Event :", true)
select *
insert into barStream;
```
<p style="word-wrap: break-word">This will log as INFO with the message "Sample Event :" + fooStream:events.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from fooStream#log("Sample Event :", true)
select *
insert into barStream;
```
<p style="word-wrap: break-word">This will logs with default log level as INFO.</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
from fooStream#log("Sample Event :", fasle)
select *
insert into barStream;
```
<p style="word-wrap: break-word">This will only log message.</p>

<span id="example-4" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 4</span>
```
from fooStream#log(true)
select *
insert into barStream;
```
<p style="word-wrap: break-word">This will only log fooStream:events.</p>

<span id="example-5" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 5</span>
```
from fooStream#log()
select *
insert into barStream;
```
<p style="word-wrap: break-word">This will only log fooStream:events.</p>

<span id="example-6" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 6</span>
```
from fooStream#log("Sample Event :")
select *
insert into barStream;
```
<p style="word-wrap: break-word">This will log message and fooStream:events.</p>

### batch *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A window that holds an incoming events batch. When a new set of events arrives, the previously arrived old events will be expired. Batch window can be used to aggregate events that comes in batches. If it has the parameter length specified, then batch window process the batch as several chunks.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
batch()
batch(<INT> window.length)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.length</td>
        <td style="vertical-align: top; word-wrap: break-word">The length of a chunk</td>
        <td style="vertical-align: top">If length value was not given it assign 0 as length and process the whole batch as once</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream consumerItemStream (itemId string, price float)

from consumerItemStream#window.batch()
select price, str:groupConcat(itemId) as itemIds
group by price
insert into outputStream;
```
<p style="word-wrap: break-word">This will output comma separated items IDs that have the same price for each incoming batch of events.</p>

### cron *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">This window outputs the arriving events as and when they arrive, and resets (expires) the window periodically based on the given cron expression.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
cron(<STRING> cron.expression)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">cron.expression</td>
        <td style="vertical-align: top; word-wrap: break-word">The cron expression that resets the window.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InputEventStream (symbol string, price float, volume int);

@info(name = 'query1')
from InputEventStream#cron('*/5 * * * * ?')
select symbol, sum(price) as totalPrice 
insert into OutputStream;
```
<p style="word-wrap: break-word">This let the totalPrice to gradually increase and resets to zero as a batch every 5 seconds.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
define stream StockEventStream (symbol string, price float, volume int)
define window StockEventWindow (symbol string, price float, volume int) cron('*/5 * * * * ?');

@info(name = 'query0')
from StockEventStream
insert into StockEventWindow;

@info(name = 'query1')
from StockEventWindow 
select symbol, sum(price) as totalPrice
insert into OutputStream ;
```
<p style="word-wrap: break-word">The defined window will let the totalPrice to gradually increase and resets to zero as a batch every 5 seconds.</p>

### delay *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A delay window holds events for a specific time period that is regarded as a delay period before processing them.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
delay(<INT|LONG|TIME> window.delay)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.delay</td>
        <td style="vertical-align: top; word-wrap: break-word">The time period (specified in sec, min, ms) for which  the window should delay the events.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define window delayWindow(symbol string, volume int) delay(1 hour);
define stream PurchaseStream(symbol string, volume int);
define stream DeliveryStream(symbol string);
define stream OutputStream(symbol string);

@info(name='query1') 
from PurchaseStream
select symbol, volume
insert into delayWindow;

@info(name='query2') 
from delayWindow join DeliveryStream
on delayWindow.symbol == DeliveryStream.symbol
select delayWindow.symbol
insert into OutputStream;
```
<p style="word-wrap: break-word">In this example, purchase events that arrive in the 'PurchaseStream' stream are directed to a delay window. At any given time, this delay window holds purchase events that have arrived within the last hour. These purchase events in the window are matched by the 'symbol' attribute, with delivery events that arrive in the 'DeliveryStream' stream. This monitors whether the delivery of products is done with a minimum delay of one hour after the purchase.</p>

### externalTime *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A sliding time window based on external time. It holds events that arrived during the last windowTime period from the external timestamp, and gets updated on every monotonically increasing timestamp.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
externalTime(<LONG> timestamp, <INT|LONG|TIME> window.time)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">timestamp</td>
        <td style="vertical-align: top; word-wrap: break-word">The time which the window determines as current time and will act upon. The value of this parameter should be monotonically increasing.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">LONG</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">window.time</td>
        <td style="vertical-align: top; word-wrap: break-word">The sliding time period for which the window should hold events.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define window cseEventWindow (symbol string, price float, volume int) externalTime(eventTime, 20 sec) output expired events;

@info(name = 'query0')
from cseEventStream
insert into cseEventWindow;

@info(name = 'query1')
from cseEventWindow
select symbol, sum(price) as price
insert expired events into outputStream ;
```
<p style="word-wrap: break-word">processing events arrived within the last 20 seconds from the eventTime and output expired events.</p>

### externalTimeBatch *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A batch (tumbling) time window based on external time, that holds events arrived during windowTime periods, and gets updated for every windowTime.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
externalTimeBatch(<LONG> timestamp, <INT|LONG|TIME> window.time)
externalTimeBatch(<LONG> timestamp, <INT|LONG|TIME> window.time, <INT|LONG|TIME> start.time)
externalTimeBatch(<LONG> timestamp, <INT|LONG|TIME> window.time, <INT|LONG|TIME> start.time, <INT|LONG|TIME> timeout)
externalTimeBatch(<LONG> timestamp, <INT|LONG|TIME> window.time, <INT|LONG|TIME> start.time, <INT|LONG|TIME> timeout, <BOOL> replace.with.batchtime)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">timestamp</td>
        <td style="vertical-align: top; word-wrap: break-word">The time which the window determines as current time and will act upon. The value of this parameter should be monotonically increasing.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">LONG</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">window.time</td>
        <td style="vertical-align: top; word-wrap: break-word">The batch time period for which the window should hold events.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">start.time</td>
        <td style="vertical-align: top; word-wrap: break-word">User defined start time. This could either be a constant (of type int, long or time) or an attribute of the corresponding stream (of type long). If an attribute is provided, initial value of attribute would be considered as startTime.</td>
        <td style="vertical-align: top">Timestamp of first event</td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">timeout</td>
        <td style="vertical-align: top; word-wrap: break-word">Time to wait for arrival of new event, before flushing and giving output for events belonging to a specific batch.</td>
        <td style="vertical-align: top">System waits till an event from next batch arrives to flush current batch</td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">replace.with.batchtime</td>
        <td style="vertical-align: top; word-wrap: break-word">This indicates to replace the expired event timeStamp as the batch end timeStamp</td>
        <td style="vertical-align: top">System waits till an event from next batch arrives to flush current batch</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define window cseEventWindow (symbol string, price float, volume int) externalTimeBatch(eventTime, 1 sec) output expired events;
@info(name = 'query0')
from cseEventStream
insert into cseEventWindow;
@info(name = 'query1')
from cseEventWindow
select symbol, sum(price) as price
insert expired events into outputStream ;
```
<p style="word-wrap: break-word">This will processing events that arrive every 1 seconds from the eventTime.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
define window cseEventWindow (symbol string, price float, volume int) externalTimeBatch(eventTime, 20 sec, 0) output expired events;
```
<p style="word-wrap: break-word">This will processing events that arrive every 1 seconds from the eventTime. Starts on 0th millisecond of an hour.</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
define window cseEventWindow (symbol string, price float, volume int) externalTimeBatch(eventTime, 2 sec, eventTimestamp, 100) output expired events;
```
<p style="word-wrap: break-word">This will processing events that arrive every 2 seconds from the eventTim. Considers the first event's eventTimestamp value as startTime. Waits 100 milliseconds for the arrival of a new event before flushing current batch.</p>

### <s>frequent *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*</s>
<p><i>Deprecated</i></p>
<p style="word-wrap: break-word">This window returns the latest events with the most frequently occurred value for a given attribute(s). Frequency calculation for this window processor is based on Misra-Gries counting algorithm.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
frequent(<INT> event.count)
frequent(<INT> event.count, <STRING> attribute)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">event.count</td>
        <td style="vertical-align: top; word-wrap: break-word">The number of most frequent events to be emitted to the stream.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">attribute</td>
        <td style="vertical-align: top; word-wrap: break-word">The attributes to group the events. If no attributes are given, the concatenation of all the attributes of the event is considered.</td>
        <td style="vertical-align: top">The concatenation of all the attributes of the event is considered.</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@info(name = 'query1')
from purchase[price >= 30]#window.frequent(2)
select cardNo, price
insert all events into PotentialFraud;
```
<p style="word-wrap: break-word">This will returns the 2 most frequent events.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
@info(name = 'query1')
from purchase[price >= 30]#window.frequent(2, cardNo)
select cardNo, price
insert all events into PotentialFraud;
```
<p style="word-wrap: break-word">This will returns the 2 latest events with the most frequently appeared card numbers.</p>

### length *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A sliding length window that holds the last 'window.length' events at a given time, and gets updated for each arrival and expiry.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
length(<INT> window.length)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.length</td>
        <td style="vertical-align: top; word-wrap: break-word">The number of events that should be included in a sliding length window.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define window StockEventWindow (symbol string, price float, volume int) length(10) output all events;

@info(name = 'query0')
from StockEventStream
insert into StockEventWindow;
@info(name = 'query1')

from StockEventWindow
select symbol, sum(price) as price
insert all events into outputStream ;
```
<p style="word-wrap: break-word">This will process last 10 events in a sliding manner.</p>

### lengthBatch *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A batch (tumbling) length window that holds and process a number of events as specified in the window.length.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
lengthBatch(<INT> window.length)
lengthBatch(<INT> window.length, <BOOL> stream.current.event)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.length</td>
        <td style="vertical-align: top; word-wrap: break-word">The number of events the window should tumble.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">stream.current.event</td>
        <td style="vertical-align: top; word-wrap: break-word">Let the window stream the current events out as and when they arrive to the window while expiring them in batches.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InputEventStream (symbol string, price float, volume int);

@info(name = 'query1')
from InputEventStream#lengthBatch(10)
select symbol, sum(price) as price 
insert into OutputStream;
```
<p style="word-wrap: break-word">This collect and process 10 events as a batch and output them.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
define stream InputEventStream (symbol string, price float, volume int);

@info(name = 'query1')
from InputEventStream#lengthBatch(10, true)
select symbol, sum(price) as sumPrice 
insert into OutputStream;
```
<p style="word-wrap: break-word">This window sends the arriving events directly to the output letting the <code>sumPrice</code> to increase gradually, after every 10 events it clears the window as a batch and resets the <code>sumPrice</code> to zero.</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
define stream InputEventStream (symbol string, price float, volume int);
define window StockEventWindow (symbol string, price float, volume int) lengthBatch(10) output all events;

@info(name = 'query0')
from InputEventStream
insert into StockEventWindow;

@info(name = 'query1')
from StockEventWindow
select symbol, sum(price) as price
insert all events into OutputStream ;
```
<p style="word-wrap: break-word">This uses an defined window to process 10 events  as a batch and output all events.</p>

### <s>lossyFrequent *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*</s>
<p><i>Deprecated</i></p>
<p style="word-wrap: break-word">This window identifies and returns all the events of which the current frequency exceeds the value specified for the supportThreshold parameter.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
lossyFrequent(<DOUBLE> support.threshold)
lossyFrequent(<DOUBLE> support.threshold, <DOUBLE> error.bound)
lossyFrequent(<DOUBLE> support.threshold, <DOUBLE> error.bound, <STRING> attribute)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">support.threshold</td>
        <td style="vertical-align: top; word-wrap: break-word">The support threshold value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">error.bound</td>
        <td style="vertical-align: top; word-wrap: break-word">The error bound value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">DOUBLE</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">attribute</td>
        <td style="vertical-align: top; word-wrap: break-word">The attributes to group the events. If no attributes are given, the concatenation of all the attributes of the event is considered.</td>
        <td style="vertical-align: top">The concatenation of all the attributes of the event is considered.</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream purchase (cardNo string, price float);
define window purchaseWindow (cardNo string, price float) lossyFrequent(0.1, 0.01);
@info(name = 'query0')
from purchase[price >= 30]
insert into purchaseWindow;
@info(name = 'query1')
from purchaseWindow
select cardNo, price
insert all events into PotentialFraud;
```
<p style="word-wrap: break-word">lossyFrequent(0.1, 0.01) returns all the events of which the current frequency exceeds 0.1, with an error bound of 0.01.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
define stream purchase (cardNo string, price float);
define window purchaseWindow (cardNo string, price float) lossyFrequent(0.3, 0.05, cardNo);
@info(name = 'query0')
from purchase[price >= 30]
insert into purchaseWindow;
@info(name = 'query1')
from purchaseWindow
select cardNo, price
insert all events into PotentialFraud;
```
<p style="word-wrap: break-word">lossyFrequent(0.3, 0.05, cardNo) returns all the events of which the cardNo attributes frequency exceeds 0.3, with an error bound of 0.05.</p>

### session *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">This is a session window that holds events that belong to a specific session. The events that belong to a specific session are identified by a grouping attribute (i.e., a session key). A session gap period is specified to determine the time period after which the session is considered to be expired. A new event that arrives with a specific value for the session key is matched with the session window with the same session key.<br>&nbsp;There can be out of order and late arrival of events, these events can arrive after the session is expired, to include those events to the matching session key specify a latency time period that is less than the session gap period.To have aggregate functions with session windows, the events need to be grouped by the session key via a 'group by' clause.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
session(<INT|LONG|TIME> window.session)
session(<INT|LONG|TIME> window.session, <STRING> window.key)
session(<INT|LONG|TIME> window.session, <STRING> window.key, <INT|LONG|TIME> window.allowed.latency)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.session</td>
        <td style="vertical-align: top; word-wrap: break-word">The time period for which the session considered is valid. This is specified in seconds, minutes, or milliseconds (i.e., 'min', 'sec', or 'ms'.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">window.key</td>
        <td style="vertical-align: top; word-wrap: break-word">The grouping attribute for events.</td>
        <td style="vertical-align: top">default-key</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">window.allowed.latency</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies the time period for which the session window is valid after the expiration of the session. The time period specified here should be less than the session time gap (which is specified via the 'window.session' parameter).</td>
        <td style="vertical-align: top">0</td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream PurchaseEventStream (user string, item_number int, price float, quantity int);

@info(name='query0) 
from PurchaseEventStream#window.session(5 sec, user, 2 sec) 
select * 
insert all events into OutputStream;
```
<p style="word-wrap: break-word">This query processes events that arrive at the PurchaseEvent input stream. The 'user' attribute is the session key, and the session gap is 5 seconds. '2 sec' is specified as the allowed latency. Therefore, events with the matching user name that arrive 2 seconds after the expiration of the session are also considered when performing aggregations for the session identified by the given user name.</p>

### sort *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">This window holds a batch of events that equal the number specified as the windowLength and sorts them in the given order.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
sort(<INT> window.length, <STRING|DOUBLE|INT|LONG|FLOAT|LONG> attribute)
sort(<INT> window.length, <STRING|DOUBLE|INT|LONG|FLOAT|LONG> attribute, <STRING> order, <STRING> ...)
sort(<INT> window.length, <STRING|DOUBLE|INT|LONG|FLOAT|LONG> attribute, <STRING> order, <STRING|DOUBLE|INT|LONG|FLOAT|LONG> attribute, <STRING|DOUBLE|INT|LONG|FLOAT|LONG> ...)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.length</td>
        <td style="vertical-align: top; word-wrap: break-word">The size of the window length.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">attribute</td>
        <td style="vertical-align: top; word-wrap: break-word">The attribute that should be checked for the order.</td>
        <td style="vertical-align: top">The concatenation of all the attributes of the event is considered.</td>
        <td style="vertical-align: top">STRING<br>DOUBLE<br>INT<br>LONG<br>FLOAT<br>LONG</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">order</td>
        <td style="vertical-align: top; word-wrap: break-word">The order define as "asc" or "desc".</td>
        <td style="vertical-align: top">asc</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream cseEventStream (symbol string, price float, volume long);
define window cseEventWindow (symbol string, price float, volume long) sort(2,volume, 'asc');
@info(name = 'query0')
from cseEventStream
insert into cseEventWindow;
@info(name = 'query1')
from cseEventWindow
select volume
insert all events into outputStream ;
```
<p style="word-wrap: break-word">sort(5, price, 'asc') keeps the events sorted by price in the ascending order. Therefore, at any given time, the window contains the 5 lowest prices.</p>

### time *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A sliding time window that holds events that arrived during the last windowTime period at a given time, and gets updated for each event arrival and expiry.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
time(<INT|LONG|TIME> window.time)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.time</td>
        <td style="vertical-align: top; word-wrap: break-word">The sliding time period for which the window should hold events.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define window cseEventWindow (symbol string, price float, volume int) time(20) output all events;
@info(name = 'query0')
from cseEventStream
insert into cseEventWindow;
@info(name = 'query1')
from cseEventWindow
select symbol, sum(price) as price
insert all events into outputStream ;
```
<p style="word-wrap: break-word">This will processing events that arrived within the last 20 milliseconds.</p>

### timeBatch *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A batch (tumbling) time window that holds and process events that arrive during 'window.time' period as a batch.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
timeBatch(<INT|LONG|TIME> window.time)
timeBatch(<INT|LONG|TIME> window.time, <INT|LONG> start.time)
timeBatch(<INT|LONG|TIME> window.time, <BOOL> stream.current.event)
timeBatch(<INT|LONG|TIME> window.time, <INT|LONG> start.time, <BOOL> stream.current.event)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.time</td>
        <td style="vertical-align: top; word-wrap: break-word">The batch time period in which the window process the events.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">start.time</td>
        <td style="vertical-align: top; word-wrap: break-word">This specifies an offset in milliseconds in order to start the window at a time different to the standard time.</td>
        <td style="vertical-align: top">Timestamp of first event</td>
        <td style="vertical-align: top">INT<br>LONG</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">stream.current.event</td>
        <td style="vertical-align: top; word-wrap: break-word">Let the window stream the current events out as and when they arrive to the window while expiring them in batches.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream InputEventStream (symbol string, price float, volume int);

@info(name = 'query1')
from InputEventStream#timeBatch(20 sec)
select symbol, sum(price) as price 
insert into OutputStream;
```
<p style="word-wrap: break-word">This collect and process incoming events as a batch every 20 seconds and output them.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
define stream InputEventStream (symbol string, price float, volume int);

@info(name = 'query1')
from InputEventStream#timeBatch(20 sec, true)
select symbol, sum(price) as sumPrice 
insert into OutputStream;
```
<p style="word-wrap: break-word">This window sends the arriving events directly to the output letting the <code>sumPrice</code> to increase gradually and on every 20 second interval it clears the window as a batch resetting the <code>sumPrice</code> to zero.</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
define stream InputEventStream (symbol string, price float, volume int);
define window StockEventWindow (symbol string, price float, volume int) timeBatch(20 sec) output all events;

@info(name = 'query0')
from InputEventStream
insert into StockEventWindow;

@info(name = 'query1')
from StockEventWindow
select symbol, sum(price) as price
insert all events into OutputStream ;
```
<p style="word-wrap: break-word">This uses an defined window to process events arrived every 20 seconds as a batch and output all events.</p>

### timeLength *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#window">(Window)</a>*
<p style="word-wrap: break-word">A sliding time window that, at a given time holds the last window.length events that arrived during last window.time period, and gets updated for every event arrival and expiry.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
timeLength(<INT|LONG|TIME> window.time, <INT> window.length)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">window.time</td>
        <td style="vertical-align: top; word-wrap: break-word">The sliding time period for which the window should hold events.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>TIME</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">window.length</td>
        <td style="vertical-align: top; word-wrap: break-word">The number of events that should be be included in a sliding length window..</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream cseEventStream (symbol string, price float, volume int);
define window cseEventWindow (symbol string, price float, volume int) timeLength(2 sec, 10);
@info(name = 'query0')
from cseEventStream
insert into cseEventWindow;
@info(name = 'query1')
from cseEventWindow select symbol, price, volume
insert all events into outputStream;
```
<p style="word-wrap: break-word">window.timeLength(2 sec, 10) holds the last 10 events that arrived during last 2 seconds and gets updated for every event arrival and expiry.</p>

## Sink

### inMemory *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#sink">(Sink)</a>*
<p style="word-wrap: break-word">In-memory transport that can communicate with other in-memory transports within the same JVM, itis assumed that the publisher and subscriber of a topic uses same event schema (stream definition).</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
@sink(type="inMemory", topic="<STRING>", @map(...)))
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">topic</td>
        <td style="vertical-align: top; word-wrap: break-word">Event will be delivered to allthe subscribers of the same topic</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@sink(type='inMemory', @map(type='passThrough'))
define stream BarStream (symbol string, price float, volume long)
```
<p style="word-wrap: break-word">In this example BarStream uses inMemory transport which emit the Siddhi events internally without using external transport and transformation.</p>

### log *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#sink">(Sink)</a>*
<p style="word-wrap: break-word">This is a sink that can be used as a logger. This will log the output events in the output stream with user specified priority and a prefix</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
@sink(type="log", priority="<STRING>", prefix="<STRING>", @map(...)))
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">priority</td>
        <td style="vertical-align: top; word-wrap: break-word">This will set the logger priority i.e log level. Accepted values are INFO, DEBUG, WARN, FATAL, ERROR, OFF, TRACE</td>
        <td style="vertical-align: top">INFO</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">prefix</td>
        <td style="vertical-align: top; word-wrap: break-word">This will be the prefix to the output message. If the output stream has event [2,4] and the prefix is given as "Hello" then the log will show "Hello : [2,4]"</td>
        <td style="vertical-align: top">default prefix will be <Siddhi App Name> : <Stream Name></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@sink(type='log', prefix='My Log', priority='DEBUG') 
define stream BarStream (symbol string, price float, volume long)
```
<p style="word-wrap: break-word">In this example BarStream uses log sink and the prefix is given as My Log. Also the priority is set to DEBUG.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
@sink(type='log', priority='DEBUG') 
define stream BarStream (symbol string, price float, volume long)
```
<p style="word-wrap: break-word">In this example BarStream uses log sink and the priority is set to DEBUG. User has not specified prefix so the default prefix will be in the form &lt;Siddhi App Name&gt; : &lt;Stream Name&gt;</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
@sink(type='log', prefix='My Log') 
define stream BarStream (symbol string, price float, volume long)
```
<p style="word-wrap: break-word">In this example BarStream uses log sink and the prefix is given as My Log. User has not given a priority so it will be set to default INFO.</p>

<span id="example-4" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 4</span>
```
@sink(type='log') 
define stream BarStream (symbol string, price float, volume long)
```
<p style="word-wrap: break-word">In this example BarStream uses log sink. The user has not given prefix or priority so they will be set to their default values.</p>

## Sinkmapper

### passThrough *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#sink-mapper">(Sink Mapper)</a>*
<p style="word-wrap: break-word">Pass-through mapper passed events (Event[]) through without any mapping or modifications.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
@sink(..., @map(type="passThrough")
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@sink(type='inMemory', @map(type='passThrough'))
define stream BarStream (symbol string, price float, volume long);
```
<p style="word-wrap: break-word">In the following example BarStream uses passThrough outputmapper which emit Siddhi event directly without any transformation into sink.</p>

## Source

### inMemory *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#source">(Source)</a>*
<p style="word-wrap: break-word">In-memory source that can communicate with other in-memory sinks within the same JVM, it is assumed that the publisher and subscriber of a topic uses same event schema (stream definition).</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
@source(type="inMemory", topic="<STRING>", @map(...)))
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">topic</td>
        <td style="vertical-align: top; word-wrap: break-word">Subscribes to sent on the given topic.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@source(type='inMemory', @map(type='passThrough'))
define stream BarStream (symbol string, price float, volume long)
```
<p style="word-wrap: break-word">In this example BarStream uses inMemory transport which passes the received event internally without using external transport.</p>

## Sourcemapper

### passThrough *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#source-mapper">(Source Mapper)</a>*
<p style="word-wrap: break-word">Pass-through mapper passed events (Event[]) through without any mapping or modifications.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
@source(..., @map(type="passThrough")
```

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@source(type='tcp', @map(type='passThrough'))
define stream BarStream (symbol string, price float, volume long);
```
<p style="word-wrap: break-word">In this example BarStream uses passThrough inputmapper which passes the received Siddhi event directly without any transformation into source.</p>

## Str

### groupConcat *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p style="word-wrap: break-word">This function aggregates the received events by concatenating the keys in those events using a separator, e.g.,a comma (,) or a hyphen (-), and returns the concatenated key string.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:groupConcat(<STRING> key, <STRING> separator, <STRING> distinct, <STRING> order)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">key</td>
        <td style="vertical-align: top; word-wrap: break-word">The string that needs to be aggregated.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">separator</td>
        <td style="vertical-align: top; word-wrap: break-word">The separator that separates each string key after concatenating the keys.</td>
        <td style="vertical-align: top">,</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">distinct</td>
        <td style="vertical-align: top; word-wrap: break-word">This is used to only have distinct values in the concatenated string that is returned.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">order</td>
        <td style="vertical-align: top; word-wrap: break-word">This parameter accepts 'ASC' or 'DESC' strings to sort the string keys in either ascending or descending order respectively.</td>
        <td style="vertical-align: top">No order</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from InputStream#window.time(5 min)
select str:groupConcat("key") as groupedKeys
input OutputStream;
```
<p style="word-wrap: break-word">When we input events having values for the <code>key</code> as <code>'A'</code>, <code>'B'</code>, <code>'S'</code>, <code>'C'</code>, <code>'A'</code>, it returns <code>"A,B,S,C,A"</code> to the 'OutputStream'.</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
from InputStream#window.time(5 min)
select groupConcat("key","-",true,"ASC") as groupedKeys
input OutputStream;
```
<p style="word-wrap: break-word">When we input events having values for the <code>key</code> as <code>'A'</code>, <code>'B'</code>, <code>'S'</code>, <code>'C'</code>, <code>'A'</code>, specify the seperator as hyphen and choose the order to be ascending, the function returns <code>"A-B-C-S"</code> to the 'OutputStream'.</p>

### <s>charAt *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*</s>
<p><i>Deprecated</i></p>
<p style="word-wrap: break-word">This function returns the 'char' value that is present at the given index position. of the input string.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:charAt(<STRING> input.value, <INT> index)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.value</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string of which the char value at the given position needs to be returned.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">index</td>
        <td style="vertical-align: top; word-wrap: break-word">The variable that specifies the index of the char value that needs to be returned.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
charAt("WSO2", 1)
```
<p style="word-wrap: break-word">In this case, the functiion returns the character that exists at index 1. Hence, it returns 'S'.</p>

### coalesce *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word"> This returns the first input parameter value of the given argument, that is not null.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> str:coalesce(<INT|LONG|DOUBLE|FLOAT|STRING|BOOL|OBJECT> argn)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">argn</td>
        <td style="vertical-align: top; word-wrap: break-word">It can have one or more input parameters in any data type. However, all the specified parameters are required to be of the same type.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT<br>LONG<br>DOUBLE<br>FLOAT<br>STRING<br>BOOL<br>OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
coalesce(null, "BBB", "CCC")
```
<p style="word-wrap: break-word">This returns the first input parameter that is not null. In this example, it returns "BBB".</p>

### concat *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">This function returns a string value that is obtained as a result of concatenating two or more input string values.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:concat(<STRING> argn)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">argn</td>
        <td style="vertical-align: top; word-wrap: break-word">This can have two or more <code>string</code> type input parameters.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
concat("D533", "8JU^", "XYZ")
```
<p style="word-wrap: break-word">This returns a string value by concatenating two or more given arguments. In the example shown above, it returns "D5338JU^XYZ".</p>

### contains *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">This function returns <code>true</code> if the<code>input.string</code> contains the specified sequence of char values in the <code>search.string</code>. </p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> str:contains(<STRING> input.string, <STRING> search.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">Input string value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">search.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The string value to be searched for in the <code>input.string</code>.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
contains("21 products are produced by WSO2 currently", "WSO2")
```
<p style="word-wrap: break-word">This returns a boolean value as the output. In this case, it returns<code>true</code>.</p>

### equalsIgnoreCase *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">This returns a boolean value by comparing two strings lexicographically without considering the letter case.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> str:equalsIgnoreCase(<STRING> arg1, <STRING> arg2)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg1</td>
        <td style="vertical-align: top; word-wrap: break-word">The first input string argument.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">arg2</td>
        <td style="vertical-align: top; word-wrap: break-word">The second input string argument. This is compared with the first argument.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
equalsIgnoreCase("WSO2", "wso2")
```
<p style="word-wrap: break-word">This returns a boolean value as the output. In this scenario, it returns "true". </p>

### fillTemplate *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">This extension replaces the templated positions that are marked with an index value in a specified template with the strings provided.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:fillTemplate(<STRING> template, <STRING|INT|LONG|DOUBLE|FLOAT|BOOL> replacement.strings)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">template</td>
        <td style="vertical-align: top; word-wrap: break-word">The string with templated fields that needs to be filled with the given strings. The format of the templated fields should be as follows:<br>{{INDEX}} where 'INDEX' is an integer. <br>This index is used to map the strings that are used to replace the templated fields.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">replacement.strings</td>
        <td style="vertical-align: top; word-wrap: break-word">The strings with which the templated positions in the template need to be replaced.<br>The minimum of two arguments need to be included in the execution string. There is no upper limit on the number of arguments allowed to be included.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING<br>INT<br>LONG<br>DOUBLE<br>FLOAT<br>BOOL</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
str:fillTemplate("This is {{1}} for the {{2}} function", 'an example', 'fillTemplate')
```
<p style="word-wrap: break-word">In this example, the template is 'This is {{1}} for the {{2}} function'.Here, the templated string {{1}} is replaced with the 1st string value provided, which is 'an example'.<br>{{2}} is replaced with the 2nd string provided, which is 'fillTemplate'<br>The complete return string is 'This is an example for the fillTemplate function'.</p>

### hex *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">This function returns a hexadecimal string by converting each byte of each character in the input string to two hexadecimal digits.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:hex(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to derive the hexadecimal value.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
hex("MySQL") 
```
<p style="word-wrap: break-word">This returns the hexadecimal value of the input.string. In this scenario, the output is "4d7953514c".</p>

### length *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the length of the input string.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT> str:length(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to derive the length.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
length("Hello World")
```
<p style="word-wrap: break-word">This outputs the length of the provided string. In this scenario, the, output is <code>11</code> .</p>

### lower *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Converts the capital letters in the input string to the equivalent simple letters.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:lower(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to convert to the lower case (i.e., equivalent simple letters).</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
lower("WSO2 cep ")
```
<p style="word-wrap: break-word">This converts the capital letters in the input.string to the equivalent simple letters. In this scenario, the output is "wso2 cep ".</p>

### regexp *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns a boolean value based on the matchability of the input string and the given regular expression.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> str:regexp(<STRING> input.string, <STRING> regex)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to match with the given regular expression.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">regex</td>
        <td style="vertical-align: top; word-wrap: break-word">The regular expression  to be matched with the input string.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
regexp("WSO2 abcdh", "WSO(.*h)")
```
<p style="word-wrap: break-word">This returns a boolean value after matching regular expression with the given string. In this scenario, it returns "true" as the output.</p>

### repeat *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Repeats the input string for a specified number of times.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:repeat(<STRING> input.string, <INT> times)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string that is repeated the number of times as defined by the user.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">times</td>
        <td style="vertical-align: top; word-wrap: break-word">The number of times the input.string needs to be repeated .</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
repeat("StRing 1", 3)
```
<p style="word-wrap: break-word">This returns a string value by repeating the string for a specified number of times. In this scenario, the output is "StRing 1StRing 1StRing 1".</p>

### replaceAll *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Finds all the substrings of the input string that matches with the given expression, and replaces them with the given replacement string.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:replaceAll(<STRING> input.string, <STRING> regex, <STRING> replacement.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to be replaced.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">regex</td>
        <td style="vertical-align: top; word-wrap: break-word">The regular expression to be matched with the input string.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">replacement.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The string with which each substring that matches the given expression should be replaced.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
replaceAll("hello hi hello",  'hello', 'test')
```
<p style="word-wrap: break-word">This returns a string after replacing the substrings of the input string with the replacement string. In this scenario, the output is "test hi test" .</p>

### replaceFirst *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Finds the first substring of the input string that matches with the given regular expression, and replaces itwith the given replacement string.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:replaceFirst(<STRING> input.string, <STRING> regex, <STRING> replacement.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string that should be replaced.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">regex</td>
        <td style="vertical-align: top; word-wrap: break-word">The regular expression with which the input string should be matched.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">replacement.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The string with which the first substring of input string that matches the regular expression should be replaced.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
replaceFirst("hello WSO2 A hello",  'WSO2(.*)A', 'XXXX')
```
<p style="word-wrap: break-word">This returns a string after replacing the first substring with the given replacement string. In this scenario, the output is "hello XXXX hello".</p>

### reverse *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns the input string in the reverse order character-wise and string-wise.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:reverse(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to be reversed.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
reverse("Hello World")
```
<p style="word-wrap: break-word">This outputs a string value by reversing the incoming <code>input.string</code>. In this scenario, the output is "dlroW olleH".</p>

### split *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Splits the  <code>input.string</code> into substrings using the value parsed in the <code>split.string</code> and returns the substring at the position specified in the <code>group.number</code>.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:split(<STRING> input.string, <STRING> split.string, <INT> group.number)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to be replaced.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">split.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The string value to be used to split the <code>input.string</code>.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">group.number</td>
        <td style="vertical-align: top; word-wrap: break-word">The index of the split group</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
split("WSO2,ABM,NSFT", ",", 0)
```
<p style="word-wrap: break-word">This splits the given <code>input.string</code> by given <code>split.string</code> and returns the string in the index given by group.number. In this scenario, the output will is "WSO2".</p>

### strcmp *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Compares two strings lexicographically and returns an integer value. If both strings are equal, 0 is returned. If  the first string is lexicographically greater than the second string, a positive value is returned. If the first string is lexicographically greater than the second string, a negative value is returned.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT> str:strcmp(<STRING> arg1, <STRING> arg2)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg1</td>
        <td style="vertical-align: top; word-wrap: break-word">The first input string argument.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">arg2</td>
        <td style="vertical-align: top; word-wrap: break-word">The second input string argument that should be compared with the first argument lexicographically.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
strcmp("AbCDefghiJ KLMN", 'Hello')
```
<p style="word-wrap: break-word">This compares two strings lexicographically and outputs an integer value.</p>

### substr *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns a substring of the input string by considering a subset or all of the following factors: starting index, length, regular expression, and regex group number.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:substr(<STRING> input.string, <INT> begin.index, <INT> length, <STRING> regex, <INT> group.number)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string to be processed.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">begin.index</td>
        <td style="vertical-align: top; word-wrap: break-word">Starting index to consider for the substring.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">length</td>
        <td style="vertical-align: top; word-wrap: break-word">The length of the substring.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">regex</td>
        <td style="vertical-align: top; word-wrap: break-word">The regular expression that should be matched with the input string.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">group.number</td>
        <td style="vertical-align: top; word-wrap: break-word">The regex group number</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
substr("AbCDefghiJ KLMN", 4)
```
<p style="word-wrap: break-word">This outputs the substring based on the given <code>begin.index</code>. In this scenario, the output is "efghiJ KLMN".</p>

<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
substr("AbCDefghiJ KLMN",  2, 4) 
```
<p style="word-wrap: break-word">This outputs the substring based on the given <code>begin.index</code> and length. In this scenario, the output is "CDef".</p>

<span id="example-3" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 3</span>
```
substr("WSO2D efghiJ KLMN", '^WSO2(.*)')
```
<p style="word-wrap: break-word">This outputs the substring by applying the regex. In this scenario, the output is "WSO2D efghiJ KLMN".</p>

<span id="example-4" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 4</span>
```
substr("WSO2 cep WSO2 XX E hi hA WSO2 heAllo",  'WSO2(.*)A(.*)',  2)
```
<p style="word-wrap: break-word">This outputs the substring by applying the regex and considering the <code>group.number</code>. In this scenario, the output is " ello".</p>

### trim *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns a copy of the input string without the leading and trailing whitespace (if any).</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:trim(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string that needs to be trimmed.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
trim("  AbCDefghiJ KLMN  ")
```
<p style="word-wrap: break-word">This returns a copy of the <code>input.string</code> with the leading and/or trailing white-spaces omitted. In this scenario, the output is "AbCDefghiJ KLMN".</p>

### unhex *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Returns a string by converting the hexadecimal characters in the input string.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:unhex(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The hexadecimal input string that needs to be converted to string.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
unhex("4d7953514c")
```
<p style="word-wrap: break-word">This converts the hexadecimal value to string.</p>

### upper *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#function">(Function)</a>*
<p style="word-wrap: break-word">Converts the simple letters in the input string to the equivalent capital/block letters.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<STRING> str:upper(<STRING> input.string)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string that should be converted to the upper case (equivalent capital/block letters).</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
upper("Hello World")
```
<p style="word-wrap: break-word">This converts the simple letters in the <code>input.string</code> to theequivalent capital letters. In this scenario, the output is "HELLO WORLD".</p>

### tokenize *<a target="_blank" href="http://siddhi.io/en/v5.0/docs/query-guide/#stream-processor">(Stream Processor)</a>*
<p style="word-wrap: break-word">This function splits the input string into tokens using a given regular expression and returns the split tokens.</p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
str:tokenize(<STRING> input.string, <STRING> regex, <BOOL> distinct)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">input.string</td>
        <td style="vertical-align: top; word-wrap: break-word">The input string which needs to be split.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">regex</td>
        <td style="vertical-align: top; word-wrap: break-word">The string value which is used to tokenize the 'input.string'.</td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">distinct</td>
        <td style="vertical-align: top; word-wrap: break-word">This flag is used to return only distinct values.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>
<span id="extra-return-attributes" class="md-typeset" style="display: block; font-weight: bold;">Extra Return Attributes</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Possible Types</th>
    </tr>
    <tr>
        <td style="vertical-align: top">token</td>
        <td style="vertical-align: top; word-wrap: break-word">The attribute which contains a single token.</td>
        <td style="vertical-align: top">STRING</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
define stream inputStream (str string);
@info(name = 'query1')
from inputStream#str:tokenize(str , ',')
select text
insert into outputStream;
```
<p style="word-wrap: break-word">This query performs tokenization on the given string. If the str is "Android,Windows8,iOS", then the string is split into 3 events containing the <code>token</code> attribute values, i.e., <code>Android</code>, <code>Windows8</code> and <code>iOS</code>.</p>

