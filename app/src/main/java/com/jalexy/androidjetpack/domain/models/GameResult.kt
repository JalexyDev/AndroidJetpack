package com.jalexy.androidjetpack.domain.models

import java.io.Serializable

data class GameResult(
    val win: Boolean,
    val countRightAnswers: Int,
    val countQuestions: Int,
    val gameSettings: GameSettings
): Serializable