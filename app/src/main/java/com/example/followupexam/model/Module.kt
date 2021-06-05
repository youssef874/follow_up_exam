package com.example.followupexam.model

import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import com.example.followupexam.R

/**
 * This data class to cache all the modules data in the database
 * It inherit [Parcelable] to passed as argument between fragments
 * using navigation component saf args plugin
 * @param moyModule: the module average result
 */
data class Module(
    val idModule: Int? = 0,
    val nomModule:  String? =  "",
    val coefficientModule: Double? = 0.0,
    val moyModule: Double?  = 0.01,
    val semester: Int? = 0
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idModule)
        parcel.writeString(nomModule)
        parcel.writeValue(coefficientModule)
        parcel.writeValue(moyModule)
        parcel.writeValue(semester)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Module> {
        override fun createFromParcel(parcel: Parcel): Module {
            return Module(parcel)
        }

        override fun newArray(size: Int): Array<Module?> {
            return arrayOfNulls(size)
        }
    }

    fun setAverage(): String{
        if (moyModule == 0.1){
            return Resources.getSystem().getString(R.string.error_average)
        }
        return moyModule.toString()
    }
}

