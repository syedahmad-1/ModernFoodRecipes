package com.ahmad.modernfoodrecipes.data

import android.content.Context
import android.health.connect.datatypes.MealType
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.ahmad.modernfoodrecipes.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.ahmad.modernfoodrecipes.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.ahmad.modernfoodrecipes.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.ahmad.modernfoodrecipes.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.ahmad.modernfoodrecipes.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.ahmad.modernfoodrecipes.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.ahmad.modernfoodrecipes.util.Constants.Companion.PREFERNCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context:Context) {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId= intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
    }

    // At the top level of your kotlin file:
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERNCES_NAME)

    suspend fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int){
        context.dataStore.edit {preferences->
            preferences[PreferenceKeys.selectedDietType]=dietType
            preferences[PreferenceKeys.selectedDietTypeId]=dietTypeId
            preferences[PreferenceKeys.selectedMealType]=mealType
            preferences[PreferenceKeys.selectedMealTypeId]=mealTypeId
        }
    }

    val readMealAndDietType:Flow<MealAndDietType> = context.dataStore.data
        .catch {exception->
            if (exception is IOException ){
                emit(emptyPreferences())
            }
            else {
                throw exception
            }
        }
        .map{ preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType]?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId]?:0
            val selectedDietType = preferences[PreferenceKeys.selectedDietType]?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId]?:0

            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId)
        }



}
data class MealAndDietType(
    val selectedMealType:String,
    val selectedMealTypeId: Int,
    val selectedDietType:String,
    val selectedDietTypeId:Int
)