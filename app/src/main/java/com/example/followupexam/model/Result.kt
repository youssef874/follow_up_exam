package com.example.followupexam.model

import android.os.Parcelable

/**
 * This data class to cache all the results data in the database
 * It inherit [Parcelable] to passed as argument between fragments
 * using navigation component saf args plugin
 * This class will represent the result detail from all result of a
 * IdModule
 */
data class Result(
    val idModule: Int? = 0,
    val scoreName: String? = "",
    val score: Double? = 0.01,
    val scoreCoefficient: Double? = 0.0
)
