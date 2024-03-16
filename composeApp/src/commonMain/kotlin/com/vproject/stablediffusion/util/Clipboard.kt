package com.vproject.stablediffusion.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

/**
 * Platform's context.
 */
expect class PlatformContext

@get:Composable
@get:ReadOnlyComposable
expect val platformContext: PlatformContext

/**
 * Clipboard manager for the platform that has capability to read the text from clipboard.
 */
class ClipboardManager(private val platformContext: PlatformContext) {
    /**
     * Returns the text from the clipboard.
     */
    suspend fun getText(): String? {
        return platformContext.getText()
    }

    suspend fun setText(text: String) {
        platformContext.setText(text)
    }

    suspend fun share() {
        platformContext.share()
    }
}

/**
 * Returns the [ClipboardManager] for the current platform in the current composition.
 */
@Composable
fun rememberClipboardManager(): ClipboardManager {
    val context = platformContext
    return remember { ClipboardManager(context) }
}

expect suspend fun PlatformContext.getText(): String?
expect suspend fun PlatformContext.setText(text: String)
expect suspend fun PlatformContext.share()