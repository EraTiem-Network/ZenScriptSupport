package net.eratiem.zenscriptsupport.language

import com.intellij.lang.Language

class ZenScriptLanguage : Language("ZenScript") {
    companion object {
        val INSTANCE: ZenScriptLanguage = ZenScriptLanguage()
    }
}