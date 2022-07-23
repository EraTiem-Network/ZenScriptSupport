package net.eratiem.zenscriptsupport.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static net.eratiem.zenscriptsupport.language.psi.ZenScriptTypes.*;

%%

%{
  public ZenScriptLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class ZenScriptLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

NUMBER=[0-9]+
DECIMAL=[0-9]+\.[0-9]+
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*[^\*/]*\*"/"
LOWER_CASE_ID=[a-z][a-zA-Z0-9_]*
UPPER_CASE_ID=[A-Z_][a-zA-Z0-9_]*

%%
<YYINITIAL> {
  {WHITE_SPACE}        { return WHITE_SPACE; }

  "="                  { return EQUALS; }
  ","                  { return COMMA; }
  ";"                  { return SEMICOLON; }
  "{"                  { return BRACE_LEFT; }
  "}"                  { return BRACE_RIGHT; }
  "["                  { return BRACKET_LEFT; }
  "]"                  { return BRACKET_RIGHT; }
  "("                  { return PAREN_LEFT; }
  ")"                  { return PAREN_RIGHT; }
  "<"                  { return LESS_THAN; }
  ">"                  { return GREATER_THAN; }
  "\""                 { return QUOTE; }
  "."                  { return DOT; }
  ":"                  { return COLON; }
  "if"                 { return IF; }
  "else"               { return ELSE; }
  "import"             { return IMPORT; }
  "global"             { return GLOBAL; }
  "static"             { return STATIC; }
  "var"                { return VAR; }
  "val"                { return VAL; }
  "as"                 { return AS; }
  "ore"                { return ORE; }
  "liquid"             { return LIQUID; }
  "true"               { return TRUE; }
  "false"              { return FALSE; }
  "null"               { return NULL; }
  "void"               { return VOID; }
  "bool"               { return BOOL; }
  "int"                { return INT; }
  "short"              { return SHORT; }
  "long"               { return LONG; }
  "byte"               { return BYTE; }
  "float"              { return FLOAT; }
  "double"             { return DOUBLE; }
  "string"             { return STRING; }
  "IIngredient"        { return IINGREDIENT; }
  "IItemStack"         { return IITEMSTACK; }
  "IOreDictEntry"      { return IOREDICTENTRY; }
  "ILiquidStack"       { return ILIQUIDSTACK; }

  {NUMBER}             { return NUMBER; }
  {DECIMAL}            { return DECIMAL; }
  {LINE_COMMENT}       { return LINE_COMMENT; }
  {BLOCK_COMMENT}      { return BLOCK_COMMENT; }
  {LOWER_CASE_ID}      { return LOWER_CASE_ID; }
  {UPPER_CASE_ID}      { return UPPER_CASE_ID; }

}

[^] { return BAD_CHARACTER; }
