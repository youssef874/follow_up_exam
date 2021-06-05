package com.example.followupexam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.followupexam.R
import com.example.followupexam.databinding.FragmentResultBinding
import com.example.followupexam.factory.ResultViewModelFactory
import com.example.followupexam.model.Module
import com.example.followupexam.model.Result
import com.example.followupexam.viewmodel.ResultViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * This class is ui controller fo the Result interface
 */
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    //This variable will hold the module send by SemesterFragment
    lateinit var module: Module

    private lateinit var factory: ResultViewModelFactory
    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultBinding.inflate(inflater,container,false)
        //Get the module send by SemesterFragment
        arguments?.let {
            module = it.get("module") as Module
        }

        //Change this fragment title
        (activity as HomeActivity).supportActionBar?.title = module.nomModule
        val user = FirebaseAuth.getInstance().currentUser
        factory = ResultViewModelFactory(user,module)
        viewModel = ViewModelProvider(this,factory).get(ResultViewModel::class.java)

        //bind resultFragment variable in the fragment_result xml layout with this class to use iy is attribute by dataBinding
        binding.resultFragment = this@ResultFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //observe the result attribute of ResultViewModel claas and if it not null display data
        viewModel.result.observe(viewLifecycleOwner,{
            if (it != null){

                when(it.size){
                    //If there is 3 Result display three element each Hold one Result
                    3 -> {
                        setScoreOneData(it[0])
                        setScoreTowData(it[1])
                        setScoreThreeData(it[2])
                    }
                    //If there is 1 Result display 1 element Hold the Result and hide the rest
                    1 -> {
                        binding.resultThreeContainer.visibility = View.GONE
                        binding.resultTowContainer.visibility = View.GONE
                        setScoreOneData(it[0])
                    }
                    //If there is 2 Result display 2 element each Hold one Result and hide the rest
                    else ->{
                        binding.resultTowContainer.visibility = View.GONE
                        setScoreOneData(it[0])
                        setScoreThreeData(it[1])
                    }
                }
            }
        })
    }

    /**
     * This method responsible on The First result
     * @param result: the result it going to display
     */
    private fun setScoreOneData(result: Result){
        binding.resultOneContainer.visibility = View.VISIBLE
        binding.resultOneCoefficient.text = result.scoreCoefficient.toString()
        binding.resultOneName.text = result.scoreName
        if (result.score == 0.1||result.score == 0.01){
            binding.resultOneValue.text = getString(R.string.error_average)
        }else{
            binding.resultOneValue.text = result.score.toString()
        }
    }

    /**
     * This method responsible on The second result
     * @param result: the result it going to display
     */
    private fun setScoreTowData(result: Result){
        binding.resultTowContainer.visibility = View.VISIBLE
        binding.resultTowCoefficient.text = result.scoreCoefficient.toString()
        binding.resultTowName.text = result.scoreName
        if (result.score == 0.1||result.score == 0.01){
            binding.resultTowValue.text = getString(R.string.error_average)
        }else{
            binding.resultTowValue.text = result.score.toString()
        }
    }

    /**
     * This method responsible on The third result
     * @param result: the result it going to display
     */
    private fun setScoreThreeData(result: Result){
        binding.resultThreeContainer.visibility = View.VISIBLE
        binding.resultThreeCoefficient.text = result.scoreCoefficient.toString()
        binding.resultThreeName.text = result.scoreName
        if (result.score == 0.1||result.score == 0.01){
            binding.resultThreeValue.text = getString(R.string.error_average)
        }else{
            binding.resultThreeValue.text = result.score.toString()
        }
    }

    /**
     * This method responsible on displaying the average score of module
     * @param average:the value we are going to display
     */
    fun setAverage(average: Double):String{
        if (average == 0.1)
            return getString(R.string.error_average)
        return average.toString()
    }
}