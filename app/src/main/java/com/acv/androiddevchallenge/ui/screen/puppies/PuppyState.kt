package com.acv.androiddevchallenge.ui.screen.puppies

import com.acv.androiddevchallenge.common.store.State
import com.acv.androiddevchallenge.data.Filter
import com.acv.androiddevchallenge.ui.model.Breed
import com.acv.androiddevchallenge.ui.model.Gender
import com.acv.androiddevchallenge.ui.model.Puppy

sealed class PuppyState(
    open val filter: Filter,
    open val onSelectedGender: (Gender) -> Unit,
    open val onSelectedBreed: (Breed) -> Unit,
) : State

object Initial : PuppyState(Filter.empty(), {}, {})
object Loading : PuppyState(Filter.empty(), {}, {})
data class Main(
    val puppies: List<Puppy>,
    override val onSelectedGender: (Gender) -> Unit,
    override val onSelectedBreed: (Breed) -> Unit,
    override val filter: Filter,
) : PuppyState(filter, onSelectedGender, onSelectedBreed)
