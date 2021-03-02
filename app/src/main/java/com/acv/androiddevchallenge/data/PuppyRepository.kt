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
            age = 2,
            gender = Gender.Male,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Bubbly, Temperament.Adaptable, Temperament.Intelligent
            ),
            location = Madrid,
            image = Url("https://images-na.ssl-images-amazon.com/images/I/510GX0AWflL._SX258_BO1,204,203,200_.jpg"),
        ),
        Puppy(
            id = Id(2),
            name = "Pepe",
            breed = Breed.Beagles,
            age = 1,
            gender = Gender.Male,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Bubbly, Temperament.Adaptable, Temperament.Intelligent
            ),
            location = Madrid,
            image = Url("https://i.ytimg.com/vi/bx7BjjqHf2U/maxresdefault.jpg")
        ),
        Puppy(
            id = Id(3),
            name = "Maria",
            breed = Breed.GermanShepherds,
            age = 8,
            gender = Gender.Female,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Bubbly, Temperament.Adaptable, Temperament.Intelligent
            ),
            location = Madrid,
            image = Url("https://www.purina.com.au/-/media/project/purina/main/breeds/puppies/puppy-german-shepherd.jpg"),
        ),
        Puppy(
            id = Id(4),
            name = "Pepa",
            breed = Breed.FrenchBulldogs,
            age = 8,
            gender = Gender.Female,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Intelligent, Temperament.Sociable, Temperament.Playful
            ),
            location = Madrid,
            image = Url("https://i.pinimg.com/originals/d0/ab/a9/d0aba97aa691dd0ae599941c3d798dc1.jpg"),
        ),
        Puppy(
            id = Id(5),
            name = "Pepa",
            breed = Breed.GoldenRetrievers,
            age = 8,
            gender = Gender.Male,
            info = "sdafdsa",
            size = Size.Small,
            temperament = listOf(
                Temperament.Bubbly, Temperament.Adaptable, Temperament.Intelligent
            ),
            location = Madrid,
            image = Url("https://www.officialgoldenretriever.com/sites/default/files/blog/puppy-golden.jpg"),
        ),
    )

    suspend fun getAll(): List<Puppy> {
        delay(2000)
        return puppies
    }

    suspend fun getByFilter(filter: Filter): List<Puppy> {
        delay(2000)
        return puppies
            .filter { filter.genders.contains(it.gender) }
            .filter { filter.breeds.contains(it.breed)  }
    }

    suspend fun getById(id: Id): Puppy? =
        puppies.firstOrNull { it.id == id }
}

data class Filter(
    val genders: List<Gender>,
    val breeds: List<Breed>,
){
    companion object{
        fun empty() = Filter(emptyList(), emptyList())
    }
}