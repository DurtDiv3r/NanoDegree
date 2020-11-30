package com.udacity.shoestore.viewmodel

import android.util.Log
import android.widget.EditText
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private var _shoeList = MutableLiveData<MutableList<LiveData<Shoe>>>()
    val shoeList: LiveData<MutableList<LiveData<Shoe>>>
        get() = _shoeList

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private val _newShoe = MutableLiveData<Shoe>()
    val newShoe: LiveData<Shoe> get() = _newShoe

    fun add() {
        _shoeList.addNewItem(_newShoe)
        _navigate.value = true
    }

    private fun <T> MutableLiveData<MutableList<T>>.addNewItem(item: T) {
        val oldValue = this.value ?: mutableListOf()
        oldValue.add(item)
        this.value = oldValue
    }

    fun resetNav() {
        _navigate.value = false
    }

}