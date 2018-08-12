package com.mercaritask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mercaritask.R
import com.mercaritask.ui.fragment.MainFragmentKt

class MainActivityKt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpActionBar()
        var fragment = supportFragmentManager.findFragmentByTag(MainFragmentKt.TAG)
        if (fragment == null) {
            fragment = MainFragmentKt()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment,
                    MainFragmentKt.TAG).commit()
        }
    }

    private fun setUpActionBar() {
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setIcon(R.mipmap.ic_launcher)
        }
    }
}