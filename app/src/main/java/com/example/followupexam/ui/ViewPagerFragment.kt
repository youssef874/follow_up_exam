package com.example.followupexam.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.followupexam.R
import com.example.followupexam.adapter.ViewPagerAdapter
import com.example.followupexam.databinding.FragmentViewPagerBinding
import com.example.followupexam.model.Grade
import com.example.followupexam.model.Module
import com.example.followupexam.model.Student
import com.google.android.material.tabs.TabLayoutMediator

const val TAG_P = "ViewPagerFragment"

/**
 * This class represent the start direction of nvGraph of navigation component
 * and holder of TbView using [ViewPager2]
 */
class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPager: ViewPager2

    //This variable will hold student send it by SplashActivity
    private lateinit var student: Student
    //This variable will hold modules list send it by SplashActivity
    private lateinit var modules: ArrayList<Module>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = requireActivity().intent.extras
        //Get the student send by SplashActivity
        student = bundle?.getParcelable<Student>("student") as Student
        //Get the modules list send by SplashActivity
        modules = bundle?.getParcelableArrayList<Grade>("modules")!!  as ArrayList<Module>

        binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        viewPager = binding.viewPager

        //create a viewPager2 adapter instance
        val adapter = ViewPagerAdapter(modules,this)
        //bind the adapter instance with ViewPagerAdapter of xml layout ViewPager2 element using viewBinding
        viewPager.adapter = adapter
        val tabLayout = binding.tabLayout
        //Create tow Lab layout one for semester1 and one for semester 2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Semester ${(position + 1)}"
        }.attach()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.prolile_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.profile_menu_item -> {
                navigateToProfile()
                true
            }else ->  super.onOptionsItemSelected(item)
        }
    }

    /**
     * This method responsible for navigating to [ProfileFragment] and send the modules using navigation
     * component and safeArgs plugin
     */
    private fun navigateToProfile(){
        val action =  ViewPagerFragmentDirections.actionViewPagerFragmentToProfileFragment(student)
        findNavController().navigate(action)
    }
}