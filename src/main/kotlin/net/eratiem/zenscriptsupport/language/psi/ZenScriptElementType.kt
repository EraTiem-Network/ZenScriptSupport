package net.eratiem.zenscriptsupport.language.psi

import com.intellij.psi.tree.IElementType
import net.eratiem.zenscriptsupport.language.ZenScriptLanguage
import org.jetbrains.annotations.NotNull

class ZenScriptElementType(@NotNull debugName: String) : IElementType(debugName, ZenScriptLanguage.INSTANCE)