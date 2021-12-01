package com.aplication.techforest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.navigation.Destinations.*
import com.aplication.techforest.navigation.Destinations1
import com.aplication.techforest.navigation.NavigationHost
import com.aplication.techforest.navigation.NavigationHost1
import com.aplication.techforest.presentation.components.AppBar
import com.aplication.techforest.presentation.components.BottomNavigationBar
import com.aplication.techforest.presentation.login.LoginScreen


import com.aplication.techforest.ui.theme.TechForestTheme
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.viewmodel.DeviceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechForestTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationHost1()
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        HomeScreen,
        Devices,
        Plants,
        Profile,
        Settings
    )
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = navigationItems
            )
        }
    ) {
        NavigationHost(navController = navController)
    }
}





