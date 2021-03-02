package com.acv.androiddevchallenge.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.androiddevchallenge.ui.component.SelectionType.Companion.None


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectionType: SelectionType = SelectionType.Single(None),
    onChange: (String) -> Unit,
    colors: ChipColors = ChipConstants.defaultOutlinedChipColors(
        selectedBackgroundColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.medium),
        selectedContentColor = contentColorFor(MaterialTheme.colors.primary),
        unselectedBackgroundColor = MaterialTheme.colors.background,
        unselectedContentColor = MaterialTheme.colors.primary,
    )
) {
    var selected: SelectionType by remember { mutableStateOf(selectionType) }
    rememberScrollState(0)
    LazyRow(modifier = modifier) {
        items.forEach { id ->
            item {
                Chip(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .animateContentSize(),
                    text = id,
                    selected = selected.isSelected(id),
                    onSelect = {
                        selected = selected.selected(id)
                        onChange(id)
                    },
                    colors = colors
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

sealed class SelectionType {
    abstract fun selected(value: String): SelectionType
    abstract fun isSelected(value: String): Boolean

    data class Multiple(private val selected: List<String> = emptyList()) : SelectionType() {
        override fun selected(value: String): SelectionType =
            if (!isSelected(value))
                copy(selected.plus(value))
            else
                copy(selected.filter { it != value })

        override fun isSelected(value: String): Boolean =
            selected.firstOrNull { it == value }?.let { true } ?: false
    }

    data class Single(private val selected: String = None) : SelectionType() {
        override fun selected(value: String): SelectionType =
            copy(selected = if (value == selected) None else value)

        override fun isSelected(value: String): Boolean =
            selected == value
    }

    companion object {
        const val None = "none"
    }
}
