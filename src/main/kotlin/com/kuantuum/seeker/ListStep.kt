package com.kuantuum.seeker

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class ListStep(
    private val files: MutableList<VirtualFile>,
    private val project: Project
) : BaseListPopupStep<VirtualFile>("Seeker", files) {
    override fun getIconFor(file: VirtualFile?): Icon? {
        return getIcon(file)
    }

    override fun getTextFor(file: VirtualFile): String {
        return getText(files, file, project)
    }

    override fun onChosen(
        selectedValue: VirtualFile,
        finalChoice: Boolean
    ): PopupStep<*>? {
        openFile(selectedValue, project)

        return FINAL_CHOICE
    }

    private fun openFile(file: VirtualFile, project: Project) {
        FileEditorManager.getInstance(project)
            .openFile(file, true)
    }

    private fun getIcon(file: VirtualFile?): Icon? {
        return file?.let {
            FileTypeManager.getInstance()
                .getFileTypeByFile(it).icon
        }
    }

    private fun getText(
        files: MutableList<VirtualFile>,
        currentFile: VirtualFile,
        project: Project
    ): String {
        return project.presentableUrl
            ?.let {
                currentFile.presentableUrl.replace("\\", "/")
                    .removePrefix(it)
            }
            ?.let {
                val index = files.indexOf(currentFile)

                if (index > 9) {
                    return "${currentFile.name} - $it"
                }

                return "$index : ${currentFile.name} - $it"
            }
            .toString()
    }
}