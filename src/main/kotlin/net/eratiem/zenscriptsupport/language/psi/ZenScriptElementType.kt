package net.eratiem.zenscriptsupport.language.psi

import com.intellij.psi.tree.IElementType
import net.eratiem.zenscriptsupport.language.ZenScriptLanguage
import javax.annotation.Nonnull

class ZenScriptElementType(@Nonnull debugName: String) : IElementType(debugName, ZenScriptLanguage.INSTANCE)