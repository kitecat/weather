package com.test.weather.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<Weather>()
    val selectedItem: LiveData<Weather> get() = mutableSelectedItem

    fun selectItem(item: Weather) {
        mutableSelectedItem.value = item
    }
}