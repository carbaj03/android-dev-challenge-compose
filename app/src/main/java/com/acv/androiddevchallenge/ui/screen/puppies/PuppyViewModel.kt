package com.acv.androiddevchallenge.ui.screen.puppies

import com.acv.androiddevchallenge.common.store.Store
import com.acv.androiddevchallenge.data.PuppyRepository

class PuppyViewModel(
    private val repository: PuppyRepository
) : Store<PuppyAction, PuppyState>(Initial) {

    override fun onInit() {
        Load()
    }

    override fun PuppyAction.reduce(currentState: PuppyState): PuppyState =
        when (this) {
            Load -> Loading
            is LoadSuccess -> Main(
                puppies = puppies,
                onSelectedBreed = { OnSelectedBreed(it)() },
                onSelectedGender = { OnSelectedGender(it)() },
                filter = currentState.filter,
            )
            is OnSelectedBreed -> when (currentState) {
                Initial -> currentState
                Loading -> currentState
                is Main -> currentState.copy(
                    filter = currentState.filter.copy(
                        breeds =
                        if (currentState.filter.breeds.firstOrNull { it == breed }?.let { true } ?: false)
                            currentState.filter.breeds.filter { it != breed }
                        else
                            currentState.filter.breeds.plus(breed)
                    )
                )
            }
            is OnSelectedGender -> when (currentState) {
                Initial -> currentState
                Loading -> currentState
                is Main -> currentState.copy(
                    filter = currentState.filter.copy(
                        genders =
                        if (currentState.filter.genders.firstOrNull { it == gender }?.let { true } ?: false)
                            currentState.filter.genders.filter { it != gender }
                        else
                            currentState.filter.genders.plus(gender)
                    )
                )
            }
        }

    override fun PuppyAction.sideEffects(currentState: PuppyState) {
        when (this) {
            Load -> launch({ repository.getAll() }) { LoadSuccess(it)() }
            is OnSelectedBreed -> launch({ repository.getByFilter(currentState.filter) }) { LoadSuccess(it)() }
            is OnSelectedGender -> launch({ repository.getByFilter(currentState.filter) }) { LoadSuccess(it)() }
        }
    }
}