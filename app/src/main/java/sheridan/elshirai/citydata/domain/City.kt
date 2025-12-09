package sheridan.elshirai.citydata.domain

data class CityResponse(
    val cities: List<City>
)

data class City(
    val cityId: String,
    val name: String,
    val population: Int,
    val isCapital: Boolean,
    val area: Double,
    val country: String
)