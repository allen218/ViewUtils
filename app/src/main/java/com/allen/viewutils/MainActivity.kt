package com.allen.viewutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

@ContentView(value = R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    @Inject(R.id.tv1)
    lateinit var tv1: TextView

    @Inject(R.id.tv2)
    lateinit var tv2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewUtils.inject(this)
        tv1.text = "tv1."
        tv2.text = "tv2"
    }
}
