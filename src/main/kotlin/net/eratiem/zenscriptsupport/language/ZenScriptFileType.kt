package net.eratiem.zenscriptsupport.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ZenScriptFileType: LanguageFileType(ZenScriptLanguage.INSTANCE) {
    companion object {
        val INSTANCE: ZenScriptFileType = ZenScriptFileType()
    }

    override fun getName(): String = "ZenScript-File"

    override fun getDescription(): String = "ZenScript language file"

    override fun getDefaultExtension(): String = "zs"

    override fun getIcon(): Icon = ZenScriptIcons.FILE
}