package net.eratiem.zenscriptsupport.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import net.eratiem.zenscriptsupport.language.ZenScriptFileType
import net.eratiem.zenscriptsupport.language.ZenScriptLanguage

class ZenScriptFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZenScriptLanguage.INSTANCE) {
    override fun getFileType() = ZenScriptFileType.INSTANCE

    override fun toString(): String = "ZenScript-File"
}