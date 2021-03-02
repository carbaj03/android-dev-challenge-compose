package com.acv.androiddevchallenge.ui.screen.puppies

import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acv.androiddevchallenge.ui.model.Url
import dev.chrisbanes.accompanist.coil.CoilImage

data class PuppyAvatarModel(
    val image: Url?,
    val name: String,
)

@Composable
fun PuppyAvatar(
    puppy: PuppyAvatarModel,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    elevation: Dp = 0.dp,
) {
    Card(
        modifier = modifier,
//        shape = MaterialTheme.shapes.small.copy(topEnd = CornerSize(percent = 40),bottomStart =  CornerSize(percent = 40)),
//        backgroundColor = backgroundColor,
//        elevation = elevation,
    ) {
        if (puppy.image == null) {
            Icon(
                modifier = Modifier
                    .size(40.dp),
                imageVector = Icons.Default.Pets,
                contentDescription = puppy.name,
            )
        } else {
            CoilImage(
                data = puppy.image.value,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                loading = {
//                        PuppyLoadingIcon(contentDescription = "${puppy.name} picture loading")
                },
                error = { error ->
//                        Log.w("PuppyAvatar", error.throwable)
//                        PuppyErrorIcon(contentDescription = "${puppy.name} picture error")
                }
            )
        }
    }
}
