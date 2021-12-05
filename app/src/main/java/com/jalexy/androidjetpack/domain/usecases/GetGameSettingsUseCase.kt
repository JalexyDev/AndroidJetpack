package com.jalexy.androidjetpack.domain.usecases

import com.jalexy.androidjetpack.domain.models.GameSettings
import com.jalexy.androidjetpack.domain.models.Level
import com.jalexy.androidjetpack.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings =
        repository.getGameSettings(level)
}