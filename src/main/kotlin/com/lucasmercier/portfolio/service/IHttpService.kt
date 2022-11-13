package com.lucasmercier.portfolio.service

interface IHttpService {
    fun <T> get(url: String, aClass: Class<T>): T
}