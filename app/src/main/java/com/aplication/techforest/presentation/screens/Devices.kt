package com.aplication.techforest.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.BottomMenuContent
import com.aplication.techforest.R
import com.aplication.techforest.model.Device
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.ui.theme.*
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.viewmodel.DeviceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@Preview
@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Devices(
    viewModel: DeviceViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val getAllDevicesData = viewModel.getDeviceData.observeAsState()


    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .background(DarkStateGray)
                    .fillMaxSize()
            ) {
                Column {
                    Title()
                    scope.launch {
                        val result = viewModel.getDeviceData()

                        if (result is Resource.Success) {
                            Toast.makeText(
                                context,
                                "Fetching data success",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (result is Resource.Error) {
                            Toast.makeText(
                                context,
                                "Error: ${result.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    if (viewModel.isLoading.value) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    if (viewModel.isLoading.value) {
                        if (viewModel.getDeviceData.value!!.isNotEmpty()) {
                            LazyColumn(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                items(getAllDevicesData.value!!.size) { index ->
                                    DeviceListItem(device = getAllDevicesData.value!![index])
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun DeviceListItem(
    color: Color = BlueViolet3,
    device: Device
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
                text = device.name,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = device.id.toString(),
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = device.active.toString(),
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
fun Title(
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

@Composable
fun DeviceListItem2(device: Device) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = device.id.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Title: ${device.name}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "Status: ${device.active}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}