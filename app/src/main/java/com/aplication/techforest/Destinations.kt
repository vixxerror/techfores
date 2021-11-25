package com.aplication.techforest

sealed class Destinations (
    val route:String
){
    object  HomeScreen: Destinations("pantalla1")
    object devista: Destinations("pantalla2")
}