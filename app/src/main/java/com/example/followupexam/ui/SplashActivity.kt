package com.example.followupexam.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.followupexam.R
import com.example.followupexam.factory.SplashViewModelFactory
import com.example.followupexam.model.Module
import com.example.followupexam.model.Student
import com.example.followupexam.viewmodel.SplashViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * This class is ui controller fo the Splash interface
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var factory: SplashViewModelFactory
    private lateinit var viewModel: SplashViewModel
    //This variables will hold the student received from LogoActivity
    private lateinit var currentStudent: Student
    //This variable will hod the modules list to be help filter the supported module list
    private lateinit var modules: List<Module>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val user = FirebaseAuth.getInstance().currentUser
        factory = SplashViewModelFactory(user)
        viewModel = ViewModelProvider(this,factory).get(SplashViewModel::class.java)

        //Get the student data sent from LogoActivity
        currentStudent = intent?.extras?.getParcelable<Student>("currentStudent")!!

        //Observe allModule attribute of SplashViewModel instance and if it is not null
        //set modules attribute of this class
        viewModel.allModules.observe(this,{
            if (it != null) {
                modules = it
            }
        })

        //Observe allGrade attribute of SplashViewModel instance and if it is not null
        //set call the setGrade method of viewModel with currentStudent attribute of this class help
        viewModel.allGrade.observe(this,{
            if (it != null){
                viewModel.setGrades(it,currentStudent)
            }

        })

        //Observe grade attribute of SplashViewModel instance and if it is not null
        //set call the setModule method of viewModel with modules attribute of this class help
        viewModel.grades.observe(this,{
            if (it!= null)
                viewModel.setModules(it,modules)

        })

        //Observe module attribute of SplashViewModel instance and if it is not null
        //navigate to HomeActivity passing the currentStudent and the values of this attribute as param
        viewModel.modules.observe(this,{
            if (it!=null){
                navigateToStart(it as ArrayList<Module>,currentStudent)
            }
        })


    }

    /**
     * This method responsible in navigating to [HomeActivity] and send some data with it
     * @param modules:represent the modules list that is going to be send
     * @param student:represent the student that is going to be send
     */
    private fun navigateToStart(modules :  ArrayList<Module>,student: Student){
        val intent = Intent(applicationContext, HomeActivity::class.java)
        intent.putExtra("modules",modules)
        intent.putExtra("student",student)
        //clear the back stack
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}