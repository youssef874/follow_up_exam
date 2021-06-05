package com.example.followupexam.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.followupexam.R
import com.example.followupexam.factory.LogoViewModelFactory
import com.example.followupexam.model.Student
import com.example.followupexam.viewmodel.LogoViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * This class is ui controller fo the logo interface
 */
class LogoActivity : AppCompatActivity() {

    private val user  = FirebaseAuth.getInstance().currentUser
    private val factory = LogoViewModelFactory(user)
    private val viewModel: LogoViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)


        viewModel.student.observe(this,{
            if (it!=null)
                navigateToSplashScreen(it)
        })
    }

    /**
     * This method to navigate to [SplashActivity] and sand the current student data
     * @param student: the current student data
     */
    private fun navigateToSplashScreen(student: Student){
        val intent = Intent(applicationContext, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("currentStudent",student)
        startActivity(intent)
        finish()
    }

}