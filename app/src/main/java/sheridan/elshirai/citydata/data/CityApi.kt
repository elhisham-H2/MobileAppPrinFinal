package sheridan.elshirai.citydata.data

import sheridan.elshirai.citydata.domain.CityResponse
import retrofit2.http.GET

interface CityApi {
    @GET("cities.json")
    suspend fun getCities(): CityResponse
}