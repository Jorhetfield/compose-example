package com.example.cursojetpackcompose.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cursojetpackcompose.model.Item
import com.example.cursojetpackcompose.model.itemList

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(itemList)
        private set

    fun actionClick(action: Action, index: Int) {
        state = state.toMutableList().apply {
            when (action) {
                Action.CLONE -> add(index, get(index))
                Action.DELETE -> removeAt(index)
            }
        }
    }

}