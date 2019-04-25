package com.allen.viewutils

import android.support.annotation.LayoutRes

/**
 * Created by allen on 19/4/26.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ContentView(@LayoutRes val value: Int)