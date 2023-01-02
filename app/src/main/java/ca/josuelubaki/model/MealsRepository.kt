package ca.josuelubaki.model

import ca.josuelubaki.model.api.MealsWebService
import ca.josuelubaki.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals() : MealsCategoriesResponse {
        return webService.getMeals()
    }
}