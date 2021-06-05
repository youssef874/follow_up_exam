package com.example.followupexam

import com.example.followupexam.model.Grade
import com.example.followupexam.model.Module
import com.example.followupexam.model.Result
import com.example.followupexam.model.Student

/**
 * This util method to filter all grades data based on the student grade name
 * @param student :student how is going to help filter based on it is gradeName
 * @param grades :the grade list that is going to be filtered
 * @return the filtered grade list of type [ArrayList]
 */
fun getSupportedGrade(student: Student,grades:List<Grade>?):List<Grade>{
    val list = ArrayList<Grade>()
    if (grades != null) {
        for (grade in grades){
            if (student.gradeName.equals(grade.gradeName)){
                list.add(grade)
            }
        }
    }
    return list
}

/**
 * This util method to filter all modules data based on the grade moduleId
 * @param modules :the modules list how is going to be filtered
 * @param grades :the grade list that is going to help filter based on it is moduleId
 * @return the filtered module list of type [ArrayList]
 */
fun getSupportedModules(grades: List<Grade>?,modules: List<Module>): List<Module>{
    var list = ArrayList<Module>()
    if (grades != null){
        for (grade in grades){
            for (module in modules){
                if (grade.moduleId == module.idModule){
                    list.add(module)
                }
            }
        }
    }
    return list
}

/**
 * This util method to filter all modules data based on the semester
 * @param modules :the modules list how is going to be filtered
 * @param semester :the value were going to filter based on it
 * @return the filtered module list of type [ArrayList]
 */
fun getModelForTabItem(modules: List<Module>, semester:Int):List<Module>{
    val list = ArrayList<Module>()
    for (module in modules){
        if (module.semester == semester){
            list.add(module)
        }
    }
    return list
}

/**
 * This util method to give module list equals to given module list to maintain real time
 * @param allModules :the list contain all modules data in database
 * @param tabModules :the list of module we need
 * @return a module list of type [ArrayList]
 */
fun getSemesterModule(allModules: List<Module>?,tabModules:List<Module>):List<Module>{
    val list = ArrayList<Module>()
    if (allModules != null){
        for (module in tabModules){
            for (data in allModules){
                if (module == data)
                    list.add(data)
            }
        }
    }
    return list
}

/**
 * This util method to filter all result data based on the module idModule
 * @param module :the module were going to filter based on it
 * @param results :the result list we are going to filter
 * @return the filtered result list of type [ArrayList]
 */
fun getSupportedResult(module: Module,results: List<Result>?):List<Result>{
    val list = ArrayList<Result>()
    if (results != null){
        for (result in results){
            if (module.idModule == result.idModule){
                list.add(result)
            }
        }
    }
    return list
}
