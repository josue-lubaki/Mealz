package ca.josuelubaki.mealzapp.ui.details

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.josuelubaki.model.response.MealResponse
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import androidx.compose.ui.unit.max
import java.lang.Float.min

@Composable
fun MealDetailsScreen(meal : MealResponse?) {
//    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
//    val transition = updateTransition(targetState = profilePictureState, label = "")
//    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
//    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
//    val borderWidth by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")

    val scrollState = rememberScrollState()
    val offset = min(1f, 1 - (scrollState.value / 600f))
    val size by animateDpAsState(
        targetValue = max(100.dp, 200.dp * offset),
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
    )

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column {
            Surface(
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(ImageRequest
                                .Builder(LocalContext.current)
                                .data(meal?.imageUrl)
                                .transformations(CircleCropTransformation())
                                .build()),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size)
                        )
                    }

                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
            }


            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                repeat(10){
                    Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
                }
            }

//            Button(
//                modifier = Modifier.padding(16.dp),
//                onClick = {
//                    profilePictureState = if(profilePictureState == MealProfilePictureState.Normal) {
//                        MealProfilePictureState.Expanded
//                    } else {
//                        MealProfilePictureState.Normal
//                    }
//                }) {
//                Text(text = "Change state of meal")
//            }
        }
    }


}

enum class MealProfilePictureState(val color : Color, val size : Dp, val borderWidth : Dp) {
    Normal (Color.Cyan, 120.dp, 8.dp),
    Expanded (Color.Green, 200.dp, 24.dp)
}