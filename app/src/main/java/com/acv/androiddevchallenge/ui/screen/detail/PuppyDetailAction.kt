package com.acv.androiddevchallenge.ui.screen.detail

import com.acv.androiddevchallenge.common.store.Action
import com.acv.androiddevchallenge.ui.model.Id
import com.acv.androiddevchallenge.ui.model.Puppy

sealed class PuppyDetailAction : Action
data class Load(val id: Id) : PuppyDetailAction()
data class LoadSuccess(val puppy: Puppy) : PuppyDetailAction()
object LoadError : PuppyDetailAction()
