package net.eratiem.zenscriptsupport.language.psi

import com.intellij.psi.tree.IElementType
import net.eratiem.zenscriptsupport.language.ZenScriptLanguage
import org.jetbrains.annotations.NonNls

class ZenScriptTokenType(@NonNls debugName: String) : IElementType(debugName, ZenScriptLanguage.INSTANCE) {

    override fun toString(): String {
        return "ZenScriptTokenType.${super.toString()}"
    }
}