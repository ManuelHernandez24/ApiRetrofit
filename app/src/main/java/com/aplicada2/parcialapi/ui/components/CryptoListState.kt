package com.aplicada2.parcialapi.ui.theme.Componentes

import com.aplicada2.parcialapi.data.remote.model.Crypto

data class CryptoListState (
    val isLoading: Boolean = false,
    val Coins: List<Crypto> = emptyList(),
    val error: String = ""
)