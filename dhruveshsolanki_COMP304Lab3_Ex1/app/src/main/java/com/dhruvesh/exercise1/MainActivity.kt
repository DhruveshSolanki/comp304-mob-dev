package com.dhruvesh.exercise1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.dhruvesh.exercise1.RoomDB.CityDataBase
import com.dhruvesh.exercise1.ui.theme.Dhruveshsolanki_COMP304Lab3_Ex1Theme
import com.dhruvesh.exercise1.viewModel.AppRepository
import com.dhruvesh.exercise1.viewModel.CitiesViewModel
import com.dhruvesh.exercise1.viewModel.ViewModelFactory

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = CityDataBase.getInstance(applicationContext)
        val repository = AppRepository(database.getCityDao())
        val myviewModelFactory = ViewModelFactory(repository)
        val myViewModel = ViewModelProvider(this, myviewModelFactory)[CitiesViewModel::class.java]

        setContent {
            Dhruveshsolanki_COMP304Lab3_Ex1Theme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = { Text("Weather App") })
                }) { innerPadding ->
                    HomeActivity( myViewModel,modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeActivity( vm: CitiesViewModel,  modifier: Modifier) {
    var searchText by remember { mutableStateOf("") } // Query for SearchBar
    var active by remember { mutableStateOf(false) } // Active state for SearchBar

    Column(modifier = modifier) {
        SearchBar(
            query = searchText,
            onQueryChange = {
                searchText = it
                Log.d("text", searchText)
                if (searchText.length > 2) {
                    vm.getCities(searchText)
                }
            },
            onSearch = {
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search For Cities")
            },
            shape = SearchBarDefaults.inputFieldShape,

            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (searchText.isNotEmpty()) {
                                searchText = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close icon"
                    )
                }
            }

        ) {
            Text(text = searchText)
        }
    }
}