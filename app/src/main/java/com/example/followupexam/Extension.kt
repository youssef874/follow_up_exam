package com.example.followupexam

import com.google.android.material.textfield.TextInputLayout

/**
 * This is an extension method of [TextInputLayout] to enable the error and display the message
 * @param isWong: if there error or not
 * @param msg: the msg is going to display if there an error
 */
fun TextInputLayout.setError(isWong: Boolean,msg:String){
    if (isWong){
        isErrorEnabled =true
        error = msg
    }else{
        isErrorEnabled = false
        error = null
    }
}