package com.example.proxy

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "proxy.auth")
class ProxyAuthConfig {
    lateinit var username: String
    lateinit var password: String
}
