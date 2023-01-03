package ca.josuelubaki.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ca.josuelubaki.mealzapp.ui.details.MealDetailsScreen
import ca.josuelubaki.mealzapp.ui.details.MealDetailsViewModel
import ca.josuelubaki.mealzapp.ui.meals.MealsCategoriesScreen
import ca.josuelubaki.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FoodiesApp()
                }
            }
        }
    }
}

@Composable
fun FoodiesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "meals") {
        composable("meals") {
            MealsCategoriesScreen{ navigationMealId ->
                navController.navigate("details/$navigationMealId")
            }
        }
        composable(
            route = "details/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            val viewModel : MealDetailsViewModel = viewModel()
            MealDetailsScreen(meal = viewModel.mealState.value)
        }
    }
}