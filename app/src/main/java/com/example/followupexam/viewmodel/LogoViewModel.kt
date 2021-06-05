package com.example.followupexam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.followupexam.model.Student
import com.example.followupexam.repository.Repository
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a view model for [LogoActivity]
 * @constructor take the authenticated user in form of [FirebaseUser]
 */
class LogoViewModel(user: FirebaseUser):ViewModel() {

    //instance of [Repository] to get the data we want
    private val rep = Repository(user)

    private var _student = MutableLiveData<Student?>()
    val student: LiveData<Student?>
    get() = _student

    init {
        _student = rep.getStudent() as MutableLiveData<Student?>
    }
}