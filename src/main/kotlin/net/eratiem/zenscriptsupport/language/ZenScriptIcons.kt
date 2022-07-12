package net.eratiem.zenscriptsupport.language

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class ZenScriptIcons {
    companion object {
        val FILE: Icon = IconLoader.getIcon("/META-INF/pluginIcon.svg", ZenScriptIcons::class.java)

//        val FILE_DARK: Icon = IconLoader.getDarkIcon(IconLoader.getIcon("/META-INF/pluginIcon_dark.svg", ZenScriptIcons::class.java), true)
    }
}