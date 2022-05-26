package com.example.crypttestproject.viewModel

sealed class UiState {
    object Idle : UiState()
    object Pending : UiState()
}
