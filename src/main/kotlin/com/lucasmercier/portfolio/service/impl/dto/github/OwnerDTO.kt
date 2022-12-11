package com.lucasmercier.portfolio.service.impl.dto.github

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OwnerDTO (
    @JsonProperty("login")
    val name: String,

    @JsonProperty("avatar_url")
    val avatarUrl: String,

    @JsonProperty("type")
    val type: String
)