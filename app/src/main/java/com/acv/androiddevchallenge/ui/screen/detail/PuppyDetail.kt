package com.acv.androiddevchallenge.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.androiddevchallenge.ui.component.ChipGroup
import com.acv.androiddevchallenge.ui.model.Id
import com.acv.androiddevchallenge.ui.model.Puppy
import com.acv.androiddevchallenge.ui.screen.puppies.*

@Composable
fun PuppyDetailScreen(
    mainNavigator: MainNavigator,
    id: Id,
    puppyDetailViewModel: PuppyDetailViewModel
) {
    val viewState: PuppyDetailState by puppyDetailViewModel.onCreate().collectAsState(Loading)
    viewState.getPuppy(id)
    Column() {
        when (val vs = viewState) {
            Initial -> Loading()
            Loading -> Loading()
            is Detail -> PuppyDetail(
                puppy = vs.puppy,
                onBack = { mainNavigator.goBack() }
            )
        }
    }
}


@Composable
fun PuppyDetail(
    puppy: Puppy,
    onBack: () -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            PuppyHeader(
                modifier = Modifier.requiredHeight(280.dp),
                puppy = puppy,
                goBack = onBack,
            )
            PuppyContent()
        }
    }
}

@Composable
fun PuppyHeader(
    modifier: Modifier,
    puppy: Puppy,
    goBack: () -> Unit,
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        if (puppy.image != null) {
            key(puppy.image) {
                PuppyAvatar(
                    modifier = Modifier.fillMaxSize(),
                    puppy = PuppyAvatarModel(image = puppy.image, name = puppy.name),
                )
            }
            Box(
//                modifier = Modifier.statusBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.small,
                        )
                        .clickable { goBack() }
                        .padding(4.dp),
                )
            }
        }
    }
}

@Composable
fun PuppyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                ChipGroup(
                    items = listOf(
                        "Active",
                        "Friendly",
                        "Loyal"
                    ),
                    onChange = {}
                )

                Text(
                    text = LIPSUM,
                    style = MaterialTheme.typography.body2
                )
            }

            LazyColumn {
                val infos = listOf(
                    "Puppy information",
                    "Agency contact"
                )
                itemsIndexed(infos) { index, item ->
                    if (index == 0) {
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(56.dp)
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowDropDown,
                            contentDescription = null
                        )
                        Text(text = item)
                    }
                    if (index <= infos.size) {
                        Divider(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }

        PuppyDetailButtons()
    }
}

@Composable
private fun BoxScope.PuppyDetailButtons() {
    val favState = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomEnd),
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium.copy(
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp),
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        ) {
            OutlinedButton(
                modifier = Modifier.size(48.dp),
                onClick = { favState.value = !favState.value },
                elevation = ButtonDefaults.elevation()
            ) {
                Icon(
                    imageVector = if (favState.value) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    tint = if (favState.value) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onBackground
                    },
                )
            }
            Spacer(modifier = Modifier.requiredWidth(8.dp))
            Button(
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                onClick = { }
            ) {
                Text("Adopt Now")
            }
        }
    }
}

private const val LIPSUM = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum congue tempus purus. Ut sed purus eu diam dictum dictum. Cras ut accumsan urna. Ut vulputate eget neque ut porta. Donec in dignissim urna. Mauris pellentesque mollis tristique. Morbi dapibus venenatis facilisis. Morbi ligula nisi, laoreet non ullamcorper sit amet, laoreet vel dui. Vestibulum blandit est a lectus luctus placerat. Ut sollicitudin elit at quam consequat fringilla.
"""