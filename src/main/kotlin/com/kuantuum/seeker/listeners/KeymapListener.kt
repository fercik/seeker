package com.kuantuum.seeker.listeners

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.keymap.Keymap
import com.intellij.openapi.keymap.KeymapManagerListener

class KeymapListener : KeymapManagerListener {
    private val logger = Logger.getInstance(KeymapListener::class.java)

    override fun activeKeymapChanged(newKeymap: Keymap?) {
        logger.info("Active keymap changed to: ${newKeymap?.name}")
    }
}