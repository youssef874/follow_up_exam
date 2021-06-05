package com.example.followupexam.model

import android.os.Parcel
import android.os.Parcelable

/**
 * This data class to cache the current user personal information
 * It inherit [Parcelable] to passed as argument between fragments
 * using navigation component saf args plugin
 * @param gradeName: the grade the student study in
 * @param imageUrl: hold the url from firebase storage to display the student image
 */
data class Student(
    val name: String? = null,
    val lastName:String? = null,
    val email: String? = null,
    val phoneNumber:  Int? = null,
    val gradeName: String? = null,
    val imageUrl: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeString(email)
        parcel.writeValue(phoneNumber)
        parcel.writeString(gradeName)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}