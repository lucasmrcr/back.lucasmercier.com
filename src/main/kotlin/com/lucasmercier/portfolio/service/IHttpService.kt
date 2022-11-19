package com.lucasmercier.portfolio.service

import okhttp3.Headers

interface IHttpService {
    fun <T> get(url: String, aClass: Class<T>, headers: Headers = Headers.headersOf()): T
}