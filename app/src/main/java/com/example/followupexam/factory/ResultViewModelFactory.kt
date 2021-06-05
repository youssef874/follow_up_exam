package com.example.followupexam.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.followupexam.model.Module
import com.example.followupexam.viewmodel.ResultViewModel
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a factory to [ResultViewModel]
 * so is an intermediary between ui controller and the view model
 */
class ResultViewModelFactory(private val user: FirebaseUser,private val module: Module): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(user,module) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}