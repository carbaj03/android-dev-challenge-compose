package com.acv.androiddevchallenge.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
    border: BorderStroke = ChipConstants.defaultOutlinedBorder,
    colors: ChipColors = ChipConstants.defaultOutlinedChipColors(),
    contentPadding: PaddingValues = ChipConstants.DefaultContentPadding,
    selected: Boolean = false,
    onSelect: (Boolean) -> Unit = {},
    left: (@Composable (Modifier) -> Unit)? = null,
    right: (@Composable (Modifier) -> Unit)? = null,
) {
    val contentColor = colors.contentColor(enabled, selected)

    Surface(
        shape = shape,
        color = colors.backgroundColor(enabled, selected),
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        modifier = modifier
            .selectable(
                onClick = { onSelect(!selected) },
                enabled = enabled,
                selected = selected
            )
            .animateContentSize()
    ) {
        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(
                value = MaterialTheme.typography.button
            ) {
                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ChipConstants.DefaultMinWidth,
                            minHeight = ChipConstants.DefaultMinHeight
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    left?.let {
                        it(
                            Modifier
                                .padding(start = 4.dp, end = 8.dp)
                                .width(24.dp))
                    } ?: Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = text,
                    )

                    right?.let {
                        it(
                            Modifier
                                .padding(start = 4.dp, end = 8.dp)
                                .width(24.dp))
                    } ?: Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Stable
interface ChipColors {
    fun backgroundColor(enabled: Boolean, selected: Boolean): Color
    fun contentColor(enabled: Boolean, selected: Boolean): Color
}


@OptIn(ExperimentalMaterialApi::class)
@Immutable
private data class DefaultChipColors(
    private val selectedBackgroundColor: Color,
    private val unselectedBackgroundColor: Color,
    private val disabledSelectedBackgroundColor: Color,
    private val disabledUnselectedBackgroundColor: Color,
    private val selectedContentColor: Color,
    private val unselectedContentColor: Color,
    private val selectedDisabledContentColor: Color,
    private val unselectedDisabledContentColor: Color,
) : ChipColors {

    override fun backgroundColor(enabled: Boolean, selected: Boolean): Color {
        return when {
            enabled && selected -> selectedBackgroundColor
            enabled && !selected -> unselectedBackgroundColor
            !enabled && selected -> disabledSelectedBackgroundColor
            !enabled && !selected -> disabledUnselectedBackgroundColor
            else -> selectedBackgroundColor
        }
    }

    override fun contentColor(enabled: Boolean, selected: Boolean): Color {
        return when {
            enabled && selected -> selectedContentColor
            enabled && !selected -> unselectedContentColor
            !enabled && selected -> selectedDisabledContentColor
            !enabled && !selected -> unselectedDisabledContentColor
            else -> selectedContentColor
        }
    }
}

object ChipConstants {
    private val HorizontalPadding = 16.dp
    private val VerticalPadding = 8.dp

    val DefaultMinWidth = 24.dp
    val DefaultMinHeight = 36.dp

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun defaultOutlinedChipColors(
        selectedContentColor: Color = MaterialTheme.colors.primary,
        unselectedContentColor: Color = MaterialTheme.colors.secondary,
        selectedBackgroundColor: Color = MaterialTheme.colors.surface,
        unselectedBackgroundColor: Color = MaterialTheme.colors.surface,
        selectedDisabledContentColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        unselectedDisabledContentColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
        selectedDisabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        unselectedDisabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
    ): ChipColors = DefaultChipColors(
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        disabledSelectedBackgroundColor = selectedDisabledBackgroundColor,
        disabledUnselectedBackgroundColor = unselectedDisabledBackgroundColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        selectedDisabledContentColor = selectedDisabledContentColor,
        unselectedDisabledContentColor = unselectedDisabledContentColor,
    )

    val DefaultContentPadding = PaddingValues(
        start = HorizontalPadding,
        top = VerticalPadding,
        end = HorizontalPadding,
        bottom = VerticalPadding
    )

    private val OutlinedBorderSize = 1.dp

    val defaultOutlinedBorder: BorderStroke
        @Composable
        get() = BorderStroke(
            OutlinedBorderSize, MaterialTheme.colors.onSurface.copy(alpha = ButtonDefaults.OutlinedBorderOpacity)
        )
}
