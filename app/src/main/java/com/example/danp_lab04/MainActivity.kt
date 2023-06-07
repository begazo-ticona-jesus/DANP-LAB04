package com.example.danp_lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.danp_lab04.entities.CountryEntity
import com.example.danp_lab04.models.AppDatabase
import com.example.danp_lab04.ui.theme.DANPLAB04Theme
import java.util.concurrent.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DANPLAB04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val context = LocalContext.current
    val repository = CountryRepository(AppDatabase.getInstance(context.applicationContext))

    val pager = PagingConfig(pageSize = 20)
    val countryPagingSource = CountryPagingSource(repository)

    val countryPagingData = Pager(pager) {
        countryPagingSource
    }.flow


}