package com.lucasmercier.portfolio.controller.dto

import java.util.*

data class ListRepositoryDTO(
    val fullName: String,
    val pushedAt: Date,
    val createdAt: Date
)