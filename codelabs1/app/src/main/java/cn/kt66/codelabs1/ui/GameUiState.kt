package cn.kt66.codelabs1.ui

data class GameUiState(
    val currentScrambleWord: String = "",
    val isGuessWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val isGameOver:Boolean = false,
)