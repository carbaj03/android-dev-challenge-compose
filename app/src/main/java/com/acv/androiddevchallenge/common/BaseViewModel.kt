package com.acv.androiddevchallenge.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acv.androiddevchallenge.data.PuppyRepository
import com.acv.androiddevchallenge.ui.model.Id
import com.acv.androiddevchallenge.ui.screen.detail.PuppyDetailViewModel
import com.acv.androiddevchallenge.ui.screen.puppies.PuppyViewModel

abstract class BaseViewModel : ViewModel()

object EmptyViewModel : BaseViewModel()

sealed class ViewModelProviderFactory : ViewModelProvider.Factory {
    abstract val model: BaseViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(model::class.java)) {
            return model as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PuppyViewModelFactory(repository: PuppyRepository) : ViewModelProviderFactory() {
    override val model: BaseViewModel = PuppyViewModel(repository)
}

class PuppyDetailViewModelFactory(repository: PuppyRepository, id: Id) : ViewModelProviderFactory() {
    override val model: BaseViewModel = PuppyDetailViewModel(repository, id)
}