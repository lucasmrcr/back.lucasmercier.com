package com.lucasmercier.portfolio.service

import com.lucasmercier.portfolio.service.impl.dto.github.RepositoryDTO

interface IGitHubService {
    fun listRepositories(): Collection<RepositoryDTO>
    fun listLanguagesByRepository(repositoryDTO: RepositoryDTO): RepositoryDTO
    fun listRepositoriesWithLanguages(): Collection<RepositoryDTO>
}