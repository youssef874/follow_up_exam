package com.example.followupexam.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.followupexam.getModelForTabItem
import com.example.followupexam.model.Module
import com.example.followupexam.ui.SemesterFragment

/**
 * This class represent The [ViewPager2] adapter
 * @constructor take tow param
 * @param modules represent the module to display later
 * @param fragment : represent the fragment will hold this adapter
 */
class ViewPagerAdapter (private val modules:  ArrayList<Module>,fragment: Fragment): FragmentStateAdapter(fragment){

    override fun getItemCount(): Int {
        return 2
    }

    /**
     * Since the tabbed view will be in form [SemesterFragment] so this method will construct
     * it an instance of it passing to it the modules list to display
     */
    override fun createFragment(position: Int): Fragment {
        val fragment = SemesterFragment()
        fragment.arguments = Bundle().apply {
            //This values hold the module data to display using getModelForTabItem from Util file
            val tabModule = getModelForTabItem(modules,position+1) as ArrayList<Module>
            putParcelableArrayList("modules",tabModule)
        }
        return fragment
    }

}