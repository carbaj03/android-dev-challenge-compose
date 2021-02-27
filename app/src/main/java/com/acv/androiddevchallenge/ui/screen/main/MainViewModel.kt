package com.acv.androiddevchallenge.ui.screen.main

import com.acv.androiddevchallenge.common.store.Action
import com.acv.androiddevchallenge.common.store.State
import com.acv.androiddevchallenge.common.store.Store
import com.acv.androiddevchallenge.data.PuppyRepository
import com.acv.androiddevchallenge.ui.model.Puppy

sealed class MainAction : Action
object Load : MainAction()
data class LoadSuccess(val puppies: List<Puppy>) : MainAction()

sealed class MainState : State
object Initial : MainState()
object Loading : MainState()
data class Main(
    val puppies: List<Puppy>
) : MainState()

class MainViewModel(
    private val repository: PuppyRepository
) : Store<MainAction, MainState>(Initial) {

    override fun onInit() {
        Load()
    }

    override fun MainAction.reduce(currentState: MainState): MainState =
        when (this) {
            Load -> Loading
            is LoadSuccess -> Main(puppies = puppies)
        }

    override fun MainAction.sideEffects(currentState: MainState) {
        when (this) {
            Load -> launch({ repository.getAll() }) { LoadSuccess(it) }
        }
    }
}