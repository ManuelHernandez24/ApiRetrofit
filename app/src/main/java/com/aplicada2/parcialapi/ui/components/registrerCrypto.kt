package com.aplicada2.parcialapi.ui.theme.Componentes

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aplicada2.parcialapi.view.CoinsViewModel

@Composable
fun  registroCoins(backToListado:() -> Unit, viewModel: CoinsViewModel = hiltViewModel()){
    val  ScaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    var descripcionValidar by remember { mutableStateOf(false)}
    var valorValidar by remember { mutableStateOf(false)}
    val context = LocalContext.current

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Registro") })
        },

        scaffoldState = ScaffoldState
    )
    { it
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {

            OutlinedTextField(
                value = viewModel.descripcion,
                onValueChange = {viewModel.descripcion = it},
                label = { Text(text = "Descripcion")},
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.valor,
                onValueChange = {viewModel.valor = it},
                label = { Text(text = "Valor")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = viewModel.imagenUrl,
                onValueChange = {viewModel.imagenUrl = it},
                label = { Text(text = "Url de la imagen(Opcional)")},
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {

                descripcionValidar = viewModel.descripcion.isBlank()
                valorValidar = viewModel.valor.isBlank()

                if(viewModel.descripcion.toString() == ""){
                    Toast.makeText(context, "Debe insertar una descripción", Toast.LENGTH_SHORT).show()
                }

                if(viewModel.valor.toString() == ""){
                    Toast.makeText(context, "Debe insertar un valor", Toast.LENGTH_SHORT).show()
                }

                if(!descripcionValidar && !valorValidar){
                    if(viewModel.valor.toFloat() > 0){
                        viewModel.Guardar()
                        Toast.makeText(context, "Se ha guardado con éxito.", Toast.LENGTH_SHORT).show()
                        backToListado()
                    }else{
                        Toast.makeText(context, "El valor ingresado no es valido.", Toast.LENGTH_SHORT).show()
                    }
                }

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)) {
                Text(text = "Agregar")
            }
        }
    }
}