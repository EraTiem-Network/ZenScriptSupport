package net.eratiem.zenscriptsupport.language

import com.intellij.openapi.fileTypes.LanguageFileType
import org.jetbrains.annotations.NotNull
import javax.swing.Icon

class ZenScriptFileType : LanguageFileType(ZenScriptLanguage.INSTANCE) {
    companion object {
        val INSTANCE: ZenScriptFileType = ZenScriptFileType()
    }

    @NotNull
    override fun getName(): String = "ZenScript-File"

    @NotNull
    override fun getDescription(): String = "ZenScript language file"

    @NotNull
    override fun getDefaultExtension(): String = "zs"

    @NotNull
    override fun getIcon(): Icon = ZenScriptIcons.FILE
}