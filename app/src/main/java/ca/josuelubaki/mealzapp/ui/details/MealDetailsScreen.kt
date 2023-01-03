package ca.josuelubaki.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
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

@Composable
fun MealDetailsScreen(meal : MealResponse?) {
    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    val borderWidth by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")

    Column {
        Row {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(
                    width = borderWidth,
                    color = color
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
                        .size(imageSizeDp)
                        .padding(8.dp)
                )
            }

            Text(
                text = meal?.name ?: "default name",
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                profilePictureState = if(profilePictureState == MealProfilePictureState.Normal) {
                    MealProfilePictureState.Expanded
                } else {
                    MealProfilePictureState.Normal
                }
            }) {
            Text(text = "Change state of meal")
        }
    }
}

enum class MealProfilePictureState(val color : Color, val size : Dp, val borderWidth : Dp) {
    Normal (Color.Cyan, 120.dp, 8.dp),
    Expanded (Color.Green, 200.dp, 24.dp)
}