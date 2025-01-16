package com.kuantuum.seeker.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.kuantuum.seeker.FileService

class AddAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        event.project ?: return

        val file = event.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        FileService
            .getInstance()
            .add(file)
    }
}