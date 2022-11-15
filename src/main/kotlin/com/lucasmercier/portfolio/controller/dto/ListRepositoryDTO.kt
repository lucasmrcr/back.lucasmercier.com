package com.lucasmercier.portfolio.controller.dto

import java.util.*

data class ListRepositoryDTO(
    val fullName: String,
    val pushedAt: Date,
    val createdAt: Date,
    val isPrivate: Boolean,
    val isFork: Boolean,
    val owner: OwnerDTO,
    val description: String?,
    val language: String?
)