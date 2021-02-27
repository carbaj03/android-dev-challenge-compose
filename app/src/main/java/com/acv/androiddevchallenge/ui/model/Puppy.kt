package com.acv.androiddevchallenge.ui.model

inline class Id(val value: Int)

data class Puppy(
    val id: Id,
    val name: String,
    val age: Int,
    val gender: Gender,
    val breed: Breed,
    val temperament: List<Temperament>,
    val group: Group,
    val size: Size,
    val info: String,
    val location: Location,
)

data class Location(
    val latitude: Double,
    val longitude: Double,
){
    companion object {
        val Madrid = Location(40.4168, 3.7038)
    }
}

enum class Gender {
    Female, Male
}

enum class Breed {
    YorkshireTerrier
}

enum class Size {
    Smallest,
    Small,
    Medium,
    Large,
    Giant,
}

enum class Group {
    Toy,
    Sporting,
    Hound,
    Terrier,
    Working,
    Herding,
}

enum class Temperament {
    Adaptable,
    Bubbly,
    Intelligent,
    Loving,
    Playful,
    Sociable,
    Protective,
}