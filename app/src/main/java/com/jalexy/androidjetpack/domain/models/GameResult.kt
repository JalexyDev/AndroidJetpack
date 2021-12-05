package com.jalexy.androidjetpack.domain.models

data class GameResult(
    val win: Boolean,
    val countRightAnswers: Int,
    val countQuestions: Int,
    val gameSettings: GameSettings
)