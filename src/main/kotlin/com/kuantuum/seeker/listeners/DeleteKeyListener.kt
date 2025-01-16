package com.kuantuum.seeker.listeners

import com.intellij.openapi.vfs.VirtualFile
import com.kuantuum.seeker.FileService
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.VK_DELETE
import java.awt.event.KeyListener
import javax.swing.DefaultListModel
import javax.swing.JList

class DeleteKeyListener(private val list: JList<*>) : KeyListener {
    private val fileService = FileService.getInstance()
    private val files = fileService.getAll()

    override fun keyTyped(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        if (e?.keyCode == VK_DELETE) {
            val selectedIndices = list.selectedIndices

            if (selectedIndices.isNotEmpty()) {
                for (index in selectedIndices) {
                    if (index >= files.size) {
                        return
                    }

                    val fileToRemove = files[index]
                    val model = DefaultListModel<VirtualFile>()

                    fileService.remove(fileToRemove)
                    files.forEach { model.addElement(it) }
                    list.model = model
                    list.repaint()
                }
            }
        }
    }
}