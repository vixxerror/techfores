package com.aplication.techforest

import android.media.MediaCodecList
import android.media.browse.MediaBrowser
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.ui.CurrentMeditation1
import com.aplication.techforest.ui.HomeScreen
import com.aplication.techforest.ui.HomeScreen1


import com.aplication.techforest.ui.theme.TechForestTheme
data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)
class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechForestTheme {
                Surface(color= MaterialTheme.colors.background) {
                    HomeScreen()
                }
                }
            }
        }
    }

fun RecipeCard(recipe: Recipe){

}




@Preview
@Composable
fun MediaList(){
    LazyColumn{
        items(3){

        }
    }
}


