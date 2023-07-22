package com.ahmad.modernfoodrecipes

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ahmad.modernfoodrecipes.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    var gson =Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe):String{
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data:String):FoodRecipe{

        val listType =object :TypeToken<FoodRecipe>(){}.type
        return gson.fromJson(data, listType)
    }
}