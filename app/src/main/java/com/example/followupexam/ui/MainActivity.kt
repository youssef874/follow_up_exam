package com.example.followupexam.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.followupexam.R
import com.example.followupexam.databinding.ActivityMainBinding
import com.example.followupexam.setError
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.firebase.auth.FirebaseAuth

/**
 * This class hold authentication ui and login using firebase
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val auth = FirebaseAuth.getInstance()

    private lateinit var loading : CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         //If the user is already authenticated navigate directly to LogoActivity
        if (auth.currentUser != null){
           navigateToLogoScreen()
        }

        loading = binding.loading
        //hide circle indicate first tob display only after the login button is clicked
        loading.visibility = View.GONE

        //Change this activity title
        this.setTitle(R.string.login_title)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val psw = binding.passwordEditText.text.toString()
            //If the user enter anything and press the login button
            if (email.isNotEmpty() && psw.isNotEmpty()){
                //Show the circle indicator
                loading.visibility = View.VISIBLE
                //Verify the user authentication
                login(email, psw)
            }else{
                val emptyEmail = resources.getString(R.string.empty_email)
                val emptyPsw = resources.getString(R.string.empty_password)
                //Display an error message withe loginTextInputLayout and pswTextInputLayout using an extension method
                binding.login.setError(true,emptyEmail)
                binding.password.setError(true,emptyPsw)
            }
        }

    }

    private fun login(email: String,psw: String){
        auth.signInWithEmailAndPassword(email,psw).addOnCompleteListener(this){
            if (it.isSuccessful){
                //Disable the error
                binding.login.setError(false,"")
                binding.password.setError(false,"")
                //Hide the circle indicator
                loading.visibility = View.GONE
                val user = it.result?.user
                if (user != null){
                   navigateToLogoScreen()
                }

            }else{
                loading.visibility = View.GONE
                val wrongEmail = resources.getString(R.string.wrong_email)
                val wrongPsw = resources.getString(R.string.wrong_password)
                binding.login.setError(true,wrongEmail)
                binding.password.setError(true,wrongPsw)
            }
        }
    }

    /**
     * This method to navigate to LogoActivity and clear this activity from back stack
     */
    private fun navigateToLogoScreen(){
        val intent = Intent(applicationContext, LogoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    /**
     * This method to hid the keyboard
     * @param view: the view will be clicked to hide the keyboard
     * this method is called in this activity corresponding xml layout
     */
    fun hideKeyboard(view: View){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }


}