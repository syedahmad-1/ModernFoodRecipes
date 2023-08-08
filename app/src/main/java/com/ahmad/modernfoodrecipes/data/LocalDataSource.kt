package com.ahmad.modernfoodrecipes.data

import com.ahmad.modernfoodrecipes.RecipesDao
import com.ahmad.modernfoodrecipes.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao) {

    suspend fun insertRecipe(recipesEntity: RecipesEntity){
        recipesDao.insertRecipe(recipesEntity)
    }

    fun readDatabase():Flow<List<RecipesEntity>>{
        return recipesDao.readRecipes()
    }
}