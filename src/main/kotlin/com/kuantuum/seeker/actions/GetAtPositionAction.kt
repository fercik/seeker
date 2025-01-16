package com.kuantuum.seeker.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.kuantuum.seeker.FileService

class GetAtPositionAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val fileService = FileService.getInstance()
        val listSize = fileService.size()
        val position = event
            .presentation
            .text
            .removePrefix("Get File On Position ")
            .toInt()

        if (position >= listSize) {
            return
        }

        val file = fileService.getAt(position)

        file?.let { openFile(it, project) }
    }

    private fun openFile(file: VirtualFile, project: Project) {
        FileEditorManager
            .getInstance(project)
            .openFile(file, true)
    }
}