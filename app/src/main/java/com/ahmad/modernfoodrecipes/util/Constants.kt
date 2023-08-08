package com.ahmad.modernfoodrecipes.util

class Constants {

    companion object{
        const val API_KEY ="bec86c74132d4b7bb055b896e3fc93ce"
        const val BASE_URL ="https://api.spoonacular.com"

        const val QUERY_NUMBER ="number"
        const val QUERY_API_KEY ="apiKey"
        const val QUERY_TYPE ="type"
        const val QUERY_DIET ="diet"
        const val QUERY_ADD_RECIPE_INFORMATION ="addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS ="fillIngredients"

        //Room
        const val DATABASE_NAME ="recipes_database"
        const val TABLE_NAME ="recipes_table"

        //Bottom Sheet and Preference
        const val PREFERNCES_NAME ="recipes_preferences"
        const val DEFAULT_RECIPES_NO = "50"
        const val DEFAULT_MEAL_TYPE ="main course"
        const val DEFAULT_DIET_TYPE="gluten free"
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCES_DIET_TYPE = "mealType"
        const val PREFERENCES_DIET_TYPE_ID = "mealTypeId"
    }
}