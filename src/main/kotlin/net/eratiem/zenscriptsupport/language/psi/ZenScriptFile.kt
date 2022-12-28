package net.eratiem.zenscriptsupport.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import net.eratiem.zenscriptsupport.language.ZenScriptFileType
import net.eratiem.zenscriptsupport.language.ZenScriptLanguage
import org.jetbrains.annotations.NotNull

class ZenScriptFile(
    @NotNull viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, ZenScriptLanguage.INSTANCE) {

    @NotNull
    override fun getFileType() = ZenScriptFileType.INSTANCE

    override fun toString(): String = "ZenScript-File"
}