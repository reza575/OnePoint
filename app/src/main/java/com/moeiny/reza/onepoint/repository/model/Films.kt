package com.moeiny.reza.onepoint.repository.model

data class Films(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)

