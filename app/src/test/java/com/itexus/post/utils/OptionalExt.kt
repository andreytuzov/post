package com.itexus.post.utils

import java.util.*

fun <T> Optional<T>.getOrNull() = if (isPresent) get() else null