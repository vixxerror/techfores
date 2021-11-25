package com.aplication.techforest.ui

import android.media.browse.MediaBrowser
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media2.MediaItem
import androidx.media2.exoplayer.external.video.spherical.Projection
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aplication.techforest.BottomMenuContent
import com.aplication.techforest.Feature
import com.aplication.techforest.R
import com.aplication.techforest.standardQuadFromTo
import com.aplication.techforest.ui.theme.*


@ExperimentalCoilApi
@ExperimentalFoundationApi

@Composable
fun CurrentMeditation1(
    color: Color = BlueViolet3
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Estado del Sistema",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "Humedad Minima(%)",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = "Humedad Maxima(%)",
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
@Composable
fun CurrentMeditation2(
    color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Modos de Riego",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "Automatico",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = "Manual",
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
            Text(
                text = "Perzonalido",
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
@Composable
fun CurrentMeditation3(
    color: Color = Beige3
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Opciones Extra",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "Calculadora",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = "Youtube",
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview
@ExperimentalFoundationApi
@Composable
fun HomeScreen1() {
    Box(
        modifier = Modifier
            .background(DarkStateGray)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection1()
            CurrentMeditation1()
            CurrentMeditation2()
            CurrentMeditation3()

        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Devices", R.drawable.ic_lambda),
            BottomMenuContent("Plants", R.drawable.ic_baseline_local_florist_24),
            BottomMenuContent("Profile", R.drawable.ic_profile),
            BottomMenuContent("Settings", R.drawable.ic_baseline_settings_24),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun GreetingSection1(
    name: String = "Opciones"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
        }

    }
}