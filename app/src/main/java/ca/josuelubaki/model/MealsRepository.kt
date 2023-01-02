package ca.josuelubaki.model

import ca.josuelubaki.model.api.MealsWebService
import ca.josuelubaki.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    fun getMeals(successCallback : (response : MealsCategoriesResponse?) -> Unit) {
        webService.getMeals().enqueue(object: Callback<MealsCategoriesResponse> {
            override fun onResponse(
                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if(response.isSuccessful) {
                    successCallback(response.body())
                }
            }

            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                successCallback(null)
            }
        })
        return
    }
}