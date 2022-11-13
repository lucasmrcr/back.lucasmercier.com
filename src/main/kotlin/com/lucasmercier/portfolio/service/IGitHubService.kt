package com.lucasmercier.portfolio.service

import com.lucasmercier.portfolio.model.Repository

interface IGitHubService {

    fun listRepositories(): Collection<Repository>

}