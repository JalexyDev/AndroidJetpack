package com.jalexy.androidjetpack.domain.models

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
)