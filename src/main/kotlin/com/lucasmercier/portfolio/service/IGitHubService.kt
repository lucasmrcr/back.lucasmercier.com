package com.lucasmercier.portfolio.service

import com.lucasmercier.portfolio.service.impl.dto.github.GitHubRepositoryResponseDTO

interface IGitHubService {
    fun listRepositories(): Collection<GitHubRepositoryResponseDTO>
    fun listLanguagesByRepository(gitHubRepositoryResponseDTO: GitHubRepositoryResponseDTO): GitHubRepositoryResponseDTO
    fun listRepositoriesWithLanguages(): Collection<GitHubRepositoryResponseDTO>
}