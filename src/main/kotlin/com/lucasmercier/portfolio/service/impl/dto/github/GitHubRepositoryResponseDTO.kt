package com.lucasmercier.portfolio.service.impl.dto.github

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class GitHubRepositoryResponseDTO(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("full_name")
    val fullName: String,

    @JsonProperty("pushed_at")
    val pushedAt: Date,

    @JsonProperty("created_at")
    val createdAt: Date,

    @JsonProperty("private")
    val isPrivate: Boolean,

    @JsonProperty("fork")
    val isFork: Boolean,

    @JsonProperty("owner")
    val gitHubOwnerResponseDTO: GitHubOwnerResponseDTO,

    @JsonProperty("description")
    val description: String?,

    @JsonProperty("language")
    val language: String?,

    @JsonProperty("languages")
    var languages: Map<String, Double>?
)