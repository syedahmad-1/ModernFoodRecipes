package com.ahmad.modernfoodrecipes.bindingadapters

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ahmad.modernfoodrecipes.RecipesEntity
import com.ahmad.modernfoodrecipes.models.FoodRecipe
import com.ahmad.modernfoodrecipes.util.NetworkResult

class RecipesBinding {

    companion object{

        @JvmStatic
        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse:NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ){
            if(apiResponse is NetworkResult.Error && database.isNullOrEmpty()){
                imageView.visibility = View.VISIBLE
            }
            else if (apiResponse is NetworkResult.Loading){
                imageView.visibility = View.INVISIBLE
            }
            else if (apiResponse is NetworkResult.Success){
                imageView.visibility = View.INVISIBLE
            }

        }
        @JvmStatic
        @BindingAdapter("readApiResponseText", "readDatabaseText", requireAll = true)
        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse:NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ){
            if(apiResponse is NetworkResult.Error && database.isNullOrEmpty()){
                textView.visibility = View.VISIBLE
                textView.setText(apiResponse.message)
            }
            else if (apiResponse is NetworkResult.Loading){
                textView.visibility = View.INVISIBLE
            }
            else if (apiResponse is NetworkResult.Success){
                textView.visibility = View.INVISIBLE
            }

        }
    }
}

