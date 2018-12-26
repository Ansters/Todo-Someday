package ansters.me.todosomeday.util

import kotlin.random.Random

val <T> List<T>.random: T
    get() = get(Random.nextInt(size-1))