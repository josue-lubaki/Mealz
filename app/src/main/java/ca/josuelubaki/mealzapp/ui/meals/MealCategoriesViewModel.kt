package ca.josuelubaki.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import ca.josuelubaki.model.MealsRepository
import ca.josuelubaki.model.response.MealResponse

class MealCategoriesViewModel(private val repository: MealsRepository = MealsRepository())  : ViewModel(){

    suspend fun getMeals() : List<MealResponse> {
        return repository.getMeals().categories
    }

}