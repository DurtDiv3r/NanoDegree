package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private val _newShoe = MutableLiveData<Shoe>()
    val newShoe: LiveData<Shoe> get() = _newShoe

    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _newShoe.value = Shoe("", 0.0, "", "")
        _shoeList.value = mutableListOf()
    }

    fun add() {
        _shoeList.value?.add(_newShoe.value!!)
        _navigate.value = true
    }

    fun resetNav() {
        _navigate.value = false
        _newShoe.value = Shoe("", 0.0, "", "")
    }
}