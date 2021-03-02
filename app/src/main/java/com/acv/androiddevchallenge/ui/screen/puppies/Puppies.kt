package com.acv.androiddevchallenge.ui.screen.puppies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.acv.androiddevchallenge.ui.component.ChipGroup
import com.acv.androiddevchallenge.ui.model.Breed
import com.acv.androiddevchallenge.ui.model.Gender
import com.acv.androiddevchallenge.ui.model.Puppy
import com.acv.androiddevchallenge.ui.screen.detail.PuppyDetailViewModel
import com.acv.androiddevchallenge.ui.theme.PuppyShapes
import com.acv.androiddevchallenge.ui.theme.PuppyTheme


@Composable
fun PuppyApp(
    puppyViewModel: PuppyViewModel,
    puppyDetailViewModel: PuppyDetailViewModel,
    theme: PuppyTheme,
    shape: PuppyShapes,
    onThemeChange: (PuppyTheme) -> Unit,
    onShapeChange: (PuppyShapes) -> Unit,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PuppyScreen.Puppies.route
    ) {
        main(navController, puppyViewModel, puppyDetailViewModel, theme, shape, onThemeChange, onShapeChange)
    }
}

@Composable
fun PuppiesScreen(
    mainNavigator: MainNavigator,
    mainViewModel: PuppyViewModel,
    theme: PuppyTheme,
    shape: PuppyShapes,
    onThemeChange: (PuppyTheme) -> Unit,
    onShapeChange: (PuppyShapes) -> Unit,
) {
    val viewState: PuppyState by mainViewModel.onCreate().collectAsState(Loading)
    MainBackdropScaffold(
        backLayer = {
            PuppyBackLayer(
                it,
                theme,
                shape,
                onThemeChange,
                onShapeChange,
                viewState.filter.genders,
                viewState.filter.breeds,
                { viewState.onSelectedGender(it) },
                { viewState.onSelectedBreed(it) })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .height(24.dp),
                text = "Near you"
            )

            when (val vs = viewState) {
                Initial -> Loading()
                Loading -> Loading()
                is Main -> Puppies(
                    puppies = vs.puppies,
                    onSelected = { mainNavigator.goDetail() }
                )
            }
        }
    }
}

@Composable
fun PuppyBackLayer(
    backLayerType: BackLayerType,
    theme: PuppyTheme,
    shape: PuppyShapes,
    onThemeChange: (PuppyTheme) -> Unit,
    onShapeChange: (PuppyShapes) -> Unit,
    genders: List<Gender>,
    breeds: List<Breed>,
    onGenderChange: (Gender) -> Unit,
    onBreedChange: (Breed) -> Unit
) {
    when (backLayerType) {
        BackLayerType.Settings -> Settings(
            onThemeChange = onThemeChange,
            onShapeChange = onShapeChange,
            theme = theme,
            shape = shape,
        )
        BackLayerType.Filter -> Filters(
            onGenderChange = onGenderChange,
            onBreedChange = onBreedChange,
            genders = genders,
            breeds = breeds
        )
    }
}

@Composable
fun Puppies(
    modifier: Modifier = Modifier,
    puppies: List<Puppy>,
    onSelected: (Puppy) -> Unit
) {
    LazyColumn(modifier) {
        puppies.forEach { puppy ->
            item {
                Puppy(
                    puppy = puppy,
                    onSelected = onSelected,
                )
            }
        }
    }
}

@Composable
fun Puppy(
    puppy: Puppy,
    onSelected: (Puppy) -> Unit
) {
    Column {
        Card(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .height(150.dp)
                .clickable { onSelected(puppy) },
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 2.dp,
        ) {
            Row {
                PuppyAvatar(
                    puppy = PuppyAvatarModel(puppy.image, puppy.name),
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxHeight()
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(style = MaterialTheme.typography.h5, text = puppy.name)
                    Text(style = MaterialTheme.typography.body1, text = "Please take care")
                }
            }
        }
        ChipGroup(items = listOf(puppy.gender.name, puppy.breed.name, puppy.age.toString()), onChange = { })
    }
}

@Composable
fun Error() {
    Text("Error")
}

@Composable
fun Loading() {
    CircularProgressIndicator()
}