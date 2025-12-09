package sheridan.elshirai.citydata.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sheridan.elshirai.citydata.data.CityApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tetervak.dev.fast.sheridanc.on.ca/exam-projects/city-data/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCityApi(retrofit: Retrofit): CityApi {
        return retrofit.create(CityApi::class.java)
    }
}