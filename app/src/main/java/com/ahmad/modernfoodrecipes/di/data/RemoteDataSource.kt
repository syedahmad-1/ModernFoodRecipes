package com.ahmad.modernfoodrecipes.di.data

import com.ahmad.modernfoodrecipes.di.data.network.FoodRecipeApi
import com.ahmad.modernfoodrecipes.models.FoodRecipe
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class RemoteDataSource @Inject constructor(private val foodRecipeApi: FoodRecipeApi) {

    suspend fun getRecipes(queries:Map<String, String>):Response<FoodRecipe>{
        return foodRecipeApi.getRecipes(queries)
    }
}