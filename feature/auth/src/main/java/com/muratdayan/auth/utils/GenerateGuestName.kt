package com.muratdayan.auth.utils

import kotlin.random.Random

fun generateRandomName(): String {

    val firstChar = ('A'..'Z').random()

    val randomNumbers = (1..3).joinToString("") { Random.nextInt(0, 10).toString() }

    return "$firstChar-$randomNumbers"
}