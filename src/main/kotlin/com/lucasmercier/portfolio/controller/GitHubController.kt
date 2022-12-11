package com.lucasmercier.portfolio.controller

import com.lucasmercier.portfolio.controller.adapter.github.RepositoryAdapter
import com.lucasmercier.portfolio.controller.dto.github.ListRepositoryDTO
import com.lucasmercier.portfolio.service.IGitHubService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github")
@CrossOrigin
class GitHubController(
    private val githubService: IGitHubService,
) {

    @GetMapping("/repositories")
    fun listRepositories(): ResponseEntity<Collection<ListRepositoryDTO>> =
        ResponseEntity.ok(RepositoryAdapter.fromGHRepositories(githubService.listRepositoriesWithLanguages()))

}