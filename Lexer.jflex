/* Specification for ArithExp tokens */

// user customisations
import java_cup.runtime.*;
import lib3652.util.TokenException;

// Jlex directives
    
%%

%cup
%public

%class Lexer

%type java_cup.runtime.Symbol

%throws TokenException

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%eofclose false

%char
%column
%line

%{
	StringBuffer buffer = new StringBuffer();

    public int getChar() {
	return yychar + 1;
    }

    public int getColumn() {
    	return yycolumn + 1;
    }

    public int getLine() {
	return yyline + 1;
    }

    public String getText() {
	return yytext();
    }
%}

nl = [\n\r]

cc = ([\b\f]|{nl})

ws = {cc}|[\t ]

alpha = [a-zA-Z_]

alphanum = {alpha}|[0-9]

//ban = [^\(\)\[\]\{\}\"\',:]

//ndmiddle = [^\(\)\[\]\{\}\"\',:0-9]

//ndbegin = [^\(\)\[\]\{\}\"\',:#0-9]

ndbegin = [a-zA-Z\+\-\*\/\%\^\?\.\!_]

ndmiddle = [a-zA-Z\+\-\*\/\%\^\?\.\!\#_]

ban = [a-zA-Z0-9\+\-\*\/\%\^\?\.\!\#_]


var = {ndbegin}+{ban}* | [0-9]+{ban}*{ndmiddle}+{ban}*

%state STRING


%%
<YYINITIAL> "/*" ~"*/" {}
<YYINITIAL> "//" ~{nl} {}

<YYINITIAL>	{nl}	{//skip newline, but reset char counter
			 yychar = 0;
			}
<YYINITIAL>	{ws}	{
			 // skip whitespace
			}
<YYINITIAL>	"+"	{return new Symbol(sym.PLUS);}
<YYINITIAL>	"-"	{return new Symbol(sym.MINUS);}
<YYINITIAL>	"*"	{return new Symbol(sym.MUL);}
<YYINITIAL>	"/"	{return new Symbol(sym.DIV);}
<YYINITIAL>	"%"	{return new Symbol(sym.MOD);}
<YYINITIAL>	"^"	{return new Symbol(sym.POW);}
<YYINITIAL> ":=" { return new Symbol(sym.ASSIGN); }

<YYINITIAL>	"@"	{return new Symbol(sym.AT);}

<YYINITIAL>	"//" {return new Symbol(sym.LCOM);}
<YYINITIAL>	"/*" {return new Symbol(sym.BCOMS);}
<YYINITIAL>	"*/" {return new Symbol(sym.BCOME);}

<YYINITIAL>    , { return new Symbol(sym.COMMA);}
<YYINITIAL>    ; { return new Symbol(sym.SEMI);}
<YYINITIAL>    : { return new Symbol(sym.COLON);}
<YYINITIAL>    "(" { return new Symbol(sym.LPAREN);}
<YYINITIAL>    ")" { return new Symbol(sym.RPAREN);}
<YYINITIAL>    "[" { return new Symbol(sym.LBRACKET);}
<YYINITIAL>    "]" { return new Symbol(sym.RBRACKET);}
<YYINITIAL>	   "{" {return new Symbol(sym.LBRACE);}
<YYINITIAL>    "}" {return new Symbol(sym.RBRACE);}

<YYINITIAL>	"<="	{return new Symbol(sym.COMPARISON,Cmp.LE);}
<YYINITIAL>	"<"	{return new Symbol(sym.COMPARISON,Cmp.LT);}
<YYINITIAL>	"!="	{return new Symbol(sym.COMPARISON,Cmp.NE);}
<YYINITIAL>	">="	{return new Symbol(sym.COMPARISON,Cmp.GE);}
<YYINITIAL>	">"	{return new Symbol(sym.COMPARISON,Cmp.GT);}
<YYINITIAL>	"="	{return new Symbol(sym.EQUAL,Cmp.EQ);}

<YYINITIAL>	"&"	{return new Symbol(sym.AMP);}
<YYINITIAL>	"|"	{return new Symbol(sym.BAR);}
<YYINITIAL>	"~"	{return new Symbol(sym.TILDE);}

<YYINITIAL>	"and"	{return new Symbol(sym.AND);}
<YYINITIAL>	"or"	{return new Symbol(sym.OR);}
<YYINITIAL>	"not"	{return new Symbol(sym.NOT);}

<YYINITIAL>	"proc" {return new Symbol(sym.PROC);}
<YYINITIAL>	"call" {return new Symbol(sym.CALL);}
<YYINITIAL>	"let" {return new Symbol(sym.LET);}
<YYINITIAL>	"def" {return new Symbol(sym.DEF);}
<YYINITIAL>	"if" {return new Symbol(sym.IF);}
<YYINITIAL>	"then" {return new Symbol(sym.THEN);}
<YYINITIAL>	"else" {return new Symbol(sym.ELSE);}
<YYINITIAL>	"case" {return new Symbol(sym.CASE);}
<YYINITIAL>	"print" {return new Symbol(sym.PRINT);}
<YYINITIAL>	"println" {return new Symbol(sym.PRINTLN);}
<YYINITIAL>	"read" {return new Symbol(sym.READ);}
<YYINITIAL>	"readint" {return new Symbol(sym.READINT);}

<YYINITIAL>	"pair?" {return new Symbol(sym.ISPAIR);}
<YYINITIAL>	"pair" {return new Symbol(sym.PAIR);}
<YYINITIAL>	"cdr" {return new Symbol(sym.CDR);}
<YYINITIAL>	"car" {return new Symbol(sym.CAR);}
<YYINITIAL>	"list" {return new Symbol(sym.LIST);}
<YYINITIAL>	"size" {return new Symbol(sym.SIZE);}
<YYINITIAL>	"eqv?" {return new Symbol(sym.ISEQV);}
<YYINITIAL>	"equal?" {return new Symbol(sym.ISEQ);}
<YYINITIAL>	"substr" {return new Symbol(sym.SUBSTR);}

<YYINITIAL> "#t" { return new Symbol(sym.BOOLLIT, true);}
<YYINITIAL> "#f" { return new Symbol(sym.BOOLLIT, false);}

<YYINITIAL> "#e" { return new Symbol(sym.ELIST);}

<YYINITIAL>    [0-9]+ {
	       // INTEGER
	       return new Symbol(sym.INT, 
				new Integer(yytext()));
		}

<YYINITIAL> #x[0-9A-F]+ {return new Symbol(sym.INT, Integer.parseInt(yytext().substring(2), 16));}

<YYINITIAL> #b[0-1]+ {return new Symbol(sym.INT, Integer.parseInt(yytext().substring(2), 2));}

<YYINITIAL> ([0-9]+\.[0.9]+)|(\.[0.9]+)|([0-9]+\.) {
			return new Symbol(sym.FLOAT, new Double(yytext()));
			}

<YYINITIAL> (#c[A-Za-z]) | (#c\\[n\\t]) {return new Symbol(sym.CHAR, yytext().substring(2).charAt(0));}

<YYINITIAL> #u[A-F0-9]{4} {return new Symbol(sym.CHAR, Character.toChars(Integer.parseInt(yytext().substring(2))));}


<YYINITIAL> \" {
				buffer.setLength(0); 
				yybegin(STRING); 
			}

<YYINITIAL>    {var} {
	       // VAR
	       return new Symbol(sym.VAR, yytext());
		}

<STRING> (\\[t\n]) | ([A-Za-z0-9/';:.,><[]{}=+-_|*&%$#@!~`]|\^) | \s { 
	// unsure if more should be done with this
		buffer.append(yytext());
	}

<STRING> \" {
				yybegin(YYINITIAL);
				return new Symbol(sym.STRING,buffer.toString());
			}


<YYINITIAL>    \S		{ // error situation
	       String msg = String.format("Unrecognised Token: %s", yytext());
	       throw new TokenException(msg);
	       }