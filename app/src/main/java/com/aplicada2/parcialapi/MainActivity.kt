package com.aplicada2.parcialapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aplicada2.parcialapi.ui.theme.CoinsComposeTheme
import com.aplicada2.parcialapi.ui.theme.Componentes.listadoCoins
import com.aplicada2.parcialapi.ui.theme.Componentes.registroCoins
import com.aplicada2.parcialapi.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp()
        }
    }
}

@Composable
fun myApp() {
    CoinsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navHostController = rememberNavController()
            NavHost(navController = navHostController,
                startDestination = Screens.coinsListadoScreen.route){

                composable(route  = Screens.coinsListadoScreen.route){
                    listadoCoins(goToRegistro = {navHostController.navigate(Screens.coinsRegistroScreen.route)})
                }

                composable(route = Screens.coinsRegistroScreen.route){
                    registroCoins(backToListado = {navHostController.navigate(Screens.coinsListadoScreen.route)})
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinsComposeTheme {
        myApp()
    }
}