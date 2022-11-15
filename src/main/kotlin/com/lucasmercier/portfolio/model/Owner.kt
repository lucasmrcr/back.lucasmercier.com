package com.lucasmercier.portfolio.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Owner (
    @JsonProperty("login")
    val name: String,

    @JsonProperty("avatar_url")
    val avatarUrl: String,

    @JsonProperty("type")
    val type: String
)