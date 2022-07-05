package com.aplicada2.parcialapi.util

sealed class Screens(val route: String) {
    object coinsRegistroScreen: Screens("coinsRegistroScreen")
    object coinsListadoScreen: Screens("coinsListadoScreen")
}