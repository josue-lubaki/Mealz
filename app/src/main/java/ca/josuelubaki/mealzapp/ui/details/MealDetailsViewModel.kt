package ca.josuelubaki.mealzapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.josuelubaki.model.MealsRepository
import ca.josuelubaki.model.response.MealResponse

class MealDetailsViewModel(
    private val savedStateHandle : SavedStateHandle,
) : ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("mealId") ?: ""
        mealState.value = repository.getMealById(mealId)
    }

}