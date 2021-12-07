package com.aplication.techforest.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.BottomMenuContent
import com.aplication.techforest.model.Feature
import com.aplication.techforest.R
import com.aplication.techforest.presentation.components.RetrySection
import com.aplication.techforest.standardQuadFromTo
import com.aplication.techforest.ui.theme.*
import com.aplication.techforest.viewmodel.HomeViewModel

@Preview
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val deviceList by remember { viewModel.deviceList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val featureList = viewModel.featureList

    Box(
        modifier = Modifier
            .background(DarkStateGray)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(
                chips = listOf(
                    "Plants",
                    "Devices",
                    "Water",
                    "Flowers",
                    "Trees",
                    "Connections",
                    "WiFi"
                )
            )
            CurrentMeditation()
            FeatureSection(
                features = featureList
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if(isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
                if(loadError.isNotEmpty()) {
                    RetrySection(error = loadError) {
                        viewModel.loadDevicesHome()
                    }
                }
            }
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
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun GreetingSection(
    name: String = "TechForestApp"
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
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val deviceList by remember { viewModel.deviceList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val timeList = viewModel.timeList
    if (timeList.value.size != 0){
        val time = timeList.value[0]
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
            Box(
                contentAlignment = Alignment.Center,
            ) {
                if(isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
                if(loadError.isNotEmpty()) {
                    RetrySection(error = loadError) {
                        viewModel.loadTimeHome()
                    }
                }else{
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Column {
                Text(
                    text = time.weather[0].main,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = time.weather[0].description,
                    style = MaterialTheme.typography.body1,
                    color = TextWhite
                )
                Text(
                    text = time.name,
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
    } else {
        RetrySection(error = loadError) {
            viewModel.loadTimeHome()
        }
    }



}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Devices",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 50.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            if (feature.estado == "Activo") {
                Icon(
                    painter = painterResource(id = feature.iconId),
                    contentDescription = feature.title,
                    tint = MediumAquamarine,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            } else if(feature.estado == "Desactivado"){
                Icon(
                    painter = painterResource(id = feature.iconId),
                    contentDescription = feature.title,
                    tint = RED600,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }

            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

