package com.example.androidcw2

import android.content.Context

object Verification  {
    fun validate(str: String?): Boolean{
        return if(str == "null")
            false
        else !(str=="" || str ==" ")
    }

    fun validateInput(userInputs: String, context: Context): Boolean{
        return if(userInputs != ""){
            true
        }
        else{
            InformationforUser.errorShowing("Enter an Ingredient",
                "Pleace Enter an Ingredient Before Retrieve Meals.",
                "Empty Ingredient is not valid", context)
            false
        }
    }
}