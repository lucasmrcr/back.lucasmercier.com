package com.lucasmercier.portfolio.controller.adapter

import com.lucasmercier.portfolio.controller.dto.github.OwnerDTO
import com.lucasmercier.portfolio.service.impl.dto.github.OwnerDTO

class OwnerAdapter {

    companion object {
        fun fromOwner(ownerDTO: com.lucasmercier.portfolio.service.impl.dto.github.OwnerDTO): OwnerDTO = OwnerDTO(
            ownerDTO.name,
            ownerDTO.avatarUrl,
            ownerDTO.type
        )
    }

}