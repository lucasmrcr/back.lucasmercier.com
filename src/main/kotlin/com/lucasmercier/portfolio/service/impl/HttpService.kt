package com.lucasmercier.portfolio.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.lucasmercier.portfolio.service.IHttpService
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service

@Service
class HttpService : IHttpService {
    override fun <T> get(url: String, aClass: Class<T>, headers: Headers): T {
        val objectMapper = jacksonObjectMapper()
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(url)
            .headers(headers)
            .build()

        val call = client.newCall(request)
        val response = call.execute()
        return objectMapper.readValue(response.body?.string() ?: "{}", aClass)
    }
}