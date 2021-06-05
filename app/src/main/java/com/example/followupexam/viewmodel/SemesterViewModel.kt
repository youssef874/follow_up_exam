package com.example.followupexam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.followupexam.getSemesterModule
import com.example.followupexam.model.Module
import com.example.followupexam.repository.Repository
import com.google.firebase.auth.FirebaseUser

//This enum to keep track how we going to sort our list
enum class Order {
    MOST_VALUE,
    LESS_VALUE,
    DEFAULT
}

/**
 * This class is a view model for [SemesterFragment]
 * @constructor take the authenticated user in form of [FirebaseUser] and the module to display
 */
class SemesterViewModel(user: FirebaseUser, private val modules: List<Module>) : ViewModel() {

    private val repository = Repository(user)

    //This variable will hold result from comparing modules from constructor and  repModules to be displayed
    private var _modules = MutableLiveData<List<Module>?>()
    val modulesV: LiveData<List<Module>?>
        get() = _modules

    //This variable will hold all module from rep to maintain real time data
    private val repModule = repository.getModules().map {
        getSemesterModule(it, modules)
    } as MutableLiveData<List<Module>?>


    //This varable will keep track the order type to update list based on it
    private var _orderStatus = MutableLiveData<Order?>()
    val orderStatus: LiveData<Order?>
        get() = _orderStatus

    val orders = listOf(Order.MOST_VALUE, Order.DEFAULT, Order.LESS_VALUE)

    //This Variable will hol the module item to send to ResultFragment
    private var _navigateToResultFragment = MutableLiveData<Module?>()
    val navigateToResultFragment: LiveData<Module?>
        get() = _navigateToResultFragment

    init {
        _modules = repModule

    }

    /**
     * This method will set _navigateToResultFragment value
     */
    fun displayResultDetail(module: Module){
        _navigateToResultFragment.value = module
    }

    /**
     * This method will reset _navigateToResultFragment value to null
     */
    fun displayResultComplete(){
        _navigateToResultFragment.value = null
    }

    fun setOrderStatus(status: Order?) {
        _orderStatus.value = status
    }

    /**
     * This method will sort the _modules based on the most values
     */
    fun sortMostValue() {
        _modules.value = repModule.value?.sortedByDescending { it.moyModule }

    }

    /**
     * This method will sort the _modules based on the less values
     */
    fun sortLessValue() {
        _modules.value = repModule.value?.sortedBy { it.moyModule }
    }

    /**
     * This method will sort the _modules based on the default
     */
    fun defaultOrder() {
        _modules.value = modules
    }

}