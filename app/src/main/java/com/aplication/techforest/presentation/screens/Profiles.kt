package com.aplication.techforest.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.presentation.components.AppBar
import com.aplication.techforest.presentation.components.ProfileHeader

@ExperimentalCoilApi
@Composable
fun Profiles() {
    Column {
        AppBar()
    }
    ProfileHeader()
}