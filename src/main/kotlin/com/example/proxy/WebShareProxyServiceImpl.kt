package com.example.proxy

import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Service
class WebShareProxyServiceImpl : ProxyService {
    override fun getActiveProxyUrl(): URI {
        val proxyUrls = listOf(
            "http://localhost:8080/api",
            "http://127.0.0.1:9999",
        )

        val selectedProxy = proxyUrls.first()

        return URI.create(selectedProxy)

//        return UriComponentsBuilder
//            .fromUriString(selectedProxy)
//            .build()
//            .toUri()
    }
}
