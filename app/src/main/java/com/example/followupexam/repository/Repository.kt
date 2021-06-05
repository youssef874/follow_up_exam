package com.example.followupexam.repository

import androidx.lifecycle.map
import com.example.followupexam.model.Grade
import com.example.followupexam.model.Module
import com.example.followupexam.model.Student
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

/***
 * This class hold all the necessary for the application including the database data
 * @constructor take the authenticated user in form of [FirebaseUser]
 */
class Repository(private val user: FirebaseUser) {

    //The firebase real time database root reference
    private val database = Firebase.database.reference

    //The current student data in form [DataSnapshot]
    private val studentSnapshot = DataSnapshotLiveData(database.child("student").child(user.uid))

    //All the data in the grade reference in form [DataSnapshot]
    private val gradesSnapShot = DataSnapshotLiveData(database.child("grades"))

    //All the data in the module reference in form [DataSnapshot]
    private val  modulesSnapshot =  DataSnapshotLiveData(database.child("modules"))

    //All the data in the result reference in form [DataSnapshot]
    private val resultSnapshot = DataSnapshotLiveData(database.child("results").child(user.uid))

    /**
     * serialize the student data
     * @return [Student]
     */
    fun getStudent() = studentSnapshot.map {
        it?.getValue<Student>()
    }

    /**
     * serialize the grades data
     * @return [List<Grade>]
     */
    fun getGrades() = gradesSnapShot.map {
        val list = ArrayList<Grade>()
        if (it != null) {
            for (data in it.children) {
                data?.getValue<Grade>()?.let { it1 -> list.add(it1) }
            }
        }
        return@map list
    }

    /**
     * serialize the modules data
     * @return [List<Modules>]
     */
    fun getModules() = modulesSnapshot.map {
        val list  = ArrayList<Module>()
        if (it != null){
            for (data in it.children){
                data?.getValue<Module>()?.let { module -> list.add(module) }
            }
        }
        return@map list
    }

    /**
     * serialize the results data
     * @return [List<Result>]
     */
    fun getResult() = resultSnapshot.map {
        val list  = ArrayList<com.example.followupexam.model.Result>()
        if (it != null){
            for (data in it.children){
                data?.getValue<com.example.followupexam.model.Result>()?.let { module -> list.add(module) }
            }
        }
        return@map list
    }
}