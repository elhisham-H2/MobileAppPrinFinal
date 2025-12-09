package sheridan.elshirai.citydata.data

import sheridan.elshirai.citydata.domain.City
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val api: CityApi
) {
    suspend fun getCities(): List<City> {
        return api.getCities().cities
    }
    suspend fun getCityById(id: String): City? {
        val allCities = getCities()
        return allCities.find { it.cityId == id }
    }
}