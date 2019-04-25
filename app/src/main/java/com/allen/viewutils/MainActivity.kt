package com.allen.viewutils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@ContentView(value = R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewUtils.inject(this)
    }
}
