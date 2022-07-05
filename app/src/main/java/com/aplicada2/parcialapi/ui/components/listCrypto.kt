package com.aplicada2.parcialapi.ui.theme.Componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp
import coil.compose.base.R
import com.aplicada2.parcialapi.data.remote.model.Crypto
import com.aplicada2.parcialapi.view.CoinsViewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation



@Composable
fun listadoCoins(goToRegistro :() -> Unit, viewModel: CoinsViewModel = hiltViewModel()){
    val  ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Inicio")} ,

                actions = {
                    IconButton(onClick = {
                        goToRegistro()
                    }) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                })
        },
        scaffoldState = ScaffoldState
    ){it
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.Coins){ Coins ->
                    coinsItem(
                        coins = Coins,
                        onClick = {})
                }
            }
            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Composable
fun coinsItem(coins: Crypto, onClick : (Crypto)-> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick(coins) }) {
            Row(Modifier.padding(2.dp).fillMaxSize()) {Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxHeight()
                    .weight(0.8f)
            ) {
                Row{
                    Image(
                        painter = rememberImagePainter(
                            data = coins.imageUrl,

                            builder = {
                                scale(Scale.FILL)
                                placeholder(R.drawable.notification_action_background)
                                transformations(CircleCropTransformation())

                            }
                        ),
                        contentDescription = coins.descripcion,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.4f)
                    )

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight()
                            .weight(0.8f)
                    ) {
                        Text(
                            text = "${coins.descripcion}",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text ="${coins.valor}USD$",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }

            }
            }
        }
    }}

