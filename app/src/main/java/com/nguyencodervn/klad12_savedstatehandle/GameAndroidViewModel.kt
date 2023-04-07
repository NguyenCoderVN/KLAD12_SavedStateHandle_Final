package com.nguyencodervn.klad12_savedstatehandle

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class GameAndroidViewModel(app: Application) : AndroidViewModel(app) {
    private var words = app.resources.getStringArray(R.array.list_word)
    var guessWord = words.random().uppercase()

    var display = ""
    var live = 8
    var wrong = ""
    private var correct = ""

    fun updateDisplay() {
        var str = ""
        guessWord.forEach {
            str += if (correct.contains(it.toString())) it.toString()
            else "_"
        }
        display = str
    }

    fun checkGuessWord(letter: String) {
        if (letter.isNotEmpty()) {
            if (!correct.contains(letter) &&
                !wrong.contains(letter)
            ) {
                if (guessWord.contains(letter)) {
                    correct += letter
                } else {
                    wrong += "$letter "
                    live--
                }
                updateDisplay()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameAndroidViewModel", "GameAndroidViewModel destroyed!")
    }
}