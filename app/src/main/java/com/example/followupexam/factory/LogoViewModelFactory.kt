package com.example.followupexam.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.followupexam.viewmodel.LogoViewModel
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a factory to [LogoViewModel]
 * so is an intermediary between ui controller and the view model
 */
class LogoViewModelFactory (private val user: FirebaseUser): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogoViewModel::class.java)) {
            return LogoViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}