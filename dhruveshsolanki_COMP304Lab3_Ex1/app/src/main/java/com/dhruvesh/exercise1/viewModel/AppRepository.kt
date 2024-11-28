package com.dhruvesh.exercise1.viewModel

import com.dhruvesh.exercise1.RoomDB.City
import com.dhruvesh.exercise1.RoomDB.CityDAO
import com.dhruvesh.exercise1.data.RetrofitClass
import com.dhruvesh.exercise1.data.WeatherObject

class AppRepository(private val cityDao: CityDAO) {

    private val apiService = RetrofitClass.api
    private val weatherApiService = RetrofitClass.weatherApi

    suspend fun getCities(query: String): List<String> {
        return apiService.getCities(query)
    }

    suspend fun getWeather(city: String): WeatherObject? {
        return weatherApiService.getWeather(city, "e3aecf7527ca595c4b5b16a486db5024", "metric")
    }

    suspend fun getCitiesFromDB(): List<City> {
        return cityDao.getAllCities()
    }

    suspend fun insertCity(c: City) {
        cityDao.insertCityToDB(c)
    }

    suspend fun deleteCity(c: City) {
        cityDao.deleteCity(c)
    }

    suspend fun searchForCityInDB(term: String): List<City> {
        return cityDao.getCityNamed(term)
    }

    suspend fun update(newCity: City) {
        return cityDao.updateCity(newCity)
    }


}