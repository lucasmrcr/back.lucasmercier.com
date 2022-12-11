package com.lucasmercier.portfolio.controller.adapter

import com.lucasmercier.portfolio.controller.dto.github.ListRepositoryDTO
import com.lucasmercier.portfolio.service.impl.dto.github.GitHubRepositoryResponseDTO

class RepositoryAdapter {

    companion object {
        private fun fromGHRepository(gitHubRepositoryResponseDTO: GitHubRepositoryResponseDTO) : ListRepositoryDTO {
            return ListRepositoryDTO(
                gitHubRepositoryResponseDTO.fullName,
                gitHubRepositoryResponseDTO.pushedAt,
                gitHubRepositoryResponseDTO.createdAt,
                gitHubRepositoryResponseDTO.isPrivate,
                gitHubRepositoryResponseDTO.isFork,
                OwnerAdapter.fromOwner(gitHubRepositoryResponseDTO.gitHubOwnerResponseDTO),
                gitHubRepositoryResponseDTO.description,
                gitHubRepositoryResponseDTO.language,
                gitHubRepositoryResponseDTO.languages
            )
        }

        fun fromGHRepositories(ghRepositories: Collection<GitHubRepositoryResponseDTO>): Collection<ListRepositoryDTO> {
            return ghRepositories.map { fromGHRepository(it) }
        }
    }

}