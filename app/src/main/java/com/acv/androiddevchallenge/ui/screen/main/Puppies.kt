package com.acv.androiddevchallenge.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.androiddevchallenge.ui.model.Puppy


@Composable
fun MyApp(vm: MainViewModel) {
    val viewState: MainState by vm.onCreate().collectAsState(Loading)

    when (val vs = viewState) {
        Initial -> Loading()
        Loading -> Loading()
        is Main -> Puppies(puppies = vs.puppies)
    }

}

@Composable
fun Puppies(puppies: List<Puppy>) {
    LazyColumn {
        puppies.forEach { puppy ->
            item {
                Text(modifier = Modifier.padding(100.dp), text = puppy.name)
            }
        }
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