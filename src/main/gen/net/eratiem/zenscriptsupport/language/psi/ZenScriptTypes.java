// This is a generated file. Not intended for manual editing.
package net.eratiem.zenscriptsupport.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import net.eratiem.zenscriptsupport.language.psi.impl.*;

public interface ZenScriptTypes {

  IElementType ARRAY = new ZenScriptElementType("ARRAY");
  IElementType BOOLEAN = new ZenScriptElementType("BOOLEAN");
  IElementType FUNCTION_CALL = new ZenScriptElementType("FUNCTION_CALL");
  IElementType IMPORT_STATEMENT = new ZenScriptElementType("IMPORT_STATEMENT");
  IElementType IMPORT_STR = new ZenScriptElementType("IMPORT_STR");
  IElementType ITEM_FUNCTION_CALL = new ZenScriptElementType("ITEM_FUNCTION_CALL");
  IElementType MC_ITEM = new ZenScriptElementType("MC_ITEM");
  IElementType OBJECT = new ZenScriptElementType("OBJECT");
  IElementType ORE_LIQUID_ENTRY = new ZenScriptElementType("ORE_LIQUID_ENTRY");
  IElementType VAR_INIT = new ZenScriptElementType("VAR_INIT");
  IElementType VAR_MODIFIER = new ZenScriptElementType("VAR_MODIFIER");
  IElementType VAR_VALUE = new ZenScriptElementType("VAR_VALUE");

  IElementType AS = new ZenScriptTokenType("as");
  IElementType BLOCK_COMMENT = new ZenScriptTokenType("BLOCK_COMMENT");
  IElementType BOOL = new ZenScriptTokenType("bool");
  IElementType BRACE_LEFT = new ZenScriptTokenType("{");
  IElementType BRACE_RIGHT = new ZenScriptTokenType("}");
  IElementType BRACKET_LEFT = new ZenScriptTokenType("[");
  IElementType BRACKET_RIGHT = new ZenScriptTokenType("]");
  IElementType BYTE = new ZenScriptTokenType("byte");
  IElementType COLON = new ZenScriptTokenType(":");
  IElementType COMMA = new ZenScriptTokenType(",");
  IElementType DECIMAL = new ZenScriptTokenType("DECIMAL");
  IElementType DOT = new ZenScriptTokenType(".");
  IElementType DOUBLE = new ZenScriptTokenType("double");
  IElementType ELSE = new ZenScriptTokenType("else");
  IElementType EQUALS = new ZenScriptTokenType("=");
  IElementType FALSE = new ZenScriptTokenType("false");
  IElementType FLOAT = new ZenScriptTokenType("float");
  IElementType GLOBAL = new ZenScriptTokenType("global");
  IElementType GREATER_THAN = new ZenScriptTokenType(">");
  IElementType IF = new ZenScriptTokenType("if");
  IElementType IINGREDIENT = new ZenScriptTokenType("IIngredient");
  IElementType IITEMSTACK = new ZenScriptTokenType("IItemStack");
  IElementType ILIQUIDSTACK = new ZenScriptTokenType("ILiquidStack");
  IElementType IMPORT = new ZenScriptTokenType("import");
  IElementType INT = new ZenScriptTokenType("int");
  IElementType IOREDICTENTRY = new ZenScriptTokenType("IOreDictEntry");
  IElementType LESS_THAN = new ZenScriptTokenType("<");
  IElementType LINE_COMMENT = new ZenScriptTokenType("LINE_COMMENT");
  IElementType LIQUID = new ZenScriptTokenType("liquid");
  IElementType LONG = new ZenScriptTokenType("long");
  IElementType LOWER_CASE_ID = new ZenScriptTokenType("LOWER_CASE_ID");
  IElementType NULL = new ZenScriptTokenType("null");
  IElementType NUMBER = new ZenScriptTokenType("NUMBER");
  IElementType ORE = new ZenScriptTokenType("ore");
  IElementType PAREN_LEFT = new ZenScriptTokenType("(");
  IElementType PAREN_RIGHT = new ZenScriptTokenType(")");
  IElementType QUOTE = new ZenScriptTokenType("\"");
  IElementType SEMICOLON = new ZenScriptTokenType(";");
  IElementType SHORT = new ZenScriptTokenType("short");
  IElementType STATIC = new ZenScriptTokenType("static");
  IElementType STRING = new ZenScriptTokenType("string");
  IElementType TRUE = new ZenScriptTokenType("true");
  IElementType UPPER_CASE_ID = new ZenScriptTokenType("UPPER_CASE_ID");
  IElementType VAL = new ZenScriptTokenType("val");
  IElementType VAR = new ZenScriptTokenType("var");
  IElementType VOID = new ZenScriptTokenType("void");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARRAY) {
        return new ZenScriptArrayImpl(node);
      }
      else if (type == BOOLEAN) {
        return new ZenScriptBooleanImpl(node);
      }
      else if (type == FUNCTION_CALL) {
        return new ZenScriptFunctionCallImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new ZenScriptImportStatementImpl(node);
      }
      else if (type == IMPORT_STR) {
        return new ZenScriptImportStrImpl(node);
      }
      else if (type == ITEM_FUNCTION_CALL) {
        return new ZenScriptItemFunctionCallImpl(node);
      }
      else if (type == MC_ITEM) {
        return new ZenScriptMcItemImpl(node);
      }
      else if (type == OBJECT) {
        return new ZenScriptObjectImpl(node);
      }
      else if (type == ORE_LIQUID_ENTRY) {
        return new ZenScriptOreLiquidEntryImpl(node);
      }
      else if (type == VAR_INIT) {
        return new ZenScriptVarInitImpl(node);
      }
      else if (type == VAR_MODIFIER) {
        return new ZenScriptVarModifierImpl(node);
      }
      else if (type == VAR_VALUE) {
        return new ZenScriptVarValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
