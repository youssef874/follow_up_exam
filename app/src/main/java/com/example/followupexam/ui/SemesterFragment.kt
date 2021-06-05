package com.example.followupexam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.followupexam.R
import com.example.followupexam.adapter.RecycleViewAdapter
import com.example.followupexam.databinding.FragmentSemesterBinding
import com.example.followupexam.factory.SemesterViewModelFactory
import com.example.followupexam.model.Module
import com.example.followupexam.viewmodel.Order
import com.example.followupexam.viewmodel.SemesterViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * This class is ui controller fo the logo interface
 */
class SemesterFragment : Fragment() {

    private lateinit var binding: FragmentSemesterBinding

    //This variable will hold modules lise send by ViewPagerFragment
    private lateinit var modules: List<Module>

    private lateinit var factory: SemesterViewModelFactory
    private lateinit var viewModel: SemesterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSemesterBinding.inflate(inflater,container,false)

        //Get the module list
        arguments?.takeIf { it.containsKey("modules") }?.apply {
            modules = getParcelableArrayList<Module>("modules") as List<Module>
        }
        //Get the current user
        val user = FirebaseAuth.getInstance().currentUser!!
        factory = SemesterViewModelFactory(user,modules)
        viewModel = ViewModelProvider(this,factory).get(SemesterViewModel::class.java)
        //Set this fragment to be the lifecycle owner of the the corresponding layout because were going to use dataBinding in it
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recycleView.adapter = RecycleViewAdapter(RecycleViewAdapter.OnClickListener{
            //Set the clicked item data
            viewModel.displayResultDetail(it)
        })

        viewModel.navigateToResultFragment.observe(viewLifecycleOwner,{
            if (it != null){
                val action = ViewPagerFragmentDirections.actionViewPagerFragmentToResultFragment(it)
                findNavController().navigate(action)
                viewModel.displayResultComplete()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.orderStatus.observe(viewLifecycleOwner,{
            when(it){
                Order.DEFAULT -> viewModel.defaultOrder()
                Order.MOST_VALUE -> viewModel.sortMostValue()
                else -> viewModel.sortLessValue()
            }
        })

        when(binding.filterOption.checkedRadioButtonId){
            R.id.most_value_filter ->{
                viewModel.setOrderStatus(Order.MOST_VALUE)
            }
            R.id.less_value_filter -> {
                viewModel.setOrderStatus(Order.LESS_VALUE)
            }
           else -> {
               viewModel.setOrderStatus(Order.DEFAULT)
           }

        }
    }

}