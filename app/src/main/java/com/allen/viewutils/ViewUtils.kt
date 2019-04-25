package com.allen.viewutils

import android.util.Log

/**
 * Created by allen on 19/4/26.
 */
object ViewUtils {
    fun inject(obj: Any) {
        injectContentView(obj)
    }

    private fun injectContentView(obj: Any) {

        val clz = obj.javaClass
        val contentViewAnnotation = clz.getAnnotation(ContentView::class.java)
            ?: throw RuntimeException("please input @ContentView to set ContentView.")

        val layoutId = contentViewAnnotation.value
        try {
            var setContentViewMethod = clz.getMethod("setContentView", Int::class.java)
            setContentViewMethod.invoke(obj, layoutId)
        } catch (ex: Exception) {
            Log.i("allen", "injectContentView occur exception, detail: $ex")
        }

    }
}