package com.lucasmercier.portfolio.controller.adapter

import com.lucasmercier.portfolio.controller.dto.OwnerDTO
import com.lucasmercier.portfolio.model.Owner

class OwnerAdapter {

    companion object {
        fun fromOwner(owner: Owner): OwnerDTO = OwnerDTO(
            owner.name,
            owner.avatarUrl,
            owner.type
        )
    }

}