package sheridan.elshirai.citydata.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sheridan.elshirai.citydata.data.CityRepository
import sheridan.elshirai.citydata.domain.City
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {
    private val _cityList = MutableStateFlow<List<City>>(emptyList())
    val cityList: StateFlow<List<City>> = _cityList.asStateFlow()
    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity.asStateFlow()

    init {
        fetchCities()
    }

    private fun fetchCities() {
        viewModelScope.launch {
            try {
                _cityList.value = repository.getCities()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun selectCity(id: String) {
        viewModelScope.launch {
            _selectedCity.value = repository.getCityById(id)
        }
    }
}