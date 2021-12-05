package com.aplication.techforest.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aplication.techforest.R
import com.aplication.techforest.navigation.Destinations1

import com.aplication.techforest.navigation.Destinations3
import com.aplication.techforest.ui.theme.ButtonBlue
import com.aplication.techforest.ui.theme.DarkStateGray
import com.aplication.techforest.ui.theme.DarkerButtonBlue
import com.aplication.techforest.ui.theme.TextWhite
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun ProfileHeader1(email:String) {
    Surface(
        color = DarkStateGray,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = rememberImagePainter(data = "https://images.pexels.com/photos/9604597/pexels-photo-9604597.jpeg",
                    builder = {
                        transformations(CircleCropTransformation())
                    }),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(1.dp, color = Color.White),
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = email,
                style = MaterialTheme.typography.h6,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(top = 2.dp))
            Text(text = "Premiun", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_monetization_on_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_lambda),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            buttons()
        }
    }

}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Preview
@Composable
fun PreviewProfileHeader1() {
    ProfileHeader1(email="")
}

@ExperimentalFoundationApi
@Composable
fun buttons() {
    Box(
        modifier = Modifier
            .background(DarkStateGray)
            .fillMaxSize()
    ) {
        Column {

            ChipSection1(

                chips = listOf(
                    "Logout  ",
                    "Youtube ",
                    "Ayuda   ",
                    "Autores ",
                    "Pag web ",
                    )
             ,navController = rememberNavController()
            )

        }
        /*
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Devices", R.drawable.ic_lambda),
                BottomMenuContent("Plants", R.drawable.ic_baseline_local_florist_24),
                BottomMenuContent("Profile", R.drawable.ic_profile),
                BottomMenuContent("Settings", R.drawable.ic_baseline_settings_24),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
        */
    }
}

@Composable
fun ChipSection1(
    chips: List<String>,
navController:NavController
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyColumn {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .clickable {
                        navController.navigate(route = Destinations1.LoginScreen.route)
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(horizontal = 155.dp, vertical = 30.dp)
            ) {
                Text(text = chips[it], color = TextWhite, style = MaterialTheme.typography.caption)


            }
        }
    }
}



