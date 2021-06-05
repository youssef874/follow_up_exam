package com.example.followupexam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.followupexam.databinding.FragmentProfileBinding
import com.example.followupexam.model.Student
import com.squareup.picasso.Picasso

/**
 * This class is ui controller fo the profile interface
 */
class ProfileFragment : Fragment() {

    //This variable will hold the student send by ViewPagerFragment
    private lateinit var student: Student

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get the result from ViewPagerFragment
        arguments?.let {
            student = it.get("student") as Student
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)

        setProfile()
        return binding.root
    }

    /**
     * Set the authenticated user data in the views
     */
    private fun setProfile(){
        binding.emailTextView.text = student.email
        binding.gradeTextView.text = student.gradeName
        binding.lastNameTextView.text = student.lastName
        binding.nameTextView.text = student.name
        binding.phoneNumberTextView.text = student.phoneNumber.toString()
        val link = student.imageUrl
        Picasso.get().load(link).into(binding.profileImageView)
    }
}