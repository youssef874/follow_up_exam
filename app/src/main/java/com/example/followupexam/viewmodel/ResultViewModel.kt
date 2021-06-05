package com.example.followupexam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.followupexam.getSupportedResult
import com.example.followupexam.model.Module
import com.example.followupexam.model.Result
import com.example.followupexam.repository.Repository
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a view model for [ResultFragment]
 * @constructor take the authenticated user in form of [FirebaseUser] and the module which from it is idModule
 * attribute we are going extract result and display it
 */
class ResultViewModel(user: FirebaseUser, private val module: Module):ViewModel() {

    private val rep = Repository(user)

    //This variable will hold the result list how has moduleId equal as the module idModule
    private var _results = MutableLiveData<List<Result>?>()
    val result: LiveData<List<Result>?>
        get() = _results

    init {
        _results = rep.getResult().map {
            getSupportedResult(module,it)
        } as MutableLiveData<List<Result>?>
    }
}