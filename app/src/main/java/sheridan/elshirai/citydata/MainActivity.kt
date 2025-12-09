package sheridan.elshirai.citydata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sheridan.elshirai.citydata.ui.theme.CityListScreen
import sheridan.elshirai.citydata.ui.theme.CityViewModel
import sheridan.elshirai.citydata.ui.theme.CityDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: CityViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    val list by viewModel.cityList.collectAsState()
                    CityListScreen(cityList = list) { id ->
                        viewModel.selectCity(id)
                        navController.navigate("detail")
                    }
                }

                composable("detail") {
                    val city by viewModel.selectedCity.collectAsState()
                    CityDetailScreen(city = city)
                }
            }
        }
    }
}