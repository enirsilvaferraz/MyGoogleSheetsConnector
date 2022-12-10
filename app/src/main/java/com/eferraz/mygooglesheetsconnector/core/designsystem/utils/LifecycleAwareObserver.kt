package com.eferraz.mygooglesheetsconnector.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleObserver

@Composable
fun LifecycleAwareObserver(observer: LifecycleObserver) {

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(observer, lifecycleOwner) {

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}