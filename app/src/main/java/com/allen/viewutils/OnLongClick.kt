package com.allen.viewutils

import android.view.View

/**
 * Created by allen on 19/4/27.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@EventRoot(
    subscribeMethod = "setOnLongClickListener",
    eventSource = View.OnLongClickListener::class,
    callbackMethod = "onLongClick"
)
annotation class OnLongClick(val ids: IntArray)