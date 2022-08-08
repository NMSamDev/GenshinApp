package com.example.carlosmendez_finalproject.api

import android.widget.EditText
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

const val EMAIL_STRING = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
val VALID_EMAIL_ADDRESS_REGEX: Pattern = Pattern.compile(EMAIL_STRING, Pattern.CASE_INSENSITIVE)
const val PASSWORD_STRING = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\\-\\]\\[\\\\{}/;:=<>_+.,#@!%*$?&]{8,30}$"
val VALID_PASSWORD_REGEX: Pattern = Pattern.compile(PASSWORD_STRING)

interface ValidatorRepository {
    fun validateEmail(email: EditText): Boolean {
        val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.text.toString())
        return if(!matcher.find()){
            email.error = "Email is not valid"
            false
        }
        else true
    }

    fun validatePassword(pass: EditText): Boolean {
        val matcher: Matcher = VALID_PASSWORD_REGEX.matcher(pass.text.toString())
        return if(!matcher.find()){
            pass.error = "Password is not valid"
            Toast.makeText(pass.context, "Password must contain at least 1 uppercase, 1 lowercase, 1 number and a minimum length of 8", Toast.LENGTH_LONG).show()
            false
        } else true
    }

    fun notEmptyET(editText: EditText): Boolean {
        if(editText.text.toString().isBlank()){
            editText.error = "Field cannot be empty"
            return false
        }
        return true
    }
}