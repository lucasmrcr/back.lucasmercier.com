package com.lucasmercier.portfolio.controller.adapter

import com.lucasmercier.portfolio.controller.dto.github.ListRepositoryDTO
import com.lucasmercier.portfolio.service.impl.dto.github.RepositoryDTO

class RepositoryAdapter {

    companion object {
        private fun fromGHRepository(repositoryDTO: RepositoryDTO) : ListRepositoryDTO {
            return ListRepositoryDTO(
                repositoryDTO.fullName,
                repositoryDTO.pushedAt,
                repositoryDTO.createdAt,
                repositoryDTO.isPrivate,
                repositoryDTO.isFork,
                OwnerAdapter.fromOwner(repositoryDTO.ownerDTO),
                repositoryDTO.description,
                repositoryDTO.language,
                repositoryDTO.languages
            )
        }

        fun fromGHRepositories(ghRepositories: Collection<RepositoryDTO>): Collection<ListRepositoryDTO> {
            return ghRepositories.map { fromGHRepository(it) }
        }
    }

}