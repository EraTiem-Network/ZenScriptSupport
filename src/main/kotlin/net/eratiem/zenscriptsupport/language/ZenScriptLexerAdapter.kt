package net.eratiem.zenscriptsupport.language

import com.intellij.lexer.FlexAdapter

class ZenScriptLexerAdapter : FlexAdapter(ZenScriptLexer(null))