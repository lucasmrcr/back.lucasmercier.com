package com.lucasmercier.portfolio.controller.adapter

import com.lucasmercier.portfolio.controller.dto.github.OwnerDTO
import com.lucasmercier.portfolio.service.impl.dto.github.GitHubOwnerResponseDTO


class OwnerAdapter {

    companion object {
        fun fromOwner(gitHubOwnerResponseDTO: GitHubOwnerResponseDTO): OwnerDTO = OwnerDTO(
            gitHubOwnerResponseDTO.name,
            gitHubOwnerResponseDTO.avatarUrl,
            gitHubOwnerResponseDTO.type
        )
    }

}