package com.lucasmercier.portfolio.service.impl

import com.lucasmercier.portfolio.config.GitHubProperties
import com.lucasmercier.portfolio.service.IGitHubService
import com.lucasmercier.portfolio.service.IHttpService
import com.lucasmercier.portfolio.model.Repository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class GitHubService(
    val gitHubProperties: GitHubProperties,
    val httpService: IHttpService
) : IGitHubService {

    companion object {
        private val logger = LoggerFactory.getLogger(GitHubService::class.java)
        private val listRepositoriesEndpoints = listOf(
            "/users/lucasmrcr/repos",
            "/orgs/EpsilonCorp/repos",
            "/orgs/LifeCraftMC/repos",
            "/orgs/Varelia/repos"
        )
    }

    override fun listRepositories(): Collection<Repository> {
        val repositories = ArrayList<Repository>()

        listRepositoriesEndpoints.forEach {
            logger.debug("Fetching repositories from {}", it)
            repositories.addAll(httpService.get("${gitHubProperties.endpoint}$it", Array<Repository>::class.java))
        }

        return repositories
    }

}