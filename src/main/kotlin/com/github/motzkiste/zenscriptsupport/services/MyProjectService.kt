package com.github.motzkiste.zenscriptsupport.services

import com.intellij.openapi.project.Project
import com.github.motzkiste.zenscriptsupport.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
