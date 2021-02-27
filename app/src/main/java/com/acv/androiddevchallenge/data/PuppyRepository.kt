package com.acv.androiddevchallenge.data

import com.acv.androiddevchallenge.ui.model.*
import com.acv.androiddevchallenge.ui.model.Location.Companion.Madrid
import kotlinx.coroutines.delay

object PuppyRepository {
    private val puppies = listOf(
        Puppy(
            id = Id(1),
            name = "Juan",
            breed = Breed.YorkshireTerrier,
            age = 8,
            gender = Gender.Male,
            group = Group.Hound,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Bubbly, Temperament.Adaptable, Temperament.Intelligent
            ),
            location = Madrid,
        ),
        Puppy(
            id = Id(2),
            name = "Pepe",
            breed = Breed.YorkshireTerrier,
            age = 8,
            gender = Gender.Male,
            group = Group.Hound,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Bubbly, Temperament.Adaptable, Temperament.Intelligent
            ),
            location = Madrid,
        ),
    )

    suspend fun getAll(): List<Puppy> {
        delay(2000)
        return puppies
    }

    suspend fun getById(id: Id): Puppy? =
        puppies.firstOrNull { it.id == id }
}