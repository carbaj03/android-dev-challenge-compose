package com.acv.androiddevchallenge.ui.screen.puppies

import com.acv.androiddevchallenge.common.store.Action
import com.acv.androiddevchallenge.ui.model.Breed
import com.acv.androiddevchallenge.ui.model.Gender
import com.acv.androiddevchallenge.ui.model.Puppy

sealed class PuppyAction : Action
object Load : PuppyAction()
data class OnSelectedBreed(val breed: Breed) : PuppyAction()
data class OnSelectedGender(val gender: Gender) : PuppyAction()
data class LoadSuccess(val puppies: List<Puppy>) : PuppyAction()