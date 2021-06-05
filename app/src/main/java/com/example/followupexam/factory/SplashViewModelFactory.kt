package com.example.followupexam.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.followupexam.viewmodel.SplashViewModel
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a factory to [SplashViewModel]
 * so is an intermediary between ui controller and the view model
 */
class SplashViewModelFactory(private val user: FirebaseUser): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(user) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}