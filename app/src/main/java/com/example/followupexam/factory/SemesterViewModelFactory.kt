package com.example.followupexam.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.followupexam.model.Module
import com.example.followupexam.viewmodel.SemesterViewModel
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a factory to [SemesterViewModel]
 * so is an intermediary between ui controller and the view model
 */
class SemesterViewModelFactory(private val user: FirebaseUser,private val modules: List<Module>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SemesterViewModel::class.java)) {
            return SemesterViewModel(user,modules) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}