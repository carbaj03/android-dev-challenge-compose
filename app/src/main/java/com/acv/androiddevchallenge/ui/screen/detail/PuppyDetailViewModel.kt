package com.acv.androiddevchallenge.ui.screen.detail

import com.acv.androiddevchallenge.common.store.Store
import com.acv.androiddevchallenge.data.PuppyRepository
import com.acv.androiddevchallenge.ui.model.Id

class PuppyDetailViewModel(
    private val repository: PuppyRepository,
    private val id: Id,
) : Store<PuppyDetailAction, PuppyDetailState>(Initial) {

    override fun onInit() {
        Load(id)()
    }

    override fun PuppyDetailAction.reduce(currentState: PuppyDetailState): PuppyDetailState =
        when (this) {
            is Load -> Loading
            is LoadSuccess -> Detail(
                puppy = puppy,
                getPuppy = { Load(it)() },
            )
            is LoadError -> Error
        }


    override fun PuppyDetailAction.sideEffects(currentState: PuppyDetailState) {
        when (this) {
            is Load -> launch({ repository.getById(id) }) { it?.let { LoadSuccess(it)() } ?: LoadError() }
        }
    }
}