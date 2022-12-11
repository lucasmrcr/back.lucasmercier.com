package com.lucasmercier.portfolio.service.impl

import com.lucasmercier.portfolio.config.GitHubProperties
import com.lucasmercier.portfolio.service.impl.dto.github.GitHubRepositoryResponseDTO
import com.lucasmercier.portfolio.service.IGitHubService
import com.lucasmercier.portfolio.service.IHttpService
import okhttp3.Headers
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class GitHubService(
    val gitHubProperties: GitHubProperties,
    val httpService: IHttpService
) : IGitHubService {

    private final val whitelistedProject = listOf(
        "LifeCraftMC/cms-backend",
        "lucasmrcr/back.lucasmercier.com",
        "lucasmrcr/front.lucasmercier.com",
    )

    companion object {
        private val logger = LoggerFactory.getLogger(GitHubService::class.java)
    }

    override fun listLanguagesByRepository(gitHubRepositoryResponseDTO: GitHubRepositoryResponseDTO): GitHubRepositoryResponseDTO {
        logger.debug("Fetching repositories from https://api.github.com/repos/${gitHubRepositoryResponseDTO.fullName}/languages")
        val languages = httpService.get(
            "https://api.github.com/repos/${gitHubRepositoryResponseDTO.fullName}/languages",
            HashMap<String, Int>().javaClass,
            Headers.headersOf("Authorization", "Bearer ${gitHubProperties.oauth2}")
        )

        val totalBytes = languages.values.reduce { acc, i -> acc + i }.toDouble()
        gitHubRepositoryResponseDTO.languages = languages.map { it.key to (it.value / totalBytes) }.toMap()

        return gitHubRepositoryResponseDTO
    }

    @Cacheable(cacheNames = ["repositoriesWithLanguages"])
    override fun listRepositoriesWithLanguages(): Collection<GitHubRepositoryResponseDTO> {
        val repositories = listRepositories()
        return repositories.map { listLanguagesByRepository(it) }
    }

    override fun listRepositories(): Collection<GitHubRepositoryResponseDTO> {
        val repositories = ArrayList<GitHubRepositoryResponseDTO>()

        logger.debug("Fetching repositories from https://api.github.com/user/repos")
        val fetchedFromGitHubRepositories = httpService.get(
            "${gitHubProperties.endpoint}/user/repos",
            Array<GitHubRepositoryResponseDTO>::class.java,
            Headers.headersOf("Authorization", "Bearer ${gitHubProperties.oauth2}")
        )

        repositories.addAll(
            fetchedFromGitHubRepositories
                .filter { whitelistedProject.contains(it.fullName) || !it.isPrivate }
        )

        return repositories
    }

}