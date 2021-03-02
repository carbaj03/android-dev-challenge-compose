package com.acv.androiddevchallenge.ui.screen.puppies

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainTopBar(
    title: String,
    onFilter: () -> Unit,
    onSettings: () -> Unit,
    onClose: () -> Unit,
) {
    var state by remember { mutableStateOf(MainTopBarState.None) }
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            when (state) {
                MainTopBarState.Filter,
                MainTopBarState.Menu -> IconButton(onClick = {
                    state = MainTopBarState.None
                    onClose()
                }) {
                    Icon(Icons.Default.Close, null)
                }
                MainTopBarState.None -> IconButton(onClick = {
                    state = MainTopBarState.Menu
                    onSettings()
                }) {
                    Icon(Icons.Default.Menu, null)
                }
            }
        },
        actions = {
            if (state != MainTopBarState.Filter) {
                IconButton(onClick = {
                    state = MainTopBarState.Filter
                    onFilter()
                }) {
                    Icon(Icons.Default.Settings, null)
                }
            }
        },
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    )
}

enum class MainTopBarState {
    Menu, Filter, None
}