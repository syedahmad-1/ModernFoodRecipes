package com.ahmad.modernfoodrecipes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmad.modernfoodrecipes.models.FoodRecipe
import com.ahmad.modernfoodrecipes.util.Constants.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
class RecipesEntity(var foodRecipe: FoodRecipe) {
    @PrimaryKey(autoGenerate = false, )
     var id :Int=0
}