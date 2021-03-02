package com.acv.androiddevchallenge.ui.screen.detail

import com.acv.androiddevchallenge.common.store.State
import com.acv.androiddevchallenge.ui.model.Id
import com.acv.androiddevchallenge.ui.model.Puppy

sealed class PuppyDetailState(
    open val getPuppy: (Id) -> Unit,
) : State

object Error :  PuppyDetailState({})
object Initial : PuppyDetailState({})
object Loading : PuppyDetailState({})
data class Detail(
    val puppy: Puppy,
    override val getPuppy: (Id) -> Unit,
) : PuppyDetailState(getPuppy)
