package sheridan.elshirai.citydata.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home 
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sheridan.elshirai.citydata.domain.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(
    cityList: List<City>,
    onCityClick: (String) -> Unit
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("City Data") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(cityList) { city ->
                CityRow(city, onCityClick)
            }
        }
    }
}

@Composable
fun CityRow(city: City, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(city.cityId) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = city.name, style = MaterialTheme.typography.titleLarge)
                Text(text = city.country, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailScreen(city: City?) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(city?.name ?: "Details") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            if (city != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = city.name, style = MaterialTheme.typography.headlineLarge)
                }

                Spacer(modifier = Modifier.height(24.dp))
                Divider()
                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Country: ${city.country}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Population: ${city.population}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Area: ${city.area} km²", style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "Capital: ${if(city.isCapital) "Yes" else "No"}",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}