package com.lucasmercier.portfolio.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repository(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("full_name")
    val fullName: String,

    @JsonProperty("pushed_at")
    val pushedAt: Date,

    @JsonProperty("created_at")
    val createdAt: Date
)