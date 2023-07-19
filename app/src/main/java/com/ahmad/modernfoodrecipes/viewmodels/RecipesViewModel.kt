package com.ahmad.modernfoodrecipes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ahmad.modernfoodrecipes.util.Constants
import com.ahmad.modernfoodrecipes.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.ahmad.modernfoodrecipes.util.Constants.Companion.QUERY_API_KEY
import com.ahmad.modernfoodrecipes.util.Constants.Companion.QUERY_DIET
import com.ahmad.modernfoodrecipes.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.ahmad.modernfoodrecipes.util.Constants.Companion.QUERY_NUMBER
import com.ahmad.modernfoodrecipes.util.Constants.Companion.QUERY_TYPE

class RecipesViewModel(application: Application): AndroidViewModel(application) {

        fun applyQueries():HashMap<String, String>{
        val queries: HashMap<String, String> = HashMap()

        queries[Constants.QUERY_NUMBER]="50"
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_TYPE] = "snack"
        queries[Constants.QUERY_DIET] = "vegan"
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS]="true"

        return queries
    }
}