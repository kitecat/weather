package com.test.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.weather.view.CitiesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    CitiesListFragment(),
                    CitiesListFragment::class.java.simpleName
                )
                .commit()
        }
    }
}