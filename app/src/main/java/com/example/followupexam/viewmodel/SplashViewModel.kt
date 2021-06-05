package com.example.followupexam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.followupexam.getSupportedGrade
import com.example.followupexam.getSupportedModules
import com.example.followupexam.model.Grade
import com.example.followupexam.model.Module
import com.example.followupexam.model.Student
import com.example.followupexam.repository.Repository
import com.google.firebase.auth.FirebaseUser

/**
 * This class is a view model for [SplashActivity]
 * @constructor take the authenticated user in form of [FirebaseUser]
 */
class SplashViewModel(user: FirebaseUser) : ViewModel() {

    //instance of [Repository] to get the data we want
    private val rep = Repository(user)

    //This variable will hold all modules from [Repository]
    private var _allModules = MutableLiveData<List<Module>?>()
    val allModules: LiveData<List<Module>?>
        get() = _allModules

    init {

        _allModules = rep.getModules() as MutableLiveData<List<Module>?>
    }

    //This variable will hold all grades from [Repository]
    val allGrade = rep.getGrades()

    //This variables will hold only the list modules wheres idModule
    //equals the grades  moduleId
    private var _modules = MutableLiveData<List<Module>?>()
    val modules: LiveData<List<Module>?>
        get() = _modules

    //This variables will hold only the list grade wheres gradeName
    //equals the student gradeNAME
    private var _grades = MutableLiveData<List<Grade>?>()
    val grades: LiveData<List<Grade>?>
        get() = _grades

    /**
     * Tis method to set the _grades attribute value
     * @param grades :all grades from rep
     * @param student:the student to set the value based on it's gradeName
     */
    fun setGrades(grades: List<Grade>, student: Student) {
        //This method exist in util file
        _grades.value = getSupportedGrade(student, grades)
    }

    /**
     * Tis method to set the _modules attribute value
     * @param grades :the filtered grade
     * @param moduleF:all modules list from rep
     */
    fun setModules(grades: List<Grade>, moduleF: List<Module>) {
        //This method exist in util file
        _modules.value = getSupportedModules(grades, moduleF)
    }

}