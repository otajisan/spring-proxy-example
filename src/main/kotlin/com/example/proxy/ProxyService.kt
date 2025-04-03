package com.example.proxy

import java.net.URI

interface ProxyService {
    fun getActiveProxyUrl(): URI
}
