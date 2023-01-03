package ca.josuelubaki.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
//    val isScrolled = remember { derivedStateOf { scrollState.firstVisibleItemIndex > 0.5 } }.value

    val scrollState = rememberLazyListState()
    val firstElement = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }.value
    val offset = min(1f, 1 - (firstElement / 600f + firstElement))
    val size by animateDpAsState(
        targetValue = max(100.dp, 140.dp * offset),
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

           val dummyContentList = (0..100).map { "Item $it" }
            LazyColumn(
                state = scrollState,
            ) {
                items(dummyContentList) { item ->
                    Text(text = item, modifier =Modifier.padding(24.dp))
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