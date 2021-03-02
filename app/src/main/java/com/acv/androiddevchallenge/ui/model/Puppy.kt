package com.acv.androiddevchallenge.ui.model

inline class Id(val value: Int)
inline class Url(val value: String)

data class Puppy(
    val id: Id,
    val name: String,
    val age: Int,
    val gender: Gender,
    val breed: Breed,
    val temperament: List<Temperament>,
    val size: Size,
    val info: String,
    val location: Location,
    val image: Url?,
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
    YorkshireTerrier,
    GermanShepherds,
    GoldenRetrievers,
    FrenchBulldogs,
    Beagles,
}

enum class Size {
    Smallest,
    Small,
    Medium,
    Large,
    Giant,
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