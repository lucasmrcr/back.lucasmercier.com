package com.lucasmercier.portfolio.controller

import com.lucasmercier.portfolio.controller.adapter.RepositoryAdapter
import com.lucasmercier.portfolio.controller.dto.ListRepositoryDTO
import com.lucasmercier.portfolio.service.IGitHubService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github")
class GitHubController(
    private val githubService: IGitHubService,
) {

    @GetMapping("/repositories")
    fun listRepositories(): ResponseEntity<Collection<ListRepositoryDTO>> =
        ResponseEntity.ok(RepositoryAdapter.fromGHRepositories(githubService.listRepositories()))

}