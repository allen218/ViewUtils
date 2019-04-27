package com.allen.viewutils

import android.view.View

/**
 * Created by allen on 19/4/27.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@EventRoot(
    subscribeMethod = "setOnClickListener",
    eventSource = View.OnClickListener::class,
    callbackMethod = "onClick"
)
annotation class OnClick(val ids: IntArray)