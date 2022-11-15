package com.lucasmercier.portfolio.controller.adapter

import com.lucasmercier.portfolio.controller.dto.ListRepositoryDTO
import com.lucasmercier.portfolio.model.Repository

class RepositoryAdapter {

    companion object {
        private fun fromGHRepository(repository: Repository) : ListRepositoryDTO {
            return ListRepositoryDTO(
                repository.fullName,
                repository.pushedAt,
                repository.createdAt,
                repository.isPrivate,
                repository.isFork,
                OwnerAdapter.fromOwner(repository.owner),
                repository.description,
                repository.language
            )
        }

        fun fromGHRepositories(ghRepositories: Collection<Repository>): Collection<ListRepositoryDTO> {
            return ghRepositories.map { fromGHRepository(it) }
        }
    }

}