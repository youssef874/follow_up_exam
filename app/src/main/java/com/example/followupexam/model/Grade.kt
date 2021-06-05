package com.example.followupexam.model

import android.os.Parcel
import android.os.Parcelable

/**
 * This data class to cache all the grades data in the database
 * It inherit [Parcelable] to passed as argument between fragments
 * using navigation component saf args plugin
 * @param moduleId: the module id of grade
 */
data class Grade(
    val gradeName: String? =  "",
    val moduleId: Int? = 0
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gradeName)
        parcel.writeValue(moduleId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Grade> {
        override fun createFromParcel(parcel: Parcel): Grade {
            return Grade(parcel)
        }

        override fun newArray(size: Int): Array<Grade?> {
            return arrayOfNulls(size)
        }
    }
}

class Grades() : ArrayList<Grade>(),Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Grades> {
        override fun createFromParcel(parcel: Parcel): Grades {
            return Grades(parcel)
        }

        override fun newArray(size: Int): Array<Grades?> {
            return arrayOfNulls(size)
        }
    }
}
