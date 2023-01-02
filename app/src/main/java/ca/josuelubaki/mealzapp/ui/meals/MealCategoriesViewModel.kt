package ca.josuelubaki.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import ca.josuelubaki.model.MealsRepository
import ca.josuelubaki.model.response.MealsCategoriesResponse

class MealCategoriesViewModel(private val repository: MealsRepository = MealsRepository())  : ViewModel(){

    fun getMeals(successCallback : (response : MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }

}