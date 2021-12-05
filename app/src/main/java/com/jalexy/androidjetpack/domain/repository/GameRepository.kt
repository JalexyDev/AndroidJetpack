package com.jalexy.androidjetpack.domain.repository

import com.jalexy.androidjetpack.domain.models.GameSettings
import com.jalexy.androidjetpack.domain.models.Level
import com.jalexy.androidjetpack.domain.models.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}