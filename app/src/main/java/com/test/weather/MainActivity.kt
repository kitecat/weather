package com.test.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.test.weather.model.ItemViewModel
import com.test.weather.view.CitiesListFragment
import com.test.weather.view.WeeklyForecastsFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            changeFragment(CitiesListFragment())
        }

        viewModel.selectedItem.observe(this, Observer { item ->
            changeFragment(WeeklyForecastsFragment())
        })
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                fragment,
                fragment::class.java.simpleName
            )
            .commit()
    }
}