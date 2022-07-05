package com.aplicada2.parcialapi.view


import androidx.lifecycle.viewModelScope
import com.aplicada2.parcialapi.data.remote.repository.CoinsRepository
import com.aplicada2.parcialapi.ui.theme.Componentes.CryptoListState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aplicada2.parcialapi.data.remote.model.Crypto
import com.aplicada2.parcialapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository
) : ViewModel(){

    var descripcion by mutableStateOf("")
    var imagenUrl by mutableStateOf("")
    var valor by mutableStateOf("")

    private var _state = mutableStateOf(CryptoListState())
    val state: State<CryptoListState> = _state

    init {
        coinsRepository.getCoins().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CryptoListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CryptoListState(Coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CryptoListState(error = result.message?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun Guardar(){
        viewModelScope.launch {
            coinsRepository.postCoins(
                Crypto(
                    id = 0,
                    descripcion = descripcion,
                    valor = valor.toDouble(),
                    imageUrl = imagenUrl
                )
            )
        }
    }
}