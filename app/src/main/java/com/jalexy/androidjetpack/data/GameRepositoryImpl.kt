package com.jalexy.androidjetpack.data

import com.jalexy.androidjetpack.domain.models.GameSettings
import com.jalexy.androidjetpack.domain.models.Level
import com.jalexy.androidjetpack.domain.models.Question
import com.jalexy.androidjetpack.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = generateOptions(
            maxSumValue = maxSumValue,
            sum = sum,
            visibleNumber = visibleNumber,
            countOptions = countOptions
        )
        return Question(sum, visibleNumber, options)
    }

    private fun generateOptions(
        maxSumValue: Int,
        sum: Int,
        visibleNumber: Int,
        countOptions: Int
    ): List<Int> {
        val rightAnswer = sum - visibleNumber
        val from = max(rightAnswer - countOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOptions)

        val options = HashSet<Int>()
        options.add(rightAnswer)
        while (options.size < countOptions) {
            options.add(Random.nextInt(from, to))
        }
        return options.toList()
    }

    override fun getGameSettings(level: Level): GameSettings = when(level) {
        Level.TEST -> GameSettings(
            10,
            3,
            50,
            8
        )
        Level.EASY -> GameSettings(
            10,
            10,
            70,
            60
        )
        Level.NORMAL -> GameSettings(
            20,
            20,
            80,
            50
        )
        Level.HARD -> GameSettings(
            30,
            30,
            90,
            40
        )
    }

}