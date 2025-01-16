package com.kuantuum.seeker

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.vfs.VirtualFile

@Service
class FileService {
    private val list = mutableListOf<VirtualFile>()
    private var currentIndex = -1

    fun getAll(): MutableList<VirtualFile> {
        return list
    }

    fun getAt(position: Int): VirtualFile? {
        currentIndex = position
        return list[position]
    }

    fun add(file: VirtualFile) {
        if (!list.contains(file)) {
            list.add(file)
        }
    }

    fun addAtPosition(file: VirtualFile, position: Int) {
        list.add(position, file)
        currentIndex = position
    }

    fun remove(file: VirtualFile) {
        list.remove(file)
    }

    fun next(): VirtualFile? {
        if (list.isEmpty()) return null
        currentIndex = (currentIndex + 1) % list.size
        return list[currentIndex]
    }

    fun prev(): VirtualFile? {
        if (list.isEmpty()) return null
        currentIndex = (currentIndex - 1 + list.size) % list.size
        return list[currentIndex]
    }

    fun size(): Int {
        return list.size
    }

    fun has(file: VirtualFile): Boolean {
        return list.contains(file)
    }

    companion object {
        fun getInstance(): FileService = service()
    }
}