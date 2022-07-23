// This is a generated file. Not intended for manual editing.
package net.eratiem.zenscriptsupport.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static net.eratiem.zenscriptsupport.language.psi.ZenScriptTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ZenScriptParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return zenScriptFile(b, l + 1);
  }

  /* ********************************************************** */
  // BRACKET_LEFT (var_value (COMMA var_value)*)? BRACKET_RIGHT
  public static boolean array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array")) return false;
    if (!nextTokenIs(b, BRACKET_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACKET_LEFT);
    r = r && array_1(b, l + 1);
    r = r && consumeToken(b, BRACKET_RIGHT);
    exit_section_(b, m, ARRAY, r);
    return r;
  }

  // (var_value (COMMA var_value)*)?
  private static boolean array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_1")) return false;
    array_1_0(b, l + 1);
    return true;
  }

  // var_value (COMMA var_value)*
  private static boolean array_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = var_value(b, l + 1);
    r = r && array_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA var_value)*
  private static boolean array_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!array_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "array_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA var_value
  private static boolean array_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && var_value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TRUE | FALSE
  public static boolean boolean_$(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_$")) return false;
    if (!nextTokenIs(b, "<boolean $>", FALSE, TRUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN, "<boolean $>");
    r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LOWER_CASE_ID PAREN_LEFT var_value (COMMA var_value)* PAREN_RIGHT SEMICOLON?
  public static boolean function_call(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call")) return false;
    if (!nextTokenIs(b, LOWER_CASE_ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LOWER_CASE_ID, PAREN_LEFT);
    r = r && var_value(b, l + 1);
    r = r && function_call_3(b, l + 1);
    r = r && consumeToken(b, PAREN_RIGHT);
    r = r && function_call_5(b, l + 1);
    exit_section_(b, m, FUNCTION_CALL, r);
    return r;
  }

  // (COMMA var_value)*
  private static boolean function_call_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!function_call_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "function_call_3", c)) break;
    }
    return true;
  }

  // COMMA var_value
  private static boolean function_call_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && var_value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEMICOLON?
  private static boolean function_call_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_call_5")) return false;
    consumeToken(b, SEMICOLON);
    return true;
  }

  /* ********************************************************** */
  // IMPORT import_str object (AS object)? SEMICOLON
  public static boolean import_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && import_str(b, l + 1);
    r = r && object(b, l + 1);
    r = r && import_statement_3(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, IMPORT_STATEMENT, r);
    return r;
  }

  // (AS object)?
  private static boolean import_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement_3")) return false;
    import_statement_3_0(b, l + 1);
    return true;
  }

  // AS object
  private static boolean import_statement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_statement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AS);
    r = r && object(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ((LOWER_CASE_ID | LIQUID | ORE) DOT)*
  public static boolean import_str(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_str")) return false;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_STR, "<import str>");
    while (true) {
      int c = current_position_(b);
      if (!import_str_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "import_str", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // (LOWER_CASE_ID | LIQUID | ORE) DOT
  private static boolean import_str_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_str_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = import_str_0_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    exit_section_(b, m, null, r);
    return r;
  }

  // LOWER_CASE_ID | LIQUID | ORE
  private static boolean import_str_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "import_str_0_0")) return false;
    boolean r;
    r = consumeToken(b, LOWER_CASE_ID);
    if (!r) r = consumeToken(b, LIQUID);
    if (!r) r = consumeToken(b, ORE);
    return r;
  }

  /* ********************************************************** */
  // import_statement | function_call | item_function_call | var_init
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = import_statement(b, l + 1);
    if (!r) r = function_call(b, l + 1);
    if (!r) r = item_function_call(b, l + 1);
    if (!r) r = var_init(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (mc_item | ore_liquid_entry) DOT function_call
  public static boolean item_function_call(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_function_call")) return false;
    if (!nextTokenIs(b, LESS_THAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = item_function_call_0(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && function_call(b, l + 1);
    exit_section_(b, m, ITEM_FUNCTION_CALL, r);
    return r;
  }

  // mc_item | ore_liquid_entry
  private static boolean item_function_call_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_function_call_0")) return false;
    boolean r;
    r = mc_item(b, l + 1);
    if (!r) r = ore_liquid_entry(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LESS_THAN LOWER_CASE_ID COLON LOWER_CASE_ID (COLON NUMBER)? GREATER_THAN
  public static boolean mc_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mc_item")) return false;
    if (!nextTokenIs(b, LESS_THAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LESS_THAN, LOWER_CASE_ID, COLON, LOWER_CASE_ID);
    r = r && mc_item_4(b, l + 1);
    r = r && consumeToken(b, GREATER_THAN);
    exit_section_(b, m, MC_ITEM, r);
    return r;
  }

  // (COLON NUMBER)?
  private static boolean mc_item_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mc_item_4")) return false;
    mc_item_4_0(b, l + 1);
    return true;
  }

  // COLON NUMBER
  private static boolean mc_item_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mc_item_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // UPPER_CASE_ID | LOWER_CASE_ID | IINGREDIENT | IITEMSTACK | IOREDICTENTRY | ILIQUIDSTACK
  public static boolean object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT, "<object>");
    r = consumeToken(b, UPPER_CASE_ID);
    if (!r) r = consumeToken(b, LOWER_CASE_ID);
    if (!r) r = consumeToken(b, IINGREDIENT);
    if (!r) r = consumeToken(b, IITEMSTACK);
    if (!r) r = consumeToken(b, IOREDICTENTRY);
    if (!r) r = consumeToken(b, ILIQUIDSTACK);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LESS_THAN (ORE | LIQUID) COLON LOWER_CASE_ID GREATER_THAN
  public static boolean ore_liquid_entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ore_liquid_entry")) return false;
    if (!nextTokenIs(b, LESS_THAN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LESS_THAN);
    r = r && ore_liquid_entry_1(b, l + 1);
    r = r && consumeTokens(b, 0, COLON, LOWER_CASE_ID, GREATER_THAN);
    exit_section_(b, m, ORE_LIQUID_ENTRY, r);
    return r;
  }

  // ORE | LIQUID
  private static boolean ore_liquid_entry_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ore_liquid_entry_1")) return false;
    boolean r;
    r = consumeToken(b, ORE);
    if (!r) r = consumeToken(b, LIQUID);
    return r;
  }

  /* ********************************************************** */
  // var_modifier object ((AS object) | (EQUALS var_value AS object (BRACKET_LEFT BRACKET_RIGHT)* SEMICOLON))
  public static boolean var_init(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_init")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_INIT, "<var init>");
    r = var_modifier(b, l + 1);
    r = r && object(b, l + 1);
    r = r && var_init_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AS object) | (EQUALS var_value AS object (BRACKET_LEFT BRACKET_RIGHT)* SEMICOLON)
  private static boolean var_init_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_init_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = var_init_2_0(b, l + 1);
    if (!r) r = var_init_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // AS object
  private static boolean var_init_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_init_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AS);
    r = r && object(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EQUALS var_value AS object (BRACKET_LEFT BRACKET_RIGHT)* SEMICOLON
  private static boolean var_init_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_init_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUALS);
    r = r && var_value(b, l + 1);
    r = r && consumeToken(b, AS);
    r = r && object(b, l + 1);
    r = r && var_init_2_1_4(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BRACKET_LEFT BRACKET_RIGHT)*
  private static boolean var_init_2_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_init_2_1_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!var_init_2_1_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "var_init_2_1_4", c)) break;
    }
    return true;
  }

  // BRACKET_LEFT BRACKET_RIGHT
  private static boolean var_init_2_1_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_init_2_1_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BRACKET_LEFT, BRACKET_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STATIC | VAL | VAR | GLOBAL
  public static boolean var_modifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_modifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_MODIFIER, "<var modifier>");
    r = consumeToken(b, STATIC);
    if (!r) r = consumeToken(b, VAL);
    if (!r) r = consumeToken(b, VAR);
    if (!r) r = consumeToken(b, GLOBAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // array | NUMBER | boolean | mc_item | ore_liquid_entry
  public static boolean var_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_VALUE, "<var value>");
    r = array(b, l + 1);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = boolean_$(b, l + 1);
    if (!r) r = mc_item(b, l + 1);
    if (!r) r = ore_liquid_entry(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean zenScriptFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "zenScriptFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "zenScriptFile", c)) break;
    }
    return true;
  }

}
