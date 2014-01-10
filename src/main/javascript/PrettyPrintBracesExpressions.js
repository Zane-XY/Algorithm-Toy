/**
Given a string with following syntax:
------
<filter> ::= <and-expr>
            | <or-expr>
            | <not-expr>
            | <filter-expr>
            | <literal>
<and-expr> ::= AND(<filter-list>)
<or-expr> ::= OR(<filter-list>)
<not-expr> ::= NOT(<filter>)
<filter-expr> ::= FILTER(<string>)
<filter-list> ::= <filter>
                | <filter>,<filter-list>
<literal> ::= <pval>
            | <dval-id>
            | <dval-path>
<pval> ::= <prop-key>:<prop-value>
<prop-key> ::= <string>
<prop-value> ::= <string>
<dval-path> ::= <string>
            | <string>:<dval-path>
<dval-id> ::= <unsigned-int>
<string> ::= any character string
------
pretty print the string with 2 space indents

**/

var t = "AND(OR(AND(key:value string,key:value string),key:value string,key:value string,AND(key:value string,key:value string),AND(key:value string,key:value string),key:value string,AND(key:value string,OR(key:value string,key:value string)),key:value string,AND(key:value string,key:value string),key:value string,key:value string,key:value string,key:value string,AND(key:value string,key:value string),AND(key:value string,key:value string),AND(key:value string,key:value string),AND(key:value string,OR(key:value string,key:value string))),key:value string)"

function closeBraceIndex(openIndex, arr) {
	var balance = 1;
	for (i = openIndex + 1; i < arr.length; i++) {
		if (arr[i].indexOf("(") !== -1) balance++;
		if (arr[i].indexOf(")") !== -1) balance--;
		if (balance === 0) return i;
	}
	return -1;
}

function indentBrace(s) {
	var arr = s.replace(/\(|\),|\)|,/g, function(m) {
		return m == ")," || m == ")" ? "\n" + m + "\n" : m + "\n";
	}).split("\n");

	for (k = 0; k < arr.length; k++) {
		if (arr[k].indexOf("(") != -1) {
			var end = closeBraceIndex(k, arr);
			for (j = k + 1; j < end; j++) {
				arr[j] = "  " + arr[j];
			}
		}
	}
	return arr.join("\n");
}

var s1 = indentBrace(t);
console.log(s1);
