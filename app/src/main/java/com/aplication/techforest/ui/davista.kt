package com.aplication.techforest.ui

import android.media.browse.MediaBrowser
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media2.MediaItem
import androidx.media2.exoplayer.external.video.spherical.Projection
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aplication.techforest.R



@Composable
fun MediaList(){
    LazyColumn{
        items(20){
            MediaItem()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediaItem(){
    Column{
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = rememberImagePainter(data ="http://lorempixel.com/400/200/sports/1/"
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
                )
        {
            Text(
                text = "Title 1",
                style = MaterialTheme.typography.h6
            )
        }


    }
}
