package cn.kt66.codelabs1.ui

data class GameUiState(
    val currentScrambleWord: String = "",
    val isGuessWordWrong: Boolean = false,
)