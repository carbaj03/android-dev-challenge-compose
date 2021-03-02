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
package com.acv.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acv.androiddevchallenge.common.PuppyDetailViewModelFactory
import com.acv.androiddevchallenge.common.PuppyViewModelFactory
import com.acv.androiddevchallenge.data.PuppyRepository
import com.acv.androiddevchallenge.ui.model.Id
import com.acv.androiddevchallenge.ui.screen.puppies.PuppyApp
import com.acv.androiddevchallenge.ui.theme.PuppyShapes
import com.acv.androiddevchallenge.ui.theme.PuppyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var theme by remember { mutableStateOf(PuppyTheme.Light) }
            var shape by remember { mutableStateOf(PuppyShapes.Cut) }
            PuppyTheme(theme, shape) {
                PuppyApp(
                    puppyViewModel = viewModel(factory = PuppyViewModelFactory(PuppyRepository)),
                    puppyDetailViewModel = viewModel(factory = PuppyDetailViewModelFactory(PuppyRepository, Id(1))),
                    onThemeChange = { theme = it },
                    onShapeChange = { shape = it },
                    theme = theme,
                    shape = shape,
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    PuppyTheme(PuppyTheme.Light, PuppyShapes.Cut) {
        PuppyApp(
            puppyViewModel = viewModel(factory = PuppyViewModelFactory(PuppyRepository)),
            puppyDetailViewModel = viewModel(factory = PuppyDetailViewModelFactory(PuppyRepository, Id(1))),
            onThemeChange = { },
            onShapeChange = { },
            theme = PuppyTheme.Light,
            shape = PuppyShapes.Cut,
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    PuppyTheme(PuppyTheme.Dark, PuppyShapes.Cut) {
        PuppyApp(
            puppyViewModel = viewModel(factory = PuppyViewModelFactory(PuppyRepository)),
            puppyDetailViewModel = viewModel(factory = PuppyDetailViewModelFactory(PuppyRepository, Id(1))),
            onThemeChange = { },
            onShapeChange = { },
            theme = PuppyTheme.Dark,
            shape = PuppyShapes.Cut,
        )
    }
}
