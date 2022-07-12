package net.eratiem.zenscriptsupport.services

import com.intellij.openapi.project.Project
import net.eratiem.zenscriptsupport.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
