package com.kuantuum.seeker.listeners

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.kuantuum.seeker.FileService
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.VK_0
import java.awt.event.KeyEvent.VK_1
import java.awt.event.KeyEvent.VK_2
import java.awt.event.KeyEvent.VK_3
import java.awt.event.KeyEvent.VK_4
import java.awt.event.KeyEvent.VK_5
import java.awt.event.KeyEvent.VK_6
import java.awt.event.KeyEvent.VK_7
import java.awt.event.KeyEvent.VK_8
import java.awt.event.KeyEvent.VK_9
import java.awt.event.KeyListener

class NumericKeyListener(private val project: Project) : KeyListener {
    private val fileService = FileService.getInstance()
    private val files = fileService.getAll()

    override fun keyTyped(event: KeyEvent?) {}
    override fun keyReleased(event: KeyEvent?) {}

    override fun keyPressed(event: KeyEvent?) {
        if (files.isEmpty()) {
            return
        }

        if (event == null) {
            return
        }

        println("Pressed ${event.keyCode}")

        val file: VirtualFile? = when (event.keyCode) {
            VK_0 -> fileService.getAt(0)
            VK_1 -> fileService.getAt(1)
            VK_2 -> fileService.getAt(2)
            VK_3 -> fileService.getAt(3)
            VK_4 -> fileService.getAt(4)
            VK_5 -> fileService.getAt(5)
            VK_6 -> fileService.getAt(6)
            VK_7 -> fileService.getAt(7)
            VK_8 -> fileService.getAt(8)
            VK_9 -> fileService.getAt(9)
            else -> {
                return
            }
        }

        openFile(file, project)
    }

    private fun openFile(file: VirtualFile?, project: Project) {
        if (file == null) {
            return
        }

        FileEditorManager
            .getInstance(project)
            .openFile(file, true)
    }
}