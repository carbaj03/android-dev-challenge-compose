/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acv.androiddevchallenge.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette: Colors =
    darkColors(
        primary = purple200,
        primaryVariant = purple700,
        secondary = teal200
    )

private val PinkColorPalette: Colors =
    Colors(
        primary = pink200,
        primaryVariant = pink700,
        secondary = teal200,
        secondaryVariant = pink200,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
        error = Color.Red,
        onError = Color.White,
        isLight = true,
    )

private val LightColorPalette: Colors =
    lightColors(
        primary = purple500,
        primaryVariant = purple700,
        secondary = teal200,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
    )

@Composable
fun PuppyTheme(
    theme: PuppyTheme = PuppyTheme.Light,
    shapes: PuppyShapes = PuppyShapes.Cut,
    content: @Composable () -> Unit
) {
    val colors = when (theme) {
        PuppyTheme.Dark -> DarkColorPalette
        PuppyTheme.Light -> LightColorPalette
        PuppyTheme.Pink -> PinkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes.value,
        content = content
    )
}

enum class PuppyTheme {
    Dark, Light, Pink
}