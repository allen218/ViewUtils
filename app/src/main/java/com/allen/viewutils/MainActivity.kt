package com.allen.viewutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
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

    @OnClick([R.id.tv1])
    fun tv1OnClick(view: View) {
        Log.i("allen", "tv1 clicked.")
    }

    @OnLongClick([R.id.tv2])
    fun tv1OnLongClick(view: View): Boolean {

        Log.i("allen", "tv2 long click.")
        return true
    }
}
