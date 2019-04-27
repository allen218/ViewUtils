package com.allen.viewutils

import kotlin.reflect.KClass

/**
 * Created by allen on 19/4/26.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class EventRoot(val subscribeMethod: String, val eventSource: KClass<*>, val callbackMethod: String)