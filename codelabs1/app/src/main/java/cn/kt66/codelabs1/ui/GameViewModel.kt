package cn.kt66.codelabs1.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cn.kt66.codelabs1.data.MAX_NO_OF_WORDS
import cn.kt66.codelabs1.data.SCORE_INCREASE
import cn.kt66.codelabs1.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambleWord = pickRandomWordAndShuffle())
    }

    var userGuess by mutableStateOf("")
        private set

    fun updateUserGuess(value: String) {
        userGuess = value
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE) // 得分 + 20
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessWordWrong = true)// 标记错误
                //val jack = User(name = "Jack", age = 1)
                //val olderJack = jack.copy(age = 2)
            }

        }
        updateUserGuess("")
    }

    /**
     * 更新得分，增加当前单词数，并从 WordsData.kt 文件中选择一个新单词。
     */
    private fun updateGameState(value: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update { item ->
                item.copy(
                    isGuessWordWrong = false,
                    score = value,
                    isGameOver = true,
                )
            }
        } else {
            _uiState.update { item ->
                item.copy(
                    isGuessWordWrong = false,
                    currentScrambleWord = pickRandomWordAndShuffle(),
                    score = value,
                    currentWordCount = item.currentWordCount.inc(),// 单词数 + 1
                )
            }
        }
    }

    fun skipWorld() {
        updateGameState(_uiState.value.score)
        updateUserGuess("") // 清除用户的输入
    }

    init {
        resetGame()
    }


    //使用后备属性，可以从 getter 返回确切对象之外的某些其他内容。
    private var _count = 0
    val count: Int
        get() = _count
}