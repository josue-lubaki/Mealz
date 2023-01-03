package ca.josuelubaki.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.josuelubaki.model.MealsRepository
import ca.josuelubaki.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance())  : ViewModel(){
    val mealsState : MutableState<List<MealResponse>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO){
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    private suspend fun getMeals() : List<MealResponse> {
        return repository.getMeals().categories
    }

}