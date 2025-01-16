package com.kuantuum.seeker.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.kuantuum.seeker.FileService

class AddAtPositionAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        event.project ?: return

        val fileService = FileService.getInstance()
        val position = event
            .presentation
            .text
            .removePrefix("Add Current File On Position ")
            .toInt()
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        if (fileService.has(file)) {
            fileService.remove(file)
        }

        fileService.addAtPosition(file, position)
    }
}