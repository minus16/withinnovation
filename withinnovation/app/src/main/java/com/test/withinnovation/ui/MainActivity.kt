package com.test.withinnovation.view

import android.os.Bundle
import com.test.withinnovation.R
import com.test.withinnovation.ui.fragment.MainFragment
import com.test.withinnovation.view.base.BaseActivity

class MainActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment:MainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container_layout, fragment).commitAllowingStateLoss()
    }
}