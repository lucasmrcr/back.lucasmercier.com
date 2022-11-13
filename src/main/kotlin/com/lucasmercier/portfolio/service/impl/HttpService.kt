package com.lucasmercier.portfolio.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.lucasmercier.portfolio.service.IHttpService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service

@Service
class HttpService : IHttpService {
    override fun <T> get(url: String, aClass: Class<T>): T {
        val objectMapper = ObjectMapper()
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        val response = call.execute()
        return objectMapper.readValue(response.body?.string() ?: "{}", aClass)
    }
}