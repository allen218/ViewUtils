package com.allen.viewutils

import android.util.Log
import java.lang.reflect.Proxy

/**
 * Created by allen on 19/4/26.
 */
object ViewUtils {
    fun inject(obj: Any) {
        injectContentView(obj)
        injectViewId(obj)
        injectEvent(obj)
    }

    private fun injectEvent(obj: Any) {
        val clz = obj.javaClass
        val declaredMethods = clz.declaredMethods
        declaredMethods.forEach { method ->
            val methodAnnotation = method.annotations ?: return@forEach

            methodAnnotation.forEach {
                val annotationClass = it.annotationClass
                val javaObjectType = annotationClass.javaObjectType

                val eventRoot = javaObjectType.getAnnotation(EventRoot::class.java) ?: return@forEach

                val subscribeMethod = eventRoot.subscribeMethod
                val eventSource = eventRoot.eventSource
                val callbackMethod = eventRoot.callbackMethod

//                val idsField = javaObjectType.getDeclaredField("ids") ?: return@forEach

//                val idsArr = idsField.get(javaObjectType) as IntArray
                val annotationStr = it.toString()
                val idsArr =
                    annotationStr.subSequence(
                        annotationStr.indexOf("[") + 1,
                        annotationStr.indexOf("]")
                    ).split(",")
                if (idsArr.isEmpty()) {
                    return@forEach
                }

                for (id in idsArr) {
                    val findViewById = clz.getMethod("findViewById", Int::class.java)
                    val view = findViewById.invoke(obj, id.toInt())
                    val resultMethod = view.javaClass.getMethod(subscribeMethod, eventSource.java)
                    val eventProxy = Proxy.newProxyInstance(
                        eventSource::class.java.classLoader,
                        arrayOf(eventSource.java)
                    ) { proxy, proxyMethod, args ->
                        if (callbackMethod != proxyMethod.name) {
                            return@newProxyInstance proxyMethod.invoke(proxy, *args)
                        }
                        return@newProxyInstance method.invoke(obj, *args)
                    }
                    resultMethod.invoke(view, eventProxy)
                }
            }

        }
    }

    private fun injectViewId(obj: Any) {
        val clz = obj.javaClass
        val fields = clz.declaredFields
        if (fields.isEmpty()) {
            return
        }

        val findViewById = clz.getMethod("findViewById", Int::class.java)
        fields.forEach {
            it.isAccessible = true
            val injectAnnotation = it.getAnnotation(Inject::class.java) ?: return@forEach

            val viewId = injectAnnotation.value
            val viewValue = findViewById.invoke(obj, viewId) ?: return@forEach

            it.set(obj, viewValue)
        }
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