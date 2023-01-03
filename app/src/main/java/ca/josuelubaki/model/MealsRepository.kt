package ca.josuelubaki.model

import ca.josuelubaki.model.api.MealsWebService
import ca.josuelubaki.model.response.MealResponse
import ca.josuelubaki.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals() : MealsCategoriesResponse {
        val response =  webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMealById(id: String) : MealResponse? {
        return cachedMeals.firstOrNull { it.id == id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() : MealsRepository {
            if (instance == null) {
                instance = MealsRepository()
            }
            return instance!!
        }

//        fun getInstance() = instance ?: synchronized(this) {
//            instance ?: MealsRepository().also { instance = it }
//        }
    }
}