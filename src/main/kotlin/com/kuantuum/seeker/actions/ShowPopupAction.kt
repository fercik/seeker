package com.kuantuum.seeker.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.popup.*
import com.intellij.ui.popup.list.ListPopupImpl
import com.intellij.util.ReflectionUtil
import com.kuantuum.seeker.FileService
import com.kuantuum.seeker.ListStep
import com.kuantuum.seeker.listeners.DeleteKeyListener
import com.kuantuum.seeker.listeners.NumericKeyListener
import javax.swing.JList

class ShowPopupAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val files = FileService.getInstance()
            .getAll()
        val popupStep = ListStep(files, project)
        val popup: ListPopup = JBPopupFactory
            .getInstance()
            .createListPopup(popupStep)

        popup.showInFocusCenter()

        try {
            val list: JList<*> = ReflectionUtil.getField<JList<*>>(
                ListPopupImpl::class.java,
                popup,
                null,
                "myList"
            ) as JList<*>

            list.addKeyListener(DeleteKeyListener(list))
            list.addKeyListener(NumericKeyListener(project))
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
            return
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return
        }
    }
}