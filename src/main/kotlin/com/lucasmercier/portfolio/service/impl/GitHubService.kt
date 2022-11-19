package com.lucasmercier.portfolio.service.impl

import com.lucasmercier.portfolio.config.GitHubProperties
import com.lucasmercier.portfolio.model.Repository
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

    override fun listLanguagesByRepository(repository: Repository): Repository {
        logger.debug("Fetching repositories from https://api.github.com/repos/${repository.fullName}/languages")
        val languages = httpService.get(
            "https://api.github.com/repos/${repository.fullName}/languages",
            HashMap<String, Int>().javaClass,
            Headers.headersOf("Authorization", "Bearer ${gitHubProperties.oauth2}")
        )

        val totalBytes = languages.values.reduce { acc, i -> acc + i }.toDouble()
        repository.languages = languages.map { it.key to (it.value / totalBytes) }.toMap()

        return repository
    }

    @Cacheable(cacheNames = ["repositoriesWithLanguages"])
    override fun listRepositoriesWithLanguages(): Collection<Repository> {
        val repositories = listRepositories()
        return repositories.map { listLanguagesByRepository(it) }
    }

    override fun listRepositories(): Collection<Repository> {
        val repositories = ArrayList<Repository>()

        logger.debug("Fetching repositories from https://api.github.com/user/repos")
        val fetchedFromGitHubRepositories = httpService.get(
            "${gitHubProperties.endpoint}/user/repos",
            Array<Repository>::class.java,
            Headers.headersOf("Authorization", "Bearer ${gitHubProperties.oauth2}")
        )

        repositories.addAll(
            fetchedFromGitHubRepositories
                .filter { whitelistedProject.contains(it.fullName) || !it.isPrivate }
        )

        return repositories
    }

}