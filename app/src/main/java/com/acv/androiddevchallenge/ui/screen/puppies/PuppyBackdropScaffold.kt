package com.acv.androiddevchallenge.ui.screen.puppies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.acv.androiddevchallenge.ui.component.ChipGroup
import com.acv.androiddevchallenge.ui.component.SelectionType
import com.acv.androiddevchallenge.ui.model.Breed
import com.acv.androiddevchallenge.ui.model.Gender
import com.acv.androiddevchallenge.ui.theme.PuppyShapes
import com.acv.androiddevchallenge.ui.theme.PuppyTheme
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun MainBackdropScaffold(
    backLayer: @Composable (BackLayerType) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    var backLayerType by remember { mutableStateOf(BackLayerType.Settings) }

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            MainTopBar(
                title = "Adopt One",
                onFilter = {
                    backLayerType = BackLayerType.Filter
                    scope.launch { scaffoldState.reveal() }
                },
                onSettings = {
                    backLayerType = BackLayerType.Settings
                    scope.launch { scaffoldState.reveal() }
                },
                onClose = {
                    backLayerType = BackLayerType.Settings
                    scope.launch { scaffoldState.conceal() }
                },
            )
        },
        backLayerBackgroundColor = Color.LightGray.copy(alpha = 0.25f),
        backLayerContent = { backLayer(backLayerType) },
        frontLayerElevation = 8.dp,
        stickyFrontLayer = false,
        gesturesEnabled = false,
        frontLayerContent = { content() }
    )
}

enum class BackLayerType {
    Settings, Filter
}

@Composable
fun Settings(
    theme: PuppyTheme,
    shape: PuppyShapes,
    onThemeChange: (PuppyTheme) -> Unit,
    onShapeChange: (PuppyShapes) -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("Theme")
        ChipGroup(
            items = PuppyTheme.values().map { it.name },
            onChange = { onThemeChange(PuppyTheme.valueOf(it)) },
            selectionType = SelectionType.Single(theme.name),
        )
        Text("Shape")
        ChipGroup(
            items = PuppyShapes.values().map { it.name },
            onChange = { onShapeChange(PuppyShapes.valueOf(it)) },
            selectionType = SelectionType.Single(shape.name),
        )
    }
}

@Composable
fun Filters(
    onGenderChange: (Gender) -> Unit,
    onBreedChange: (Breed) -> Unit,
    genders: List<Gender>,
    breeds: List<Breed>,
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("Gender")
        ChipGroup(
            items = Gender.values().map { it.name },
            onChange = { onGenderChange(Gender.valueOf(it)) },
            selectionType = SelectionType.Multiple(genders.map { it.name }),
        )
        Text("Breed")
        ChipGroup(
            items = Breed.values().map { it.name },
            onChange = { onBreedChange(Breed.valueOf(it)) },
            selectionType = SelectionType.Multiple(breeds.map { it.name }),
        )
    }
}